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
import test.customerDb.XStoogeRowDocument;
import test.customerDb.SMLXSizeType;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 */
public class XmlBeanResultsTest extends TestCase {

    private static final Logger _logger = Logger.getLogger(XmlBeanResultsTest.class);
    private ControlContainerContext _controlContext = null;

    @Control
            public ResultsTestCtrl testCtrl;

    @Control
            @JdbcControl.ConnectionDataSource(jndiName = "java:/comp/env/jdbc/TestDB")
            private ResultsTestCtrl testCtrl_ds;

    public void setUp() throws Exception {
        super.setUp();

        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        _controlContext = ControlThreadContext.getContext();
        if (_controlContext == null) {
            _controlContext = TestContextInitializer.initContext(this);
        } else {
            XmlBeanResultsTestClientInitializer.initialize(_controlContext, this);
            testCtrl = testCtrl_ds;
        }

        Connection conn = testCtrl.getConnection();
        Statement s = conn.createStatement();
        try {
            s.executeUpdate("drop table XBEAN_USERS");
        } catch (Exception e) {
        }

        s.executeUpdate("create table XBEAN_USERS (STOOGE_NAME varchar(32), STOOGE_PECKINGORDER int, STOOGE_PANTSIZE varchar(16))");
        s.executeUpdate("insert into XBEAN_USERS values ('larry', 3, 'small')");
        s.executeUpdate("insert into XBEAN_USERS values ('moe', 1, 'medium')");
        s.executeUpdate("insert into XBEAN_USERS values ('curley', 2, 'xlarge')");
        s.executeUpdate("insert into XBEAN_USERS values ('shemp', 4, 'large')");
        conn = null;
    }

    public void tearDown() throws Exception {
        _controlContext.endContext();
        super.tearDown();
    }

    //
    // test XmlBean return type
    //
    public void testXmlBeanReturnType() throws Exception {

        XStoogeRowDocument.XStoogeRow customerXmlObj = testCtrl.getAUserXmlBean("moe");
        assertNotNull(customerXmlObj);

        assertEquals(1, customerXmlObj.getSTOOGEPECKINGORDER());
        assertEquals("moe", customerXmlObj.getSTOOGENAME());
        assertEquals("medium", customerXmlObj.getSTOOGEPANTSIZE().toString());
    }

    //
    // test XmlBean array return type
    //
    public void testXmlBeanArrayReturnType() throws Exception {

        XStoogeRowDocument.XStoogeRow[] customersXml = testCtrl.getAllUserXmlBean();
        assertNotNull(customersXml);

        assertEquals(customersXml.length, 4);
        assertEquals("larry", customersXml[0].getSTOOGENAME());
        assertEquals(3, customersXml[0].getSTOOGEPECKINGORDER());
        assertEquals("small", customersXml[0].getSTOOGEPANTSIZE().toString());

        assertEquals("moe", customersXml[1].getSTOOGENAME());
        assertEquals(1, customersXml[1].getSTOOGEPECKINGORDER());
        assertEquals("medium", customersXml[1].getSTOOGEPANTSIZE().toString());

        assertEquals("curley", customersXml[2].getSTOOGENAME());
        assertEquals(2, customersXml[2].getSTOOGEPECKINGORDER());
        assertEquals("xlarge", customersXml[2].getSTOOGEPANTSIZE().toString());

        assertEquals("shemp", customersXml[3].getSTOOGENAME());
        assertEquals(4, customersXml[3].getSTOOGEPECKINGORDER());
        assertEquals("large", customersXml[3].getSTOOGEPANTSIZE().toString());
    }

    //
    // insert an xmlbean
    //
    public void testInsertXmlBean() throws Exception {
        XStoogeRowDocument.XStoogeRow stooge = XStoogeRowDocument.XStoogeRow.Factory.newInstance();
        assertNotNull(stooge);
        stooge.setSTOOGENAME("fred");
        stooge.setSTOOGEPECKINGORDER(9);
        stooge.setSTOOGEPANTSIZE(SMLXSizeType.Enum.forString("small"));
        testCtrl.insertAXmlBean(stooge);

        XStoogeRowDocument.XStoogeRow res = testCtrl.getAUserXmlBean("fred");
        assertEquals(9, res.getSTOOGEPECKINGORDER());
        assertEquals("fred", res.getSTOOGENAME());
        assertEquals("small", res.getSTOOGEPANTSIZE().toString());
    }

    public XmlBeanResultsTest(String name) throws Exception {
        super(name);
    }

    public static Test suite() { return new TestSuite(XmlBeanResultsTest.class); }

    public static void main(String[] args) { junit.textui.TestRunner.run(suite()); }
}

