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

// original type: ['urn:partner.soap.sforce.com']:Field


public  class Field 
  
  implements java.io.Serializable
{

  public Field() {}

  public Field(int p_byteLength
,     boolean p_createable
,     boolean p_custom
,     int p_digits
,     boolean p_filterable
,     java.lang.String p_label
,     int p_length
,     java.lang.String p_name
,     boolean p_nameField
,     boolean p_nillable
,     rpctypes.PicklistEntry[] p_picklistValues
,     int p_precision
,     java.lang.String[] p_referenceTo
,     boolean p_restrictedPicklist
,     int p_scale
,     rpctypes.SoapType p_soapType
,     rpctypes.FieldType p_type
,     boolean p_updateable) 
  {
     this.byteLength = p_byteLength;
     
     this.createable = p_createable;
     
     this.custom = p_custom;
     
     this.digits = p_digits;
     
     this.filterable = p_filterable;
     
     this.label = p_label;
     
     this.length = p_length;
     
     this.name = p_name;
     
     this.nameField = p_nameField;
     
     this.nillable = p_nillable;
     
     this.picklistValues = p_picklistValues;
     this.__isset_picklistValues = true;

     this.precision = p_precision;
     
     this.referenceTo = p_referenceTo;
     this.__isset_referenceTo = true;

     this.restrictedPicklist = p_restrictedPicklist;
     
     this.scale = p_scale;
     
     this.soapType = p_soapType;
     
     this.type = p_type;
     
     this.updateable = p_updateable;
     

  }




  
  private int byteLength ;

  /**
  <br>  Derived from byteLength.

  <br>  schema name = ['urn:partner.soap.sforce.com']:byteLength
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public int getByteLength() {
    return byteLength;
  }

  public void setByteLength(int v) {
    
    this.byteLength = v;
    
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





  
  private int digits ;

  /**
  <br>  Derived from digits.

  <br>  schema name = ['urn:partner.soap.sforce.com']:digits
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public int getDigits() {
    return digits;
  }

  public void setDigits(int v) {
    
    this.digits = v;
    
  }





  
  private boolean filterable ;

  /**
  <br>  Derived from filterable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:filterable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getFilterable() {
    return filterable;
  }

  public void setFilterable(boolean v) {
    
    this.filterable = v;
    
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





  
  private int length ;

  /**
  <br>  Derived from length.

  <br>  schema name = ['urn:partner.soap.sforce.com']:length
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public int getLength() {
    return length;
  }

  public void setLength(int v) {
    
    this.length = v;
    
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





  
  private boolean nameField ;

  /**
  <br>  Derived from nameField.

  <br>  schema name = ['urn:partner.soap.sforce.com']:nameField
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getNameField() {
    return nameField;
  }

  public void setNameField(boolean v) {
    
    this.nameField = v;
    
  }





  
  private boolean nillable ;

  /**
  <br>  Derived from nillable.

  <br>  schema name = ['urn:partner.soap.sforce.com']:nillable
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getNillable() {
    return nillable;
  }

  public void setNillable(boolean v) {
    
    this.nillable = v;
    
  }





  
  private rpctypes.PicklistEntry[] picklistValues ;

  /**
  <br>  Derived from picklistValues.

  <br>  schema name = ['urn:partner.soap.sforce.com']:picklistValues
  <br>  schema type = ['urn:partner.soap.sforce.com']:Field__picklistValues__ArrayAnonType
  <br>  schema formQualified = true
  */
  public rpctypes.PicklistEntry[] getPicklistValues() {
    return picklistValues;
  }

  public void setPicklistValues(rpctypes.PicklistEntry[] v) {
    
    this.picklistValues = v;
    this.__isset_picklistValues = true;

  }

  private boolean __isset_picklistValues;
  public boolean _isSetPicklistValues() {
    return this.__isset_picklistValues;
  }
  public void _unsetPicklistValues() {
    this.__isset_picklistValues = false;
  }




  
  private int precision ;

  /**
  <br>  Derived from precision.

  <br>  schema name = ['urn:partner.soap.sforce.com']:precision
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public int getPrecision() {
    return precision;
  }

  public void setPrecision(int v) {
    
    this.precision = v;
    
  }





  
  private java.lang.String[] referenceTo ;

  /**
  <br>  Derived from referenceTo.

  <br>  schema name = ['urn:partner.soap.sforce.com']:referenceTo
  <br>  schema type = ['urn:partner.soap.sforce.com']:Field__referenceTo__ArrayAnonType
  <br>  schema formQualified = true
  */
  public java.lang.String[] getReferenceTo() {
    return referenceTo;
  }

  public void setReferenceTo(java.lang.String[] v) {
    
    this.referenceTo = v;
    this.__isset_referenceTo = true;

  }

  private boolean __isset_referenceTo;
  public boolean _isSetReferenceTo() {
    return this.__isset_referenceTo;
  }
  public void _unsetReferenceTo() {
    this.__isset_referenceTo = false;
  }




  
  private boolean restrictedPicklist ;

  /**
  <br>  Derived from restrictedPicklist.

  <br>  schema name = ['urn:partner.soap.sforce.com']:restrictedPicklist
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getRestrictedPicklist() {
    return restrictedPicklist;
  }

  public void setRestrictedPicklist(boolean v) {
    
    this.restrictedPicklist = v;
    
  }





  
  private int scale ;

  /**
  <br>  Derived from scale.

  <br>  schema name = ['urn:partner.soap.sforce.com']:scale
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:int
  <br>  schema formQualified = true
  */
  public int getScale() {
    return scale;
  }

  public void setScale(int v) {
    
    this.scale = v;
    
  }





  
  private rpctypes.SoapType soapType ;

  /**
  <br>  Derived from soapType.

  <br>  schema name = ['urn:partner.soap.sforce.com']:soapType
  <br>  schema type = ['urn:partner.soap.sforce.com']:soapType
  <br>  schema formQualified = true
  */
  public rpctypes.SoapType getSoapType() {
    return soapType;
  }

  public void setSoapType(rpctypes.SoapType v) {
    
    this.soapType = v;
    
  }





  
  private rpctypes.FieldType type ;

  /**
  <br>  Derived from type.

  <br>  schema name = ['urn:partner.soap.sforce.com']:type
  <br>  schema type = ['urn:partner.soap.sforce.com']:fieldType
  <br>  schema formQualified = true
  */
  public rpctypes.FieldType getType() {
    return type;
  }

  public void setType(rpctypes.FieldType v) {
    
    this.type = v;
    
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










  public java.lang.String toString() {
  return "Field" + "{"
             + " byteLength=<" + getByteLength() + ">"
             + " createable=<" + getCreateable() + ">"
             + " custom=<" + getCustom() + ">"
             + " digits=<" + getDigits() + ">"
             + " filterable=<" + getFilterable() + ">"
             + " label=<" + getLabel() + ">"
             + " length=<" + getLength() + ">"
             + " name=<" + getName() + ">"
             + " nameField=<" + getNameField() + ">"
             + " nillable=<" + getNillable() + ">"
             + " picklistValues=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getPicklistValues() ) + ">"
             + " precision=<" + getPrecision() + ">"
             + " referenceTo=<" + weblogic.xml.schema.binding.RuntimeUtils.arrayToString( getReferenceTo() ) + ">"
             + " restrictedPicklist=<" + getRestrictedPicklist() + ">"
             + " scale=<" + getScale() + ">"
             + " soapType=<" + getSoapType() + ">"
             + " type=<" + getType() + ">"
             + " updateable=<" + getUpdateable() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof Field) {
      
      Field __obj__ =  (Field) __other__;


      return true
            && (byteLength == __obj__.getByteLength())
            && (createable == __obj__.getCreateable())
            && (custom == __obj__.getCustom())
            && (digits == __obj__.getDigits())
            && (filterable == __obj__.getFilterable())
            && (length == __obj__.getLength())
            && (nameField == __obj__.getNameField())
            && (nillable == __obj__.getNillable())
            && (precision == __obj__.getPrecision())
            && (restrictedPicklist == __obj__.getRestrictedPicklist())
            && (scale == __obj__.getScale())
            && (updateable == __obj__.getUpdateable())
            && (label==null ? __obj__.getLabel()==null : label.equals(__obj__.getLabel()))
            && (name==null ? __obj__.getName()==null : name.equals(__obj__.getName()))
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(picklistValues, __obj__.getPicklistValues())
            && weblogic.xml.schema.binding.RuntimeUtils.compareObjects(referenceTo, __obj__.getReferenceTo())
            && (soapType==null ? __obj__.getSoapType()==null : soapType.equals(__obj__.getSoapType()))
            && (type==null ? __obj__.getType()==null : type.equals(__obj__.getType()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + byteLength ;
    __hash__result__ = 37*__hash__result__ + (createable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (custom ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + digits ;
    __hash__result__ = 37*__hash__result__ + (filterable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (label==null ? 0 : label.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + length ;
    __hash__result__ = 37*__hash__result__ + (name==null ? 0 : name.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (nameField ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (nillable ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (picklistValues==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(picklistValues)) ;
    __hash__result__ = 37*__hash__result__ + precision ;
    __hash__result__ = 37*__hash__result__ + (referenceTo==null ? 0 : weblogic.xml.schema.binding.RuntimeUtils.arrayHashCode(referenceTo)) ;
    __hash__result__ = 37*__hash__result__ + (restrictedPicklist ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + scale ;
    __hash__result__ = 37*__hash__result__ + (soapType==null ? 0 : soapType.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (type==null ? 0 : type.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (updateable ? 0 : 1) ;
    

    return __hash__result__;
  }

}


