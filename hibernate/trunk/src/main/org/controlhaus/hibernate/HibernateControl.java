package org.controlhaus.hibernate;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;

import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.properties.PropertySet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

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
    
    Transaction getTransaction();
    
    /**
     * Close the session for the current Thread.
     * @throws HibernateException
     */
    void closeSession() throws HibernateException;
    
    @PropertySet(prefix="ManagedTransactions")
    @Target( {ElementType.TYPE, ElementType.FIELD, ElementType.METHOD} )
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ManagedTransactions
    {
        boolean value() default false;
    }
}
