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
    
    /**
     * Override this method to provide logic on how to get the
     * Hibernate configuration file. This can be a file or a resource
     * on the classpath.
     * 
     * @return
     */
    String getConfigurationLocation();
    
    /**
     * The instance of hibernate that you want this control to use.
     * This will be "default" unless you use the HibernateInstance annotation
     * to override it.
     * 
     * @return
     */
    String getHibernateInstance();
    
    @PropertySet(prefix="HibernateInstance")
    @Target( {ElementType.TYPE, ElementType.FIELD, ElementType.METHOD} )
    @Retention(RetentionPolicy.RUNTIME)
    public @interface HibernateInstance
    {
        String value() default "default";
    }
    
    @PropertySet(prefix="ManagedTransactions")
    @Target( {ElementType.TYPE, ElementType.FIELD, ElementType.METHOD} )
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ManagedTransactions
    {
        boolean value() default false;
    }
}
