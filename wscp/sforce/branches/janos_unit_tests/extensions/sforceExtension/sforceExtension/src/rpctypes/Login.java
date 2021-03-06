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

// original type: ['urn:partner.soap.sforce.com']:login


public  class Login 
  
  implements java.io.Serializable
{

  public Login() {}

  public Login(java.lang.String p_username
,     java.lang.String p_password) 
  {
     this.username = p_username;
     
     this.password = p_password;
     

  }




  
  private java.lang.String username ;

  /**
  <br>  Derived from username.

  <br>  schema name = ['urn:partner.soap.sforce.com']:username
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getUsername() {
    return username;
  }

  public void setUsername(java.lang.String v) {
    
    this.username = v;
    
  }





  
  private java.lang.String password ;

  /**
  <br>  Derived from password.

  <br>  schema name = ['urn:partner.soap.sforce.com']:password
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getPassword() {
    return password;
  }

  public void setPassword(java.lang.String v) {
    
    this.password = v;
    
  }










  public java.lang.String toString() {
  return "Login" + "{"
             + " username=<" + getUsername() + ">"
             + " password=<" + getPassword() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof Login) {
      
      Login __obj__ =  (Login) __other__;


      return true
            && (username==null ? __obj__.getUsername()==null : username.equals(__obj__.getUsername()))
            && (password==null ? __obj__.getPassword()==null : password.equals(__obj__.getPassword()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (username==null ? 0 : username.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (password==null ? 0 : password.hashCode()) ;
    

    return __hash__result__;
  }

}


