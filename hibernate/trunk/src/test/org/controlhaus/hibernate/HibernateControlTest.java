package org.controlhaus.hibernate;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import junit.framework.TestCase;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;
import org.controlhaus.hibernate.util.AbstractHibernateTest;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Oct 28, 2004
 */
public class HibernateControlTest
    extends AbstractHibernateTest
{
    @Control HibernateControl hib;

    public void setUp() throws Exception
    {
        System.setProperty(SETUP_SQL, 
                new File("./src/sql/setup.sql").getAbsolutePath());
        System.setProperty(TEARDOWN_SQL, 
                new File("./src/sql/teardown.sql").getAbsolutePath());
        super.setUp();
    }
    
    public void testControl() 
        throws Exception
    {
        assertNotNull(hib);

        SessionFactory factory = hib.getSessionFactory();
        assertNotNull(factory);
        
        Session session = hib.getSession();

        session.save(new Parent());

        session.flush();
        hib.closeSession();
        
        session = hib.getSession();
        
        List results = session.find("select from " + Parent.class.getName());
        assertEquals(1, results.size());
    }
}
