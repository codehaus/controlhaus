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
 * Factory for creating row mappers which map a ResultSet row to a return type.
 */
public final class RowMapperFactory {

    //@todo: Re-evaluate: should the _rowMappings table be a static ?
    // currently applies to all dbcontrol instances -- also need to enhance API
    // to allow for more control when adding an new mapper.

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
     * Return a RowMapper instance which knows how to map a ResultSet row to the given return type.
     *
     * @param rs
     * @param returnTypeClass
     * @param cal
     * @return
     * @throws ControlException
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
     * Helper class for storing row mappers
     */
    private static final class RowMapping {

        private final static Class[] _params = {ResultSet.class, Class.class, Calendar.class};
        private final Class _mapperFor;
        private final Class _rowMapper;

        /**
         * constructor
         *
         * @param mapperFor
         * @param rowMapper
         */
        RowMapping(Class mapperFor, Class<? extends RowMapper> rowMapper) {
            _mapperFor = mapperFor;
            _rowMapper = rowMapper;
        }

        /**
         * Does this mapper map to the specified return type?
         *
         * @param type
         * @return
         */
        boolean isMappingForType(Class type) {
            return type == _mapperFor;
        }

        /**
         * Can this mapper map to the specified return type?
         *
         * @param returnTypeClass
         * @return
         */
        boolean canMapToReturnType(Class returnTypeClass) {
            return _mapperFor.isAssignableFrom(returnTypeClass);
        }

        /**
         * get an instance of the RowMapper class
         *
         * @param rs
         * @param returnType
         * @param cal
         * @return
         * @throws ControlException
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
