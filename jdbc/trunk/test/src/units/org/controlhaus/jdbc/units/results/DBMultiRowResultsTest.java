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
import junit.framework.TestSuite;
import junit.framework.TestCase;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.controlhaus.jdbc.test.results.ResultsTestCtrl;
import org.controlhaus.jdbc.units.utils.TestContextInitializer;
import org.controlhaus.jdbc.JdbcControl;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 */
public class DBMultiRowResultsTest extends TestCase {
    private static final Logger _logger = Logger.getLogger(DBMultiRowResultsTest.class);
    private ControlContainerContext _controlContext = null;

    @Control
    public ResultsTestCtrl testCtrl;

    @Control
    @JdbcControl.ConnectionDataSource(jndiName="java:/comp/env/jdbc/TestDB")
    private ResultsTestCtrl testCtrl_ds;

    public void setUp() throws Exception {
        //BasicConfigurator.configure();
        super.setUp();

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        _controlContext = ControlThreadContext.getContext();
        if (_controlContext == null) {
            _controlContext = TestContextInitializer.initContext(this);
        } else {
            DBMultiRowResultsTestClientInitializer.initialize(_controlContext, this);
            testCtrl = testCtrl_ds;
        }

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
    // test array return type
    //
    public void testArrayReturnType() throws Exception {

        ResultsTestCtrl.Customer[] customers = testCtrl.getCustomerArray();
        assertNotNull(customers);
        assertEquals(customers.length, 4);
        assertEquals(customers[0].getFname(), "tester1");
        assertEquals(customers[3].userid, 24);
    }

    //
    // test array return type, restricted array size returned (2)
    //
    public void testArrayReturnTypeMaxSize() throws Exception {

        ResultsTestCtrl.Customer[] customers = testCtrl.getCustomerArrayLimitedSize();
        assertNotNull(customers);
        assertEquals(customers.length, 2);
    }

    //
    // test array return type, restricted array size returned (2), restricted maxrows (1)
    //
    public void testArrayReturnTypeMaxSize2() throws Exception {

        ResultsTestCtrl.Customer[] customers = testCtrl.getCustomerArrayLimitedSize2();
        assertNotNull(customers);
        assertEquals(customers.length, 1);
    }

    //
    // test array return type, restricted array size returned (2), restricted maxrows (4)
    //
    public void testArrayReturnTypeMaxSize3() throws Exception {

        ResultsTestCtrl.Customer[] customers = testCtrl.getCustomerArrayLimitedSize3();
        assertNotNull(customers);
        assertEquals(customers.length, 2);
    }

    //
    // test max rows, value of 1
    //
    public void testMaxRows1() throws Exception {

        ResultsTestCtrl.Customer[] customers = testCtrl.getCustomerArrayLimitedSize4();
        assertNotNull(customers);
        assertEquals(customers.length, 1);
    }

    //
    // test array of HashMap return type
    //
    public void testArrayHashMapReturnType() throws Exception {

        HashMap[] customerHashMap = testCtrl.getCustomerHashMapArray(22);
        assertTrue(customerHashMap.length > 0);
        assertEquals(customerHashMap[0].get("FNAME"), "tester2");
        assertEquals(customerHashMap[0].get("USERID"), 22);
    }


    //
    // test EMPTY array of HashMap return type
    //
    public void testEmptyArrayHashMapReturnType() throws Exception {

        HashMap[] customerHashMap = testCtrl.getCustomerHashMapArray(1000);
        assertEquals(customerHashMap.length, 0);
    }

    //
    // test Iterator return type
    //
    public void testIteratorReturnType() throws Exception {

        Iterator customersIterator = testCtrl.getCustomerIterator();
        assertNotNull(customersIterator);
        int idCheck = 21;
        assertTrue(customersIterator.hasNext());
        while (customersIterator.hasNext()) {
            ResultsTestCtrl.Customer c = (ResultsTestCtrl.Customer) customersIterator.next();
            assertEquals(idCheck, c.userid);
            idCheck++;
        }
        assertEquals(idCheck, 25);
    }

    //
    // test RowSet return type, defines its own rowset mapper
    //
    public void testRowSetReturnType() throws Exception {

        RowSet customersRowSet = testCtrl.getAllUsersINRS();
        assertNotNull(customersRowSet);

        customersRowSet.beforeFirst();
        customersRowSet.next();
        assertEquals(customersRowSet.getInt("USERID"), 21);

    }

    //
    // test scrollable result set feature, sensitive / updateable
    //
    public void testScrollableInsensitiveUpdateablepResultSet() throws Exception {
        try {
            ResultSet rs = testCtrl.getScrollableResultSet_IU();
            fail("This feature has not been impelented in Derby yet (1/19/2005), need to add test case once it has.");
        } catch (ControlException ce) {
            assertTrue(true);
        }
    }

    //
    // test scrollable result set feature, forward only / updateable
    //
    public void testScrollableSensitiveResultSet() throws Exception {
        try {
            ResultSet rs = testCtrl.getScrollableResultSet_SR();
            fail("This feature has not been impelented in Derby yet (1/19/2005), need to add test case once it has.");
        } catch (ControlException ce) {
            assertTrue(true);
        }
//        rs.afterLast();
//        rs.beforeFirst();
//        rs.next();
//        rs.next();
//        assertEquals(rs.getInt(2), 22);
//        rs.close();
    }

    //
    // test holdable result set cursors
    //
    public void testResultSetHoldability() throws Exception {
        ResultSet rs = testCtrl.getResultSetHoldablity();
    }

    //
    // test for fetchSize / fetchDirection
    //
    public void testFetchSizeDirection() throws Exception {
        ResultSet rs = testCtrl.getFetchOptmizedResultSet();
        rs.next();
        assertEquals(rs.getInt(2), 21);
    }

    //
    // test single column result set mapping
    //
    public void testSingleColumnResultSetMapping() throws Exception {
        String fnames[] = testCtrl.getFnameColumn();
        assertEquals(fnames[1], "tester2");
        assertEquals(fnames[3], "tester4");
    }

    public DBMultiRowResultsTest(String name) throws Exception {
        super(name);
    }

    public static Test suite() { return new TestSuite(DBMultiRowResultsTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

