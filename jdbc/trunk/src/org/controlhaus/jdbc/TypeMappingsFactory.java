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

import java.lang.reflect.Method;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Currently contains all types of type mappings.  Need to determine what should be done with this class.
 */
//@todo: refactor!
public final class TypeMappingsFactory {


    private static TypeMappingsFactory _instance;

    public static TypeMappingsFactory getInstance() {
        if (_instance == null) {
            _instance = new TypeMappingsFactory();
        }
        return _instance;
    }

    public static final int TYPE_UNKNOWN = 0;
    static final int TYPE_BYTE = 1;
    static final int TYPE_SHORT = 2;
    static final int TYPE_INT = 3;
    static final int TYPE_LONG = 4;
    static final int TYPE_FLOAT = 5;
    static final int TYPE_DOUBLE = 6;
    static final int TYPE_BOOLEAN = 7;
    static final int TYPE_BYTE_OBJ = 8;
    static final int TYPE_SHORT_OBJ = 9;
    static final int TYPE_INT_OBJ = 10;
    static final int TYPE_LONG_OBJ = 11;
    static final int TYPE_FLOAT_OBJ = 12;
    static final int TYPE_DOUBLE_OBJ = 13;
    static final int TYPE_BOOLEAN_OBJ = 14;
    static final int TYPE_BIG_DECIMAL = 15;
    static final int TYPE_STRING = 16;
    static final int TYPE_BYTES = 17;
    static final int TYPE_SQLDATE = 18;
    static final int TYPE_TIME = 19;
    static final int TYPE_TIMESTAMP = 20;
    static final int TYPE_STREAM = 21;
    static final int TYPE_READER = 22;
    static final int TYPE_CLOB = 23;
    static final int TYPE_BLOB = 24;
    static final int TYPE_ARRAY = 25;
    static final int TYPE_REF = 26;
    static final int TYPE_DATE = 27;
    static final int TYPE_CALENDAR = 28;
    static final int TYPE_MAX = 29;

    private Map<Class, Object> _primitiveDefaults;
    private Map<Class, Integer> _typeMap;
    private Map<Class, Integer> _typeSqlMap;

    /**
     * Map a string version of sql type to sql type (java.sql.Types).
     * example: "INTEGER" maps to java.sql.Types.INTEGER
     */
    private Map<String, Integer> _typeSqlNameMap;

    private static Method _methodMapGet;

