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

// original type: ['urn:partner.soap.sforce.com']:PicklistEntry


public  class PicklistEntry 
  
  implements java.io.Serializable
{

  public PicklistEntry() {}

  public PicklistEntry(boolean p_active
,     boolean p_defaultValue
,     java.lang.String p_label
,     java.lang.String p_value) 
  {
     this.active = p_active;
     
     this.defaultValue = p_defaultValue;
     
     this.label = p_label;
     
     this.value = p_value;
     

  }




  
  private boolean active ;

  /**
  <br>  Derived from active.

  <br>  schema name = ['urn:partner.soap.sforce.com']:active
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getActive() {
    return active;
  }

  public void setActive(boolean v) {
    
    this.active = v;
    
  }





  
  private boolean defaultValue ;

  /**
  <br>  Derived from defaultValue.

  <br>  schema name = ['urn:partner.soap.sforce.com']:defaultValue
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:boolean
  <br>  schema formQualified = true
  */
  public boolean getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(boolean v) {
    
    this.defaultValue = v;
    
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





  
  private java.lang.String value ;

  /**
  <br>  Derived from value.

  <br>  schema name = ['urn:partner.soap.sforce.com']:value
  <br>  schema type = ['http://www.w3.org/2001/XMLSchema']:string
  <br>  schema formQualified = true
  */
  public java.lang.String getValue() {
    return value;
  }

  public void setValue(java.lang.String v) {
    
    this.value = v;
    
  }










  public java.lang.String toString() {
  return "PicklistEntry" + "{"
             + " active=<" + getActive() + ">"
             + " defaultValue=<" + getDefaultValue() + ">"
             + " label=<" + getLabel() + ">"
             + " value=<" + getValue() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof PicklistEntry) {
      
      PicklistEntry __obj__ =  (PicklistEntry) __other__;


      return true
            && (active == __obj__.getActive())
            && (defaultValue == __obj__.getDefaultValue())
            && (label==null ? __obj__.getLabel()==null : label.equals(__obj__.getLabel()))
            && (value==null ? __obj__.getValue()==null : value.equals(__obj__.getValue()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (active ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (defaultValue ? 0 : 1) ;
    __hash__result__ = 37*__hash__result__ + (label==null ? 0 : label.hashCode()) ;
    __hash__result__ = 37*__hash__result__ + (value==null ? 0 : value.hashCode()) ;
    

    return __hash__result__;
  }

}


