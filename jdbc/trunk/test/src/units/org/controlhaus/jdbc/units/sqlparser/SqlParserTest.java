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

package org.controlhaus.jdbc.units.sqlparser;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.beehive.controls.api.ControlException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.controlhaus.jdbc.parser.SqlParser;
import org.controlhaus.jdbc.parser.SqlStatement;

/**
 *
 */
public class SqlParserTest extends TestCase {
    private static final Logger _logger = Logger.getLogger(SqlParserTest.class);

    private static final String SimpleREFLECTPARAMS = "create table users (username VARCHAR(50), password VARCHAR(50))";
    private static final String SimpleREFLECTPARAMS_2 = "INSERT INTO USERS (username, password) \nVALUES({username},{password})";
    private static final String SimpleREFLECTPARAMS_2_RESULT = "INSERT INTO USERS (username, password) \nVALUES(?,?)";
    private static final String SimpleREFLECTPARAMS_3 = "UPDATE USERS SET USERNAME = {username}, PASSWORD = {password} WHERE USERNAME = {username}";
    private static final String SimpleREFLECTPARAMS_3_RESULT = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE USERNAME = ?";
    private static final String SimpleREFLECTPARAMS_4 = "UPDATE USERS SET USERNAME = {username|VARCHAR}, PASSWORD = {password|VARCHAR} WHERE USERNAME = {username|BIGINT}";
    private static final String SimpleREFLECTPARAMS_5 = "SELECT * FROM USERS WHERE NAME={  param}";
    private static final String SimpleREFLECTPARAMS_5_RESULT = "SELECT * FROM USERS WHERE NAME=?";
    private static final String SimpleREFLECTPARAMS_6 = "SELECT * FROM users WHERE foo='{' AND";


    private static final String SimpleJDBCPARAMS_1 = "INSERT INTO USERS (username, password) \nVALUES({call username(x)},{password})";
    private static final String SimpleJDBCPARAMS_1_RESULT = "INSERT INTO USERS (username, password) \nVALUES({call username(x)},?)";
    private static final String SimpleJDBCPARAMS_2 = "INSERT INTO USERS (username, password) \nVALUES({?= username()},{password})";
    private static final String SimpleJDBCPARAMS_2_RESULT = "INSERT INTO USERS (username, password) \nVALUES({?= username()},?)";
    private static final String SimpleJDBCPARAMS_3 = "INSERT INTO USERS (username, password) \nVALUES({d {foo}},{password})";
    private static final String SimpleJDBCPARAMS_3_RESULT = "INSERT INTO USERS (username, password) \nVALUES({d ?},?)";
    private static final String SimpleJDBCPARAMS_4 = "SELECT name FROM Identifiers WHERE Id LIKE '\\%' {escape '\\'})";
    private static final String SimpleJDBCPARAMS_5 = "{fn user()}{fn concat('Hot', 'Java')}";
    private static final String SimpleJDBCPARAMS_6 = "{d  'yyyy-mm-dd'}{t 'hh:mm:ss'}{ts 'yyyy-mm-dd hh:mm:ss.f'}{d {foo}}";
    private static final String SimpleJDBCPARAMS_6_RESULT = "{d  'yyyy-mm-dd'}{t 'hh:mm:ss'}{ts 'yyyy-mm-dd hh:mm:ss.f'}{d ?}";
    private static final String SimpleJDBCPARAMS_7 = "SELECT * FROM {oj TABLE1 LEFT OUTER JOIN TABLE2 ON DEPT_NO = 003420930}";


    private static final String SimpleSQLPARAMS_1 = "INSERT INTO USERS (username, password) \nVALUES({sql:fn in(x,{y})},{password})";
    private static final String SimpleSQLPARAMS_1_RESULT = "INSERT INTO USERS (username, password) \nVALUES((x IN (?)),?)";
    private static final String SimpleSQLPARAMS_2 = "INSERT INTO USERS (username, password) \nVALUES({sql:subst x}),{password})";
    private static final String SimpleSQLPARAMS_2_RESULT = "INSERT INTO USERS (username, password) \nVALUES(?),?)";
    private static final String SimpleSQLPARAMS_3 = "INSERT INTO USERS (username, password) \nVALUES({sql: x}),{password})";
    private static final String SimpleSQLPARAMS_3_RESULT = "INSERT INTO USERS (username, password) \nVALUES(?),?)";


