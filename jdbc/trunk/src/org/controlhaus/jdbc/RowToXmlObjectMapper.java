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
import org.apache.xmlbeans.SchemaProperty;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

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
 * Maps a ResultSet row to an XmlObject.
 */
public class RowToXmlObjectMapper extends RowMapper {

    private final int _columnCount;
    private final SchemaType _schemaType;

    private AccessibleObject[] _fields;
    private AccessibleObject[] _fieldNilable;
    private int[] _fieldTypes;

    private final Object[] _args = new Object[1];

    /**
     * Create a new RowToXmlObjectMapper.
     * @param resultSet ResultSet to map
     * @param returnTypeClass Class to map to.
     * @param cal Calendar instance for date/time mappings.
     * @throws SQLException on error.
     */
    RowToXmlObjectMapper(ResultSet resultSet, Class returnTypeClass, Calendar cal) throws SQLException {
        super(resultSet, returnTypeClass, cal);

        _columnCount = resultSet.getMetaData().getColumnCount();
        _schemaType = getSchemaType(_returnTypeClass);

        getFieldMappings();
    }

    /**
     * map a row from the ResultSet to an XmlObject instance
     *
     * @return An XmlObject instance.
     * @throws ControlException on error.
     * @throws SQLException on error.
     */
    public Object mapRowToReturnType() throws ControlException, SQLException {

        Object resultObject = null;
        if (_columnCount == 1) {
            resultObject = mapSingleColumnResultSet(_returnTypeClass);
            if (resultObject != null) return resultObject;
        }

        resultObject = XmlObject.Factory.newInstance(new XmlOptions().setDocumentType(_schemaType));

        for (int i = 1; i < _fields.length; i++) {
            AccessibleObject f = _fields[i];
            Object resultValue = extractColumnValue(i, _fieldTypes[i]);

            try {
                _args[0] = resultValue;
                ((Method) f).invoke(resultObject, _args);

                if (_fieldNilable != null) {
                    if (_resultSet.wasNull()) {
                        Method setNil = (Method) _fieldNilable[i];
                        if (setNil != null) {
                            setNil.invoke(resultObject, (Object[]) null);
                        }
                    }
                }
            } catch (IllegalArgumentException iae) {
                ResultSetMetaData md = _resultSet.getMetaData();
                throw new ControlException("The declared Java type for method " + ((Method) f).getName()
                                           + ((Method) f).getParameterTypes()[0].toString()
                                           + " is incompatible with the SQL format of column " + md.getColumnName(i).toString()
                                           + md.getColumnTypeName(i).toString()
                                           + " which returns objects of type " + resultValue.getClass().getName());
            } catch (IllegalAccessException e) {
                throw new ControlException("IllegalAccessException when trying to access method " + ((Method) f).getName(), e);
            } catch (InvocationTargetException e) {
                throw new ControlException("IllegalInvocationException when trying to access method " + ((Method) f).getName(), e);
            }
        }
        return resultObject;
    }


// ///////////////////////////////////////////////// private methods /////////////////////////////////////////////////

    /**
     * Build the necessary structures to do the mapping
     *
     * @throws SQLException
     */
    private void getFieldMappings() throws SQLException {

        //
        // special case for XmlObject, find factory class
        //
        if (_schemaType.isDocumentType()) {
            return;
        }

        final String[] keys = getKeysFromResultSet();

        // check schemaProperty mapping names for more accurate column->field mapping
        SchemaProperty[] props = _schemaType.getElementProperties();
        for (int i = 0; i < props.length; i++) {

            int col = -1;
            try {
                col = _resultSet.findColumn(props[i].getName().getLocalPart());
            } catch (SQLException x) {
            }

            if (col > 0) {
                keys[col] = props[i].getJavaPropertyName().toUpperCase();
            }
        }

        //
        // find fields or setters for return class
        //

        HashMap<String, AccessibleObject> mapFields = new HashMap<String, AccessibleObject>(_columnCount * 2);
        for (int i = 1; i <= _columnCount; i++) {
            mapFields.put(keys[i], null);
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
            _fieldTypes[i] = _tmf.getTypeId(((Method) f).getParameterTypes()[0]);
        }

        AccessibleObject[] classFields = new AccessibleObject[_fields.length - 1];
        System.arraycopy(_fields, 1, classFields, 0, classFields.length);
        try {
            AccessibleObject.setAccessible(classFields, true);
        } catch (SecurityException e) {
        }

        // this takes care of the special case for xml beans return types.
        // since they return primitive types even if they are nillable, we
        // must explicitly call setNil after getting the column out of the resultSet.
        // for that, we need to keep track of each field's setNil method.
        // if the field is not nillable, it will not have a setNil method, and
        // that array index will be null.
        //
        // here is a summary of the arrays this method sets up:
        // the fields array is an array of AccessibleObjects (either fields or methods)
        // fieldTypes is an array where the indexes match fields, and the values contain the typeId for that item in fields
        // Ex
        // fields[1]       = setBlah(int i)
        // fieldTypes[1]   = TYPE_INT
        // fieldNilable[1] = setNilBlah()
        _fieldNilable = new AccessibleObject[_fields.length];

        for (int i = 0; i < _fields.length; i++) {
            AccessibleObject mm = _fields[i];
            if (mm != null) {
                if (Method.class.isAssignableFrom(mm.getClass())) {
                    Method meth = (Method) mm;
                    String fieldname = meth.getName().substring(3);
                    String setnilmethodname = "setNil" + fieldname;
                    Method[] returnClassMethods = _returnTypeClass.getMethods();

                    for (int j = 0; j < returnClassMethods.length; j++) {
                        Method mmm = returnClassMethods[j];
                        String mmmname = mmm.getName();
                        Class mmmreturntype = mmm.getReturnType();
                        Class[] mmmparametertype = mmm.getParameterTypes();

                        if (mmmname.equals(setnilmethodname) &&
                                mmmreturntype.equals(Void.TYPE) &&
                                mmmparametertype.length == 0) {
                            _fieldNilable[i] = mmm;
                            break;
                        }
                    }
                }
            }
        } // for
        // end of special case stuff for returnTypeIsXmlBean
    }

    /**
     * Get the SchemaType for the specified class.
     * @param returnType Class to get the SchemaType for.
     * @return SchemaType
     */
    private SchemaType getSchemaType(Class returnType) {
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
