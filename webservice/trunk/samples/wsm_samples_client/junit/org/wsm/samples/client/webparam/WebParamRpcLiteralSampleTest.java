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

package org.wsm.samples.client.webparam;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeaderElement;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.controlhaus.controlunit.ControlTestCase;
import org.w3c.dom.Element;

import test.RpcLiteralSampleService;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;
import org.apache.beehive.web.webservice.rpc_examples.Phone;
import org.apache.beehive.web.webservice.rpc_examples.Address;

import org.apache.beehive.web.webservice.rpc_examples.StateType;
import org.apache.beehive.wsm.databinding.GenericHolder;

public class WebParamRpcLiteralSampleTest extends ControlTestCase {
    static Logger logger = Logger.getLogger(WebParamRpcLiteralSampleTest.class);
    



    @Control RpcLiteralSampleService client;


    public void setUp() throws Exception {
        super.setUp(); // initialize my control instance....

    }

 



    public void testcreatePhoneNumberInBody() throws Exception {

		GenericHolder<Address> addressHolder = new GenericHolder<Address>(new Address());
		javax.xml.rpc.holders.IntHolder res = new IntHolder();
        client.createAddressInBody(addressHolder, res);
        assertTrue(addressHolder.value.getState().getState().equals("PA") );

    }
 
    public void testcreatePhoneNumberInHeader() throws Exception {
		GenericHolder<Address> addressHolder = new GenericHolder<Address>(new Address());
		javax.xml.rpc.holders.IntHolder res = new IntHolder();
       client.createAddressInHeader(addressHolder, res);
        assertTrue(addressHolder.value.getState().getState().equals("CA") );

    }


}
