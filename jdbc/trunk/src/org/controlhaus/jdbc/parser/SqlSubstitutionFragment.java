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
 * Represents a fragement from the SQL annotation's statement member which begins with '{sql:'.
 * Substitution fragements are unique in that they are fully evaluated BEFORE a PreparedStatement
 * is generated.
 * <p/>
 * Supported 'sql:' escapes are subst and fn. subst is the default mode, and will be used if 'sql: ' 
 * is specified.
 *
 * The <tt>fn</tt> variant of this construct has a very ridgid syntax at this point.  It must conform to:
 *
 * <pre>
 *   {sql:fn in(x,{y})}
 * </pre>
 *
 * where the '{y}' could also be some literal term.
 */
public class SqlSubstitutionFragment extends SqlFragmentContainer {

    /**
     * Constructor for subst or function with no param substitution
     *
     * @param child An child which is contained in this fragment.
     */
    SqlSubstitutionFragment(SqlFragment child) {
        super();
        addChild(child);
    }

    /**
     * Constructor for a function which includes a ReflectionFragment
     *
     * @param lf A LiteralFragment which contains the text up to the parameter substitution.
     * @param rf The ReflectionFragment containing the parameter substitution
     * @param lff A LiteralFragment which contains any text which occures after the parameter substitution.
     */
    SqlSubstitutionFragment(LiteralFragment lf, ReflectionFragment rf, LiteralFragment lff) {
        super();
        addChild(lf);
        addChild(rf);
        addChild(lff);
    }

    /**
     * Always true for this fragment type
     * @return true
     */
    boolean isDynamicFragment() { return true; }

    /**
     * Always false for this fragment type, since all param values are resolved
     * before the prepared statement is created.
     * @return false
     */
    boolean hasParamValue() { return false; }

    /**
     * Return the text for a PreparedStatement from this fragment.
     *
     * @param context A ControlBeanContext instance
     * @param m The annotated method
     * @param args The method parameters
     * @return A String containing the value of this fragment and its children
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
            arr = TypeMappingsFactory.toObjectArray(value);
        }

        if (value == null || (arr != null && arr.length == 0)) {
            return "";
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
}
