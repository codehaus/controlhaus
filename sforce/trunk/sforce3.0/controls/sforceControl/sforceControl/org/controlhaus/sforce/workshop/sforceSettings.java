/*   Copyright 2004 Salesforce.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
 
package org.controlhaus.sforce.workshop;

public class sforceSettings 
{ 
    /**
     * Control zip file name, identical with control project name
     */    
    public static final String CONTROL_ZIPFILE = "SForceWLWControl.zip";
    /**
     * 
     */
    public static final String SERVER_URL_ANNOTATION_DEFAULT = "https://www.salesforce.com/services/Soap/c/3.0";
    /**
     * Login URL to salesforce.com
     */
    public static final String SFORCE_LOGIN_URL = "https://www.salesforce.com/login.jsp";
    /**
     * Wsdl download url for automatic download
     */
    public static final String GET_WSDL_AUTO_URL = "/soap/wsdl.jsp?type=*";
    /**
     * Wsdl download url for manual download
     */    
    public static final String GET_WSDL_MANUAL_URL = "https://na1.salesforce.com/soap/wsdl.jsp?type=*";    
    /**
     * Sign up url to salesforce.com
     */
    public static final String SIGNUP_URL = "https://www.sforce.com/us/orderEntry/signup.jsp?ide=weblogic_workshop_8.1";
} 
