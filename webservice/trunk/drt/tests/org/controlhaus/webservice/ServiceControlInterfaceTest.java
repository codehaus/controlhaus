/*
 * ServiceControlInterfaceTest.java
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
package org.controlhaus.webservice;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.xmlbeans.XmlObject;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class ServiceControlInterfaceTest extends ServiceControlTestCase {

    @Control InterfaceTestControl mItc;

    public void setUp() throws Exception {
        super.setUp();
        mItc.setEndPoint(new java.net.URL("http://localhost/controlhaus/org/controlhaus/webservice/InterfaceTestService.jws"));
    }


    public void testEndpoint() throws Exception {
        System.out.println("current endpoint is: " + mItc.getEndPoint());
        assertTrue(mItc.isReady());
    }


    public void testTimeout() throws Exception {
        int initialTimeout = mItc.getTimeout();
        int oneSecond = 1000;
        int fiveSeconds = 5000;
        int tenSeconds = 10000;
        mItc.setTimeout(fiveSeconds);   
        Exception ex = null;
        try {
            mItc.timeOut(oneSecond);       
        }
        catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
        try {
            mItc.timeOut(tenSeconds);
        }
        catch (Exception e) {
            ex = e;
        }
        mItc.setTimeout(initialTimeout);
        assertNotNull(ex);
    }

    public void testWsdlPort() throws Exception {
        
    }

    public void testBasicAuth() throws Exception {
        
    }

    public void testBeanHeaders() throws Exception {
        mItc.setOutputHeadersAsXmlBean(XmlObject.Factory.parse("<tossed><shh>This header added by the testBeanHeaders test in ServiceControlInterfaceTest.java</shh><anotherone>here is another header</anotherone></tossed>"));

        int headersReceived = mItc.headOn();
        assertTrue(headersReceived == 2);

        XmlObject xObj = mItc.getInputHeadersAsXmlBean();
        String headerContents = xObj.toString();
        assertTrue(headerContents.indexOf("SOAPHeaderHandler") >= 0);

    }

    public void testDomHeaders() throws Exception {

        NodeList nl = XmlObject.Factory.parse("<tossed><shh>This header added by the testDomHeaders test in ServiceControlInterfaceTest.java</shh><anotherone>here is another header</anotherone></tossed>")
            .newDomNode().getChildNodes();
        int len =  nl.getLength();
        Element[] elems = new Element[len];
        for (int j = 0; j < len; j++) {
            elems[j] = (Element)nl.item(j);
        }

        mItc.setOutputHeaders(elems);

        int headersReceived = mItc.headOn();
        assertTrue(headersReceived == 2);

        Element[] incomingHeaders = mItc.getInputHeaders();
        boolean foundShh = false;
        for (int j = 0; !foundShh && j < incomingHeaders.length; j++) {
            Node txt = incomingHeaders[j].getFirstChild();
            if (txt != null && txt.getNodeType() == Node.TEXT_NODE) {
                String headerContents = txt.getNodeValue();
                foundShh = headerContents.indexOf("SOAPHeaderHandler") >= 0;
            }
        }
        assertTrue(foundShh);
    }
}
