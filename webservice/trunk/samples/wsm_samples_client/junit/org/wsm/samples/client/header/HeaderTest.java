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

package org.wsm.samples.client.header;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;
import javax.xml.rpc.holders.StringHolder;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeaderElement;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.controlhaus.controlunit.ControlTestCase;
import org.w3c.dom.Element;

import test.StockQuoteImplService;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;

public class HeaderTest extends ControlTestCase {
    static Logger logger = Logger.getLogger(HeaderTest.class);
    

    /**
     * Header element namespace prefix
     */
    private static final String SIGN_PREFIX
        = "sign";

    /**
     * Header element
     */
    private static final String SIGN_ELEMENT
        = "sign";

    

    @Control StockQuoteImplService client;

    SignatureTool signatureTool;

    public void setUp() throws Exception {
        super.setUp(); // initialize my control instance....
        signatureTool = new SignatureToolImpl();
    }

    public void testBadHeader() throws Exception {
        SOAPElement signature = signatureTool.getSignature("InvalidName");
        SOAPHeaderElement headerRoot = new org.apache.axis.message.SOAPHeaderElement(SIGN_ELEMENT,
                                                            SIGN_PREFIX);
        headerRoot.setActor("simple-security");
        headerRoot.addChildElement(signature);
        Element[] badHeader = { headerRoot };
        client.setOutputHeaders(badHeader);
        client.getQuote("bea");
        Element[] respHeader = client.getInputHeaders();
        System.out.println("Response header");
        for(Element nxtHeader : respHeader) {
            System.out.println(nxtHeader);
        }
    }

    public void testGoodHeader() throws Exception {
        SOAPElement signature = signatureTool.getSignature("InvalidName");
        SOAPHeaderElement headerRoot = new org.apache.axis.message.SOAPHeaderElement(SIGN_ELEMENT,
                                                            SIGN_PREFIX);
        headerRoot.setActor("simple-security");
        headerRoot.addChildElement(signature);
        Element[] goodHeader = { headerRoot };
        client.setOutputHeaders(goodHeader);
        client.getQuote("bea");
        Element[] respHeader = client.getInputHeaders();
       System.out.println("Response header");
        for(Element nxtHeader : respHeader) {
            System.out.println(nxtHeader);
        }
    }

}
