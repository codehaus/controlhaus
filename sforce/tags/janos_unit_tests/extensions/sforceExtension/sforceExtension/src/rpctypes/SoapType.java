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
 * by weblogic.xml.schema.binding.internal.codegen.EnumGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:soapType


public class SoapType
  implements java.io.Serializable
{

  private java.lang.String __value;

  protected SoapType(java.lang.String value) {
    __value = value;
  }

  public static final java.lang.String _value1 = "xsd:date";
  public static final rpctypes.SoapType value1 = new rpctypes.SoapType(_value1);

  public static final java.lang.String _value2 = "xsd:int";
  public static final rpctypes.SoapType value2 = new rpctypes.SoapType(_value2);

  public static final java.lang.String _value3 = "tns:ID";
  public static final rpctypes.SoapType value3 = new rpctypes.SoapType(_value3);

  public static final java.lang.String _value4 = "xsd:dateTime";
  public static final rpctypes.SoapType value4 = new rpctypes.SoapType(_value4);

  public static final java.lang.String _value5 = "xsd:string";
  public static final rpctypes.SoapType value5 = new rpctypes.SoapType(_value5);

  public static final java.lang.String _value6 = "xsd:double";
  public static final rpctypes.SoapType value6 = new rpctypes.SoapType(_value6);

  public static final java.lang.String _value7 = "xsd:boolean";
  public static final rpctypes.SoapType value7 = new rpctypes.SoapType(_value7);

  public static final java.lang.String _value8 = "xsd:base64Binary";
  public static final rpctypes.SoapType value8 = new rpctypes.SoapType(_value8);



  private static final java.util.Map _valueMap = _buildValueMap();


  // Gets the value for a enumerated value
  public java.lang.String getValue() {
    return __value;
  }


  // Gets enumeration with a specific value
  // throws java.lang.IllegalArgumentException if
  // any invalid value is specified
   public static  rpctypes.SoapType fromValue(java.lang.String value) {
    Object obj = _valueMap.get(value);
    if (obj==null) {
      java.lang.String msg = invalidValueMsg(""+value);
    msg=msg+(" valmap="+_valueMap);
      throw new java.lang.IllegalArgumentException(msg);
    }
    return (rpctypes.SoapType)obj;

   }

  private static java.lang.String invalidValueMsg(java.lang.String value) {
    java.lang.String msg = "invalid enumeration value: " + value;
    return msg;
  }


   // Gets enumeration from a String
   // throws java.lang.IllegalArgumentException if
   // any invalid value is specified
   public static rpctypes.SoapType fromString(java.lang.String value) {
    java.lang.String __tmp = 
     __tmp = value;
    return fromValue(__tmp);

   }


   // Returns String representation of the enumerated value
   public java.lang.String toString() {
     return java.lang.String.valueOf(__value);
   }

   public boolean equals(java.lang.Object obj) {
     if (obj instanceof rpctypes.SoapType) {
       java.lang.String tmp_val = ((rpctypes.SoapType) obj).getValue();
       return tmp_val.equals(__value);

     }
     return false;
   }


  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + (__value==null ? 0 : __value.hashCode()) ;
    
    return __hash__result__;
  }

  private java.lang.Object readResolve() throws java.io.ObjectStreamException {
    return fromValue(__value);
  }


  private static java.util.Map _buildValueMap() {
    java.util.Map __valmap = new java.util.HashMap(); 

    __valmap.put( _value1, value1);
    __valmap.put( _value2, value2);
    __valmap.put( _value3, value3);
    __valmap.put( _value4, value4);
    __valmap.put( _value5, value5);
    __valmap.put( _value6, value6);
    __valmap.put( _value7, value7);
    __valmap.put( _value8, value8);


    return __valmap;
  }


}


