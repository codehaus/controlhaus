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
package org.controlhaus.webservice.jaxrpc;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.Handler;
import javax.xml.rpc.handler.HandlerInfo;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

public class HeaderHandler implements Handler {

    static Logger logger = Logger.getLogger(HeaderHandler.class);

    static ThreadLocal localInHeaders = new ThreadLocal();

    static ThreadLocal localOutHeaders = new ThreadLocal();

    /**
     * Sign outgoing request message.
     * 
     * @throws SOAPException
     */
    public boolean handleRequest(MessageContext mc) {
        System.out.println("In HeaderHandler's handleRequest myOutHeaders: "
                + localOutHeaders.get());

        try {
            SOAPMessageContext smc = (SOAPMessageContext) mc;
            SOAPMessage msg = smc.getMessage();
            logger.debug("Request message body: "
                    + msg.getSOAPBody().toString() + "\n header: "
                    + msg.getSOAPHeader().toString());
           SOAPPart part = msg.getSOAPPart();
            SOAPEnvelope envelope = part.getEnvelope();
            SOAPHeader header = envelope.getHeader();

            /**
             * Create new header element. We don't specify a role on this header
             * element, meaning the target role is the "ultimate destination".
             */
            Element[] elemsToAdd = (Element[]) localOutHeaders.get();

            if (null == elemsToAdd) {
                logger.debug("no header to send");
                return true;
            }
            for (Element nxtElement : elemsToAdd) {
                header
                        .addChildElement(new org.apache.axis.message.SOAPHeaderElement(
                                nxtElement)); // NOTE: Axis specific
            }

        } catch (SOAPException e) {
            e.printStackTrace();
            logger.error("Failed to add header.", e);
        }

        return true;
    }

    /**
     * 
     */
    static public void reset() {
        localInHeaders = new ThreadLocal();
        localOutHeaders = new ThreadLocal();

    }

    /**
     * Check incoming response message.
     */
    public boolean handleResponse(MessageContext mc) {
        System.out.println("In HeaderHandler's handleResponse");

        try {
            SOAPMessageContext smc = (SOAPMessageContext) mc;
            SOAPMessage msg = smc.getMessage();
            logger.debug("Response message body: "
                    + msg.getSOAPBody().toString() + "\n header: "
                    + msg.getSOAPHeader().toString());
            SOAPPart part = msg.getSOAPPart();
            SOAPEnvelope envelope = part.getEnvelope();
            SOAPHeader header = envelope.getHeader();
            if (header == null)
                return true;

            Iterator it = header.extractAllHeaderElements();
            ArrayList<Element> list = new ArrayList<Element>();
            while (it.hasNext()) {
                SOAPElement nxtSoapElement = (SOAPElement) it.next();
                list.add((Element) nxtSoapElement.cloneNode(true));
            }

            Element[] elems = (Element[]) list
                    .toArray(new Element[list.size()]); // Use generics
            localInHeaders.set(elems);

        } catch (SOAPException e) {
            e.printStackTrace();
            logger.error("Error extracting the header.", e);
        }

        logger.debug("myInHeaders: " + localInHeaders.get());
        return true;
    }

    /**
     * Check incoming response message.
     */
    public boolean handleFault(MessageContext mc) {
        System.out.println("In HeaderHandler's handleFault");
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.xml.rpc.handler.Handler#init(javax.xml.rpc.handler.HandlerInfo)
     */
    public void init(HandlerInfo arg0) {
        System.out.println("In HeaderHandler's init");

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.xml.rpc.handler.Handler#destroy()
     */
    public void destroy() {
        logger.debug("In HeaderHandler's destroy");

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.xml.rpc.handler.Handler#getHeaders()
     */
    public QName[] getHeaders() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return Returns the inHeaders.
     */
    static public Element[] getInHeaders() {
        return (Element[]) localInHeaders.get();
    }

    /**
     * @param outHeaders
     *            The outHeaders to set.
     */
    static public void setOutHeaders(Element[] outHeaders) {
        localOutHeaders.set(outHeaders);
    }
}