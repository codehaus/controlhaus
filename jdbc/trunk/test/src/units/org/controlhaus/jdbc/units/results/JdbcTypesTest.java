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
import org.controlhaus.jdbc.units.utils.TestContextInitializer;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Tests dbcontrol results for single row result sets
 */
public class JdbcTypesTest extends TestCase {

    private static final Logger _logger = Logger.getLogger(JdbcTypesTest.class);
    private ControlContainerContext _controlContext = null;

    @Control
    public ResultsTestCtrl testCtrl;

    @Control
    @JdbcControl.ConnectionDataSource(jndiName="java:comp/env/jdbc/TestDB")
    private ResultsTestCtrl testCtrl_ds;

    public void setUp() throws Exception {
        super.setUp();

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        _controlContext = ControlThreadContext.getContext();
        if (_controlContext == null) {
            _controlContext = TestContextInitializer.initContext(this);
        } else {
            JdbcTypesTestClientInitializer.initialize(_controlContext, this);
            testCtrl = testCtrl_ds;
        }

        // setup the database
        Connection conn = testCtrl.getConnection();
        Statement s = conn.createStatement();
        try {
            s.executeUpdate("DROP TABLE blob_table");
            s.executeUpdate("DROP TABLE clob_table");
        } catch (Exception e) {
        }

        s.executeUpdate("CREATE TABLE blob_table (id INT, BLB BLOB(4k))");
        s.executeUpdate("CREATE TABLE clob_table (id INT, CLB CLOB(8k))");
        s.executeUpdate("INSERT INTO clob_table VALUES (1234, 'thisisaclob1')");
        s.executeUpdate("INSERT INTO clob_table VALUES (5678, 'thisisaclob2')");
        conn = null;
    }

    public void tearDown() throws Exception {
        _controlContext.endContext();
        super.tearDown();
    }

    public void testBlob() throws Exception {

        assertNotNull(testCtrl);

        //
        // attempt to insert a blob value
        //
        Blob myBlob = new SerialBlob(new byte[] {1,2,3});
        int result = testCtrl.insertABlob(123, myBlob);
        assertEquals(result,1);

        //
        // retrieve the blob value just inserted and validate
        //
        ResultsTestCtrl.BlobInfo b = testCtrl.getABlob(123);
        assertNotNull(b);
        byte[] check = b.getBlb().getBytes(1,3);
        assertEquals(check[0], 1);
    }

    public void testClob() throws Exception {

        assertNotNull(testCtrl);

        //
        // get a clob and validate
        //
        Clob c = testCtrl.getAClob(1234);
        assertNotNull(c);
        assertEquals(c.getSubString(1,3), "thi");
    }

    public JdbcTypesTest(String name) throws Exception { super(name); }

    public static Test suite() { return new TestSuite(JdbcTypesTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

