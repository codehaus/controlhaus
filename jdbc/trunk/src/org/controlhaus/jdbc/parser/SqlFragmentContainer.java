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
 * Fragment class for fragment types which contain other fragments.
 */
public abstract class SqlFragmentContainer extends SqlFragment {

    protected ArrayList<SqlFragment> _children;

    /**
     * Constructor
     */
    SqlFragmentContainer() {
        _children = new ArrayList<SqlFragment>();
    }

    /**
     * Does this fragment contain a parameter value for a prepared statement
     *
     * @return
     */
    boolean hasParamValue() {
        for (SqlFragment f : _children) {
            if (f.hasParamValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a child to this container
     * @param child
     */
    void addChild(SqlFragment child) {
        _children.add(child);
    }

    /**
     * Return the array of children from this container
     * @return
     */
    SqlFragment[] getChildren() {
        return (SqlFragment[]) _children.toArray();
    }

    /**
     * Must be implemented for JUnit testing.
     *
     * @return
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (SqlFragment f : _children) {
            s.append(f.toString());
        }
        return s.toString();
    }

    /**
     * builds the text of the prepared statement
     *
     * @param context
     * @param m
     * @param args
     * @return
     */
    String getPreparedStatementText(ControlBeanContext context, Method m, Object[] args) {
        StringBuilder sb = new StringBuilder();
        for (SqlFragment sf : _children) {
            sb.append(sf.getPreparedStatementText(context, m, args));
        }
        return sb.toString();
    }
}
