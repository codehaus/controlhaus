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

// original type: ['urn:partner.soap.sforce.com']:setPassword


public  class SetPassword 
  
  implements java.io.Serializable
{

  public SetPassword() {}

  public SetPassword(java.lang.String p_userId
,     java.lang.String p_password) 
  {
     this.userId = p_userId;
     
     this.password = p_password;
     

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
  return "SetPassword" + "{"
             + " userId=<" + getUserId() + ">"
             + " password=<" + getPassword() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof SetPassword) {
      
      SetPassword __obj__ =  (SetPassword) __other__;


      return true
            && (userId==null ? __obj__.getUserId()==null : userId.equals(__obj__.getUserId()))
            && (password==null ? __obj__.getPassword()==null : password.equals(__obj__.getPassword()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (userId==null ? 0 : userId.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (password==null ? 0 : password.hashCode()) ;
    

    return __hash__result__;
  }

}


