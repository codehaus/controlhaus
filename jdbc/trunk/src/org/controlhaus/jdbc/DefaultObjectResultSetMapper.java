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
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.xmlbeans.SchemaType;
import org.controlhaus.jdbc.JdbcControl.SQL;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Default ResultSetMapper implementation for Objects.
 */
public class DefaultObjectResultSetMapper extends ResultSetMapper {

    protected static final TypeMappingsFactory _tmf = TypeMappingsFactory.getInstance();

    /**
     * Map a ResultSet to an Object.  Object type is defined by the return type of the method.
     *
     * @param context
     * @param m         Method assoicated with this call
     * @param resultSet Result set to map
     * @param cal
     * @return
     * @throws Exception
     */
    public Object mapToResultType(ControlBeanContext context, Method m, ResultSet resultSet, Calendar cal) throws Exception {

        final Class returnType = m.getReturnType();
        final boolean isArray = returnType.isArray();

        if (isArray) {
            final SQL methodSQL = (SQL) context.getMethodPropertySet(m, SQL.class);
            return arrayFromResultSet(resultSet, methodSQL.arrayMaxLength(), returnType, cal);
        } else {
            if (!resultSet.next()) {
                return _tmf.fixNull(m.getReturnType());
            }

            return RowMapperFactory.getRowMapper(resultSet, returnType, cal).mapRowToReturnType();
        }
    }

    //
    // ////////////////////////////////// PRIVATE METHODS //////////////////////////////////////////
    //

    protected Object arrayFromResultSet(ResultSet rs, int maxRows, Class arrayClass, Calendar cal)
            throws Exception {

        Class componentType = arrayClass.getComponentType();
        ResultSetMetaData md = rs.getMetaData();

        ArrayList list = new ArrayList();
        int numRows;

        boolean hasMoreRows = rs.next();
        RowMapper rowMapper = RowMapperFactory.getRowMapper(rs, componentType, cal);

        for (numRows = 0; numRows != maxRows && hasMoreRows; numRows++) {
            list.add(rowMapper.mapRowToReturnType());
            hasMoreRows = rs.next();
        }

        Object array = java.lang.reflect.Array.newInstance(componentType, numRows);
        try {
            for (int i = 0; i < numRows; i++) {
                java.lang.reflect.Array.set(array, i, list.get(i));
            }
        } catch (IllegalArgumentException iae) {
            // assuming no errors in resultSetObject() this can only happen
            // for single column result sets.
            throw new ControlException("The declared Java type for array " + componentType.getName()
                                       + "is incompatible with the SQL format of column " + md.getColumnName(1)
                                       + md.getColumnTypeName(1) + "which returns objects of type + "
                                       + list.get(0).getClass().getName());
        }
        return array;
    }
}
