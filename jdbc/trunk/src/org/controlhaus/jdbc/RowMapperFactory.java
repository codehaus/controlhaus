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

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating row mappers.
 * Row mapper types supported by this factory include: HashMap, Map, Object, XmlObject. The factory determines the
 * proper row mapper to use by checking its List of RowMappers against the type of mapping requested.  When performing
 * the lookup, the factory attempts to find the most specific type match.  If a match can't be found the most general
 * type of RowMapper is returned, RowToObjectMapper.
 */
public final class RowMapperFactory {

    //@todo: Re-evaluate: should the _rowMappings table be a static ?
    //@todo: currently applies to all dbcontrol instances -- also need to enhance API
    //@todo: to allow for more control when adding an new mapper.
    private static final ArrayList<RowMapping> _rowMappings = new ArrayList<RowMapping>();

    private static final RowMapping DEFAULT_ROWMAPPING = new RowMapping(Object.class, RowToObjectMapper.class);

    static {

        _rowMappings.add(new RowMapping(HashMap.class, RowToHashMapMapper.class));
        _rowMappings.add(new RowMapping(Map.class, RowToMapMapper.class));

        try {
            Class xmlObj = Class.forName("org.apache.xmlbeans.XmlObject");
            _rowMappings.add(new RowMapping(xmlObj, RowToXmlObjectMapper.class));
        } catch (ClassNotFoundException e) {
            // NOOP if apache xml beans not present
        }
    }

    /**
     * Get a RowMapper instance which knows how to map a ResultSet row to the given return type.
     *
     * @param rs The ResultSet to map.
     * @param returnTypeClass The class to map a ResultSet row to.
     * @param cal Calendar instance for mapping date/time values.
     * @return A RowMapper instance.
     * @throws ControlException on error.
     */
    public static RowMapper getRowMapper(ResultSet rs, Class returnTypeClass, Calendar cal) throws ControlException {

        for (RowMapping rm : _rowMappings) {
            if (rm.canMapToReturnType(returnTypeClass)) {
                return rm.getMapper(rs, returnTypeClass, cal);
            }
        }

        //
        // if we made it to here, use the default mapper
        //
        return DEFAULT_ROWMAPPING.getMapper(rs, returnTypeClass, cal);
    }

    /**
     * Append a new row mapper to the list of available row mappers
     *
     * @param returnTypeClass
     * @param rowMapperClass
     */
    public static void appendRowMapping(Class returnTypeClass, Class<? extends RowMapper> rowMapperClass) {
        for (RowMapping rm : _rowMappings) {
            if (rm._mapperFor == returnTypeClass) {
                _rowMappings.set(_rowMappings.indexOf(rm), new RowMapping(rowMapperClass, rowMapperClass));
                return;
            }
        }
        _rowMappings.add(new RowMapping(returnTypeClass, rowMapperClass));
    }

    /**
     * Helper class for storing row mappers.
     */
    private static final class RowMapping {

        private final static Class[] _params = {ResultSet.class, Class.class, Calendar.class};
        private final Class _mapperFor;
        private final Class _rowMapper;

        /**
         * Create a new RowMapping associating a specific class to a RowMapper.
         *
         * @param mapperFor Class this mapper maps to.
         * @param rowMapper A RowMapper implementation.
         */
        RowMapping(Class mapperFor, Class<? extends RowMapper> rowMapper) {
            _mapperFor = mapperFor;
            _rowMapper = rowMapper;
        }

        /**
         * Can this mapper map to the specified return type?
         *
         * @param returnTypeClass Class to attempt mapping to.
         * @return true if this mapper can map the the returnTypeClass.
         */
        boolean canMapToReturnType(Class returnTypeClass) {
            return _mapperFor.isAssignableFrom(returnTypeClass);
        }

        /**
         * Create an instance of the RowMapper class.
         *
         * @param rs ResultSet we are mapping from.
         * @param returnType Class to map rows to.
         * @param cal Calendar instance for date/time values.
         * @return A RowMapper instance.
         * @throws ControlException on error.
         */
        RowMapper getMapper(ResultSet rs, Class returnType, Calendar cal) throws ControlException {
            Constructor c = null;
            try {
                c = _rowMapper.getDeclaredConstructor(_params);
                return (RowMapper) c.newInstance(new Object[]{rs, returnType, cal});
            } catch (Exception e) {
                throw new ControlException("Failure creating new instance of RowMapper:", e);
            }
        }
    }
}
