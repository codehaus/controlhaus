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
 * The HibernateControl allows easy session and transaction management
 * between Beehive Controls and Hibernate.
 * 
 * Use the <code>ManagedTransactions</code> property on the HibernateControl 
 * and transactions will be managed for you autmatically.  The transaction 
 * will start at the first access to the session.
 * 
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
     * @return The session that is currently associated with this Thread.
     * If there is no session yet, one will be created.
     * @throws HibernateException
     */
    Session getSession() throws HibernateException;
    
    /**
     * @return The transaction for the current session. If there is no 
     * session or the control is not managing the transactions, it will
     * return <code>null</code>.
     */
    Transaction getTransaction();
    
    /**
     * Close the session for the current Thread. If there is no session
     * it will fail gracefully and no exception is thrown.
     * 
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
