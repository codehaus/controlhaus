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

import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.controlhaus.jdbc.TypeMappingsFactory;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Handles fragments which consist of a parameter substitution, ex. '{foo}' foo is resolved by using
 * java refelection on the method's parameters.
 */
public final class ReflectionFragment extends SqlFragment {

    private static final String PREPARED_STATEMENT_SUB_MARK = "?";
    private static final Pattern s_parameterNamePattern = Pattern.compile("\\.");

    private final String _parameterName;
    private final String[] _nameQualifiers;
    private int _sqlDataType;

    /**
     * Constructor
     * @param parameterName
     */
    ReflectionFragment(String parameterName) {
        _parameterName = parameterName;
        _sqlDataType = TypeMappingsFactory.TYPE_UNKNOWN;
        _nameQualifiers = s_parameterNamePattern.split(_parameterName);
    }

    /**
     * Constructor
     * @param parameterName
     * @param sqlDataType
     */
    ReflectionFragment(String parameterName, String sqlDataType) {
        this(parameterName);
        if (sqlDataType != null) {
            _sqlDataType = TypeMappingsFactory.getInstance().convertStringToSQLType(sqlDataType);
        }
    }

    /**
     * Return text generated by this fragment for a PreparedStatement
     * @param context
     * @param m
     * @param args
     * @return
     */
    String getPreparedStatementText(ControlBeanContext context, Method m, Object[] args) {
        return PREPARED_STATEMENT_SUB_MARK;
    }

    /**
     * Always true for ReflectionFragment.
     * @return
     */
    boolean hasParamValue() { return true; }

    /**
     * Get the SQL data type of this param.
     * @return
     */
    int getParamSqlDataType() { return _sqlDataType; }

    /**
     * For JUnit testing.
     * @return
     */
    public String toString() { return PREPARED_STATEMENT_SUB_MARK; }

    /**
     * Get the value of this parameter.
     *
     * @param context ControlBeanContext instance to evaluate the parameter's value against.
     * @param method  Method instance to evaluate against.
     * @param args    Method argument values
     * @return All parameter object values contained within this fragment
     * @throws ControlException if evaluation fails
     */
    Object[] getParameterValues(ControlBeanContext context, Method method, Object[] args) throws ControlException {

        Object value = null;
        try {
            value = context.getParameterValue(method, _nameQualifiers[0], args);
        } catch (IllegalArgumentException iae) {
            throw new ControlException("Invalid argument name in SQL statement: " + _nameQualifiers[0], iae);
        }

        for (int i = 1; i < _nameQualifiers.length; i++) {
            // handle maps, properties, and fields...
            value = extractValue(value, _nameQualifiers[i - 1], _nameQualifiers[i]);
        }
        return new Object[] {value};
    }

    //
    // /////////////////////////////////////////////// PRIVATE METHODS /////////////////////////////////////////////
    //

    /**
     * Get the value from the referenced method parameter using java reflection
     * @param aValue
     * @param aName
     * @param bName
     * @return
     * @throws ControlException
     */
    private Object extractValue(Object aValue, String aName, String bName) throws ControlException {

        Class aClass = aValue.getClass();
        Object value = null;

        //
        //  a.isB() or a.getB()
        //
        String bNameCapped = Character.toUpperCase(bName.charAt(0)) + bName.substring(1);
        Method getMethod = null;
        Class retType = null;
        try {
            getMethod = aClass.getMethod("is" + bNameCapped, (Class[]) null);
            retType = getMethod.getReturnType();
            if (!(retType.equals(Boolean.class) ||
                    retType.equals(Boolean.TYPE))) {
                // only boolean returns can be isB()
                getMethod = null;
            }
        } catch (NoSuchMethodException e) {
        }
        if (getMethod == null) {
            try {
                getMethod = aClass.getMethod("get" + bNameCapped, (Class[]) null);
                retType = getMethod.getReturnType();
            } catch (NoSuchMethodException e) {
            }
        }

        if (getMethod != null) {
            // OK- a.getB()
            try {
                value = getMethod.invoke(aValue, (Object[]) null);
            } catch (IllegalAccessException e) {
                throw new ControlException("Unable to access public method: " + e.toString());
            } catch (java.lang.reflect.InvocationTargetException e) {
                throw new ControlException("Exception thrown when executing : " + getMethod.getName() + "() to use as parameter");
            }
            return value;
        }

        //
        // try a.b
        //

        try {
            value = aClass.getField(bName).get(aValue);
            return value;
        } catch (Exception e) {
        }

        //
        // try a.get(b)
        //

        if (aValue instanceof Map) {
            try {
                value = TypeMappingsFactory.getInstance().lookupType(aValue, new Object[]{bName});
                return value;
            } catch (Exception mapex) {
                throw new ControlException("Exception thrown when executing Map.get() to resolve parameter" + mapex.toString());
            }
        }

        // no other options...
        if (true) {
            throw new ControlException("Illegal argument in SQL statement: " + _parameterName.toString()
                                       + "; unable to find suitable method of retrieving property " + bName.toString()
                                       + " out of object " + aName.toString() + ".");
        }
        return null;
    }
}
