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

/**
 * This code was automatically generated at 2:42:16 AM on May 5, 2004
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:CallOptions


public  class CallOptions 
  
  implements java.io.Serializable
{

  public CallOptions() {}

  public CallOptions(java.lang.String p_client) 
  {
     this.client = p_client;
     

  }




  
  private java.lang.String client ;

  /**
  <br>  Derived from client.

  <br>  schema name = ['urn:partner.soap.sforce.com']:client
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getClient() {
    return client;
  }

  public void setClient(java.lang.String v) {
    
    this.client = v;
    
  }










  public java.lang.String toString() {
  return "CallOptions" + "{"
             + " client=<" + getClient() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof CallOptions) {
      
      CallOptions __obj__ =  (CallOptions) __other__;


      return true
            && (client==null ? __obj__.getClient()==null : client.equals(__obj__.getClient()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (client==null ? 0 : client.hashCode()) ;
    

    return __hash__result__;
  }

}


