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
import java.util.Collections;
import java.util.Calendar;

/**
 * Map a ResultSet row to a java.util.Map object.
 */
public final class RowToMapMapper extends RowMapper {
    
    private final String[] _keys;

    /**
     * Create a new RowToMapMapper.
     * @param resultSet ResultSet to map
     * @param returnTypeClass Class to map to.
     * @param cal Calendar instance for date/time mappings.
     * @throws SQLException on error.
     */
    RowToMapMapper(ResultSet resultSet, Class returnTypeClass, Calendar cal) throws SQLException {
        super(resultSet, returnTypeClass, cal);
        _keys = getKeysFromResultSet();
    }

    /**
     * Do the mapping.
     * @return A Map.
     * @throws ControlException on error.
     */
    public Object mapRowToReturnType() throws ControlException {
        try {
        return Collections.unmodifiableMap(new ResultSetHashMap(_resultSet, _keys));
        } catch (Exception e) {
           throw new ControlException("Exception while creating ResultSetHashMap.", e);
        }
    }
}
