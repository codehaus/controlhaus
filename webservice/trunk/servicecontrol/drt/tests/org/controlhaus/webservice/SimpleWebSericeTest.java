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
package org.controlhaus.webservice;


import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.holders.StringHolder;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.controlhaus.controlunit.ControlTestCase;
import org.controlhaus.webservice.testmodel.client.*;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;

public class SimpleWebSericeTest extends ControlTestCase {
	static Logger logger = Logger.getLogger(SimpleWebSericeTest.class);

	@Control SimpleWSClient client;
//	@Control SimpleWSClientXMLBeansType xmlbeanTypeClient;
	
	
	   public void setUp() throws Exception  {
	   		super.setUp();   // initialize my control instance.... 
	   		
	   		// TODO:  The local Axis server is not working.... 
//	   		LocalAxisServiceFacotry.registerAsServiceFactory();
//	   		LocalAxisServiceFacotry.setWebServiceProvider(SimpleWebService.class);
	   }
	   
//	   public void testFactoryInstantiation() throws ServiceException {
//        ServiceFactory factory = ServiceFactory.newInstance();
//        System.out.println("Service factory class: " + factory);
//        assertTrue(factory.getClass().equals(LocalAxisServiceFacotry.class));
//        
//	   }
	   public void testSimpleInvoke() throws Exception {
	   		ClientAddress res = client.getAddressFromName("Jack");
	        logger.debug("Jack... city: " + res.getCity() + " zipcode: " + res.getZip() + " street: " + res.getStreetName());
	   		assertTrue("Result address is not as expected!:  Result: " +res.getCity() + " Expected: " + "Seattle", 0 == "Seattle".compareTo(res.getCity()));
	   		assertTrue("Result zipcode is not as expected!:  Result: " +res.getZip() + " Expected: " + "98119", 98119 == res.getZip());	   		
	   }
	   
	   
	   // TODO: Holders are not working yet,..... enable this test after the code is in place
//	   public void testHolder() throws Exception {
//	   	StringHolder data = new StringHolder();
//	   	data.value = "Ja";
//   		int res = client.FindClosestName(data);
//   		System.out.println("Result: " + res + "data + " + data.value);
//   		assertTrue(res == 1);
//   		assertTrue(0 == data.value.compareTo("Jack"));
//    }


}
