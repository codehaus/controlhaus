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

// original type: ['urn:partner.soap.sforce.com']:DeleteResult


public  class DeleteResult 
  
  implements java.io.Serializable
{

  public DeleteResult() {}

  public DeleteResult(rpctypes.Error[] p_errors
,     java.lang.String p_id
,     boolean p_success) 
  {
     this.errors = p_errors;
     this.__isset_errors = true;

     this.id = p_id;
     
     this.success = p_success;
     

  }




  
  private rpctypes.Error[] errors ;

  /**
  <br>  Derived from errors.

  <br>  schema name = ['urn:partner.soap.sforce.com']:errors
  <br>  schema type = ['urn:partner.soap.sforce.com']:DeleteResult__errors__ArrayAnonType
  <br>  schema formQualified = true
  */
  public rpctypes.Error[] getErrors() {
    return errors;
  }

  public void setErrors(rpctypes.Error[] v) {
    
    this.errors = v;
    this.__isset_errors = true;

  }

  private boolean __isset_errors;
  public boolean _isSetErrors() {
    return this.__isset_errors;
  }
  public void _unsetErrors() {
    this.__isset_errors = false;
  }




  
  private java.lang.String id ;

  /**
  <br>  Derived from id.

  <br>  schema name = ['urn:partner.soap.sforce.com']:id
  <br>  schema type = ['urn:partner.soap.sforce.com']:ID
  <br>  schema formQualified = true
  */
  public java.lang.String getId() {
    return id;
  }

  public void setId(java.lang.String v) {
    
    this.id = v;
    
  }





  
  private boolean success ;

  /**
  <br>  Derived from success.

  <br>  schema name = ['urn:partner.soap.sforce.com']:success
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getSuccess() {
    return success;
  }

  public void setSuccess(boolean v) {
    
    this.success = v;
    
  }










  public java.lang.String toString() {
  return "DeleteResult" + "{"
             + " errors=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getErrors() ) + ">"
             + " id=<" + getId() + ">"
             + " success=<" + getSuccess() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof DeleteResult) {
      
      DeleteResult __obj__ =  (DeleteResult) __other__;


      return true
            && (success == __obj__.getSuccess())
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(errors, __obj__.getErrors())
            && (id==null ? __obj__.getId()==null : id.equals(__obj__.getId()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (errors==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(errors)) ;
    __hash__result__ = 37*__hash__result__ + (id==null ? 0 : id.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (success ? 0 : 1) ;
    

    return __hash__result__;
  }

}


