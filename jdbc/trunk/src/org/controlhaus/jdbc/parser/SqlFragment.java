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

package org.controlhaus.jdbc.parser;

import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.controlhaus.jdbc.TypeMappingsFactory;

import java.lang.reflect.Method;

/**
 * Base class for fragments generated during parsing.
 * Basic fragments exist for literals, reflection, substition and jdbc escapes.
 */
public abstract class SqlFragment {

    /**
     * True if this fragment shouldn't be cached since the prepared statement may change on each invocation.
     * @return
     */ 
    boolean isDynamicFragment() {
        return false;
    }

    /**
     * Does this fragment contain a parameter value for a prepared statement
     * @return
     */
    boolean hasParamValue() {
        return false;
    }

    /**
     * Get the SQL data type for the parameter value contained within this fragment.
     * @return
     */
    int getParamSqlDataType() {
        return TypeMappingsFactory.TYPE_UNKNOWN;
    }

    /**
     * Get the prepared statement parameter value contained within this fragment.
     *
     * @param context
     * @param method
     * @param args
     * @return null if this fragment doesn't contain a parameter value.
     */
    Object[] getParameterValues(ControlBeanContext context, Method method, Object[] args)  {
        return null;
    }

    /**
     * Get the text for a prepared statement generated by this fragment.
     * @param context
     * @param method
     * @param args
     * @return
     */
    abstract String getPreparedStatementText(ControlBeanContext context, Method method, Object[] args);

    /**
     * Must be implemented for JUnit testing.
     * @return
     */
    public String toString() {
        assert false : "Classes which extend SqlFragment must implement toString()";
        return null;
    }
}