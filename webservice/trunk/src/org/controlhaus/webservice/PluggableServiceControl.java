/*
 * PluggableServiceControl.java
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
package org.controlhaus.webservice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.properties.PropertySet;
import org.apache.beehive.wsm.jsr181.wsdl.WSDLProcessor;
import org.apache.beehive.wsm.jsr181.wsdl.XmlBeanWSDLProcessor;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import org.controlhaus.webservice.invocation.AxisInvocationTarget;
import org.controlhaus.webservice.invocation.WebServiceInvocationTarget;

import org.w3c.dom.Element;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
@ControlInterface (defaultBinding = "org.controlhaus.webservice.ServiceControlImpl")
public interface PluggableServiceControl extends ServiceControl {
    
    /**
     * WSDLProcessor Implementation
     */
    @PropertySet(prefix="WSDLProcessorImpl")
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface WSDLProcessorImpl {
        
        Class<? extends WSDLProcessor> processor()
            default XmlBeanWSDLProcessor.class;
    }

    
    /**
     * Web Service Invocation Target Implementation
     */
    @PropertySet(prefix="InvocationTargetImpl")
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    public @interface InvocationTargetImpl {
        
        Class<? extends WebServiceInvocationTarget> invoker()
            default AxisInvocationTarget.class;
    }
    
    public WebServiceInvocationTarget getInvocationTarget() throws Exception;    	
    	
    public void setInvocationTarget(WebServiceInvocationTarget wsit);
    
    public Element[] xObj2Elements(XmlObject xObj);  //utility

    public XmlObject elements2XmlObject(Element[] elements) throws XmlException; //utility

    public boolean insertChild(XmlObject parent, XmlObject child); //utility

    public XmlObject getInputHeadersAsXmlBean();

    public void setInputHeadersAsXmlBean(XmlObject headers);

    public XmlObject getOutputHeadersAsXmlBean();

    public void setOutputHeadersAsXmlBean(XmlObject headers);

}