    /**
     * Constructor
     */
    TypeMappingsFactory() {

        _primitiveDefaults = new HashMap<Class, Object>();
        _primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
        _primitiveDefaults.put(Integer.TYPE, new Integer(0));
        _primitiveDefaults.put(Long.TYPE, new Long(0));
        _primitiveDefaults.put(Byte.TYPE, new Byte((byte) 0));
        _primitiveDefaults.put(Short.TYPE, new Short((short) 0));
        _primitiveDefaults.put(Character.TYPE, new Character('\u0000'));
        _primitiveDefaults.put(Float.TYPE, new Float(0.0f));
        _primitiveDefaults.put(Double.TYPE, new Double(0.0d));

        // Class to internal enum
        _typeMap = new HashMap<Class, Integer>(TYPE_MAX * 2);
        _typeMap.put(Boolean.TYPE, new Integer(TYPE_BOOLEAN));
        _typeMap.put(Integer.TYPE, new Integer(TYPE_INT));
        _typeMap.put(Long.TYPE, new Integer(TYPE_LONG));
        _typeMap.put(Byte.TYPE, new Integer(TYPE_BYTE));
        _typeMap.put(Short.TYPE, new Integer(TYPE_SHORT));
        _typeMap.put(Float.TYPE, new Integer(TYPE_FLOAT));
        _typeMap.put(Double.TYPE, new Integer(TYPE_DOUBLE));
        _typeMap.put(Boolean.class, new Integer(TYPE_BOOLEAN_OBJ));
        _typeMap.put(Integer.class, new Integer(TYPE_INT_OBJ));
        _typeMap.put(Long.class, new Integer(TYPE_LONG_OBJ));
        _typeMap.put(Byte.class, new Integer(TYPE_BYTE_OBJ));
        _typeMap.put(Short.class, new Integer(TYPE_SHORT_OBJ));
        _typeMap.put(Float.class, new Integer(TYPE_FLOAT_OBJ));
        _typeMap.put(Double.class, new Integer(TYPE_DOUBLE_OBJ));
        _typeMap.put(String.class, new Integer(TYPE_STRING));
        _typeMap.put(java.math.BigDecimal.class, new Integer(TYPE_BIG_DECIMAL));
        _typeMap.put(byte[].class, new Integer(TYPE_BYTES));
        _typeMap.put(java.sql.Timestamp.class, new Integer(TYPE_TIMESTAMP));
        _typeMap.put(java.sql.Time.class, new Integer(TYPE_TIME));
        _typeMap.put(java.sql.Date.class, new Integer(TYPE_SQLDATE));
        _typeMap.put(java.sql.Ref.class, new Integer(TYPE_REF));
        _typeMap.put(java.sql.Blob.class, new Integer(TYPE_BLOB));
        _typeMap.put(java.sql.Clob.class, new Integer(TYPE_CLOB));
        _typeMap.put(java.sql.Array.class, new Integer(TYPE_ARRAY));
        _typeMap.put(java.io.Reader.class, new Integer(TYPE_READER));
        _typeMap.put(java.io.InputStream.class, new Integer(TYPE_STREAM));
        _typeMap.put(java.util.Date.class, new Integer(TYPE_DATE));
        _typeMap.put(java.util.Calendar.class, new Integer(TYPE_CALENDAR));
        _typeMap.put(java.util.GregorianCalendar.class, new Integer(TYPE_CALENDAR));
        //       _typeMap.put(com.bea.xml.StringEnumAbstractBase.class, new Integer(TYPE_STRING));

        // Class to java.sql.Types
        _typeSqlMap = new HashMap<Class, Integer>(TYPE_MAX * 2);
        _typeSqlMap.put(Boolean.TYPE, new Integer(Types.BOOLEAN));
        _typeSqlMap.put(Integer.TYPE, new Integer(Types.INTEGER));
        _typeSqlMap.put(Long.TYPE, new Integer(Types.BIGINT));
        _typeSqlMap.put(Byte.TYPE, new Integer(Types.TINYINT));
        _typeSqlMap.put(Short.TYPE, new Integer(Types.SMALLINT));
        _typeSqlMap.put(Float.TYPE, new Integer(Types.REAL));
        _typeSqlMap.put(Double.TYPE, new Integer(Types.DOUBLE));
        _typeSqlMap.put(Boolean.class, new Integer(Types.BOOLEAN));
        _typeSqlMap.put(Integer.class, new Integer(Types.INTEGER));
        _typeSqlMap.put(Long.class, new Integer(Types.BIGINT));
        _typeSqlMap.put(Byte.class, new Integer(Types.TINYINT));
        _typeSqlMap.put(Short.class, new Integer(Types.SMALLINT));
        _typeSqlMap.put(Float.class, new Integer(Types.REAL));
        _typeSqlMap.put(Double.class, new Integer(Types.DOUBLE));
        _typeSqlMap.put(String.class, new Integer(Types.VARCHAR));
        _typeSqlMap.put(java.math.BigDecimal.class, new Integer(Types.DECIMAL));
        _typeSqlMap.put(byte[].class, new Integer(Types.VARBINARY));
        _typeSqlMap.put(java.sql.Timestamp.class, new Integer(Types.TIMESTAMP));
        _typeSqlMap.put(java.sql.Time.class, new Integer(Types.TIME));
        _typeSqlMap.put(java.sql.Date.class, new Integer(Types.DATE));
        _typeSqlMap.put(java.sql.Ref.class, new Integer(Types.REF));
        _typeSqlMap.put(java.sql.Blob.class, new Integer(Types.BLOB));
        _typeSqlMap.put(java.sql.Clob.class, new Integer(Types.CLOB));
        _typeSqlMap.put(java.sql.Array.class, new Integer(Types.ARRAY));
        _typeSqlMap.put(java.util.Date.class, new Integer(Types.TIMESTAMP));
        _typeSqlMap.put(java.util.Calendar.class, new Integer(Types.TIMESTAMP));
        _typeSqlMap.put(java.util.GregorianCalendar.class, new Integer(Types.TIMESTAMP));
//        _typeSqlMap.put(com.bea.xml.StringEnumAbstractBase.class, new Integer(Types.VARCHAR));
        //_typeSqlMap.put(java.io.Reader.class, new Integer(Types.READER));
        //_typeSqlMap.put(java.io.InputStream.class, new Integer(Types.STREAM));

        // String to java.sql.Types
        _typeSqlNameMap = new ResultSetHashMap(TYPE_MAX * 2);
        _typeSqlNameMap.put("BIT", new Integer(Types.BIT));
        _typeSqlNameMap.put("TINYINT", new Integer(Types.TINYINT));
        _typeSqlNameMap.put("SMALLINT", new Integer(Types.SMALLINT));
        _typeSqlNameMap.put("INTEGER", new Integer(Types.INTEGER));
        _typeSqlNameMap.put("BIGINT", new Integer(Types.BIGINT));
        _typeSqlNameMap.put("FLOAT", new Integer(Types.REAL));
        _typeSqlNameMap.put("REAL", new Integer(Types.REAL));
        _typeSqlNameMap.put("DOUBLE", new Integer(Types.DOUBLE));
        _typeSqlNameMap.put("NUMERIC", new Integer(Types.NUMERIC));
        _typeSqlNameMap.put("DECIMAL", new Integer(Types.DECIMAL));
        _typeSqlNameMap.put("CHAR", new Integer(Types.CHAR));
        _typeSqlNameMap.put("VARCHAR", new Integer(Types.VARCHAR));
        _typeSqlNameMap.put("LONGVARCHAR", new Integer(Types.LONGVARCHAR));
        _typeSqlNameMap.put("DATE", new Integer(Types.DATE));
        _typeSqlNameMap.put("TIME", new Integer(Types.TIME));
        _typeSqlNameMap.put("TIMESTAMP", new Integer(Types.TIMESTAMP));
        _typeSqlNameMap.put("BINARY", new Integer(Types.BINARY));
        _typeSqlNameMap.put("VARBINARY", new Integer(Types.VARBINARY));
        _typeSqlNameMap.put("LONGVARBINARY", new Integer(Types.LONGVARBINARY));
        _typeSqlNameMap.put("NULL", new Integer(Types.NULL));
        _typeSqlNameMap.put("OTHER", new Integer(Types.OTHER));
        _typeSqlNameMap.put("JAVA_OBJECT", new Integer(Types.JAVA_OBJECT));
        _typeSqlNameMap.put("DISTINCT", new Integer(Types.DISTINCT));
        _typeSqlNameMap.put("STRUCT", new Integer(Types.STRUCT));
        _typeSqlNameMap.put("ARRAY", new Integer(Types.ARRAY));
        _typeSqlNameMap.put("BLOB", new Integer(Types.BLOB));
        _typeSqlNameMap.put("CLOB", new Integer(Types.CLOB));
        _typeSqlNameMap.put("REF", new Integer(Types.REF));
        _typeSqlNameMap.put("DATALINK", new Integer(Types.DATALINK));
        _typeSqlNameMap.put("BOOLEAN", new Integer(Types.BOOLEAN));

        // some JAVA synonyms
        _typeSqlNameMap.put("BYTE", new Integer(Types.TINYINT));
        _typeSqlNameMap.put("SHORT", new Integer(Types.SMALLINT));
        _typeSqlNameMap.put("INT", new Integer(Types.INTEGER));
        _typeSqlNameMap.put("LONG", new Integer(Types.BIGINT));

        // cache the Map.get method for efficiency
        try {
            _methodMapGet = java.util.Map.class.getMethod("get", new Class[]{Object.class});
        } catch (NoSuchMethodException e) {
            throw new ControlException("Can not find java.util.Map.get(Object) method");
        }
    }

