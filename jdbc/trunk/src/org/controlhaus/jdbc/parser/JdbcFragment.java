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

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * - JDBC fragments, supported types include:
 * <p/>
 * {call
 * {?=
 * {d
 * {t
 * {ts
 * {fn
 * {escape
 * {oj
 * <p/>
 */
public final class JdbcFragment extends SqlFragmentContainer {

    /**
     * constructor
     */
    JdbcFragment() {
        super();
    }

    /**
     * Get the prepared statement parameter value(s) contained within this fragment.
     *
     * @param context
     * @param method
     * @param args
     * @return null if this fragment doesn't contain a parameter value.
     */
    Object[] getParameterValues(ControlBeanContext context, Method method, Object[] args) {

        ArrayList<Object> values = new ArrayList<Object>();
        for (SqlFragment sf : _children) {
            if (sf.hasParamValue()) {
                Object[] moreValues = sf.getParameterValues(context, method, args);
                for (Object o : moreValues) {
                    values.add(o);
                }
            }
        }
        return values.toArray();
    }
}
