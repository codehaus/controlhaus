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

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.NoSuchElementException;

/**
 * Iterator used for mapping a ResultSet to an Iterator type
 */
public class ResultSetIterator implements java.util.Iterator {

    private final Class _returnClass;
    private final ResultSet _rs;
    private final RowMapper _rowMapper;

    private boolean _primed = false;

    ResultSetIterator(ControlBeanContext context, Method method, ResultSet rs, Calendar cal) throws Exception {
        _rs = rs;

        JdbcControl.SQL methodSQL = (JdbcControl.SQL) context.getMethodPropertySet(method, JdbcControl.SQL.class);
        _returnClass = methodSQL.iteratorElementType();

        if (_returnClass == null) {
            throw new ControlException("Invalid return class declared for Iterator:" + _returnClass.getName());
        }

        _rowMapper = RowMapperFactory.getRowMapper(rs, _returnClass, cal);
    }

    /**
     * @return
     */
    public boolean hasNext() {
        if (_primed) {
            return true;
        }

        try {
            _primed = _rs.next();
        } catch (SQLException sqle) {
            return false;
        }
        return _primed;
    }

    /**
     * @return
     */
    public Object next() {
        try {
            if (!_primed) {
                _primed = _rs.next();
                if (!_primed) {
                    throw new NoSuchElementException();
                }
            }
            // reset upon consumption
            _primed = false;
            return _rowMapper.mapRowToReturnType();
        } catch (Exception e) {
            // Since Iterator interface is locked, all we can do
            // is put the real exception inside an expected one.
            NoSuchElementException xNoSuch = new NoSuchElementException("ResultSet exception: " + e);
            xNoSuch.initCause(e);
            throw xNoSuch;
        }
    }

    /**
     *
     */
    public void remove() {
        throw new UnsupportedOperationException("remove not supported");
    }
}

