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

import org.apache.beehive.controls.api.ControlException;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Map a ResultSet row to an Object. This mapper uses Java reflection to perform the mapping.  The Class being mapped
 * to must have setter methods which match the ResultSet column names.  For example, if a column in the ResultSet
 * named USERID, the object must have a setter method named setUserid().  If a setter method cannot be class fields
 * are also checked, the same naming conventions applies, USERID -> userid.
 */
public class RowToObjectMapper extends RowMapper {

    private final int _columnCount;

    private AccessibleObject[] _fields;
    private int[] _fieldTypes;

    private final Object[] _args = new Object[1];

    /**
     * Create a new RowToObjectMapper.
     *
     * @param resultSet ResultSet to map
     * @param returnTypeClass Class to map to.
     * @param cal Calendar instance for date/time mappings.
     * @throws SQLException on error.
     */
    RowToObjectMapper(ResultSet resultSet, Class returnTypeClass, Calendar cal) throws SQLException, ControlException {
        super(resultSet, returnTypeClass, cal);

        _columnCount = resultSet.getMetaData().getColumnCount();
        _fields = null;
    }


    /**
     * Do the mapping.
     *
     * @return An object instance.
     * @throws ControlException on error.
     * @throws SQLException on error.
     */
    public Object mapRowToReturnType() throws ControlException, SQLException {

        Object resultObject = null;

        // if the ResultSet only contains a single column we may be able to map directly
        // to the return type -- if so we don't need to build any structures to support
        // mapping
        if (_columnCount == 1) {
            resultObject = mapSingleColumnResultSet(_returnTypeClass);
            if (resultObject != null) return resultObject;
        }

        if (_fields == null) {
            getFieldMappings();
        }


        try {
            resultObject = _returnTypeClass.newInstance();
        } catch (InstantiationException e) {
            throw new ControlException("Exception: ", e);
        } catch (IllegalAccessException e) {
            throw new ControlException("Exception: ", e);
        }

        for (int i = 1; i < _fields.length; i++) {
            AccessibleObject f = _fields[i];
            Object resultValue = extractColumnValue(i, _fieldTypes[i]);

            try {
                if (f instanceof Field) {
                    ((Field) f).set(resultObject, resultValue);
                } else {
                    _args[0] = resultValue;
                    ((Method) f).invoke(resultObject, _args);
                }
            } catch (IllegalArgumentException iae) {
                ResultSetMetaData md = _resultSet.getMetaData();
                if (f instanceof Field) {
                    throw new ControlException("The declared Java type for field " + ((Field) f).getName()
                                               + ((Field) f).getType().toString()
                                               + " is incompatible with the SQL format of column " + md.getColumnName(i).toString()
                                               + md.getColumnTypeName(i).toString()
                                               + " which returns objects of type " + resultValue.getClass().getName());
                } else {
                    throw new ControlException("The declared Java type for method " + ((Method) f).getName()
                                               + ((Method) f).getParameterTypes()[0].toString()
                                               + " is incompatible with the SQL format of column " + md.getColumnName(i).toString()
                                               + md.getColumnTypeName(i).toString()
                                               + " which returns objects of type " + resultValue.getClass().getName());
                }
            } catch (IllegalAccessException e) {
                if (f instanceof Field) {
                    throw new ControlException("IllegalAccessException when trying to access field " + ((Field) f).getName(), e);
                } else {
                    throw new ControlException("IllegalAccessException when trying to access method " + ((Method) f).getName(), e);
                }
            } catch (InvocationTargetException e) {
                if (f instanceof Field) {
                    throw new ControlException("InvocationTargetException when trying to access field " + ((Field) f).getName(), e);
                } else {
                    throw new ControlException("InvocationTargetException when trying to access method " + ((Method) f).getName(), e);
                }
            }
        }
        return resultObject;
    }

    /**
     * Build the structures necessary to do the mapping
     *
     * @throws SQLException on error.
     * @throws ControlException on error.
     */
    private void getFieldMappings() throws SQLException, ControlException {

        final String[] keys = getKeysFromResultSet();

        //
        // find fields or setters for return class
        //

        HashMap<String, AccessibleObject> mapFields = new HashMap<String, AccessibleObject>(_columnCount * 2);
        for (int i = 1; i <= _columnCount; i++) {
            mapFields.put(keys[i], null);
        }

        // fix for 8813: include inherited and non-public fields
        for (Class clazz = _returnTypeClass; clazz != null && clazz != Object.class; clazz = clazz.getSuperclass()) {
            Field[] classFields = clazz.getDeclaredFields();
            for (Field f : classFields) {
                if (Modifier.isStatic(f.getModifiers())) continue;
                String fieldName = f.getName().toUpperCase();
                if (!mapFields.containsKey(fieldName)) continue;
                mapFields.put(fieldName, f);
            }
        }

        // public methods
        Method[] classMethods = _returnTypeClass.getMethods();
        for (Method m : classMethods) {

            // method name checks
            String methodName = m.getName();
            if (methodName.length() < 4 || !methodName.startsWith("set")) continue;
            if (!Character.isUpperCase(methodName.charAt(3))) continue;

            // verify that the setter method's field has not already been captured
            String fieldName = methodName.substring(3).toUpperCase();
            if (!mapFields.containsKey(fieldName)) continue;
            if (Modifier.isStatic(m.getModifiers())) continue;

            // method parameter checks
            Class[] params = m.getParameterTypes();
            if (params.length != 1) continue;
            if (TypeMappingsFactory.TYPE_UNKNOWN == _tmf.getTypeId(params[0])) continue;
            if (!Void.TYPE.equals(m.getReturnType())) continue;

            // check for overloads
            Object field = mapFields.get(fieldName);
            if (field != null) {
                if (field instanceof Field) {
                    continue;
                } else {
                    throw new ControlException("Unable to choose between overloaded methods " + methodName
                                               + " on the " + _returnTypeClass.getName() + " class. Mapping is done using "
                                               + "a case insensitive comparision of SQL ResultSet columns to field "
                                               + "names and public setter methods on the return class.");
                }
            }
            mapFields.put(fieldName, m);
        }

        // finally actually init the fields array
        _fields = new AccessibleObject[_columnCount + 1];
        _fieldTypes = new int[_columnCount + 1];

        for (int i = 1; i < _fields.length; i++) {
            AccessibleObject f = mapFields.get(keys[i]);
            if (f == null) {
                throw new ControlException("Unable to map the SQL column " + keys[i]
                                           + " to a field on the " + _returnTypeClass.getName() +
                                           " class. Mapping is done using a case insensitive comparision of SQL ResultSet "
                                           + "columns to field names and public setter methods on the return class.");
            }

            _fields[i] = f;
            if (f instanceof Field) {
                _fieldTypes[i] = _tmf.getTypeId(((Field) f).getType());
            } else {
                _fieldTypes[i] = _tmf.getTypeId(((Method) f).getParameterTypes()[0]);
            }
        }

        AccessibleObject[] classFields = new AccessibleObject[_fields.length - 1];
        System.arraycopy(_fields, 1, classFields, 0, classFields.length);
        try {
            AccessibleObject.setAccessible(classFields, true);
        } catch (SecurityException e) {
        }
    }
}
