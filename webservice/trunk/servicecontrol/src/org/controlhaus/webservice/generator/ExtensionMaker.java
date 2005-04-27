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

import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.encoding.TypeMapping;

import org.apache.axis.wsdl.toJava.Namespaces;
import org.apache.axis.wsdl.toJava.Utils;
import org.apache.beehive.wsm.axis.ant.WSDLFilter;

import org.apache.beehive.wsm.axis.databinding.SystemTypeLookupService;
import org.apache.beehive.wsm.model.BeehiveWsMethodMetadata;
import org.apache.beehive.wsm.model.BeehiveWsParameterMetadata;
import org.apache.beehive.wsm.model.BeehiveWsTypeMetadata;
import org.apache.beehive.wsm.model.wsdl.XmlBeanWSDLProcessor;

import org.apache.beehive.wsm.wsdl.WSDLParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.xmlbeans.XmlException;


/*******************************************************************************
 * 
 * 
 * @author Jonathan Colwell
 */
public class ExtensionMaker {

    private static final String[] standardImports = {
            "org.apache.beehive.controls.api.bean.ControlExtension",
            "org.controlhaus.webservice.ServiceControl",
            "org.controlhaus.webservice.ServiceControl.Location",
            "org.controlhaus.webservice.ServiceControl.WSDL", };

    File mOutputDir;

    String mWSDLPath = "<path to WSDL>";

    String packageName = null;

    private String serviceURL = null;

    public ExtensionMaker(File outputDir) {
        mOutputDir = outputDir;
    }

    public void setWSDLPath(String wsdlPath) {
        mWSDLPath = wsdlPath.replace('\\', '/');
    }

