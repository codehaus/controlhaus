/*
* Copyright 2005 BEA Systems, Inc.
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

package org.controlhaus.jdbc.containertest;

import junit.framework.Test;
import junit.framework.TestCase;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlThreadContext;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.cactus.ServletTestSuite;
import org.controlhaus.jdbc.test.dbconnection.DataSourceConnectionCtrl;
import org.controlhaus.jdbc.units.errors.ErrorPathsTest;
import org.controlhaus.jdbc.units.results.DBMultiRowResultsTest;
import org.controlhaus.jdbc.units.results.DBSingleRowResultsTest;
import org.controlhaus.jdbc.units.results.JdbcTypesTest;
import org.controlhaus.jdbc.units.results.XmlBeanResultsTest;
import org.controlhaus.jdbc.units.sqlparser.SqlParserTest;

import java.sql.Connection;
import java.sql.SQLException;

/**
 */
public class TestJdbcControlContainer extends TestCase {

    private ControlContainerContext _controlContext = null;

    @Control
    private DataSourceConnectionCtrl ctrl;

    /**
     * Wrap the standalone Junit tests in Cactus.
     *
     * @return
     */
    public static Test suite() {
        ServletTestSuite suite = new ServletTestSuite();
        suite.addTestSuite(TestJdbcControlContainer.class);
        suite.addTestSuite(DBSingleRowResultsTest.class);
        suite.addTestSuite(DBMultiRowResultsTest.class);
        suite.addTestSuite(XmlBeanResultsTest.class);
        suite.addTestSuite(JdbcTypesTest.class);
        suite.addTestSuite(SqlParserTest.class);
        suite.addTestSuite(ErrorPathsTest.class);
        return suite;
    }

    public void setUp() throws Exception {
        super.setUp();
        _controlContext = ControlThreadContext.getContext();
        TestJdbcControlContainerClientInitializer.initialize(_controlContext, this);
    }

    public void testDataSourceConnection() throws SQLException {
        Connection conn = ctrl.getConnection();
        assertNotNull(conn);
    }
}
