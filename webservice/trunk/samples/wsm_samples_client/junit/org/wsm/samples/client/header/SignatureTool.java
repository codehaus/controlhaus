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
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;


interface SignatureTool {
    /**
     * @return Result of signer signing content.
     */
    public SOAPElement getSignature(String signersName) //, SOAPElement content) 
    throws SOAPException;


    /**
     * @return true if content was signed, unchanged, by signer of signature.
     * This is only true if the content is the same content signed originally
     * by a signer, resulting in signature.
     */
    public boolean isSignatureValid(SOAPElement signature) //, SOAPElement content) 
    throws SOAPException;
}
