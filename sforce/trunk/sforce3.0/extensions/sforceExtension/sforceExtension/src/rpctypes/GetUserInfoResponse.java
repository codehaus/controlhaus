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

// original type: ['urn:partner.soap.sforce.com']:getUserInfoResponse


public  class GetUserInfoResponse 
  
  implements java.io.Serializable
{

  public GetUserInfoResponse() {}

  public GetUserInfoResponse(rpctypes.GetUserInfoResult p_result) 
  {
     this.result = p_result;
     

  }




  
  private rpctypes.GetUserInfoResult result ;

  /**
  <br>  Derived from result.

  <br>  schema name = ['urn:partner.soap.sforce.com']:result
  <br>  schema type = ['urn:partner.soap.sforce.com']:GetUserInfoResult
  <br>  schema formQualified = true
  */
  public rpctypes.GetUserInfoResult getResult() {
    return result;
  }

  public void setResult(rpctypes.GetUserInfoResult v) {
    
    this.result = v;
    
  }










  public java.lang.String toString() {
  return "GetUserInfoResponse" + "{"
             + " result=<" + getResult() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof GetUserInfoResponse) {
      
      GetUserInfoResponse __obj__ =  (GetUserInfoResponse) __other__;


      return true
            && (result==null ? __obj__.getResult()==null : result.equals(__obj__.getResult()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (result==null ? 0 : result.hashCode()) ;
    

    return __hash__result__;
  }

}