    private static final String BADLY_FORMED_1 = "INSERT INTO USERS (username, password) \nVALUES({sql: x}),{password)";
    private static final String BADLY_FORMED_2 = "INSERT INTO USERS (username, password) \nVALUES(sql: x}),{password})";
    private static final String BADLY_FORMED_3 = "INSERT INTO USERS (username, password)  VALUES(({sql: 'x}),{password})";
    private static final String BADLY_FORMED_4 = "INSERT INTO USERS (username, password) \nVALUES(({sqlxxx: x}),{password})";
    private static final String BADLY_FORMED_5 = "INSERT INTO USERS (username, password) \nVALUES(({sql:badfunc x}),{password})";
    private static final String BADLY_FORMED_6 = "INSERT INTO USERS (username, password) \nVALUES(({sql: x),{password})";
    private static final String BADLY_FORMED_7 = "INSERT INTO USERS (username, password) \nVALUES(({sql: x}),{})";
    private static final String BADLY_FORMED_8 = "INSERT INTO USERS (username, password) \nVALUES(({sql: x}),{p)";

//    private static final String CORE_SQL_1 = "create table users (username VARCHAR(50), password VARCHAR(50))";
//    private static final String CORE_SQL_2 = "INSERT INTO USERS (username, password) \nVALUES(({x}),{p})";
//    private static final String CORE_SQL_3 = "INSERT INTO USERS (username, password) \nVALUES(({sql:x}),{p})";
//    private static final String CORE_SQL_4 = "INSERT INTO USERS (username, password) \nVALUES({sql:fn in(x,{y})},{password})";
//    private static final String CORE_SQL_4_OUT = "INSERT INTO USERS (username, password) \nVALUES({x IN (y)},{password})";
//    private static final String CORE_SQL_5 = "INSERT INTO USERS (username, password) \nVALUES({call {foo}},{password})";
//    private static final String CORE_SQL_6 = "INSERT INTO USERS (username, password) \nVALUES({?= username},{password})";

    private SqlParser _parser;

    public void setUp() throws Exception {
//        BasicConfigurator.configure();
        _parser = new SqlParser();
    }

    public void tearDown() throws Exception { }

    //
    // tests for basic param sub parsing
    //


    //
    // parse with no substitutions
    //
    public void testSimpleParse() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleREFLECTPARAMS);
        assertEquals(SimpleREFLECTPARAMS, frag.toString());
    }

    //
    // test parsing with several reflected params
    //
    public void testParamReflection() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleREFLECTPARAMS_2);
        assertEquals(SimpleREFLECTPARAMS_2_RESULT, frag.toString());
    }

    //
    // test parsing with several reflected params
    //
    public void testParamReflection2() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleREFLECTPARAMS_3);
        assertEquals(SimpleREFLECTPARAMS_3_RESULT, frag.toString());
    }

    //
    // test parsing with several reflected params which specify types
    //
    public void testParamReflectionWithTypes() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleREFLECTPARAMS_4);
        assertEquals(SimpleREFLECTPARAMS_3_RESULT, frag.toString());
    }

    //
    // test parsing with several reflected params + whitespace
    //
    public void testParamReflectionWhitespace() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleREFLECTPARAMS_5);
        assertEquals(SimpleREFLECTPARAMS_5_RESULT, frag.toString());
    }

    //
    // test parsing with several reflected params + a literal brace
    //
    public void testParamReflectionLiteralBrace() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleREFLECTPARAMS_6);
        assertEquals(SimpleREFLECTPARAMS_6, frag.toString());
    }

