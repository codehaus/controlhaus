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
 * This code was automatically generated at 2:42:27 AM on May 5, 2004
 * by weblogic.xml.schema.binding.internal.codegen.BeanCodecGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:PicklistEntry


public final class PicklistEntryCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "urn:partner.soap.sforce.com" , "PicklistEntry" );

  private static final java.lang.String JAVA_TYPE = 
     "rpctypes.PicklistEntry";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['urn:partner.soap.sforce.com']:active
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","active",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "active",
                                                            boolean.class,
                                                            false,
                                                            true),


  //package name = 
  //class   name = boolean
  //java    type = boolean
  //schema  name = ['urn:partner.soap.sforce.com']:defaultValue
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:boolean
  //array: false primitive: true  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","defaultValue",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","boolean",null),
                                                            "defaultValue",
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


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['urn:partner.soap.sforce.com']:value
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","value",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "value",
                                                            java.lang.String.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new rpctypes.PicklistEntry();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }





  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    rpctypes.PicklistEntry typed_obj = (rpctypes.PicklistEntry) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    rpctypes.PicklistEntry typed_obj = (rpctypes.PicklistEntry) my_obj;
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


  private static java.lang.Object typedInvokeGetter(rpctypes.PicklistEntry my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = new java.lang.Boolean(my_obj.getActive());
      break;
    case 1:
      obj = new java.lang.Boolean(my_obj.getDefaultValue());
      break;
    case 2:
      obj = my_obj.getLabel();
      break;
    case 3:
      obj = my_obj.getValue();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(rpctypes.PicklistEntry my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setActive(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 1:
      my_obj.setDefaultValue(((java.lang.Boolean)setter_arg).booleanValue());
      break;
    case 2:
      my_obj.setLabel((java.lang.String)setter_arg);
      break;
    case 3:
      my_obj.setValue((java.lang.String)setter_arg);
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

  }

  protected weblogic.xml.schema.binding.ModelGroupCompositor getCompositor() {
    return weblogic.xml.schema.binding.ModelGroupCompositor.SEQUENCE ;
  }





  







}
