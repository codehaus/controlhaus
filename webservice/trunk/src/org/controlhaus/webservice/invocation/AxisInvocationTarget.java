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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.rpc.holders.Holder;
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
import org.apache.axis.encoding.XMLType;
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


        // NOTE jcolwell@bea.com 2005-Jan-04 -- 
        // convert holder classes to underlying type to retrieve the method metadata
        // NOTE: You can use GenericHolder<T> to make a holder for any class T.
        // For now the extension maker doesn't use this... This should be fixed.
        Type[] genericParamTypes = method.getGenericParameterTypes();
        Class[] paramTypes = new Class[genericParamTypes.length];
        int paramIndex = 0;
        for (Type t : genericParamTypes) {
            if (t instanceof Class) {
                paramTypes[paramIndex++] = (Class)t;
            }
            else if (t instanceof java.lang.reflect.ParameterizedType) {
                java.lang.reflect.ParameterizedType pt =
                    ((java.lang.reflect.ParameterizedType)t);
                Type[] typeArgs = pt.getActualTypeArguments();
                Type raw = pt.getRawType();
                if (GenericHolder.class.isAssignableFrom((Class)raw)
                    && typeArgs.length == 1) {
                    paramTypes[paramIndex++] = (Class)typeArgs[0];
                }
                else {
                    throw new Exception(raw + " ~ " + typeArgs[0]);
                }
            }
            else {
                throw new Exception("Only Classes and ParameterizedTypes"
                                    + " are currently handled, support for " 
                                    + t.getClass() + " should be added.");
            }
        }

        // note the obejct model doesn't store holders, rather the underlying types, so to get the method
        // we need to get the underlying types.  After the loop above the paramTypes have the underlying types
        // for any holders in the arg list.
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
        	// list of arguments to the call.
            List<Object> argList = new ArrayList<Object>(Arrays.asList(args));
            //out list is the list of arguments that are OUT or IN/OUT the createOperationDesc figures out
            //the list.  The list is used after the Call.invoke() to actaully set the result values.
            List<Object> outList = new ArrayList<Object>(argList.size());
            
            //NOTE:  Look into this call, it may be possible to replace this with
            // straight JAX-RPC interface.  This way this code would remain unchanged
            // if internal axis code was changed.
            OperationDesc od = createOperationDesc(wmm,
                                                   method,
                                                   paramTypes,
                                                   tm,
                                                   wstm.getWsTargetNamespace(),
                                                   argList,  // arglist contains in and in/out parameters, this list gets populated by this call
                                                   outList); // outList is the list of out parameters and in/out that gets populated by this call.

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
                               + " with " + argList.size()
                               + " arguments while " + od.getNumInParams() 
                               + " are expected");
            
            Object ret = call.invoke(od.getElementQName(), argList.toArray());
            
            int expectedOuts = od.getNumOutParams();
            if (expectedOuts > 0) {
               
                Map outParamMap = call.getOutputParams();
                if (outParamMap != null) {
                    
                    int actualOuts = outParamMap.size();
                            
                    if (expectedOuts == actualOuts) {
                        // NOTE jcolwell@bea.com 2005-Jan-06 -- 
                        // iterate through the parameters looking for QNames
                        // matching the returned out params
                        ArrayList allParams = od.getParameters();
                        int outParamIndex = 0;
                        for (Object paramObj : allParams) {

                            Object currentArg = args[outParamIndex++];
                            
                            ParameterDesc pd = (ParameterDesc)paramObj;
                            if (pd.getMode() != ParameterDesc.IN) {
                                QName q = pd.getQName();
                                Object op = outParamMap.get(q);
                                Class cl = pd.getJavaType();
                                
                                if (currentArg instanceof Holder) {
                                    Holder h = (Holder)currentArg;
                                    Field value = h.getClass().getField("value");
                                    if (cl.isAssignableFrom(op.getClass())) {
                                        value.set(h, op);
                                    }
                                    else {
                                        throw new Exception("out param mismatch "
                                                            + cl
                                                            + " not assignable from " 
                                                            + op.getClass());
                                    }
                                }
                                else {
                                    throw new Exception("out parameters must provide a holder class");
                                }
                            }
                        }
                    }
                    else {
                        throw new Exception("out param count mismatch: " 
                                            + actualOuts
                                            + " out params returned while "
                                            + expectedOuts
                                            + " were expected");
                    }
                }
            }
            
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
                                              Class[] paramTypes,
                                              TypeMapping tm,
                                              String defaultNamespace,
                                              List argList,
                                              List outList) throws Exception {
        
        TypeMappingUtil tmu = new AxisTypeMappingUtil(tm);
        String operationName = meta.getWmOperationName();
        QName opQName = new QName(defaultNamespace, operationName);
    
        OperationDesc od = new OperationDesc();

        od.setName(operationName);
        od.setElementQName(opQName);
        od.setSoapAction(meta.getWmAction());
        
        Class returnClass = meth.getReturnType();

        if (meta.isOneWay()) {
            od.setMep(OperationType.ONE_WAY);
        } 
        else if (returnClass == null || Void.TYPE.equals(returnClass)) {
            od.setReturnClass(Void.TYPE);
            od.setReturnType(XMLType.AXIS_VOID);
        }
        else {
            od.setReturnQName(new QName(meta.getWrTargetNamespace(),
                                        meta.getWrName()));

            //Class metaReturnClass = meta.getJavaReturnType();

            QName type;
            
                type = tmu.registerType(returnClass,
                                        meta.getXmlReturnType());
          

            if (type == null) {
                return null;
            }
            od.setReturnClass(returnClass);
            od.setReturnType(type);
        }

        List<Jsr181ParameterMetadata> parameters = meta.getParams();
        Class[] paramClasses = paramTypes;
        Class[] originalParamClasses = meth.getParameterTypes();
        int paramIndex = 0;
        int argIndex = 0;
        
        for (Jsr181ParameterMetadata param : parameters) {
            
            ParameterDesc pd = new ParameterDesc();
            pd.setQName(new QName(param.getWpTargetNamespace(),
                                  param.getWpName()));

            QName expectedType = null;
            // Just to be safe, the param should always be the ClientParameterMetadata
            // This is to make sure the model came from a wsdl processing...

                expectedType = param.getXmlType();


            Class providedClass = paramClasses[paramIndex];

            if (Holder.class.isAssignableFrom(providedClass)) {
                //providedClass = (Class)
                    providedClass.getField("value")
                    .getGenericType().toString();
            }

            System.out.println("registering " + providedClass.getName() 
                               + " with " + expectedType);

            QName paramType = tmu.registerType(providedClass,
                                               expectedType);
           
            if (paramType == null) {
                throw new Exception("failed to register "
                                    + providedClass.getName());
            }
            
            if (paramType.equals(expectedType)) {
                //System.out.println("parameter type" + paramType + " as expected");
            }
            else {
                System.out.println("parameter type" + paramType + " does not match " + expectedType);
            }

            pd.setTypeQName(paramType);

            // FIXME jcolwell@bea.com 2005-Jan-06 -- 
            // ParameterDesc.setJavaType should not throw and
            // IllegalArgumentException when OUT and INOUT params are set that
            // are not holders since Clients do not even use holders in their
            // descriptions
            pd.setJavaType(paramClasses[paramIndex]);
            WebParam.Mode mo = param.getWpMode();
            switch (mo) {
            case OUT:
                pd.setMode(ParameterDesc.OUT);
                pd.setInHeader(false);
                pd.setOutHeader(param.isWpHeader());
                outList.add(argList.remove(argIndex--));
                break;
            case INOUT:
                pd.setMode(ParameterDesc.INOUT);
                boolean header = param.isWpHeader();
                pd.setInHeader(header);
                pd.setOutHeader(header);
                Holder inout = (Holder)argList.get(argIndex);
                Class holderClass = inout.getClass();
                Field value = holderClass.getField("value");                
                outList.add(inout);
                argList.set(argIndex, value.get(inout));
                break;
            case IN:
            default:
                pd.setMode(ParameterDesc.IN);
                pd.setInHeader(param.isWpHeader());
                pd.setOutHeader(false);
            }
            paramIndex++;
            argIndex++;

            System.out.println("adding parameter " + pd.getQName() + " of type "
                               + pd.getTypeQName() + " | "  + pd.getJavaType()
                               .getName());

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
