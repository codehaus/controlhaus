/*
 * WSDLFilter.java
 * 
 * Copyright 2004 BEA Systems, Inc. * 
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
package org.controlhaus.webservice.util;                   

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class WSDLFilter implements java.io.FileFilter {


    public boolean accept(java.io.File f) {
        return (f.isFile() 
                && (f.getName().endsWith("wsdl")
                    || f.getName().endsWith("WSDL")));
    }
}