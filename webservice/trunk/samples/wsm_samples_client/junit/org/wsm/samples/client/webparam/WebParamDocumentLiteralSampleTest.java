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

import test.WebParamDocumentLiteralSampleService;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;
import web.webparam.Phone;
public class WebParamDocumentLiteralSampleTest extends ControlTestCase {
    static Logger logger = Logger.getLogger(WebParamDocumentLiteralSampleTest.class);
    



    @Control WebParamDocumentLiteralSampleService client;


    public void setUp() throws Exception {
        super.setUp(); // initialize my control instance....

    }

 



    public void testcreatePhoneNumberInBody() throws Exception {

        Phone  newPhone = client.createPhoneNumberInBody(206 );
        System.out.println("***********************************************************Newphone code: " + newPhone.getAreaCode());
        assertTrue(newPhone.getAreaCode() == 206);

    }
 
    public void testcreatePhoneNumberInHeader() throws Exception {
        Phone oldPhone = Phone.Factory.newInstance();
        oldPhone.setAreaCode(111);
        oldPhone.setExchange("MyExchange");
        oldPhone.setNumber("1111111");
        Phone  newPhone = client.createPhoneNumberInHeader(206, oldPhone );
        System.out.println("***********************************************************Newphone code: " + newPhone.getAreaCode());
        assertTrue(newPhone.getAreaCode() == 206);
        assertTrue(newPhone.getExchange().equals("MyExchange"));
        assertTrue(newPhone.getNumber().equals("1111111"));
    }


}
