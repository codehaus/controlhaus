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

// original type: ['urn:partner.soap.sforce.com']:DescribeSObjectResult


public  class DescribeSObjectResult 
  
  implements java.io.Serializable
{

  public DescribeSObjectResult() {}

  public DescribeSObjectResult(boolean p_activateable
,     boolean p_createable
,     boolean p_custom
,     boolean p_deletable
,     rpctypes.Field[] p_fields
,     java.lang.String p_label
,     java.lang.String p_name
,     boolean p_queryable
,     boolean p_retrieveable
,     boolean p_replicateable
,     boolean p_searchable
,     boolean p_updateable
,     boolean p_undeletable) 
  {
     this.activateable = p_activateable;
     
     this.createable = p_createable;
     
     this.custom = p_custom;
     
     this.deletable = p_deletable;
     
     this.fields = p_fields;
     this.__isset_fields = true;

     this.label = p_label;
     
     this.name = p_name;
     
     this.queryable = p_queryable;
     
     this.retrieveable = p_retrieveable;
     
     this.replicateable = p_replicateable;
     
     this.searchable = p_searchable;
     
     this.updateable = p_updateable;
     
     this.undeletable = p_undeletable;
     

  }




  
  private boolean activateable ;

  /**
  <br>  Derived from activateable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:activateable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getActivateable() {
    return activateable;
  }

  public void setActivateable(boolean v) {
    
    this.activateable = v;
    
  }





  
  private boolean createable ;

  /**
  <br>  Derived from createable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:createable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getCreateable() {
    return createable;
  }

  public void setCreateable(boolean v) {
    
    this.createable = v;
    
  }





  
  private boolean custom ;

  /**
  <br>  Derived from custom.

  <br>  schema name = ['urn:partner.soap.sforce.com']:custom
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getCustom() {
    return custom;
  }

  public void setCustom(boolean v) {
    
    this.custom = v;
    
  }





  
  private boolean deletable ;

  /**
  <br>  Derived from deletable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:deletable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getDeletable() {
    return deletable;
  }

  public void setDeletable(boolean v) {
    
    this.deletable = v;
    
  }





  
  private rpctypes.Field[] fields ;

  /**
  <br>  Derived from fields.

  <br>  schema name = ['urn:partner.soap.sforce.com']:fields
  <br>  schema type = ['urn:partner.soap.sforce.com']:DescribeSObjectResult__fields__ArrayAnonType
  <br>  schema formQualified = true
  */
  public rpctypes.Field[] getFields() {
    return fields;
  }

  public void setFields(rpctypes.Field[] v) {
    
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




  
  private java.lang.String label ;

  /**
  <br>  Derived from label.

  <br>  schema name = ['urn:partner.soap.sforce.com']:label
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getLabel() {
    return label;
  }

  public void setLabel(java.lang.String v) {
    
    this.label = v;
    
  }





  
  private java.lang.String name ;

  /**
  <br>  Derived from name.

  <br>  schema name = ['urn:partner.soap.sforce.com']:name
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getName() {
    return name;
  }

  public void setName(java.lang.String v) {
    
    this.name = v;
    
  }





  
  private boolean queryable ;

  /**
  <br>  Derived from queryable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:queryable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getQueryable() {
    return queryable;
  }

  public void setQueryable(boolean v) {
    
    this.queryable = v;
    
  }





  
  private boolean retrieveable ;

  /**
  <br>  Derived from retrieveable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:retrieveable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getRetrieveable() {
    return retrieveable;
  }

  public void setRetrieveable(boolean v) {
    
    this.retrieveable = v;
    
  }





  
  private boolean replicateable ;

  /**
  <br>  Derived from replicateable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:replicateable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getReplicateable() {
    return replicateable;
  }

  public void setReplicateable(boolean v) {
    
    this.replicateable = v;
    
  }





  
  private boolean searchable ;

  /**
  <br>  Derived from searchable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:searchable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getSearchable() {
    return searchable;
  }

  public void setSearchable(boolean v) {
    
    this.searchable = v;
    
  }





  
  private boolean updateable ;

  /**
  <br>  Derived from updateable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:updateable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getUpdateable() {
    return updateable;
  }

  public void setUpdateable(boolean v) {
    
    this.updateable = v;
    
  }





  
  private boolean undeletable ;

  /**
  <br>  Derived from undeletable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:undeletable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getUndeletable() {
    return undeletable;
  }

  public void setUndeletable(boolean v) {
    
    this.undeletable = v;
    
  }










  public java.lang.String toString() {
  return "DescribeSObjectResult" + "{"
             + " activateable=<" + getActivateable() + ">"
             + " createable=<" + getCreateable() + ">"
             + " custom=<" + getCustom() + ">"
             + " deletable=<" + getDeletable() + ">"
             + " fields=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getFields() ) + ">"
             + " label=<" + getLabel() + ">"
             + " name=<" + getName() + ">"
             + " queryable=<" + getQueryable() + ">"
             + " retrieveable=<" + getRetrieveable() + ">"
             + " replicateable=<" + getReplicateable() + ">"
             + " searchable=<" + getSearchable() + ">"
             + " updateable=<" + getUpdateable() + ">"
             + " undeletable=<" + getUndeletable() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof DescribeSObjectResult) {
      
      DescribeSObjectResult __obj__ =  (DescribeSObjectResult) __other__;


      return true
            && (activateable == __obj__.getActivateable())
            && (createable == __obj__.getCreateable())
            && (custom == __obj__.getCustom())
            && (deletable == __obj__.getDeletable())
            && (queryable == __obj__.getQueryable())
            && (retrieveable == __obj__.getRetrieveable())
            && (replicateable == __obj__.getReplicateable())
            && (searchable == __obj__.getSearchable())
            && (updateable == __obj__.getUpdateable())
            && (undeletable == __obj__.getUndeletable())
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(fields, __obj__.getFields())
            && (label==null ? __obj__.getLabel()==null : label.equals(__obj__.getLabel()))
            && (name==null ? __obj__.getName()==null : name.equals(__obj__.getName()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (activateable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (createable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (custom ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (deletable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (fields==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(fields)) ;
    __hash__result__ = 37*__hash__result__ + (label==null ? 0 : label.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (name==null ? 0 : name.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (queryable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (retrieveable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (replicateable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (searchable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (updateable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (undeletable ? 0 : 1) ;
    

    return __hash__result__;
  }

}


