/*
 * SOAPHeaderHandler.java
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

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPEnvelope;

import org.apache.axis.message.SOAPHeader;
import org.apache.axis.message.SOAPHeaderElement;

import org.apache.xmlbeans.XmlObject;
import org.w3c.dom.Element;
/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class SOAPHeaderHandler extends GenericHandler {

    private SOAPHeader mIncomingHeader;

    public SOAPHeaderHandler() {}

    public boolean handleRequest(MessageContext mc) {
        
        try {
            mIncomingHeader = (SOAPHeader)((SOAPMessageContext)mc).getMessage()
                .getSOAPPart().getEnvelope().getHeader();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return true;
    }

    public boolean handleResponse(MessageContext mc) {
        try {
            SOAPEnvelope env = ((SOAPMessageContext)mc).getMessage()
                .getSOAPPart().getEnvelope();

            SOAPHeader head = (SOAPHeader)env.getHeader();
            if (head == null) {
                head = (SOAPHeader)env.addHeader();
            }

            head.addChildElement(new SOAPHeaderElement((Element)XmlObject.Factory.parse("<shh>This header added by the SOAPHeaderHandler</shh>").newDomNode().getFirstChild()));

            java.util.Iterator requestHeadIt = mIncomingHeader
                .getChildElements();
            while (requestHeadIt.hasNext()) {
                head.addChildElement(((SOAPHeaderElement)requestHeadIt.next()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public QName[] getHeaders() {
        return new QName[]{new QName("shh"), new QName("anotherone")};
    }
}