    /**
     * Convert a type string to its SQL Type value.
     * @param type
     * @return
     */
    public int convertStringToSQLType(String type) {
        if (_typeSqlNameMap.containsKey(type.toUpperCase())) {
            return _typeSqlNameMap.get(type.toUpperCase());
        }
        return TYPE_UNKNOWN;
    }

    /**
     * Get the SQL type of a class, start at top level class an check all super classes until match is found.
     * @param classType
     * @return
     */
    public int getSqlType(Class classType) {
        while (classType != null) {
            Integer type = _typeSqlMap.get(classType);
            if (type != null) {
                return type.intValue();
            }
            classType = classType.getSuperclass();
        }
        return Types.OTHER;
    }

    /**
     * Get the SQL type for an object.
     * @param o
     * @return
     */
    public int getSqlType(Object o) {
        if (null == o) {
            return Types.NULL;
        }
        return getSqlType(o.getClass());
    }

    /**
     *
     * @param val
     * @param args
     * @return
     * @throws Exception
     */
    public Object lookupType(Object val, Object[] args) throws Exception {
        return _methodMapGet.invoke(val, args);
    }

    /**
     * Get the type id (defined by this class) for the given class.
     * @param classType
     * @return
     */
    public int getTypeId(Class classType) {
        while (null != classType) {
            Integer typeObj = (Integer) _typeMap.get(classType);
            if (null != typeObj) {
                return typeObj.intValue();
            }
            classType = classType.getSuperclass();
        }
        return TYPE_UNKNOWN;
    }

   /**
    * Returns a primitive legal value as opposed to null if type is primitive
    */
   public Object fixNull(Class type) {
       return type.isPrimitive() ? _primitiveDefaults.get(type) : null;
   }
}
