/*
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
 * Original author: Daryoush Mehrtash
 */
package org.controlhaus.webservice.framework;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.configuration.BasicServerConfig;
import org.apache.axis.configuration.SimpleProvider;
import org.apache.axis.handlers.soap.SOAPService;
import org.apache.axis.providers.java.RPCProvider;
import org.apache.axis.server.AxisServer;
import org.apache.beehive.wsm.axis.AnnotatedWebServiceDeploymentHandler;
import org.apache.log4j.Logger;


import org.w3c.dom.Element;


public class LocalAxisServiceFacotry extends ServiceFactory {
	static Logger logger = Logger.getLogger(LocalAxisServiceFacotry.class);

	static Class theWebService;   // the jws file
	
	public static void registerAsServiceFactory() {
		System.setProperty(SERVICEFACTORY_PROPERTY, LocalAxisServiceFacotry.class.getCanonicalName());
	}
	
	public static void setWebServiceProvider(Class provider) {
		theWebService = provider;
	}
	
	
	/* (non-Javadoc)
	 * @see javax.xml.rpc.ServiceFactory#createService(java.net.URL, javax.xml.namespace.QName)
	 */
	public Service createService(URL arg0, QName arg1) throws ServiceException {
		logger.debug("createService from URL... TBD");
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.xml.rpc.ServiceFactory#createService(javax.xml.namespace.QName)
	 */
	public Service createService(QName arg0)  {
		logger.debug("createService from Qname... ");
		org.apache.axis.client.Service service = null;
		try {
			// set up a local axis service
			TestWebServiceHandler handler = new TestWebServiceHandler();
			SOAPService ss = handler.getSOAPService(theWebService);
			AxisServer server = new DebugAxisServer(ss);
			ss.setEngine(server);  	// Explicitly setting the engine to be the local
			// axis server as oppose to opening connection as the normal
			service = new org.apache.axis.client.Service();	   // axis service 
			service.setEngine(server);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service;
	}

	/* (non-Javadoc)
	 * @see javax.xml.rpc.ServiceFactory#loadService(java.lang.Class)
	 */
	public Service loadService(Class arg0) throws ServiceException {
		logger.debug("loadService from class... TBD");
		throw new RuntimeException("Unimplemented method");
	}

	/* (non-Javadoc)
	 * @see javax.xml.rpc.ServiceFactory#loadService(java.net.URL, java.lang.Class, java.util.Properties)
	 */
	public Service loadService(URL arg0, Class arg1, Properties arg2)
			throws ServiceException {
		logger.debug("loadService from url/class... TBD");
		throw new RuntimeException("Unimplemented method");
	}

	/* (non-Javadoc)
	 * @see javax.xml.rpc.ServiceFactory#loadService(java.net.URL, javax.xml.namespace.QName, java.util.Properties)
	 */
	public Service loadService(URL arg0, QName arg1, Properties arg2)
			throws ServiceException {
		logger.debug("loadService from url/qname... TBD");
		throw new RuntimeException("Unimplemented method");
	}
	
	public String toString() {
		return "This is the local Axis service factory";
	}
	
    // a server that doesn't go to all chains
    private class DebugAxisServer extends AxisServer {
        
        /**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = 1L;
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
        
    	/**
		 * Comment for <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = 1L;

		// This method is only here to make the call public.
        public SOAPService getSOAPService(Class cls)
            throws Exception{

            return super.getSOAPService(cls);
        }
    }

 
}


