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
import org.apache.beehive.controls.api.bean.Control;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.controlhaus.jdbc.test.results.ResultsTestCtrl;
import org.controlhaus.jdbc.units.utils.AbstractControlTest;
import test.customerDb.XCustomerRowDocument;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests dbcontrol results for single row result sets
 */
public class DBSingleRowResultsTest extends AbstractControlTest {

    private static final Logger _logger = Logger.getLogger(DBSingleRowResultsTest.class);

    @Control
            public ResultsTestCtrl testCtrl;

    public void setUp() throws Exception {
        BasicConfigurator.configure();
        super.setUp();

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        // setup the database
        Connection conn = DriverManager.getConnection("jdbc:derby:MyDB;create=true");
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
        conn.close();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSingleRowResults() throws Exception {

        assertNotNull(testCtrl);

        //
        // test query return ResultSet
        //
        ResultSet rs = testCtrl.getAllUsers();
        assertNotNull(rs);
        rs.next();
        String name = rs.getString("FNAME");
        assertEquals(name, "tester1");
        rs.close();

        //
        // test query with sql: escape, returns ResultSet
        //
        rs = testCtrl.getSomeUser("tester4");
        assertNotNull(rs);
        rs.next();
        name = rs.getString("FNAME");
        assertEquals(name, "tester4");
        rs.close();

        rs = testCtrl.getSomeUser(24);
        assertNotNull(rs);
        rs.next();
        name = rs.getString("FNAME");
        assertEquals(name, "tester4");
        rs.close();

        //
        // test query with sql: escape
        //
        rs = testCtrl.getJustOneUser("fname='tester4'");
        assertNotNull(rs);
        rs.next();
        name = rs.getString("FNAME");
        assertEquals(name, "tester4");
        rs.close();

        //
        // test HashMap return type
        //
        HashMap customerHashMap = testCtrl.getCustomerHashMap(23);
        assertNotNull(customerHashMap);
        assertEquals(customerHashMap.get("FNAME"), "tester3");

        //
        // test Map return type
        //
        Map customerMap = testCtrl.getCustomerMap(22);
        assertNotNull(customerMap);
        assertEquals(customerMap.get("FNAME"), "tester2");

        //
        // test null / both Object and int return Types
        //
        int prim = testCtrl.getNoUsers(111);
        assertEquals(prim, 0);

        ResultsTestCtrl.Customer customer = testCtrl.getACustomer(111);
        assertNull(customer);

        //
        // test Object return type
        //

        customer = testCtrl.getACustomer(23);
        assertNotNull(customer);
        assertEquals(customer.getFname(), "tester3");
        assertEquals(customer.userid, 23);


        //
        // test XmlBean return type
        //
        XCustomerRowDocument.XCustomerRow customerXmlObj = testCtrl.getAUserXmlBean("tester2");
        assertNotNull(customerXmlObj);

        assertEquals(customerXmlObj.getUSERID(), 22);
        assertEquals(customerXmlObj.getFNAME(), "tester2");

        //
        // add some new users to the table using batch update
        //
        int[] results = testCtrl.doABatchUpdate(
                new String[] {"tester44", "tester55", "tester66"}, new int[] {44, 55, 66});
        assertEquals(1, results[0]);
        assertEquals(1, results[1]);
        assertEquals(1, results[2]);
    }


    public DBSingleRowResultsTest(String name) { super(name); }

    public static Test suite() { return new TestSuite(DBSingleRowResultsTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

