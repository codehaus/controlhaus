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

import java.util.HashMap;
import java.io.StringReader;

/**
 * The SqlParser class is a thread-safe class which parses a string containing a sql statement with substitituion
 * delimiters.
 * <p/>
 * The start and end delimiters are '{' and '}' respectively.
 * <p/>
 * This parser will also cache all parsed SQLStatements which contain non-volitale SQL.
 */
public final class SqlParser {

    // maintain a cache of SQLStatements which have already been parsed
    private HashMap<String, SqlStatement> _cachedSqlStatements;

    /**
     * Constructor
     */
    public SqlParser() {
        _cachedSqlStatements = new HashMap<String, SqlStatement>();
    }

    /**
     * Parse the sql and return an SqlStatement.
     *
     * @param sql A String contianing the sql to parse.
     * @return A SQLStatement instance.
     * @throws org.apache.beehive.controls.api.ControlException If statement cannot be parsed.
     */
    public SqlStatement parse(String sql) throws ControlException {

        // does a cached parse result exist for this statement?
        if (_cachedSqlStatements.containsKey(sql)) {
            return _cachedSqlStatements.get(sql);
        }

        SqlGrammar _parser = new SqlGrammar(new StringReader(sql));
        SqlStatement parsed = null;
        try {
            parsed = _parser.parse();
        } catch (ParseException e) {
            throw new ControlException("Error parsing SQL statment.", e);
        } catch (TokenMgrError tme) {
            throw new ControlException("Error parsing SQL statment.", tme);
        }

        if (parsed.isCacheable()) {
            _cachedSqlStatements.put(sql, parsed);
        }
        return parsed;
    }
}
