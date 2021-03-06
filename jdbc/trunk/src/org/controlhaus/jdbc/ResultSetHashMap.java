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

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * The ResultSetHashMap class extends a standard HashMap and
 * populates it with data derived from a JDBC ResultSet.
 * <p/>
 * Note: the keys are treated case-insensitively, and therefore requests
 * made on the map are case-insensitive.  Any direct access to the keys
 * will yield uppercase keys.
 * <p/>
 * Note: only the row associated with the current cursor position
 * is used.
 */
public class ResultSetHashMap extends HashMap<String, Object> {

    ResultSetHashMap() {
        super();
    }

    ResultSetHashMap(int size) {
        super(size);
    }

    /**
     * This constructor is optimized for being called in a loop.
     * Preserve the upper case column list for performance.
     */
    ResultSetHashMap(ResultSet rs, String[] keys) throws SQLException {
        super();
        assert keys.length == rs.getMetaData().getColumnCount() + 1;

        for (int i = 1; i < keys.length; i++) {
            assert keys[i].equals(keys[i].toUpperCase());
            super.put(keys[i], rs.getObject(i));
        }
    }


    ResultSetHashMap(ResultSet rs) throws SQLException {
        super();
        ResultSetMetaData md = rs.getMetaData();
        for (int i = 1; i <= md.getColumnCount(); i++) {
            super.put(md.getColumnName(i).toUpperCase(), rs.getObject(i));
        }
    }


    public boolean containsKey(String key) {
        return super.containsKey(key.toUpperCase());
    }


    public Object get(String key) {
        return super.get(key.toUpperCase());
    }


    public Object put(String key, Object value) {
        return super.put(key.toUpperCase(), value);
    }


    public Object remove(String key) {
        return super.remove(key.toUpperCase());
    }
}

