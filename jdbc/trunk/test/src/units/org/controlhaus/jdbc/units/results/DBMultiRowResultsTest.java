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

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Iterator;
import java.util.HashMap;

/**
 *
 */
public class DBMultiRowResultsTest extends AbstractControlTest {
    private static final Logger _logger = Logger.getLogger(DBMultiRowResultsTest.class);

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

    public void testMultiRowResults() throws Exception {

        //
        // test array return type
        //
        ResultsTestCtrl.Customer[] customers = testCtrl.getCustomerArray();
        assertNotNull(customers);
        assertEquals(customers.length, 4);
        assertEquals(customers[0].getFname(), "tester1");
        assertEquals(customers[3].userid, 24);

        //
        // test array return type, restricted array size returned (2)
        //
        customers = testCtrl.getCustomerArrayLimitedSize();
        assertNotNull(customers);
        assertEquals(customers.length, 2);


        //
        // test array of HashMap return type
        //
        HashMap[] customerHashMap = testCtrl.getCustomerHashMapArray(22);
        assertTrue(customerHashMap.length > 0);
        assertEquals(customerHashMap[0].get("FNAME"),"tester2");
        assertEquals(customerHashMap[0].get("USERID"), 22);


        //
        // test EMPTY array of HashMap return type
        //
        customerHashMap = testCtrl.getCustomerHashMapArray(1000);
        assertEquals(customerHashMap.length, 0);


        //
        // test Iterator return type
        //
        Iterator customersIterator = testCtrl.getCustomerIterator();
        assertNotNull(customers);
        int idCheck = 21;
        assertTrue(customersIterator.hasNext());
        while (customersIterator.hasNext()) {
            ResultsTestCtrl.Customer c = (ResultsTestCtrl.Customer) customersIterator.next();
            assertEquals(idCheck, c.userid);
            idCheck++;
        }
        assertEquals(idCheck, 25);

        //
        // test RowSet return type, defines its own rowset mapper
        //
        RowSet customersRowSet = testCtrl.getAllUsersINRS();
        assertNotNull(customersRowSet);

        customersRowSet.beforeFirst();
        customersRowSet.next();
        assertEquals(customersRowSet.getInt("USERID"), 21);

        //
        // test XmlBean array return type
        //
        XCustomerRowDocument.XCustomerRow[] customersXml = testCtrl.getAllUserXmlBean();
        assertNotNull(customers);

        assertEquals(customersXml.length, 4);
        assertEquals(customersXml[0].getFNAME(), "tester1");
        assertEquals(customersXml[0].getUSERID(), 21);
        assertEquals(customersXml[1].getFNAME(), "tester2");
        assertEquals(customersXml[1].getUSERID(), 22);
        assertEquals(customersXml[2].getFNAME(), "tester3");
        assertEquals(customersXml[2].getUSERID(), 23);
        assertEquals(customersXml[3].getFNAME(), "tester4");
        assertEquals(customersXml[3].getUSERID(), 24);
    }


    public DBMultiRowResultsTest(String name) { super(name); }

    public static Test suite() { return new TestSuite(DBMultiRowResultsTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

