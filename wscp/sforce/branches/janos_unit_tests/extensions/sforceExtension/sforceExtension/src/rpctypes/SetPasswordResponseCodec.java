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
 * This code was automatically generated at 2:42:23 AM on May 5, 2004
 * by weblogic.xml.schema.binding.internal.codegen.BeanCodecGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:setPasswordResponse


public final class SetPasswordResponseCodec 
  extends weblogic.xml.schema.binding.BeanCodecBase
{

  private static final int _SUPER_PROP_COUNT = 0;

  private static final weblogic.xml.stream.XMLName XML_TYPE = 
     weblogic.xml.stream.ElementFactory.createXMLName( "urn:partner.soap.sforce.com" , "setPasswordResponse" );

  private static final java.lang.String JAVA_TYPE = 
     "rpctypes.SetPasswordResponse";



  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo[] PROPS = 
  {
  //package name = rpctypes
  //class   name = SetPasswordResult
  //java    type = rpctypes.SetPasswordResult
  //schema  name = ['urn:partner.soap.sforce.com']:result
  //schema  type = ['urn:partner.soap.sforce.com']:SetPasswordResult
  //array: false primitive: false  attribute: false  formQualified: true

  new weblogic.xml.schema.binding.util.runtime.PropertyInfo(weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","result",null),
                                                            weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","SetPasswordResult",null),
                                                            "result",
                                                            rpctypes.SetPasswordResult.class,
                                                            false,
                                                            true),



  };




  protected java.lang.Object createObject() {
    return new rpctypes.SetPasswordResponse();
  }

  protected weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }





  protected java.lang.Object invokeGetter(java.lang.Object my_obj, int idx) {

    rpctypes.SetPasswordResponse typed_obj = (rpctypes.SetPasswordResponse) my_obj;
    return typedInvokeGetter(typed_obj, idx);
  }

  protected void invokeSetter(java.lang.Object my_obj, int idx,
                              java.lang.Object setter_arg)
  {

    rpctypes.SetPasswordResponse typed_obj = (rpctypes.SetPasswordResponse) my_obj;
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


  private static java.lang.Object typedInvokeGetter(rpctypes.SetPasswordResponse my_obj, 
                                          int idx) 
  {
    java.lang.Object obj;

    switch(idx) {

    case 0:
      obj = my_obj.getResult();
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

    return obj;

  }


  private static void typedInvokeSetter(rpctypes.SetPasswordResponse my_obj, 
                                        int idx,
                                        java.lang.Object setter_arg) 
  {
    switch(idx) {

    case 0:
      my_obj.setResult((rpctypes.SetPasswordResult)setter_arg);
      break;


    default:
      throw new java.lang.NoSuchFieldError("impossible case: " + idx);
    }

  }

  protected weblogic.xml.schema.binding.ModelGroupCompositor getCompositor() {
    return weblogic.xml.schema.binding.ModelGroupCompositor.SEQUENCE ;
  }





  protected boolean writeXsiType(weblogic.xml.schema.binding.SerializationContext context) {
    return false;
  }
  







}
