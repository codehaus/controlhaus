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
 * This code was automatically generated at 2:42:22 AM on May 5, 2004
 * by weblogic.xml.schema.binding.internal.codegen.BeanCodecGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:Field


public final class FieldCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "urn:partner.soap.sforce.com" , "Field" );

  private static final java.lang.String JAVA_TYPE = 
     "rpctypes.Field";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['urn:partner.soap.sforce.com']:byteLength
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:int
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","byteLength",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","int",null),
                                                            "byteLength",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['urn:partner.soap.sforce.com']:createable
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","createable",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "createable",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['urn:partner.soap.sforce.com']:custom
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","custom",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "custom",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['urn:partner.soap.sforce.com']:digits
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:int
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","digits",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","int",null),
                                                            "digits",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['urn:partner.soap.sforce.com']:filterable
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","filterable",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "filterable",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['urn:partner.soap.sforce.com']:label
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","label",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "label",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['urn:partner.soap.sforce.com']:length
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:int
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","length",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","int",null),
                                                            "length",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['urn:partner.soap.sforce.com']:name
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","name",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "name",
                                                            java.lang.String.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['urn:partner.soap.sforce.com']:nameField
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","nameField",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "nameField",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['urn:partner.soap.sforce.com']:nillable
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","nillable",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "nillable",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = rpctypes
  //class   name = [Lrpctypes.PicklistEntry;
  //java    type = [Lrpctypes.PicklistEntry;
  //schema  name = ['urn:partner.soap.sforce.com']:picklistValues
  //schema  type = ['urn:partner.soap.sforce.com']:Field__picklistValues__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","picklistValues",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","Field__picklistValues__ArrayAnonType",null),
                                                            "picklistValues",
                                                            rpctypes.PicklistEntry[].class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['urn:partner.soap.sforce.com']:precision
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:int
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","precision",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","int",null),
                                                            "precision",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = [Ljava.lang.String;
  //java    type = [Ljava.lang.String;
  //schema  name = ['urn:partner.soap.sforce.com']:referenceTo
  //schema  type = ['urn:partner.soap.sforce.com']:Field__referenceTo__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","referenceTo",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","Field__referenceTo__ArrayAnonType",null),
                                                            "referenceTo",
                                                            java.lang.String[].class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['urn:partner.soap.sforce.com']:restrictedPicklist
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","restrictedPicklist",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "restrictedPicklist",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = int
  //java    type = int
  //schema  name = ['urn:partner.soap.sforce.com']:scale
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:int
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","scale",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","int",null),
                                                            "scale",
                                                            int.class,
                                                            false,
                                                            true),


  //package name = rpctypes
  //class   name = SoapType
  //java    type = rpctypes.SoapType
  //schema  name = ['urn:partner.soap.sforce.com']:soapType
  //schema  type = ['urn:partner.soap.sforce.com']:soapType
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","soapType",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","soapType",null),
                                                            "soapType",
                                                            rpctypes.SoapType.class,
                                                            false,
                                                            true),


  //package name = rpctypes
  //class   name = FieldType
  //java    type = rpctypes.FieldType
  //schema  name = ['urn:partner.soap.sforce.com']:type
  //schema  type = ['urn:partner.soap.sforce.com']:fieldType
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","type",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","fieldType",null),
                                                            "type",
                                                            rpctypes.FieldType.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['urn:partner.soap.sforce.com']:updateable
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","updateable",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "updateable",
                                                            boolean.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new rpctypes.Field();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    rpctypes.Field typed_obj = (rpctypes.Field) my_obj;

    switch(idx) {

    case 10:
      return typed_obj._isSetPicklistValues();
    case 12:
      return typed_obj._isSetReferenceTo();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    rpctypes.Field typed_obj = (rpctypes.Field) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    rpctypes.Field typed_obj = (rpctypes.Field) my_obj;
    typedInvokeSetter(typed_obj, idx, setter_arg);
  }

  public int getPropertyCount()
  {
    return (_SUPER_PROP_COUNT + PROPS.length);
  }

  public weblogic.xml.schema.binding.util.runtime.PropertyInfo getPropertyInfo(int idx)
  {

    return PROPS[idx];
  }


  private static java.lang.Object typedInvokeGetter(rpctypes.Field my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = new java.lang.Integer(my_obj.getByteLength());
      break;
    case 1:
      obj = new java.lang.Boolean(my_obj.getCreateable());
      break;
    case 2:
      obj = new java.lang.Boolean(my_obj.getCustom());
      break;
    case 3:
      obj = new java.lang.Integer(my_obj.getDigits());
      break;
    case 4:
      obj = new java.lang.Boolean(my_obj.getFilterable());
      break;
    case 5:
      obj = my_obj.getLabel();
      break;
    case 6:
      obj = new java.lang.Integer(my_obj.getLength());
      break;
    case 7:
      obj = my_obj.getName();
      break;
    case 8:
      obj = new java.lang.Boolean(my_obj.getNameField());
      break;
    case 9:
      obj = new java.lang.Boolean(my_obj.getNillable());
      break;
    case 10:
      obj = my_obj.getPicklistValues();
      break;
    case 11:
      obj = new java.lang.Integer(my_obj.getPrecision());
      break;
    case 12:
      obj = my_obj.getReferenceTo();
      break;
    case 13:
      obj = new java.lang.Boolean(my_obj.getRestrictedPicklist());
      break;
    case 14:
      obj = new java.lang.Integer(my_obj.getScale());
      break;
    case 15:
      obj = my_obj.getSoapType();
      break;
    case 16:
      obj = my_obj.getType();
      break;
    case 17:
      obj = new java.lang.Boolean(my_obj.getUpdateable());
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(rpctypes.Field my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setByteLength(((java.lang.Number)setter_arg).intValue());
      break;
    case 1:
      my_obj.setCreateable(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 2:
      my_obj.setCustom(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 3:
      my_obj.setDigits(((java.lang.Number)setter_arg).intValue());
      break;
    case 4:
      my_obj.setFilterable(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 5:
      my_obj.setLabel((java.lang.String)setter_arg);
      break;
    case 6:
      my_obj.setLength(((java.lang.Number)setter_arg).intValue());
      break;
    case 7:
      my_obj.setName((java.lang.String)setter_arg);
      break;
    case 8:
      my_obj.setNameField(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 9:
      my_obj.setNillable(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 10:
      my_obj.setPicklistValues((rpctypes.PicklistEntry[])setter_arg);
      break;
    case 11:
      my_obj.setPrecision(((java.lang.Number)setter_arg).intValue());
      break;
    case 12:
      my_obj.setReferenceTo((java.lang.String[])setter_arg);
      break;
    case 13:
      my_obj.setRestrictedPicklist(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 14:
      my_obj.setScale(((java.lang.Number)setter_arg).intValue());
      break;
    case 15:
      my_obj.setSoapType((rpctypes.SoapType)setter_arg);
      break;
    case 16:
      my_obj.setType((rpctypes.FieldType)setter_arg);
      break;
    case 17:
      my_obj.setUpdateable(((java.lang.Boolean)setter_arg).booleanValue());
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

  }

  protected weblogic.xml.schema.binding.ModelGroupCompositor getCompositor() {
    return weblogic.xml.schema.binding.ModelGroupCompositor.SEQUENCE ;
  }





  







}
