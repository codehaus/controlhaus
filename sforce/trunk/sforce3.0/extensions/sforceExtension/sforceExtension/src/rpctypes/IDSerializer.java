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
 * by weblogic.xml.schema.binding.internal.codegen.SimpleTypeSerializerGenerator -- do not edit.
 *
 * @version WebLogic Server 8.1 SP2  Fri Dec 5 15:01:51 PST 2003 316284 
 * @author Copyright (c) 2004 by BEA Systems, Inc. All Rights Reserved.
 */

package rpctypes;

// original type: ['urn:partner.soap.sforce.com']:ID


public final class IDSerializer 
  extends weblogic.xml.schema.binding.internal.builtin.XSDStringSerializer
{
  private static final weblogic.xml.stream.XMLName __XML_TYPE = 
    weblogic.xml.stream.ElementFactory.createXMLName("urn:partner.soap.sforce.com","ID",null) ;

  protected weblogic.xml.stream.XMLName getXmlType() {
    return __XML_TYPE;
  }

  

  protected java.lang.String getContentFromObject(java.lang.Object obj, 
                                                  weblogic.xml.schema.binding.SerializationContext context) 
    throws weblogic.xml.schema.binding.SerializationException
    {
      if (context.isStrictValidation()) {
	try {
          IDDeserializer.validate(obj);
	} catch (weblogic.xml.schema.binding.DeserializationException de) {
          throw new weblogic.xml.schema.binding.SerializationException(de);
	}
      }
      return super.getContentFromObject(obj, context);
    }



}
