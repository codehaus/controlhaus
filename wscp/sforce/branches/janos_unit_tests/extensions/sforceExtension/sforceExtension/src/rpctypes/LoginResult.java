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
 * This code was automatically generated at 2:42:15 AM on May 5, 2004
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:LoginResult


public  class LoginResult 
  
  implements java.io.Serializable
{

  public LoginResult() {}

  public LoginResult(java.lang.String p_serverUrl
,     java.lang.String p_sessionId
,     java.lang.String p_userId) 
  {
     this.serverUrl = p_serverUrl;
     
     this.sessionId = p_sessionId;
     
     this.userId = p_userId;
     

  }




  
  private java.lang.String serverUrl ;

  /**
  <br>  Derived from serverUrl.

  <br>  schema name = ['urn:partner.soap.sforce.com']:serverUrl
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getServerUrl() {
    return serverUrl;
  }

  public void setServerUrl(java.lang.String v) {
    
    this.serverUrl = v;
    
  }





  
  private java.lang.String sessionId ;

  /**
  <br>  Derived from sessionId.

  <br>  schema name = ['urn:partner.soap.sforce.com']:sessionId
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getSessionId() {
    return sessionId;
  }

  public void setSessionId(java.lang.String v) {
    
    this.sessionId = v;
    
  }





  
  private java.lang.String userId ;

  /**
  <br>  Derived from userId.

  <br>  schema name = ['urn:partner.soap.sforce.com']:userId
  <br>  schema type = ['urn:partner.soap.sforce.com']:ID
  <br>  schema formQualified = true
  */
  public java.lang.String getUserId() {
    return userId;
  }

  public void setUserId(java.lang.String v) {
    
    this.userId = v;
    
  }










  public java.lang.String toString() {
  return "LoginResult" + "{"
             + " serverUrl=<" + getServerUrl() + ">"
             + " sessionId=<" + getSessionId() + ">"
             + " userId=<" + getUserId() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof LoginResult) {
      
      LoginResult __obj__ =  (LoginResult) __other__;


      return true
            && (serverUrl==null ? __obj__.getServerUrl()==null : serverUrl.equals(__obj__.getServerUrl()))
            && (sessionId==null ? __obj__.getSessionId()==null : sessionId.equals(__obj__.getSessionId()))
            && (userId==null ? __obj__.getUserId()==null : userId.equals(__obj__.getUserId()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (serverUrl==null ? 0 : serverUrl.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (sessionId==null ? 0 : sessionId.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (userId==null ? 0 : userId.hashCode()) ;
    

    return __hash__result__;
  }

}


