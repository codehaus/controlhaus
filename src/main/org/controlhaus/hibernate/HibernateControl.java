package org.controlhaus.hibernate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;

import org.apache.beehive.controls.api.bean.ControlInterface;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Oct 29, 2004
 */
@ControlInterface
public interface HibernateControl
{
    /**
     * Get the Hibernate <code>SessionFactory</code>.
     * @return
     */
    SessionFactory getSessionFactory();
    
    /**
     * Return the session that is currently associated with this Thread.
     * 
     * @return
     * @throws HibernateException
     */
    Session getSession() throws HibernateException;
    
    /**
     * Close the session for the current Thread.
     * @throws HibernateException
     */
    void closeSession() throws HibernateException;
    
    String getConfigurationLocation();
    
    String getHibernateInstance();
}
