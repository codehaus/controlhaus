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
 * This code was automatically generated at 2:42:17 AM on May 5, 2004
 * by weblogic.xml.schema.binding.internal.codegen.BeanCodecGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:GetUpdatedResult


public final class GetUpdatedResultCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "urn:partner.soap.sforce.com" , "GetUpdatedResult" );

  private static final java.lang.String JAVA_TYPE = 
     "rpctypes.GetUpdatedResult";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = java.lang
  //class   name = [Ljava.lang.String;
  //java    type = [Ljava.lang.String;
  //schema  name = ['urn:partner.soap.sforce.com']:ids
  //schema  type = ['urn:partner.soap.sforce.com']:GetUpdatedResult__ids__ArrayAnonType
  //array: true primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","ids",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","GetUpdatedResult__ids__ArrayAnonType",null),
                                                            "ids",
                                                            java.lang.String[].class,
                                                            false,
                                                            true),


  //package name = java.lang
  //class   name = String
  //java    type = java.lang.String
  //schema  name = ['urn:partner.soap.sforce.com']:sforceReserved
  //schema  type = ['http://www.w3.org/2001/XMLSchema']:string
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","sforceReserved",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("http://www.w3.org/2001/XMLSchema","string",null),
                                                            "sforceReserved",
                                                            java.lang.String.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new rpctypes.GetUpdatedResult();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }



  protected boolean isPropertySet(Object my_obj, int idx) {

    rpctypes.GetUpdatedResult typed_obj = (rpctypes.GetUpdatedResult) my_obj;

    switch(idx) {

    case 0:
      return typed_obj._isSetIds();
    case 1:
      return typed_obj._isSetSforceReserved();


    default:
      return true;
    }
  }




  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    rpctypes.GetUpdatedResult typed_obj = (rpctypes.GetUpdatedResult) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    rpctypes.GetUpdatedResult typed_obj = (rpctypes.GetUpdatedResult) my_obj;
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


  private static java.lang.Object typedInvokeGetter(rpctypes.GetUpdatedResult my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getIds();
      break;
    case 1:
      obj = my_obj.getSforceReserved();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(rpctypes.GetUpdatedResult my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setIds((java.lang.String[])setter_arg);
      break;
    case 1:
      my_obj.setSforceReserved((java.lang.String)setter_arg);
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

  }

  protected weblogic.xml.schema.binding.ModelGroupCompositor getCompositor() {
    return weblogic.xml.schema.binding.ModelGroupCompositor.SEQUENCE ;
  }





  







}
