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

// original type: ['urn:partner.soap.sforce.com']:GetUpdatedResult


public  class GetUpdatedResult 
  
  implements java.io.Serializable
{

  public GetUpdatedResult() {}

  public GetUpdatedResult(java.lang.String[] p_ids
,     java.lang.String p_sforceReserved) 
  {
     this.ids = p_ids;
     this.__isset_ids = true;

     this.sforceReserved = p_sforceReserved;
     this.__isset_sforceReserved = true;


  }




  
  private java.lang.String[] ids ;

  /**
  <br>  Derived from ids.

  <br>  schema name = ['urn:partner.soap.sforce.com']:ids
  <br>  schema type = ['urn:partner.soap.sforce.com']:GetUpdatedResult__ids__ArrayAnonType
  <br>  schema formQualified = true
  */
  public java.lang.String[] getIds() {
    return ids;
  }

  public void setIds(java.lang.String[] v) {
    
    this.ids = v;
    this.__isset_ids = true;

  }

  private boolean __isset_ids;
  public boolean _isSetIds() {
    return this.__isset_ids;
  }
  public void _unsetIds() {
    this.__isset_ids = false;
  }




  
  private java.lang.String sforceReserved ;

  /**
  <br>  Derived from sforceReserved.

  <br>  schema name = ['urn:partner.soap.sforce.com']:sforceReserved
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getSforceReserved() {
    return sforceReserved;
  }

  public void setSforceReserved(java.lang.String v) {
    
    this.sforceReserved = v;
    this.__isset_sforceReserved = true;

  }

  private boolean __isset_sforceReserved;
  public boolean _isSetSforceReserved() {
    return this.__isset_sforceReserved;
  }
  public void _unsetSforceReserved() {
    this.__isset_sforceReserved = false;
  }









  public java.lang.String toString() {
  return "GetUpdatedResult" + "{"
             + " ids=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getIds() ) + ">"
             + " sforceReserved=<" + getSforceReserved() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof GetUpdatedResult) {
      
      GetUpdatedResult __obj__ =  (GetUpdatedResult) __other__;


      return true
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(ids, __obj__.getIds())
            && (sforceReserved==null ? __obj__.getSforceReserved()==null : sforceReserved.equals(__obj__.getSforceReserved()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (ids==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(ids)) ;
    __hash__result__ = 37*__hash__result__ + (sforceReserved==null ? 0 : sforceReserved.hashCode()) ;
    

    return __hash__result__;
  }

}


