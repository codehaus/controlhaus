/*
 * AxisInvocationTarget.java
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
package org.controlhaus.webservice.invocation;

import java.lang.reflect.Method;

import java.util.List;

import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeader;
import javax.wsdl.OperationType;

import org.apache.axis.Message;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.constants.Style;
import org.apache.axis.constants.Use;
import org.apache.axis.description.FaultDesc;
import org.apache.axis.description.OperationDesc;
import org.apache.axis.description.ParameterDesc;
import org.apache.axis.encoding.TypeMapping;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.axis.utils.BeanPropertyDescriptor;
import org.apache.axis.wsdl.fromJava.Namespaces;
import org.apache.axis.wsdl.fromJava.Types;

import org.apache.beehive.wsm.axis.util.AxisTypeMappingUtil;
import org.apache.beehive.wsm.jsr181.model.Jsr181ParameterMetadata;
import org.apache.beehive.wsm.jsr181.model.Jsr181MethodMetadata;
import org.apache.beehive.wsm.jsr181.model.Jsr181TypeMetadata;
import org.apache.beehive.wsm.jsr181.model.SOAPBindingInfo;
import org.apache.beehive.wsm.jsr181.model.client.ClientMethodMetadata;
import org.apache.beehive.wsm.jsr181.model.client.ClientParameterMetadata;
import org.apache.beehive.wsm.jsr181.util.TypeMappingUtil;

import org.apache.xmlbeans.XmlObject;

import org.controlhaus.webservice.PluggableServiceControl;

import org.w3c.dom.Element;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class AxisInvocationTarget
    implements WebServiceInvocationTarget {
    
    Service service;

    public AxisInvocationTarget() {
        service = new Service();
    }

    public Object invokeWebService(Method method,
                                   Object[] args,
                                   PluggableServiceControl serviceControl,
                                   Jsr181TypeMetadata wstm,
                                   String alternateOperationName)
        throws Exception {      

        Call call = (Call)service.createCall();
        TypeMapping tm = call.getTypeMapping();
        
        String operationName;
        if (alternateOperationName != null) {
            operationName = alternateOperationName;
        }
        else {
            operationName = method.getName();
        }

        Class[] paramTypes = method.getParameterTypes();
        
        Jsr181MethodMetadata wmm = wstm.getMethod(operationName, paramTypes);

        if (wmm == null) {
            StringBuffer sb = 
                new StringBuffer("No Jsr181MethodMetadata found for "
                                 + operationName + '(');
            boolean first = true;
            for (Class cls : paramTypes) {
                if (first) {
                    first = false;
                }
                else {
                    sb.append(',');
                }
                sb.append(cls.getName());
            }
            sb.append(')');
            throw new NoSuchMethodException(sb.toString());

        }
        else {
         
       
            OperationDesc od = createOperationDesc(wmm,
                                                   method,
                                                   tm,
                                                   wstm.getWsTargetNamespace());

            //System.out.println(od);
            configureSoapBinding(od, wstm.getSoapBinding());
            call.setOperation(od);
            call.setOperationName(od.getElementQName());
            call.setTargetEndpointAddress(serviceControl.getEndPoint());
            String action = od.getSoapAction();
            call.setUseSOAPAction(action != null);
            call.setSOAPActionURI(action);
            QName port = serviceControl.getWsdlPort();
            if (port != null) {
                call.setPortName(port);
            }

            String username = serviceControl.getUsername();
            if (username != null) {
                call.setUsername(username);
            }

            String passwd = serviceControl.getPassword();
            if (passwd != null) {
                call.setPassword(passwd);
            }

            int timeout = serviceControl.getTimeout();
            if (timeout != 0) { 
                call.setTimeout(new Integer(timeout));
            }

            XmlObject headers = serviceControl.getOutputHeadersAsXmlBean();

            Element[] outHeadElements = serviceControl.xObj2Elements(headers);
            if (outHeadElements != null) {    
                for (Element e : outHeadElements) {
                    SOAPHeaderElement she = new SOAPHeaderElement(e);
                    call.addHeader(she);
                }
            }

            // FIXME jcolwell@bea.com 2004-Nov-19 -- configure the schema
            // version appropriately in case the server is picky about it.

            // call.getMessageContext().setSchemaVersion(SchemaVersion
            //                                        .SchemaVersion1999);

            
            System.out.println("invoking " + od.getElementQName()
                               + " with " + args.length
                               + " arguments while " + od.getNumParams() 
                               + " are expected");
            
            Object ret = call.invoke(od.getElementQName(), args);
            if (ret != null) {
                Class retClass = ret.getClass();
                Class expectedRetClass = od.getReturnClass();
                
                System.out.println(od.getName() + " returned " 
                                   + retClass.getName() 
                                   + (expectedRetClass
                                      .isAssignableFrom(retClass) 
                                      ? " as expected." 
                                      : (" but " + expectedRetClass.getName()
                                         + " was expected")));
            }
            else {
                System.out.println(od.getName() + " returned null"); 
            }
            Message m = call.getResponseMessage();
            if (m != null) {
                SOAPHeader sh = m.getSOAPHeader();
                if (sh != null) {
                    serviceControl.setInputHeadersAsXmlBean
                        (XmlObject.Factory.parse(sh));
                }
            }
            return ret;
        }
    }

    // NOTE jcolwell@bea.com 2004-Nov-17 -- see if I can unify this method with the one
    // in AxisHook to reduce the copy-and-pasted code and get more consistency.
    private OperationDesc createOperationDesc(Jsr181MethodMetadata meta,
                                              Method meth,
                                              TypeMapping tm,
                                              String defaultNamespace) throws Exception {
        
        TypeMappingUtil tmu = new AxisTypeMappingUtil(tm);
        String operationName = meta.getWmOperationName();
        QName opQName = new QName(defaultNamespace, operationName);
    
        OperationDesc od = new OperationDesc();

        od.setName(operationName);
        od.setElementQName(opQName);
        od.setSoapAction(meta.getWmAction());
   
        if (meta.isOneWay()) {
            od.setMep(OperationType.ONE_WAY);
        } else {
            od.setReturnQName(new QName(meta.getWrTargetNamespace(),
                                        meta.getWrName()));

            Class returnClass = meth.getReturnType();
            //Class metaReturnClass = meta.getJavaReturnType();

            QName type;
            if (meta instanceof ClientMethodMetadata) {
                type = tmu.registerType(returnClass,
                                        ((ClientMethodMetadata)meta)
                                        .getXmlReturnType());
            }
            else {
                type = tmu.registerType(returnClass);
            }

            if (type == null) {
                return null;
            }
            od.setReturnClass(returnClass);
            od.setReturnType(type);
        }

        List<Jsr181ParameterMetadata> parameters = meta.getParams();
        Class[] paramClasses = meth.getParameterTypes();
        int paramIndex = 0;
            
        for (Jsr181ParameterMetadata param : parameters) {
            
            ParameterDesc pd = new ParameterDesc();
            pd.setQName(new QName(param.getWpTargetNamespace(),
                                  param.getWpName()));

            //System.out.println("registering " + paramClasses[paramIndex]);
            pd.setJavaType(paramClasses[paramIndex]);
            QName expectedType = null;
            if (param instanceof ClientParameterMetadata) {
                expectedType = ((ClientParameterMetadata)param).getXmlType();
            }

            QName paramType = tmu.registerType(paramClasses[paramIndex++],
                                               expectedType);
           
            if (paramType == null) {
                throw new Exception("failed to register "
                                    + paramClasses[paramIndex-1].getName());
            }
            
            if (paramType.equals(expectedType)) {
                //System.out.println("parameter type" + paramType + " as expected");
            }
            else {
                System.out.println("parameter type" + paramType + " does not match " + expectedType);
            }

            pd.setTypeQName(paramType);
            WebParam.Mode mo = param.getWpMode();
            switch (mo) {
            case OUT:
                pd.setMode(ParameterDesc.OUT);
                pd.setInHeader(false);
                pd.setOutHeader(param.isWpHeader());
                break;
            case INOUT:
                pd.setMode(ParameterDesc.INOUT);
                boolean header = param.isWpHeader();
                pd.setInHeader(header);
                pd.setOutHeader(header);
                break;
            case IN:
            default:
                pd.setMode(ParameterDesc.IN);
                pd.setInHeader(param.isWpHeader());
                pd.setOutHeader(false);
            }
            /*
            System.out.println("adding parameter " + pd.getQName() + " of type "
                               + pd.getTypeQName() + " | "  + pd.getJavaType()
                               .getName());
            */
            od.addParameter(pd);
        }
        for (Class thrown : meth.getExceptionTypes()) {
            FaultDesc fd = new FaultDesc();
            fd.setClassName(thrown.getName());
            od.addFault(fd);
        }
        od.setMethod(meth);
        //System.out.println("done creating OperationDesc");
        return od;
    }


    protected void configureSoapBinding(OperationDesc od,
                                               SOAPBindingInfo sbi) {
        javax.jws.soap.SOAPBinding.Style style
                = javax.jws.soap.SOAPBinding.Style.DOCUMENT;
        javax.jws.soap.SOAPBinding.Use use
                = javax.jws.soap.SOAPBinding.Use.LITERAL;
        javax.jws.soap.SOAPBinding.ParameterStyle paramStyle
                = javax.jws.soap.SOAPBinding.ParameterStyle.WRAPPED;
        if (sbi != null) {
            style = sbi.getStyle();
            use = sbi.getUse();
            paramStyle = sbi.getParameterStyle();
        }
        if (style == style.RPC) {
            od.setStyle(Style.RPC);
            if (use == use.ENCODED) {
                od.setUse(Use.ENCODED);
            } else {
                od.setUse(Use.LITERAL);
            }
        } else {
            /*
             * since DOCUMENT ENCODED is not valid so
             * force to use LITERAL
             */
            od.setUse(Use.LITERAL);

            // check if this is a wrapped document literal 
            if (paramStyle == paramStyle.WRAPPED) {
                od.setStyle(Style.WRAPPED);
            } else {
                // just regular document style
                od.setStyle(Style.DOCUMENT);
            }
        }
    }
}
