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

package org.controlhaus.jdbc.units.errors;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.controlhaus.jdbc.test.errors.ErrorsTestCtrl;
import org.controlhaus.jdbc.units.utils.TestContextInitializer;
import org.controlhaus.jdbc.JdbcControl;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Tests dbcontrol error handling and recovery
 */
public final class ErrorPathsTest extends TestCase {

    private static final Logger _logger = Logger.getLogger(ErrorPathsTest.class);
    private ControlContainerContext _controlContext = null;

    @Control
    public ErrorsTestCtrl testCtrl;

    @Control
    @JdbcControl.ConnectionDataSource(jndiName="java:/comp/env/jdbc/TestDB")
    private ErrorsTestCtrl testCtrl_ds;

    public void setUp() throws Exception {
        super.setUp();

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        _controlContext = ControlThreadContext.getContext();
        if (_controlContext == null) {
            _controlContext = TestContextInitializer.initContext(this);
        } else {
            ErrorPathsTestClientInitializer.initialize(_controlContext, this);
            testCtrl = testCtrl_ds;
        }

        // setup the database
        Connection conn = testCtrl.getConnection();
        Statement s = conn.createStatement();
        try {
            s.executeUpdate("DROP TABLE USERS");
        } catch (Exception e) {
        }

        s.executeUpdate("CREATE TABLE USERS (FNAME VARCHAR(32), USERID INT)");
        s.executeUpdate("INSERT INTO USERS VALUES ('tester1', 21)");
        s.executeUpdate("INSERT INTO USERS VALUES ('tester2', 22)");
        s.executeUpdate("INSERT INTO USERS VALUES ('tester3', 23)");
        s.executeUpdate("INSERT INTO USERS VALUES ('tester4', 24)");
        conn = null;
    }

    public void tearDown() throws Exception {
        _controlContext.endContext();
        super.tearDown();
    }

    //
    // test for a control method missing an @SQL annotation
    //
    public void testMissingSQLAnnotation() throws Exception {

        assertNotNull(testCtrl);

        try {
            testCtrl.getAllUsersBad1();
            fail("A ControlException should be raised when the @SQL annoation is missing from a dbControl method.");
        } catch (ControlException ce) {
           assertTrue(true);
        }
    }

    //
    // test for a failed object mapping
    //
    public void testFailedObjectMapping() throws Exception {
        try {
            testCtrl.getAUserBad1("tester1");
            fail("A ControlException should be raised when an ResultSet->Object mapping fails.");
        } catch (ControlException ce) {
            assertTrue(true);
        }
    }

    //
    // test for a failed object mapping to a object type mismatch
    //
    public void testObjectMappingMismatch() throws Exception {
        try {
            testCtrl.getAUserBad3("tester1");
            fail("A ControlException should be raised when an ResultSet->Object mapping fails due to type mismatch.");
        } catch (ControlException ce) {
            assertTrue(true);
        }
    }

    //
    // test for a failed XML object mapping
    //
    public void testFailedXmlObjectMapping() throws Exception {
        try {
            testCtrl.getAUserBad4("tester2");
            fail("A ControlException should be raised when an ResultSet->XmlObject mapping fails due to type mismatch.");
        } catch (ControlException ce) {
            assertTrue(true);
        }
    }

    //
    // test a null value in an sql: substitution cmd
    //
    public void testNullSqlSubValue() throws Exception {
        testCtrl.getAUserBad5(null);
    }

    public ErrorPathsTest(String name) throws Exception { super(name); }

    public static Test suite() { return new TestSuite(ErrorPathsTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