// //////////////////////////////////////// END REFLECTION TESTS //////////////////////////////////////////////////

    //
    // tests for jdbc escape parsing
    //


    //
    // JDBC call cmd
    //
    public void testJdbcCall() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleJDBCPARAMS_1);
        assertEquals(SimpleJDBCPARAMS_1_RESULT, frag.toString());
    }

    //
    // JDBC ?= cmd
    //
    public void testJdbcReturnCmd() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleJDBCPARAMS_2);
        assertEquals(SimpleJDBCPARAMS_2_RESULT, frag.toString());
    }

    //
    // JDBC d cmd
    //
    public void testJdbcDateCmd() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleJDBCPARAMS_3);
        assertEquals(SimpleJDBCPARAMS_3_RESULT, frag.toString());
    }

    //
    // JDBC escape cmd
    //
    public void testJdbcEscapeCmd() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleJDBCPARAMS_4);
        assertEquals(SimpleJDBCPARAMS_4, frag.toString());
    }

    //
    // JDBC fn cmd
    //
    public void testJdbcFnCmd() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleJDBCPARAMS_5);
        assertEquals(SimpleJDBCPARAMS_5, frag.toString());
    }

    //
    // JDBC d, t, ts cmds
    //
    public void testJdbcTimeCmds() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleJDBCPARAMS_6);
        assertEquals(SimpleJDBCPARAMS_6_RESULT, frag.toString());
    }

    //
    // JDBC outerjoin cmds
    //
    public void testJdbcOuterJoinCmd() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleJDBCPARAMS_7);
        assertEquals(SimpleJDBCPARAMS_7, frag.toString());
    }

    // ///////////////////////////////////////// End of JDBC cmd tests ////////////////////////////////////////////

    //
    // tests for sql escape parsing
    //


    //
    // tests sql:fn sub
    //
    public void testSqlFn() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleSQLPARAMS_1);
        assertEquals(SimpleSQLPARAMS_1_RESULT, frag.toString());
    }

    //
    // tests sql:subst
    //
    public void testSqlSubst() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleSQLPARAMS_2);
        assertEquals(SimpleSQLPARAMS_2_RESULT, frag.toString());
    }

    //
    // tests implicit sql:subst
    //
    public void testSqlImplicitSubst() {
        SqlStatement frag = null;
        assertNotNull(_parser);

        frag = _parser.parse(SimpleSQLPARAMS_3);
        assertEquals(SimpleSQLPARAMS_3_RESULT, frag.toString());
    }

   // ///////////////////////////////////////////// End of Sql Subst Tests ////////////////////////////////////////

    //
    // tests for badly formed sql expression parsing
    //

    //
    // statement missing '}'
    //
    public void testParseErrorMissingCloseBrace() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        try {
            frag = _parser.parse(BADLY_FORMED_1);
            fail("SQL statements missing '}' should raise SqlException");
        } catch (ControlException spe) {
            assertTrue(true);
        }
    }

    //
    // statement missing '{'
    //
    public void testParseErrorMissingOpenBrace() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        try {
            frag = _parser.parse(BADLY_FORMED_2);
            fail("SQL statements with unbalanced '{}'s should raise SqlException");
        } catch (ControlException spe) {
            assertTrue(true);
        }
    }

    //
    // statement has unbalanced 's
    //
    public void testParseErrorUnbalancedSquotes() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        try {
            frag = _parser.parse(BADLY_FORMED_3);
            fail("SQL statements with unbalanced 's should raise SqlException");
        } catch (ControlException spe) {
            assertTrue(true);
        }
    }

    //
    // statement has unbalanced invalid escape sequence 'sqlxxxx:'
    //
    public void testParseErrorInvalidEscape() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        try {
            frag = _parser.parse(BADLY_FORMED_4);
            fail("SQL statements with invalid escape sequences should raise SqlException");
        } catch (ControlException spe) {
            assertTrue(true);
        }
    }

    //
    // SQLEscapeFragment: statement has unbalanced invalid sql: keyword, must be either fn or subst
    //
    public void testParseErrorInvalidKeyWord() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        try {
            frag = _parser.parse(BADLY_FORMED_5);
            fail("SQL statements with sql: escapes can only map to 'fn' or 'subst' functions");
        } catch (ControlException spe) {
            assertTrue(true);
        }
    }

    //
    // SQLEscapeFragment: statement missing '}'
    //
    public void testParseErrorMissingEscapeBrace() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        try {
            frag = _parser.parse(BADLY_FORMED_6);
            fail("SQL statements with unbalanced 's should raise SqlException");
        } catch (ControlException spe) {
            assertTrue(true);
        }
    }

    //
    // ReflectFragment: statement missing param name
    //
    public void testParseErrorMissingParamName() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        try {
            frag = _parser.parse(BADLY_FORMED_7);
            fail("SQL statements with empty '{}'s should raise SqlException");
        } catch (ControlException spe) {
            assertTrue(true);
        }
    }

    //
    // ReflectFragment: statement missing '}'
    //
    public void testParseErrorMissingReflectBrace() throws Exception {
        SqlStatement frag = null;
        assertNotNull(_parser);

        try {
            frag = _parser.parse(BADLY_FORMED_8);
            fail("SQL statements with unbalanced {}'s should raise SqlException");
        } catch (ControlException spe) {
            assertTrue(true);
        }
    }

    public SqlParserTest(String name) { super(name); }

    public static Test suite() { return new TestSuite(SqlParserTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

