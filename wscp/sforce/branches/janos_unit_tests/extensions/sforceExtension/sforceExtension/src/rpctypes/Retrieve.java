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

// original type: ['urn:partner.soap.sforce.com']:retrieve


public  class Retrieve 
  
  implements java.io.Serializable
{

  public Retrieve() {}

  public Retrieve(java.lang.String p_fieldList
,     java.lang.String p_sObjectType
,     java.lang.String[] p_ids) 
  {
     this.fieldList = p_fieldList;
     
     this.sObjectType = p_sObjectType;
     
     this.ids = p_ids;
     

  }




  
  private java.lang.String fieldList ;

  /**
  <br>  Derived from fieldList.

  <br>  schema name = ['urn:partner.soap.sforce.com']:fieldList
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getFieldList() {
    return fieldList;
  }

  public void setFieldList(java.lang.String v) {
    
    this.fieldList = v;
    
  }





  
  private java.lang.String sObjectType ;

  /**
  <br>  Derived from sObjectType.

  <br>  schema name = ['urn:partner.soap.sforce.com']:sObjectType
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getSObjectType() {
    return sObjectType;
  }

  public void setSObjectType(java.lang.String v) {
    
    this.sObjectType = v;
    
  }





  
  private java.lang.String[] ids ;

  /**
  <br>  Derived from ids.

  <br>  schema name = ['urn:partner.soap.sforce.com']:ids
  <br>  schema type = ['urn:partner.soap.sforce.com']:retrieve__ids__ArrayAnonType
  <br>  schema formQualified = true
  */
  public java.lang.String[] getIds() {
    return ids;
  }

  public void setIds(java.lang.String[] v) {
    
    this.ids = v;
    
  }










  public java.lang.String toString() {
  return "Retrieve" + "{"
             + " fieldList=<" + getFieldList() + ">"
             + " sObjectType=<" + getSObjectType() + ">"
             + " ids=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getIds() ) + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof Retrieve) {
      
      Retrieve __obj__ =  (Retrieve) __other__;


      return true
            && (fieldList==null ? __obj__.getFieldList()==null : fieldList.equals(__obj__.getFieldList()))
            && (sObjectType==null ? __obj__.getSObjectType()==null : sObjectType.equals(__obj__.getSObjectType()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(ids, __obj__.getIds())


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (fieldList==null ? 0 : fieldList.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (sObjectType==null ? 0 : sObjectType.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (ids==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(ids)) ;
    

    return __hash__result__;
  }

}


