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

// original type: ['urn:partner.soap.sforce.com']:Error


public  class Error 
  
  implements java.io.Serializable
{

  public Error() {}

  public Error(java.lang.String[] p_fields
,     java.lang.String p_message
,     rpctypes.StatusCode p_statusCode) 
  {
     this.fields = p_fields;
     this.__isset_fields = true;

     this.message = p_message;
     
     this.statusCode = p_statusCode;
     

  }




  
  private java.lang.String[] fields ;

  /**
  <br>  Derived from fields.

  <br>  schema name = ['urn:partner.soap.sforce.com']:fields
  <br>  schema type = ['urn:partner.soap.sforce.com']:Error__fields__ArrayAnonType
  <br>  schema formQualified = true
  */
  public java.lang.String[] getFields() {
    return fields;
  }

  public void setFields(java.lang.String[] v) {
    
    this.fields = v;
    this.__isset_fields = true;

  }

  private boolean __isset_fields;
  public boolean _isSetFields() {
    return this.__isset_fields;
  }
  public void _unsetFields() {
    this.__isset_fields = false;
  }




  
  private java.lang.String message ;

  /**
  <br>  Derived from message.

  <br>  schema name = ['urn:partner.soap.sforce.com']:message
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getMessage() {
    return message;
  }

  public void setMessage(java.lang.String v) {
    
    this.message = v;
    
  }





  
  private rpctypes.StatusCode statusCode ;

  /**
  <br>  Derived from statusCode.

  <br>  schema name = ['urn:partner.soap.sforce.com']:statusCode
  <br>  schema type = ['urn:partner.soap.sforce.com']:StatusCode
  <br>  schema formQualified = true
  */
  public rpctypes.StatusCode getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(rpctypes.StatusCode v) {
    
    this.statusCode = v;
    
  }










  public java.lang.String toString() {
  return "Error" + "{"
             + " fields=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getFields() ) + ">"
             + " message=<" + getMessage() + ">"
             + " statusCode=<" + getStatusCode() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof Error) {
      
      Error __obj__ =  (Error) __other__;


      return true
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(fields, __obj__.getFields())
            && (message==null ? __obj__.getMessage()==null : message.equals(__obj__.getMessage()))
            && (statusCode==null ? __obj__.getStatusCode()==null : statusCode.equals(__obj__.getStatusCode()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (fields==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(fields)) ;
    __hash__result__ = 37*__hash__result__ + (message==null ? 0 : message.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (statusCode==null ? 0 : statusCode.hashCode()) ;
    

    return __hash__result__;
  }

}