    /**
     * @param serviceURL
     *            The serviceURL to set.
     */
    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public void writeJCX(BeehiveWsTypeMetadata wsm) throws Exception {
        String serviceName = wsm.getWsServiceName();

        // TODO: Should the class generation depend on Axis?
        if (packageName == null) {
            packageName = Utils.makePackageName(wsm.getWsTargetNamespace());
        }
        if (serviceName != null && packageName != null) {
            File subDir = new File(mOutputDir, new Namespaces(null)
                    .toDir(packageName));
            subDir.mkdirs();
            if (subDir.isDirectory()) {
                File jcx = new File(subDir, serviceName + ".jcx");
                PrintWriter jcxWriter = new PrintWriter(jcx, "UTF-8");

                jcxWriter.print("package ");
                jcxWriter.print(packageName);
                jcxWriter.print(";\n\n");

                for (String imp : standardImports) {
                    jcxWriter.print("import ");
                    jcxWriter.print(imp);
                    jcxWriter.print(";\n");
                }

                jcxWriter.print("@ControlExtension\n");
                jcxWriter.print("@Location(urls = {\"");
                jcxWriter.print(serviceURL);
                jcxWriter.print("\"})\n");
                jcxWriter.print("@WSDL(path = \"");
                jcxWriter.print(mWSDLPath);
                jcxWriter.print("\",\n\tservice = \"");
                jcxWriter.print(serviceName);
                jcxWriter.print("\")\n");
                jcxWriter.print("\n\npublic interface ");
                jcxWriter.print(serviceName);
                jcxWriter.print(" extends ServiceControl {\n\n");

                for (BeehiveWsMethodMetadata method : wsm.getMethods()) {
                    jcxWriter.print("public ");
                    // String returnVal = "Object";
                    // Class javaType = method.getJavaReturnType();
                    // if (javaType != null) {
                    // returnVal = getClassName(javaType);
                    // }
                    //
                    // QName q = method.getXmlReturnType();
                    // if (q != null) {
                    // returnVal = Utils.makePackageName(q.getNamespaceURI())
                    // + q.getLocalPart();
                    // }
                    //
                    // jcxWriter.print(returnVal);
                    jcxWriter.print(method.getJavaReturnTypeFullName());
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
        } else {
            return cls.getName().replace('$', '.');
        }
    }

    // NOTE: For OUT and IN/OUT parameters this method can use the GenericHolder
    // class...TBD
    private void printParameters(
           List< ? extends BeehiveWsParameterMetadata> params, PrintWriter pw) {
        int paramPos = 0;
        for (BeehiveWsParameterMetadata param : params) {
            if (paramPos > 0) {
                pw.print(", ");
            }

            // String paramType = "Object";
            // Class javaType = param.getJavaType();
            // if (javaType != null) {
            // paramType = getClassName(javaType);
            // }
            //
            // QName q = param.getXmlType();
            // if (q != null) {
            // paramType = Utils.makePackageName(q.getNamespaceURI())
            // + q.getLocalPart();
            // }
            //
            // pw.print(paramType);
            if( param.getWpMode() == WebParam.Mode.INOUT || param.getWpMode() == WebParam.Mode.OUT)  {
                pw.print(getHolderForType(param.getJavaType()));
            } else {
                pw.print(param.getJavaTypeFullName());
            }
            pw.write(' ');
            String paramName = param.getWpName();
            if (paramName == null) {
                paramName = "param" + paramPos;
            }
            pw.print(paramName);
            paramPos++;
        }
    }

    private String getHolderForType(Class clazz) {
       if( clazz == int.class) return "javax.xml.rpc.holders.IntHolder";
       else if( clazz == boolean.class) return "javax.xml.rpc.holders.BooleanHolder";
       else if (clazz == new byte[0].getClass()) return "javax.xml.rpc.holders.ByteArrayHolder";
       else if (clazz == byte.class) return "javax.xml.rpc.holders.ByteHolder";
       else if (clazz == double.class) return "javax.xml.rpc.holders.DoubleHolder";
       else if (clazz == float.class) return "javax.xml.rpc.holders.FloatHolder";
       else if (clazz == long.class) return "javax.xml.rpc.holders.FloatHolder";
       else if (clazz == short.class) return "javax.xml.rpc.holders.ShortHolder";
       else     
       return  "org.apache.beehive.wsm.databinding.GenericHolder<" + clazz.getCanonicalName() + ">";
    }
    private static Options buildOptions() {
        Options options = new Options();
        OptionBuilder.hasArg();
        OptionBuilder.withArgName("dir");
        OptionBuilder.withDescription("Base directory of the wsdl file(s)");
        OptionBuilder.isRequired(true);
        Option option = OptionBuilder.create("wsdl");
        options.addOption(option);

        OptionBuilder.hasArg();
        OptionBuilder.withArgName("dir");
        OptionBuilder.withDescription("Root directory for the jcx file.");
        OptionBuilder.isRequired(true);
        option = OptionBuilder.create("gen_root");
        options.addOption(option);

//        OptionBuilder.hasArg();
//        OptionBuilder.withArgName("URL");
//        OptionBuilder.withDescription("URL to the web service.");
//        option = OptionBuilder.create("serviceURL");
//        OptionBuilder.isRequired(false); // if specified, it will overwrite
//                                            // the one in WSDL
//        options.addOption(option);

        OptionBuilder.hasArg();
        OptionBuilder.withArgName("dir");
        OptionBuilder.isRequired(false);
        OptionBuilder.withDescription("path annotation to use in the jcx");
        option = OptionBuilder.create("wsdl_path_annotation");
        options.addOption(option);

        OptionBuilder.hasArg();
        OptionBuilder.withArgName("package_name");
        OptionBuilder.isRequired(false);
        OptionBuilder.withDescription("Package name of the jcx");
        option = OptionBuilder.create("pkg");
        options.addOption(option);

        return options;
    }

    public static void main(String[] args) throws Exception {

        String outFileName = null;
        String wsdlDirName = null;
        String serviceURL = null;
        String wsdlPathAnnotation = null;
        String pkgName = null;

        try {
            Options options = buildOptions();
            CommandLineParser parser = new GnuParser();
            CommandLine line = parser.parse(options, args);

            outFileName = line.getOptionValue("gen_root");
            wsdlDirName = line.getOptionValue("wsdl");
//            if (line.hasOption("serviceURL"))
//                serviceURL = line.getOptionValue("serviceURL");
            if (line.hasOption("wsdl_path_annotation"))
                wsdlPathAnnotation = line
                        .getOptionValue("wsdl_path_annotation");

            if (line.hasOption("pkg"))
                pkgName = line.getOptionValue("pkg");

        } catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
            System.exit(-1);
        }

        File out = new File(outFileName);
        ExtensionMaker em = new ExtensionMaker(out);

        em.genJCX(wsdlDirName, serviceURL, pkgName, wsdlPathAnnotation);

    }

    /**
     * @param wsdlDirName
     * @param serviceURL
     * @param pkgName
     * @param em
     * @throws IOException
     * @throws Exception
     * @throws FileNotFoundException
     * @throws XmlException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private void genJCX(String wsdlDirName, String serviceURL, String pkgName,
            String wsdlPathAnnotation) throws IOException, Exception,
            FileNotFoundException, XmlException, IllegalAccessException,
            NoSuchFieldException {
        setServiceURL(serviceURL);
        setPackageName(pkgName);
        File wsdlDir = new File(wsdlDirName);
        if (wsdlDir.isDirectory()) {
            for (File wsdlFile : wsdlDir.listFiles(new WSDLFilter())) {
                genJCXForWSDLFile( wsdlPathAnnotation, wsdlFile);
            }
        } else if (wsdlDir.isFile()) {
            genJCXForWSDLFile(wsdlPathAnnotation, wsdlDir);
            
        } else {
            throw new Exception("no WSDL location specified at: " + wsdlDir.getCanonicalPath());
        }
    }

    /**
     * @param serviceURL
     * @param wsdlPathAnnotation
     * @param wsdlFile
     * @throws IOException
     * @throws Exception
     * @throws FileNotFoundException
     * @throws XmlException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private void genJCXForWSDLFile(String wsdlPathAnnotation, File wsdlFile) throws IOException, Exception, FileNotFoundException, XmlException, IllegalAccessException, NoSuchFieldException {
        if (wsdlPathAnnotation != null) {
            setWSDLPath(wsdlPathAnnotation + "/" + wsdlFile.getName());
        } else {
            setWSDLPath(wsdlFile.getCanonicalPath());

        }
        // get additional wsdl information
        WSDLParser parser = new WSDLParser(
                new FileInputStream(wsdlFile));
//        if (null == serviceURL) { // none has been defined, get it
//                                    // from wsdl
//        }

       serviceURL = parser.getSoapAddressLocation();
	   
	   
	   XmlBeanWSDLProcessor wsdlProcessor =  new XmlBeanWSDLProcessor( new FileInputStream(wsdlFile));

	   
       writeJCX(wsdlProcessor.getObjectModel( new SystemTypeLookupService()));
    }

    /**
     * @param packageName
     *            The packageName to set.
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
