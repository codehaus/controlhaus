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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * Abstract base class for all row mappers.
 */
public abstract class RowMapper {


    protected static final TypeMappingsFactory _tmf = TypeMappingsFactory.getInstance();

    protected final ResultSet _resultSet;
    protected final Calendar _cal;
    protected final Class _returnTypeClass;

    /**
     * Constructor
     * @param resultSet
     */
    protected RowMapper(ResultSet resultSet, Class returnTypeClass, Calendar cal) {
        _resultSet = resultSet;
        _returnTypeClass = returnTypeClass;
        _cal = cal;
    }

    /**
     * This method must be implemented for all sub classes
     * @return
     * @throws ControlException
     */
    public abstract Object mapRowToReturnType() throws ControlException, SQLException;


    /**
     * Build a String array of Column names from the restltSet
     * @return
     * @throws SQLException
     */
    protected String[] getKeysFromResultSet() throws SQLException {

        String[] keys;
        final ResultSetMetaData md = _resultSet.getMetaData();
        final int columnCount = md.getColumnCount();

        keys = new String[columnCount + 1];
        for (int i = 1; i <= columnCount; i++) {
            keys[i] = md.getColumnName(i).toUpperCase();
        }
        return keys;
    }

    /**
     * Map a ResultSet with a single column to the specifed returnTypeClass
     *
     * @param returnTypeClass
     * @return
     * @throws SQLException
     */
    protected Object mapSingleColumnResultSet(Class returnTypeClass) throws SQLException {

        final int typeId = _tmf.getTypeId(returnTypeClass);

        if (typeId != TypeMappingsFactory.TYPE_UNKNOWN) {
            return extractColumnValue(1, typeId);
        } else {
            // we still might want a single value (i.e. java.util.Date)
            Object val = extractColumnValue(1, typeId);
            if (returnTypeClass.isAssignableFrom(val.getClass())) {
                return val;
            }
        }
        return null;
    }

    /**
     * Extract a column value from the ResultSet and return it as resultType.
     *
     * @param index
     * @param resultType
     * @return
     * @throws java.sql.SQLException
     */
    protected Object extractColumnValue(int index, int resultType) throws SQLException {

        switch (resultType) {
            case TypeMappingsFactory.TYPE_INT:
                return new Integer(_resultSet.getInt(index));
            case TypeMappingsFactory.TYPE_LONG:
                return new Long(_resultSet.getLong(index));
            case TypeMappingsFactory.TYPE_FLOAT:
                return new Float(_resultSet.getFloat(index));
            case TypeMappingsFactory.TYPE_DOUBLE:
                return new Double(_resultSet.getDouble(index));
            case TypeMappingsFactory.TYPE_BYTE:
                return new Byte(_resultSet.getByte(index));
            case TypeMappingsFactory.TYPE_SHORT:
                return new Short(_resultSet.getShort(index));
            case TypeMappingsFactory.TYPE_BOOLEAN:
                return _resultSet.getBoolean(index) ? Boolean.TRUE : Boolean.FALSE;
            case TypeMappingsFactory.TYPE_INT_OBJ:
                {
                    int i = _resultSet.getInt(index);
                    return _resultSet.wasNull() ? null : new Integer(i);
                }
            case TypeMappingsFactory.TYPE_LONG_OBJ:
                {
                    long i = _resultSet.getLong(index);
                    return _resultSet.wasNull() ? null : new Long(i);
                }
            case TypeMappingsFactory.TYPE_FLOAT_OBJ:
                {
                    float i = _resultSet.getFloat(index);
                    return _resultSet.wasNull() ? null : new Float(i);
                }
            case TypeMappingsFactory.TYPE_DOUBLE_OBJ:
                {
                    double i = _resultSet.getDouble(index);
                    return _resultSet.wasNull() ? null : new Double(i);
                }
            case TypeMappingsFactory.TYPE_BYTE_OBJ:
                {
                    byte i = _resultSet.getByte(index);
                    return _resultSet.wasNull() ? null : new Byte(i);
                }
            case TypeMappingsFactory.TYPE_SHORT_OBJ:
                {
                    short i = _resultSet.getShort(index);
                    return _resultSet.wasNull() ? null : new Short(i);
                }
            case TypeMappingsFactory.TYPE_BOOLEAN_OBJ:
                {
                    boolean i = _resultSet.getBoolean(index);
                    return _resultSet.wasNull() ? null : (i ? Boolean.TRUE : Boolean.FALSE);
                }
            case TypeMappingsFactory.TYPE_STRING:
                return _resultSet.getString(index);
            case TypeMappingsFactory.TYPE_BIG_DECIMAL:
                return _resultSet.getBigDecimal(index);
            case TypeMappingsFactory.TYPE_BYTES:
                return _resultSet.getBytes(index);
            case TypeMappingsFactory.TYPE_TIMESTAMP:
                {
                    if (null == _cal)
                        return _resultSet.getTimestamp(index);
                    else
                        return _resultSet.getTimestamp(index, _cal);
                }
            case TypeMappingsFactory.TYPE_TIME:
                {
                    if (null == _cal)
                        return _resultSet.getTime(index);
                    else
                        return _resultSet.getTime(index, _cal);
                }
            case TypeMappingsFactory.TYPE_SQLDATE:
                {
                    if (null == _cal)
                        return _resultSet.getDate(index);
                    else
                        return _resultSet.getDate(index, _cal);
                }
            case TypeMappingsFactory.TYPE_DATE:
                {
                    // convert explicity to java.util.Date
                    // 12918 |  knex does not return java.sql.Date properly from web service
                    java.sql.Timestamp ts = (null == _cal) ? _resultSet.getTimestamp(index) : _resultSet.getTimestamp(index, _cal);
                    if (null == ts)
                        return null;
                    return new java.util.Date(ts.getTime());
                }
            case TypeMappingsFactory.TYPE_CALENDAR:
                {
                    java.sql.Timestamp ts = (null == _cal) ? _resultSet.getTimestamp(index) : _resultSet.getTimestamp(index, _cal);
                    if (null == ts)
                        return null;
                    Calendar c = (null == _cal) ? Calendar.getInstance() : (Calendar) _cal.clone();
                    c.setTimeInMillis(ts.getTime());
                    return c;
                }
            case TypeMappingsFactory.TYPE_REF:
                return _resultSet.getRef(index);
            case TypeMappingsFactory.TYPE_BLOB:
                return _resultSet.getBlob(index);
            case TypeMappingsFactory.TYPE_CLOB:
                return _resultSet.getClob(index);
            case TypeMappingsFactory.TYPE_ARRAY:
                return _resultSet.getArray(index);
            case TypeMappingsFactory.TYPE_READER:
            case TypeMappingsFactory.TYPE_STREAM:
                throw new ControlException("streaming return types are not supported by the JdbcControl; use ResultSet instead");
            case TypeMappingsFactory.TYPE_UNKNOWN:
                // JAVA_TYPE (could be any), or REF
                return _resultSet.getObject(index);
            default:
                throw new ControlException("internal error: unknown type ID: " + Integer.toString(resultType));
        }
    }
}
