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

package org.controlhaus.jdbc.units.results;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;
import org.apache.log4j.Logger;
import org.controlhaus.jdbc.JdbcControl;
import org.controlhaus.jdbc.test.results.ResultsTestCtrl;
import org.controlhaus.jdbc.test.results.TxTestCtrl;
import org.controlhaus.jdbc.units.utils.TestContextInitializer;
import test.customerDb.XStoogeRowDocument;
import test.customerDb.SMLXSizeType;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;

/**
 * Transaction tests -- sanity check for db tx support
 */
public class TxResultsTest extends TestCase {

    private static final Logger _logger = Logger.getLogger(TxResultsTest.class);
    private ControlContainerContext _controlContext = null;

    @Control
    public TxTestCtrl testCtrl;

    @Control
    @JdbcControl.ConnectionDataSource(jndiName = "java:/comp/env/jdbc/TestDB")
    private TxTestCtrl testCtrl_ds;

    public void setUp() throws Exception {
        super.setUp();

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        _controlContext = ControlThreadContext.getContext();
        if (_controlContext == null) {
            _controlContext = TestContextInitializer.initContext(this);
        } else {
            TxResultsTestClientInitializer.initialize(_controlContext, this);
            testCtrl = testCtrl_ds;
        }

        Connection conn = testCtrl.getConnection();
        Statement s = conn.createStatement();
        try {
            s.executeUpdate("drop table TX_USERS");
        } catch (Exception e) {
        }

        s.executeUpdate("create table TX_USERS (STOOGE_NAME varchar(32), STOOGE_PECKINGORDER int, STOOGE_PANTSIZE varchar(16))");
        conn = null;
    }

    public void tearDown() throws Exception {
        _controlContext.endContext();
        super.tearDown();
    }

    //
    // test transaction
    //
    public void testTxSupport() throws Exception {

        Connection con = testCtrl.getConnection();
        con.setAutoCommit(false);
        testCtrl.insertUserRow("moe", 1, "small");
        con.commit();
    }

    //
    // test test transaction commit
    //
    public void testTxCommit() throws Exception {

        Connection con = testCtrl.getConnection();
        con.setAutoCommit(false);
        testCtrl.insertUserRow("larry", 1, "small");
        testCtrl.insertUserRow("shemp", 1, "small");
        con.commit();
    }

    //
    // test test transaction rollback
    //
    public void testTxRollback() throws Exception {

        Connection con = testCtrl.getConnection();
        con.setAutoCommit(false);
        assertFalse(con.getAutoCommit());
        testCtrl.insertUserRow("curley", 1, "small");
        con.rollback();

        String nm = testCtrl.getAUser("curley");
        System.out.println("MN IS : x"+nm);
        assertNull(nm);
        con.commit();
    }


    public TxResultsTest(String name) throws Exception {
        super(name);
    }

    public static Test suite() { return new TestSuite(TxResultsTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

