/*
 * StarwarsInvocationTarget.java
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
 * 
 * Original author: Jonathan Colwell
 */
package org.controlhaus.webservice.invocation;

import java.lang.reflect.Method;

import org.apache.beehive.wsm.jsr181.model.Jsr181TypeMetadata;

import org.controlhaus.webservice.PluggableServiceControl;


/*******************************************************************************
 * An implementation of WebServiceInvocationTarget that can be set to invoke an
 * arbitrary class as long as it has a default constructor.  By default, the 
 * starwars class is used.
 *
 * @author Jonathan Colwell
 */
public class StarwarsInvocationTarget implements WebServiceInvocationTarget {

    public static String cClassName = "starwars";

    public Object invokeWebService(Method method,
                                   Object[] args,
                                   PluggableServiceControl serviceControl,
                                   Jsr181TypeMetadata wstm,
                                   String alternateOperationName)
        throws Exception {

        Class starwarsCls = Class.forName(cClassName);
        Method targetOperation = starwarsCls
            .getMethod(method.getName(), method.getParameterTypes());
        return targetOperation.invoke(starwarsCls.newInstance(), args);
    }
}
