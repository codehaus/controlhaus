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
import org.apache.xmlbeans.XmlObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating row mappers which map a ResultSet row to a return type.
 */
public final class RowMapperFactory {

    /**
     * Return a RowMapper instance which knows how to map a ResultSet row to the given return type.
     * @param rs
     * @param returnTypeClass
     * @param cal
     * @return
     * @throws SQLException
     * @throws ControlException
     */
    static RowMapper getRowMapper(ResultSet rs, Class returnTypeClass, Calendar cal) throws SQLException, ControlException {

        if (HashMap.class.isAssignableFrom(returnTypeClass)) {
            return new RowToHashMapMapper(rs);
        } else if (Map.class.isAssignableFrom(returnTypeClass)) {
            return new RowToMapMapper(rs);
        } else if (XmlObject.class.isAssignableFrom(returnTypeClass)) {
            return new RowToXmlObjectMapper(rs, returnTypeClass, cal);
        } else {
            return new RowToObjectMapper(rs, returnTypeClass, cal);
        }
    }
}
