package org.wsm.samples.client.header;

/*
 * Copyright 2004, 2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Original author: Daryoush Mehrtash
 * Note:  This sample was developed based on the example in the article:
 * http://www-128.ibm.com/developerworks/webservices/library/ws-tip-extend/
 */
import java.util.Iterator;

import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.Name;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.Text;




/**
 * Produce signature of the form: <simpleSignature><signer>... </signer>
 * <seal>... </seal> </simpleSignature>
 */
public class SignatureUtilities {
    private static final SOAPFactory factory;

    private static final String SIGNATURE_NS = "uri://org.example.webservices.signature.SimpleSignature";

    private static final String SIGNATURE_NS_PREFIX = "ssig";

    private static final String SIGNATURE_ELEMENT = "signature";

    private static final String SIGNER_ELEMENT = "signer";

    private static final String SEAL_ELEMENT = "seal";

    private static final Name SIGNATURE_NAME;

    private static final Name SIGNER_NAME;

    private static final Name SEAL_NAME;

    static {
        try {
            factory = SOAPFactory.newInstance();

            SIGNATURE_NAME = factory.createName(SIGNATURE_ELEMENT,
                    SIGNATURE_NS_PREFIX, SIGNATURE_NS);

            SIGNER_NAME = factory.createName(SIGNER_ELEMENT);

            SEAL_NAME = factory.createName(SEAL_ELEMENT);
        } catch (SOAPException e) {
            e.printStackTrace();
            throw new SignException("SignatureToolImpl init failed", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.example.webservices.signature.SignatureTool#getSignature(java.lang.String,
     *      javax.xml.soap.SOAPElement)
     */
    static public SOAPElement getSignature(String signersName, String sealValue) // ,
                                                                            // SOAPElement
            // content)
            throws SOAPException {
        SOAPElement sig = factory.createElement(SIGNATURE_NAME);

        SOAPElement signer = sig.addChildElement(SIGNER_NAME);
        signer.addTextNode(signersName);

        SOAPElement seal = sig.addChildElement(SEAL_NAME);
        seal.addTextNode(sealValue);

        return sig;
    }

    /* (non-Javadoc)
     * @see org.example.webservices.signature.SignatureTool#isSignatureValid(javax.xml.soap.SOAPElement, javax.xml.soap.SOAPElement)
     */
    static public boolean isValidSignature(SOAPElement signature) //, SOAPElement content)
        throws SOAPException
    {
        
        signature = (SOAPElement) signature.getChildElements().next();
        
        System.out.println("Testing signature");
        if (!signature.getElementName().equals(SIGNATURE_NAME)) {
            System.out.println("Failed signature test... SIGNATURE_NAME doesn't exist.");
           throw new SignException("Unexpected element.  got \"" +
                                    signature.getElementName() +
                                    "\", expected \"" +
                                    SIGNATURE_NAME + "\"");
        }
        
        Iterator signerIter = signature.getChildElements(SIGNER_NAME);
        if (!signerIter.hasNext()) {
            System.out.println("Failed signature test... SIGNATURE_NAME doesn't have any child");
           throw new SignException("Expected element missing.  expected \"" +
                                    SIGNER_NAME + "\"");
        }
        SOAPElement signer = (SOAPElement)signerIter.next();
        
        Iterator sealIter = signature.getChildElements(SEAL_NAME);
        if (!sealIter.hasNext()) {
            System.out.println("Failed signature test... SIGNATURE_NAME doesn't have SEAL_NAME");
             throw new SignException("Expected element missing.  expected \"" +
                                    SEAL_NAME + "\"");
        }
        SOAPElement seal = (SOAPElement)sealIter.next();

        String signersName = ((Node)(signer.getChildElements().next())).getValue();
 //       String strContent = getContent(content);
        String signedContent = signersName;  //, strContent);
        
        String sealedContent = ((Node)(seal.getChildElements().next())).getValue();

        System.out.println("isSignatureValid: expected= " + signedContent);
        System.out.println("isSignatureValid: received= " + sealedContent);

        if (!signedContent.equals(sealedContent)) {
            System.out.println("Signatures don't match!");
             throw new SignException("Signed document does not match signature");
        }

        System.out.println("signatures match!");
        return true;
    }
    
//    static public boolean isValidSignature(SOAPElement root) throws SOAPException {
//        /* format of the header should match
//        <sign:sign xxxxx>
//           <ssig:signature >xxxx </signature>
//           <ssig:seal>xxxx</seal>
//        </sign:sing>
//        */
//        
//        //TODO: add validation to make sure the root is sign:sign
//        SOAPElement signature = (SOAPElement) root.getChildElements().next();
//        System.out.println("Testing signature");
//        if (!signature.getElementName().equals(SIGNATURE_NAME)) {
//            System.out
//                    .println("Failed signature test... SIGNATURE_NAME doesn't exist.");
//            throw new SignException("Unexpected element.  got \""
//                    + signature.getElementName() + "\", expected \""
//                    + SIGNATURE_NAME + "\"");
//        }
//
//        Iterator signerIter = signature.getChildElements(SIGNER_NAME);
//
//        if (!signerIter.hasNext()) {
//            System.out
//                    .println("Failed signature test... SIGNATURE_NAME doesn't have any child");
//            throw new SignException("Expected element missing.  expected \""
//                    + SIGNER_NAME + "\"");
//        }
//        SOAPElement signer = (SOAPElement) signerIter.next();
//        System.out.println("++++++++++signaer node name: " + signer.getNodeName() + " type: " + signer.getNodeType());
//        String signerName = signer.getValue();
//
//        Iterator sealIter = signature.getChildElements(SEAL_NAME);
//        if (!sealIter.hasNext()) {
//            System.out
//                    .println("Failed signature test... SIGNATURE_NAME doesn't have SEAL_NAME");
//            throw new SignException("Expected element missing.  expected \""
//                    + SEAL_NAME + "\"");
//        }
//        SOAPElement seal = (SOAPElement) sealIter.next();
//        System.out.println("++++++++++seal node name: " + seal.getNodeName() + " type: " + seal.getNodeType());
//       String sealValue = seal.getValue();
//
//        if(sealValue == null || signerName == null ) {
//            System.out.println(" Null is not a valid seal, or singer name.  seal: " + sealValue + " signerName: " + signerName);
//            return false;
//        }
//        return sealValue.equals(signerName);
//
//    }
//
//    

    private String elementName(SOAPElement e) {
        return elementName(e.getElementName());
    }

    private String elementName(Name name) {
        return "{" + name.getURI() + "}" + name.getLocalName();
    }

}
