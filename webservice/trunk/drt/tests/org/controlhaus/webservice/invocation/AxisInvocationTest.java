/*
 * AxisInvocationTest.java
 * 
 * Copyright 2004 BEA Systems, Inc.
 * 
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
import java.net.URL;
import javax.xml.namespace.QName;

import junit.framework.TestCase;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.handlers.soap.SOAPService;
import org.apache.axis.server.AxisServer;
import org.apache.beehive.wsm.axis.AnnotatedWebServiceDeploymentHandler;
import org.apache.beehive.wsm.jsr181.model.Jsr181TypeMetadata;
//import org.apache.beehive.wsm.jsr181.processor.reflection
//    .WsmReflectionAnnotationProcessor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

import org.controlhaus.webservice.PluggableServiceControl;

import org.w3c.dom.Element;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class AxisInvocationTest extends TestCase {

    
    
    public void testInvokeWebService() throws Exception {

        Class cls = FakeWebService.class;
        Method meth = cls.getMethod("doInvocation", new Class[0]);
        TestWebServiceHandler handler = new TestWebServiceHandler();
        SOAPService ss = handler.getSOAPService(cls);
        AxisInvocationTarget ait = new AxisInvocationTarget();
        AxisServer server = new DebugAxisServer(ss);
        ss.setEngine(server);  // Explicitly setting the engine to be the local
        					// axis server as oppose to opening connection as the normal
        				    
        ait.service.setEngine(server);
   
        Object obj = ait.invokeWebService(meth,
                                          new Object[0],
                                          new EmptyServiceControl(),
//                                          (Jsr181TypeMetadata)
//                                          WsmReflectionAnnotationProcessor
//                                          .getInstance().getObjectModel(cls),
                                          null,  // There is no reflection package!   THIS SHOULD NOT USE REFLECTION ANYWAYS, it should build OM FROM wsdl.
                                          null);
    
        assertNotNull(obj);
        assertEquals(obj, meth.invoke(cls.newInstance(), new Object[0]));
    }

    // a server that doesn't go to all chains
    private class DebugAxisServer extends AxisServer {
        
        private SOAPService mInjectedSOAPService;
        
        public DebugAxisServer(SOAPService ss) {
            super(new SimpleProvider());
            mInjectedSOAPService = ss;
        }

        public void invoke(MessageContext mc) throws AxisFault {
            mc.setService(mInjectedSOAPService);
            super.invoke(mc);
        }
    }

    private class TestWebServiceHandler
        extends AnnotatedWebServiceDeploymentHandler {
        
    	// This method is only here to make the call public.
        public SOAPService getSOAPService(Class cls)
            throws Exception{

            return super.getSOAPService(cls);
        }
    }

    private class EmptyServiceControl implements PluggableServiceControl {

        public Element[] xObj2Elements(XmlObject xObj) {
            return new Element[0];
        }

        public XmlObject elements2XmlObject(Element[] elements) throws XmlException {
            return null;
        }
        
        public boolean insertChild(XmlObject parent, XmlObject child) { return false; }

        public XmlObject getInputHeadersAsXmlBean() { return null; }

        public void setInputHeadersAsXmlBean(XmlObject headers) {}

        public XmlObject getOutputHeadersAsXmlBean() { return null; }

        public void setOutputHeadersAsXmlBean(XmlObject headers) {}

        public void setEndPoint( URL url ) {}

        public URL getEndPoint() { return null; }

        public void setWsdlPort( QName wsdlPortName ) {}

        public QName getWsdlPort() { return null; }

        public void setUsername( String username ) {}

        public void setPassword( String password ) {}

        public String getUsername() {return null; }

        public String getPassword() { return null; }

        public Element[] getInputHeaders() { return new Element[0]; }

        public void setOutputHeaders( Element[] headers ) {}

        public void setTimeout(int timeout) {}

        public int getTimeout() { return 0; }

        public void reset() {}

		/* (non-Javadoc)
		 * @see org.controlhaus.webservice.PluggableServiceControl#getInvocationTarget()
		 */
		public WebServiceInvocationTarget getInvocationTarget() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		/* (non-Javadoc)
		 * @see org.controlhaus.webservice.PluggableServiceControl#setInvocationTarget(org.controlhaus.webservice.invocation.WebServiceInvocationTarget)
		 */
		public void setInvocationTarget(WebServiceInvocationTarget wsit) {
			// TODO Auto-generated method stub
			
		}
    }
}
