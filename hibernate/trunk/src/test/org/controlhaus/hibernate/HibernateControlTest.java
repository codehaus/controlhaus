package org.controlhaus.hibernate;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

import junit.framework.TestCase;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;
import org.controlhaus.hibernate.util.AbstractHibernateTest;
import org.controlhaus.hibernate.HibernateControl.ManagedTransactions;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Oct 28, 2004
 */
public class HibernateControlTest
    extends AbstractHibernateTest
{
    @Control HibernateControl hib;
    
    @ManagedTransactions(true)
    @Control HibernateControl txHib;

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
        Transaction t = hib.getTransaction();
        assertNull(t);
        
        session.save(new Parent());

        session.flush();
        hib.closeSession();
        
        session = hib.getSession();
        
        List results = session.find("select from " + Parent.class.getName());
        assertEquals(1, results.size());
    }

    public void testControlTXs() 
        throws Exception
    {
        assertNotNull(txHib);

        Session s = txHib.getSession();
        Transaction t = txHib.getTransaction();
        assertNotNull(t);

        s.save(new Parent());

        s.flush();

        List results = s.find("select from " + Parent.class.getName());
        assertEquals(1, results.size());
    }
}
