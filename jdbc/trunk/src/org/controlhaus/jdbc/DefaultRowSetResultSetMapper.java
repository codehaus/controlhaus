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

import com.sun.rowset.CachedRowSetImpl;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.controlhaus.jdbc.JdbcControl.SQL;

import javax.sql.RowSet;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 * Default ResultSetMapper implementation for RowSets.
 */
public class DefaultRowSetResultSetMapper extends ResultSetMapper {

    private static final TypeMappingsFactory _tmf = TypeMappingsFactory.getInstance();

    /**
     * Map a ResultSet to a RowSet.  Type of RowSet is defined by the SQL annotation for the method.
     *
     * @param context
     * @param m         Method assoicated with this call
     * @param resultSet Result set to map
     * @param cal
     * @return
     * @throws Exception
     */
    RowSet mapToResultType(ControlBeanContext context, Method m, ResultSet resultSet, Calendar cal) throws Exception {
        final SQL methodSQL = (SQL) context.getMethodPropertySet(m, SQL.class);
        final int maxrows = methodSQL.maxRows();
        CachedRowSetImpl rows = new CachedRowSetImpl();

        if (maxrows > 0) {
            rows.setMaxRows(maxrows);
        }

        rows.populate(resultSet);
        return rows;
    }
}
