package org.controlhaus.hibernate.util;

import java.lang.reflect.Method;
import java.sql.Connection;

import junit.framework.TestCase;

import net.sf.hibernate.Session;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;
import org.controlhaus.hibernate.HibernateControl;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 5, 2004
 */
public class AbstractHibernateTest
    extends TestCase
{
    public final static String SETUP_SQL = "hibernate.setupSql";
    public final static String TEARDOWN_SQL = "hibernate.teardownSql";

    private ControlContainerContext context;
    private Connection conn;

    @Control HibernateControl hibernate;
    
    public void setUp() throws Exception
    {
        super.setUp();
        
        context = new ControlContainerContext();
        
        Class top = getClass();
        while ( top != null )
        {
            initializeClass( top );
            top = top.getSuperclass();
        }

        String filename = System.getProperty(SETUP_SQL);

        if ( filename != null )
        {
	        try
	        {
	            insertSqlFile(filename);
	        }
	        catch ( Exception e )
	        {
	            e.printStackTrace();
	        }
        }
    }

    protected void initializeClass( Class clazz )
    	throws Exception
    {
        try
        {
            Class init = getClass().getClassLoader().loadClass( 
                    clazz.getName() + "ClientInitializer" );
            
            Method m = init.getMethod("initialize", 
                                      new Class[] 
                                      { 
                                            ControlBeanContext.class, 
                                            clazz
                                      } );
            
            m.invoke( null, new Object[] { context, this } );
        }
        catch ( ClassNotFoundException cnfe )
        {
            // do nothing.
        }
    }
    
    protected void insertSqlFile( String filename ) throws Exception
    {
        if ( hibernate == null )
            throw new RuntimeException("AbstractHibernateTest was not initialized!");
        
        Session sess = hibernate.getSessionFactory().openSession();
        conn = sess.connection();
        System.out.println("Loading SQL " + filename);
        BatchSqlCommandRunner runner = new BatchSqlCommandRunner(conn);
        runner.runCommands( filename );

        sess.close();
    }

    public void tearDown() throws Exception
    {
        String filename = System.getProperty(TEARDOWN_SQL);

        if ( filename != null )
        {
	        try
	        {
	            insertSqlFile(filename);
	        }
	        catch ( Exception e )
	        {
	            e.printStackTrace();
	        }
        }

        conn = null;

        super.tearDown();
    }
    
    public ControlContainerContext getContext()
    {
        return context;
    }
}
