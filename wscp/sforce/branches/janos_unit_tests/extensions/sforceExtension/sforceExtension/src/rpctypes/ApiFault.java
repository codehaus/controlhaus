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

// original type: ['urn:fault.partner.soap.sforce.com']:fns:ApiFault


public  class ApiFault 
  
  implements java.io.Serializable
{

  public ApiFault() {}

  public ApiFault(rpctypes.ExceptionCode p_exceptionCode
,     java.lang.String p_exceptionMessage) 
  {
     this.exceptionCode = p_exceptionCode;
     
     this.exceptionMessage = p_exceptionMessage;
     

  }




  
  private rpctypes.ExceptionCode exceptionCode ;

  /**
  <br>  Derived from exceptionCode.

  <br>  schema name = ['urn:fault.partner.soap.sforce.com']:exceptionCode
  <br>  schema type = ['urn:fault.partner.soap.sforce.com']:ExceptionCode
  <br>  schema formQualified = true
  */
  public rpctypes.ExceptionCode getExceptionCode() {
    return exceptionCode;
  }

  public void setExceptionCode(rpctypes.ExceptionCode v) {
    
    this.exceptionCode = v;
    
  }





  
  private java.lang.String exceptionMessage ;

  /**
  <br>  Derived from exceptionMessage.

  <br>  schema name = ['urn:fault.partner.soap.sforce.com']:exceptionMessage
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getExceptionMessage() {
    return exceptionMessage;
  }

  public void setExceptionMessage(java.lang.String v) {
    
    this.exceptionMessage = v;
    
  }










  public java.lang.String toString() {
  return "ApiFault" + "{"
             + " exceptionCode=<" + getExceptionCode() + ">"
             + " exceptionMessage=<" + getExceptionMessage() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof ApiFault) {
      
      ApiFault __obj__ =  (ApiFault) __other__;


      return true
            && (exceptionCode==null ? __obj__.getExceptionCode()==null : exceptionCode.equals(__obj__.getExceptionCode()))
            && (exceptionMessage==null ? __obj__.getExceptionMessage()==null : exceptionMessage.equals(__obj__.getExceptionMessage()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (exceptionCode==null ? 0 : exceptionCode.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (exceptionMessage==null ? 0 : exceptionMessage.hashCode()) ;
    

    return __hash__result__;
  }

}


