package servlet;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.apache.beehive.netui.util.logging.Logger;

/**
 *
 */
public class DerbyListener implements ServletContextListener {

    private static final Logger LOGGER = Logger.getInstance(DerbyListener.class);
    private static final String _dbUrlStr = "jdbc:derby:JdbcControlSampleDB";

    public void contextInitialized(ServletContextEvent event) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch(Exception e) {
            assert e instanceof ClassNotFoundException;
            e.printStackTrace();
            LOGGER.error("Exception occurred starting webapp context.  Cause: " + e.getMessage(), e);
        }
    }

    public void contextDestroyed(ServletContextEvent event) {
        try {
            DriverManager.getConnection(_dbUrlStr + ";shudown=true");
        } catch(SQLException sql) {
            sql.printStackTrace();
            LOGGER.error("Exception occurred stopping webapp context.  Cause: " + sql.getMessage(), sql);
        }
    }
}
