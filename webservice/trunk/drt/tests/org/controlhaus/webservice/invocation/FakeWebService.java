/*
 * FakeWebService.java
 * 
 * Copyright 2001-2004 The Apache Software Foundation.
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
 */
package org.controlhaus.webservice.invocation;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * ****************************************************************************
 * NOTE jcolwell@bea.com 2004-Sep-21 --
 * Add more methods and annotations to provide a thorough test of the
 * AXIS JSR-181 implementation.
 *
 * @author Jonathan Colwell
 */
@WebService(name = "FakeName",
        serviceName = "FakeServiceName",
        targetNamespace = "http://fake.target.namespace/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class FakeWebService {

    @WebMethod(action = "invocationAction")
    @WebResult(name = "invocationSucceeded",
            targetNamespace = "http://result.target.namespace/")
    public String doInvocation() {
        return "invocation succeeded";
    }
}
