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
 * Fragment class for 'sql:' escapes in the sql string. Supported 'sql:' escapes are subst and fn.
 * subst is the default mode, and will be used if 'sql: ' is specified.
 * fn must specify 'in' as the function name.
 */
public class SqlEscapeFragment extends SqlFragmentContainer {

    private static final String NULL_VALUE = "NULL";

    /**
     * constructor for subst or function with no param sub
     *
     * @param frag
     */
    SqlEscapeFragment(SqlFragment frag) {
        super();
        addChild(frag);
    }

    /**
     * Constructor for a function which includes a ReflectionFragment
     * @param lf
     * @param rf
     * @param lff
     */
    SqlEscapeFragment(LiteralFragment lf, ReflectionFragment rf, LiteralFragment lff) {
        super();
        addChild(lf);
        addChild(rf);
        addChild(lff);
    }

    /**
     * Always true for this fragment type
     * @return
     */
    boolean isDynamicFragment() { return true; }

    /**
     * Always false for this fragment type, since all param values are resolved
     * before the prepared statement is created.
     * @return
     */
    boolean hasParamValue() { return false; }

    /**
     * Return the text for a PreparedStatement from this fragment.
     * @param context
     * @param m
     * @param args
     * @return
     */
    String getPreparedStatementText(ControlBeanContext context, Method m, Object[] args) {

        StringBuilder sb = new StringBuilder();
        for (SqlFragment frag : _children) {
            if (frag.hasParamValue()) {
                Object[] pValues = frag.getParameterValues(context, m, args);
                for (Object o : pValues) {
                    sb.append(processSqlParams(o));
                }
            } else {
                sb.append(frag.getPreparedStatementText(context, m, args));
            }
        }
        return sb.toString();
    }


// ////////////////////////////////////////////// Private Methods //////////////////////////////////////////////


    /**
     * Check for the cases of a null or array type param value. If array type build a string of the array values
     * seperated by commas.
     *
     * @param value
     * @return
     */
    private String processSqlParams(Object value) {

        Object[] arr = null;
        if (value != null) {
            arr = toObjectArray(value);
        }

        if (value == null || (arr != null && arr.length == 0)) {
            return NULL_VALUE;
        } else if (arr != null) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                if (i > 0) {
                    result.append(',');
                    result.append(arr[i].toString());
                } else {
                    result.append(arr[i].toString());
                }
            }
            return result.toString();
        } else {
            return value.toString();
        }
    }

    /**
     * Create an Object array for the given array.
     *
     * @param o
     * @return
     */
    private Object[] toObjectArray(Object o) {

        Class clas = o.getClass().getComponentType();

        if (null == clas) return null;

        Object[] arr;

        if (clas == Boolean.TYPE) {
            boolean[] src = (boolean[]) o;
            arr = new Object[src.length];
            for (int i = 0; i < src.length; i++)
                arr[i] = new Boolean(src[i]);
        } else if (clas == Character.TYPE) {
            char[] src = (char[]) o;
            arr = new Object[src.length];
            for (int i = 0; i < src.length; i++)
                arr[i] = new Character(src[i]);
        } else if (clas == Byte.TYPE) {
            byte[] src = (byte[]) o;
            arr = new Object[src.length];
            for (int i = 0; i < src.length; i++)
                arr[i] = new Byte(src[i]);
        } else if (clas == Short.TYPE) {
            short[] src = (short[]) o;
            arr = new Object[src.length];
            for (int i = 0; i < src.length; i++)
                arr[i] = new Short(src[i]);
        } else if (clas == Integer.TYPE) {
            int[] src = (int[]) o;
            arr = new Object[src.length];
            for (int i = 0; i < src.length; i++)
                arr[i] = new Integer(src[i]);
        } else if (clas == Long.TYPE) {
            long[] src = (long[]) o;
            arr = new Object[src.length];
            for (int i = 0; i < src.length; i++)
                arr[i] = new Long(src[i]);
        } else if (clas == Float.TYPE) {
            float[] src = (float[]) o;
            arr = new Object[src.length];
            for (int i = 0; i < src.length; i++)
                arr[i] = new Float(src[i]);
        } else if (clas == Double.TYPE) {
            double[] src = (double[]) o;
            arr = new Object[src.length];
            for (int i = 0; i < src.length; i++)
                arr[i] = new Double(src[i]);
        } else {
            arr = (Object[]) o;
        }
        return arr;
    }
}
