/*
 * ExtensionMaker.java
 * 
 * Copyright 2004 BEA Systems, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 * Original author: Jonathan Colwell
 */
package org.controlhaus.webservice.generator;

import java.io.*;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.beehive.wsm.jsr181.model.Jsr181TypeMetadata;
import org.apache.beehive.wsm.jsr181.model.Jsr181MethodMetadata;
import org.apache.beehive.wsm.jsr181.model.Jsr181ParameterMetadata;
import org.apache.beehive.wsm.jsr181.model.client.ClientMethodMetadata;
import org.apache.beehive.wsm.jsr181.model.client.ClientParameterMetadata;
import org.apache.beehive.wsm.jsr181.wsdl.XmlBeanWSDLProcessor;
import org.apache.axis.wsdl.toJava.Namespaces;
import org.apache.axis.wsdl.toJava.Utils;
import org.controlhaus.webservice.util.WSDLFilter;;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class ExtensionMaker {


    private static final String[] standardImports = 
    {"org.apache.beehive.controls.api.bean.ControlExtension",
     "org.controlhaus.webservice.ServiceControl",
     "org.controlhaus.webservice.PluggableServiceControl",
     "org.controlhaus.webservice.ServiceControl.Location",
     "org.controlhaus.webservice.ServiceControl.WSDL",
     "org.controlhaus.webservice.PluggableServiceControl.WSDLProcessorImpl",
     "org.controlhaus.webservice.PluggableServiceControl.InvocationTargetImpl"};


    File mOutputDir;
    String mWSDLPath = "<path to WSDL>";

    public ExtensionMaker(File outputDir) {
        mOutputDir = outputDir;
    }

    public void setWSDLPath(String wsdlPath) {
        mWSDLPath = wsdlPath.replace('\\', '/');
    }
    
    public void writeJCX(Jsr181TypeMetadata wsm) throws Exception {
        String serviceName = wsm.getWsServiceName();
        String pkg = Utils.makePackageName(wsm.getWsTargetNamespace());
        if (serviceName != null && pkg != null) {
            File subDir = new File(mOutputDir, new Namespaces(null).toDir(pkg));
            subDir.mkdirs();
            if (subDir.isDirectory()) {
                File jcx = new File(subDir, serviceName + ".jcx");
                PrintWriter jcxWriter = new PrintWriter(jcx,"UTF-8");
                
                jcxWriter.print("package ");
                jcxWriter.print(pkg);
                jcxWriter.print(";\n\n");

                for (String imp : standardImports) {
                    jcxWriter.print("import ");
                    jcxWriter.print(imp);
                    jcxWriter.print(";\n");
                }
                jcxWriter.print("@ControlExtension\n");
                jcxWriter.print("@Location(urls = {\"");
                jcxWriter.print("http://???");
                jcxWriter.print("\"})\n");
                jcxWriter.print("@WSDL(path = \"");
                jcxWriter.print(mWSDLPath);
                jcxWriter.print("\",\n\tservice = \"");
                jcxWriter.print(serviceName);
                jcxWriter.print("\")\n");
                jcxWriter.print("@WSDLProcessorImpl\n");
                jcxWriter.print("@InvocationTargetImpl");
                jcxWriter.print("\n\npublic interface ");
                jcxWriter.print(serviceName);
                jcxWriter.print(" extends PluggableServiceControl {\n\n");

                for (Jsr181MethodMetadata method : wsm.getMethods()) {
                    jcxWriter.print("public ");
                    String returnVal = "Object";
                    Class javaType = method.getJavaReturnType();
                    if (javaType != null) {
                        returnVal = getClassName(javaType);
                    }
                    else if (method instanceof ClientMethodMetadata) {
                        QName q = ((ClientMethodMetadata)method)
                            .getXmlReturnType();
                        if (q != null) {
                            returnVal = Utils
                                .makePackageName(q.getNamespaceURI())
                                + q.getLocalPart();
                        }
                    }
                    jcxWriter.print(returnVal);
                    jcxWriter.write(' ');
                    jcxWriter.print(method.getWmOperationName());
                    jcxWriter.write('('); 
                    printParameters(method.getParams(), jcxWriter);
                    jcxWriter.print(") throws Exception;\n\n"); 
                }

                jcxWriter.print("}\n");
                jcxWriter.close();
            }
        }
    }

    private String getClassName(Class cls) {
        if (cls.isArray()) {
            return getClassName(cls.getComponentType()) + "[]";
        }
        else {
            return cls.getName();
        }
    }

    private void printParameters(List<Jsr181ParameterMetadata> params,
                                 PrintWriter pw) {
        int paramPos = 0;
        for (Jsr181ParameterMetadata param : params) {
            if (paramPos > 0) {
                pw.print(", ");
            }

            String paramType = "Object";
            Class javaType = param.getJavaType();
            if (javaType != null) {
                paramType = getClassName(javaType);
            }
            else if (param instanceof ClientParameterMetadata) {
                QName q = ((ClientParameterMetadata)param).getXmlType();
                if (q != null) {
                    paramType = Utils
                        .makePackageName(q.getNamespaceURI())
                        + q.getLocalPart();
                }
            }
            pw.print(paramType);
            pw.write(' ');
            String paramName = param.getWpName();
            if (paramName == null) {
                paramName = "param" + paramPos;
            }
            pw.print(paramName);
            paramPos++;
        }
    }

    public static void main(String[] args) throws Exception {
        File out;
        if (args.length > 1) {
            out = new File(args[1]);
        }
        else {
            out = new File("D:/TEMP");
        }
        ExtensionMaker em = new ExtensionMaker(out);
        if (args.length > 0) {   

            File wsdlDir = new File(args[0]);
            em.setWSDLPath(wsdlDir.getAbsolutePath());
            if (wsdlDir.isDirectory()) {
                for (File wsdlFile : wsdlDir.listFiles(new WSDLFilter())) {
                    /*
                    System.out.println("WSDL file absolute path: "
                                       + wsdlFile.getAbsolutePath() 
                                       + (wsdlFile.canRead() 
                                          ? " and it's readable"
                                          : " but it's not there or not readable"));
                    */
                    em.writeJCX(new XmlBeanWSDLProcessor()
                                .createObjectModel
                                (new FileInputStream(wsdlFile)));
                }
            }
            else if (wsdlDir.isFile()) {
                em.writeJCX(new XmlBeanWSDLProcessor()
                            .createObjectModel(new FileInputStream(wsdlDir)));
            }
            else {
                throw new Exception("no WSDL location specified");
            }            
        }
    }
}
