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

import org.apache.beehive.controls.api.context.ControlBeanContext;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Calendar;

/**
 * Default ResultSetMapper implementation for ResultSets.
 */
public class DefaultResultSetMapper extends ResultSetMapper {

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
        return resultSet;
    }

    /**
     * Can the ResultSet which this mapper uses be closed by the database control?
     * @return
     */
    public boolean canCloseResultSet() { return false; }
}
