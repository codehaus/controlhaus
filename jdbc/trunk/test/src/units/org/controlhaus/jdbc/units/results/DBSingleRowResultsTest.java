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
import java.sql.SQLException;
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
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    //
    // test query return ResultSet
    //
    public void testResultSetReturnType() throws Exception {

        assertNotNull(testCtrl);

        ResultSet rs = testCtrl.getAllUsers();
        assertNotNull(rs);
        rs.next();
        String name = rs.getString("FNAME");
        assertEquals(name, "tester1");
        rs.close();
    }

    //
    // test query with sql: escape, returns ResultSet
    //
    public void testSqlEscapeResultSet() throws Exception {

        ResultSet rs = testCtrl.getSomeUser("tester4");
        assertNotNull(rs);
        rs.next();
        String name = rs.getString("FNAME");
        assertEquals(name, "tester4");
        rs.close();

        rs = testCtrl.getSomeUser(24);
        assertNotNull(rs);
        rs.next();
        name = rs.getString("FNAME");
        assertEquals(name, "tester4");
        rs.close();
    }

    //
    // test query with sql: escape
    //
    public void testSqlEscape() throws Exception {

        ResultSet rs = testCtrl.getJustOneUser("fname='tester4'");
        assertNotNull(rs);
        rs.next();
        String name = rs.getString("FNAME");
        assertEquals(name, "tester4");
        rs.close();
    }

    //
    // test HashMap return type
    //
    public void testHashMapReturnType() throws Exception {

        HashMap customerHashMap = testCtrl.getCustomerHashMap(23);
        assertNotNull(customerHashMap);
        assertEquals(customerHashMap.get("FNAME"), "tester3");
    }

    //
    // test Map return type
    //
    public void testMapReturnType() throws Exception {

        Map customerMap = testCtrl.getCustomerMap(22);
        assertNotNull(customerMap);
        assertEquals(customerMap.get("FNAME"), "tester2");
    }

    //
    // test null / both Object and int return Types
    //
    public void testNullReturnType() throws Exception {

        int prim = testCtrl.getNoUsers(111);
        assertEquals(prim, 0);

        ResultsTestCtrl.Customer customer = testCtrl.getACustomer(111);
        assertNull(customer);
    }

    //
    // test Object return type
    //
    public void testObjectReturnType() throws Exception {

        ResultsTestCtrl.Customer customer = testCtrl.getACustomer(23);
        assertNotNull(customer);
        assertEquals(customer.getFname(), "tester3");
        assertEquals(customer.userid, 23);
    }

    //
    // test XmlBean return type
    //
    public void testXmlBeanReturnType() throws Exception {

        XCustomerRowDocument.XCustomerRow customerXmlObj = testCtrl.getAUserXmlBean("tester2");
        assertNotNull(customerXmlObj);

        assertEquals(customerXmlObj.getUSERID(), 22);
        assertEquals(customerXmlObj.getFNAME(), "tester2");
    }

    //
    // add some new users to the table using batch update
    //
    public void testBatchUpdate() throws Exception {

        int[] results = testCtrl.doABatchUpdate(
                new String[] {"tester44", "tester55", "tester66"}, new int[] {44, 55, 66});
        assertEquals(1, results[0]);
        assertEquals(1, results[1]);
        assertEquals(1, results[2]);
    }

    //
    // get the generated keys from the SQL statement
    //
    public void testGenKeys() throws Exception {
        ResultSet rs = testCtrl.getGenKeys("genmeanotherkey");
        assertNotNull(rs);

        rs.next();
        int generatedId = rs.getInt(1);
        assertEquals(generatedId, 2);
        rs.close();
    }

    //
    // get the generated keys from the SQL statement -- with specified column names
    //
    public void testGenKeysWithColumnNames() throws Exception {
        try {
        ResultSet rs = testCtrl.getGenKeys2("genmeanotherkey2");
        fail("This feature has not been impelented in Derby yet, need to add test case once it has.");
        } catch (Exception e) {
           assertTrue(true);
        }
    }

    //
    // get the generated keys from the SQL statement -- with specified column names and return type mapping
    //
    public void testGenKeysReturnTypeMapping() throws Exception {
        int result = testCtrl.getGenKeys3("genmeanotherkey3");
        assertEquals(result, 3);
    }

    //
    // get the generated keys from the SQL statement -- with specified column names and return type mapping
    //
    public void testGenKeysReturnTypeMapping2() throws Exception {
        String result = testCtrl.getGenKeys4("genmeanotherkey4");
        assertEquals(result, "4");
    }

    //
    // get the generated keys from the SQL statement -- with specified column names and return type mapping
    //
    public void testGenKeysReturnTypeMapping3() throws Exception {
        int[] result = testCtrl.getGenKeys5("genmeanotherkey5");
        assertEquals(result[0], 5);
    }



    public DBSingleRowResultsTest(String name) throws Exception {
        super(name);

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        // setup the database
        Connection conn = DriverManager.getConnection("jdbc:derby:MyDB;create=true");
        Statement s = conn.createStatement();
        try {
            s.executeUpdate("DROP TABLE users");
            s.executeUpdate("DROP TABLE usergen");
        } catch (Exception e) {
        }

        s.executeUpdate("CREATE TABLE USERS (FNAME VARCHAR(32), USERID INT)");
        s.executeUpdate("INSERT INTO USERS VALUES ('tester1', 21)");
        s.executeUpdate("INSERT INTO USERS VALUES ('tester2', 22)");
        s.executeUpdate("INSERT INTO USERS VALUES ('tester3', 23)");
        s.executeUpdate("INSERT INTO USERS VALUES ('tester4', 24)");

        s.executeUpdate("CREATE TABLE usergen (user_id INT GENERATED ALWAYS AS IDENTITY (START WITH 1) CONSTRAINT people_pk PRIMARY KEY, person VARCHAR(128))");
        s.executeUpdate("INSERT INTO usergen (person) VALUES ('genmeakey')");

        conn.close();
    }

    public static Test suite() { return new TestSuite(DBSingleRowResultsTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

