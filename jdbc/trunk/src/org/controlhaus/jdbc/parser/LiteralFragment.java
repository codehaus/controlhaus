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

/**
 * Fragement handler for literal values.
 */
public final class LiteralFragment extends SqlFragment {

    private final String _value;

    /**
     * Constructor
     * @param value
     */
    LiteralFragment(String value) {
        _value = value;
    }

    /**
     * Get the text for a PreparedStatement
     * @param context
     * @param m
     * @param args
     * @return
     */
    String getPreparedStatementText(ControlBeanContext context, Method m, Object[] args) {
       return _value;
    }

    /**
     * For JUnit testing.
     * @return
     */
    public String toString() {
        return _value;
    }
}
