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
 * This code was automatically generated at 2:42:29 AM on May 5, 2004
 * by weblogic.xml.schema.binding.internal.codegen.SequenceCodecGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:updateResponse

// from array_deserialzer.j

public final class UpdateResponseSequenceCodec extends weblogic.xml.schema.binding.SequenceCodecBase
{
  private static final  weblogic.xml.stream.XMLName XML_TYPE =
    weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","updateResponse",null);

  private static final  weblogic.xml.stream.XMLName elementType =
    weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","SaveResult",null);

  private static final  weblogic.xml.stream.XMLName elementName = 
    weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","result",null);

  private static final java.lang.Class elementClass =
    rpctypes.SaveResult.class;


  private static final weblogic.xml.schema.binding.util.runtime.PropertyInfo _componentProp = 
    new weblogic.xml.schema.binding.util.runtime.PropertyInfo(elementType, elementName, "result", elementClass, false, true);


  protected  weblogic.xml.stream.XMLName getXmlType() {
    return XML_TYPE;
  }

  protected weblogic.xml.stream.XMLName getComponentXMLType(java.lang.Object obj, 
							    weblogic.xml.schema.binding.SerializationContext context) 
    throws weblogic.xml.schema.binding.SerializationException
  {
    return elementType;
  }

  private static final weblogic.xml.schema.binding.SchemaContext elementSchemaContext =
    weblogic.xml.schema.binding.SchemaContextFactory.newInstance().createSchemaContext(elementClass.getName());

  private static final weblogic.xml.schema.binding.ClassContext elementClassContext =
    weblogic.xml.schema.binding.ClassContextFactory.newInstance().createClassContext(elementType);

  protected weblogic.xml.schema.binding.util.runtime.Accumulator 
    createAccumulator(weblogic.xml.schema.binding.DeserializationContext context) {
    return weblogic.xml.schema.binding.util.runtime.AccumulatorFactory.createAccumulator(elementClass);
  }

  protected weblogic.xml.schema.binding.Deserializer 
    getSequenceElementDeserializer(weblogic.xml.schema.binding.DeserializationContext context) 
    throws weblogic.xml.schema.binding.DeserializationException {
    return weblogic.xml.schema.binding.RuntimeUtils.lookup_deserializer(elementType,
                                                                        elementSchemaContext,
                                                                        context);
  }

  protected weblogic.xml.stream.XMLName 
    getSequenceElementXMLName() {
    return elementName;
  }

  protected weblogic.xml.schema.binding.Serializer 
    getSequenceElementSerializer(weblogic.xml.schema.binding.SerializationContext context)
    throws weblogic.xml.schema.binding.SerializationException {
    return weblogic.xml.schema.binding.RuntimeUtils.lookup_serializer(elementClass,
                                                                      elementClassContext,
                                                                      context);
  }



  protected weblogic.xml.stream.XMLName getComponentXMLName(java.lang.Object obj,
                                                            weblogic.xml.schema.binding.SerializationContext context) 
    throws weblogic.xml.schema.binding.SerializationException
  {
    return elementName;
  }


  protected weblogic.xml.stream.XMLName getExpectedArrayElementType() 
    throws weblogic.xml.schema.binding.DeserializationException
  {
    return elementType;
  }

  protected java.lang.Class getExpectedComponentClass()
    throws weblogic.xml.schema.binding.DeserializationException
  {
    return elementClass;
  }

  public int getPropertyCount() {
    return 1;
  }
  public weblogic.xml.schema.binding.util.runtime.PropertyInfo getPropertyInfo(int idx) {
    if (idx == 0) {
      return _componentProp;
    } else {
      throw new java.lang.IndexOutOfBoundsException("invalid index " + idx);
    }
  }









}

