/*
 * Copyright 2004 BEA Systems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.controlhaus.jdbc;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;

import java.util.Properties;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Various utility methods for the database control
 */
public final class ControlUtils {

    /**
     * Parse the propertiesString into a Properties object.  The string must have the format of:
     * propertyName=propertyValue;propertyName=propertyValue;...
     *
     * @param propertiesString
     * @return A Properties instance or null if parse fails
     */
    static Properties parseProperties(String propertiesString) {
        Properties properties = null;
        String[] propPairs = propertiesString.split(";");
        if (propPairs.length > 0) {
            properties = new Properties();
            for (String propPair : propPairs) {
                int eq = propPair.indexOf('=');
                assert eq > -1 : "Invalid properties syntax: " + propertiesString;
                properties.put(propPair.substring(0, eq), propPair.substring(eq + 1, propPair.length()));
            }
        }
        return properties;
    }

    /**
     * Find any one of the characters contained within the parameter chars.
     *
     * @param s     String to search for 'char's values in
     * @param chars Characters to search for in s
     * @param i     location in s to start search
     * @return index of the first character found, -1 if not found.
     */
    static int findOneOf(String s, String chars, int i) {
        if (i != -1) {
            for (; i < s.length(); i++) {
                if (-1 != chars.indexOf(s.charAt(i)))
                    return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param returnType
     * @return
     */
    static SchemaType getSchemaType(Class returnType) {
        SchemaType schemaType = null;
        if (XmlObject.class.isAssignableFrom(returnType)) {
            try {
                Field f = returnType.getField("type");
                if (SchemaType.class.isAssignableFrom(f.getType()) &&
                        Modifier.isStatic(f.getModifiers()))
                    schemaType = (SchemaType) f.get(null);
            } catch (NoSuchFieldException x) {
            } catch (IllegalAccessException x) {
            }
        }
        return schemaType;
    }
}
