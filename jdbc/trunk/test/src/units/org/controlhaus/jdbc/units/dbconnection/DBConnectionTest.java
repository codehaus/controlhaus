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

package org.controlhaus.jdbc.units.dbconnection;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.controlhaus.jdbc.test.dbconnection.DriverManagerConnectionCtrl;
import org.controlhaus.jdbc.test.dbconnection.DriverManagerConnectionCtrlAuth;
import org.controlhaus.jdbc.test.dbconnection.DriverManagerConnectionCtrlProps;
import org.controlhaus.jdbc.units.utils.TestContextInitializer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 */
public class DBConnectionTest extends TestCase {
    private static final Logger _logger = Logger.getLogger(DBConnectionTest.class);

    private ControlContainerContext _controlContext = null;

    @Control
    public DriverManagerConnectionCtrl testCtrl;
    @Control
    public DriverManagerConnectionCtrlAuth testAuthCtrl;
    @Control
    public DriverManagerConnectionCtrlProps testPropsCtrl;

    public void setUp() throws Exception {
        // BasicConfigurator.configure();
        super.setUp();
        _controlContext = TestContextInitializer.initContext(this);
    }

    public void tearDown() throws Exception {
        super.tearDown();
        _controlContext.endContext();
    }


    public void testDriverMgrConnection_simple() throws Exception {
        assertNotNull(testCtrl);
        testCtrl.getConnection();
    }

    public void testDriverMgrConnection_auth() throws Exception {

        // add authentication and test
        Connection conn = DriverManager.getConnection("jdbc:derby:MyDBAuth;create=true");
        Statement s = conn.createStatement();

       // Setting and Confirming requireAuthentication
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.connection.requireAuthentication', 'true')");
        ResultSet rs = s.executeQuery(
            "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
            "'derby.connection.requireAuthentication')");
        rs.next();
        //System.out.println(rs.getString(1));

        // Setting authentication scheme to Derby
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.authentication.provider', 'BUILTIN')");

        // Creating some sample users
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.user.foo', 'bar')");
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.user.test', 'test')");

        // Setting default connection mode to no access
        // (user authorization)
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.database.defaultConnectionMode', 'noAccess')");
        // Confirming default connection mode
        rs = s.executeQuery (
            "VALUES SYSCS_UTIL.SYSCS_GET_DATABASE_PROPERTY(" +
            "'derby.database.defaultConnectionMode')");
        rs.next();
        assertEquals(rs.getString(1), "noAccess");

        // Defining read-write users
        s.executeUpdate("CALL SYSCS_UTIL.SYSCS_SET_DATABASE_PROPERTY(" +
            "'derby.database.fullAccessUsers', 'foo')");

        conn.close();

        assertNotNull(testAuthCtrl);
        testAuthCtrl.getConnection();
    }

    public void testDriverMgrConnection_props() throws Exception {
        assertNotNull(testPropsCtrl);
        testPropsCtrl.getConnection();
    }

    public DBConnectionTest(String name) { super(name); }

    public static Test suite() { return new TestSuite(DBConnectionTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

