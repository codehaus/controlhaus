package org.controlhaus.hibernate;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.cfg.Configuration;

import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.beehive.controls.api.context.Context;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.api.events.EventHandler;
import org.apache.beehive.controls.api.context.ResourceContext;

import org.apache.log4j.Logger;
import org.controlhaus.hibernate.HibernateControl.HibernateInstance;
import org.controlhaus.hibernate.HibernateControl.ManagedTransactions;

/**
 * Hibernate service.
 * 
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since May 10, 2003
 */
@ControlImplementation
public class HibernateControlImpl
    implements HibernateControl
{
    private static Logger logger = Logger.getLogger(HibernateControlImpl.class.getName());
    
    private ThreadLocal<Session> session = new ThreadLocal<Session>();
    private ThreadLocal<Transaction> transactions = new ThreadLocal<Transaction>();

    private SessionFactory sessionFactory;
    private Configuration hibConfig;
    
    private String location = "/hibernate.cfg.xml";
    private String instance = "default";
    
    private boolean manageTXs = false;
    
    @Context ControlBeanContext context;
    @Context ResourceContext resourceContext;
    
    public HibernateControlImpl()
    {
        String propLoc = System.getProperty("hibernate.cfg.xml");
        if ( propLoc != null )
            location = propLoc;
    }
    
    /**
     * @see org.codehaus.plexus.hibernate.HibernateService#getSessionFactory()
     */
    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public Configuration getConfiguration()
    {
        return hibConfig;
    }

    public String getConfigurationLocation()
    {
        return location;
    }
    
    public String getHibernateInstance()
    {
        return instance;
    }

    @EventHandler(field="context", 
                  eventSet=ControlBeanContext.LifeCycle.class, 
                  eventName="onCreate")
    public void onCreate()
    {
        HibernateInstance iProp = 
            (HibernateInstance) context.getControlPropertySet(HibernateInstance.class);
        if (instance != null)
        {
            instance = iProp.value();
        }
        
        ManagedTransactions txProp = 
            (ManagedTransactions) context.getControlPropertySet(ManagedTransactions.class);
        if (txProp != null)
        {
            manageTXs = txProp.value();
        }
        
        sessionFactory = HibernateFactory.getInstance().getSessionFactory(this);
    }
    
    @EventHandler (field="resourceContext", 
                   eventSet=ResourceContext.ResourceEvents.class, 
                   eventName="onAcquire")
    public void onAcquire()
    {
        try
        {
            System.out.println("managed: " + manageTXs);
            
            if ( manageTXs )
            {
                Session s = getSession();
                transactions.set( s.beginTransaction() );
            }
        }
        catch (HibernateException e)
        {
            logger.error("Couldn't close session!", e);
        }
    }
    
    @EventHandler (field="resourceContext", 
                   eventSet=ResourceContext.ResourceEvents.class, 
                   eventName="onRelease")
    public void onRelease()
    {
        try
        {
            logger.debug("Closing open hibernate session.");
            if ( manageTXs )
            {
                Transaction t = getTransaction();
                try
                {
                    t.commit();
                }
                catch(HibernateException e)
                {
                    t.rollback();
                }
                finally
                {
                    closeSession();
                }
            }
            else
            {
                closeSession();
            }
        }
        catch (HibernateException e)
        {
            logger.error("Couldn't close session!", e);
        }
    }

    public Session getSession() 
        throws HibernateException
    {
        Session s = (Session) session.get();
        if (s == null)
        {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public Transaction getTransaction() 
    {
        return (Transaction) transactions.get();
    }
    
    public void closeSession() 
        throws HibernateException
    {
        logger.info("Closing session for thread.");
        
        if ( manageTXs )
        {
            transactions.remove();
        }

        Session s = (Session) session.get();
        if ( s != null )
        {
            session.remove();
            s.close();
        }

    }
}
