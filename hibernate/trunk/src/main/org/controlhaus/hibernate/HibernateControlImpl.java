package org.controlhaus.hibernate;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.beehive.controls.api.context.Context;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.api.events.EventHandler;
import org.apache.beehive.controls.api.context.ResourceContext;

import org.apache.log4j.Logger;
import org.controlhaus.hibernate.HibernateControl.HibernateInstance;

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

    private List<Session> sessions;
    
    private SessionFactory sessionFactory;
    private Configuration hibConfig;
    
    private String location = "/hibernate.cfg.xml";
    private String instance = "default";
    
    @Context ControlBeanContext context;
    @Context ResourceContext resourceContext;
    
    public HibernateControlImpl()
    {
        sessions = new ArrayList<Session>();
        
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

    @EventHandler(field="resourceContext", 
                  eventSet=ResourceContext.ResourceEvents.class, 
                  eventName="onAcquire")
    public void onAcquire()
    {
        HibernateInstance iProp = 
            (HibernateInstance) context.getControlPropertySet(HibernateInstance.class);
        if (instance != null)
        {
            instance = iProp.value();
        }
        
        sessionFactory = HibernateFactory.getInstance().getSessionFactory(this);
    }
    
    @EventHandler (field="resourceContext", eventSet=ResourceContext.ResourceEvents.class, eventName="onRelease")
    public void onRelease()
    {
        for (Iterator itr = sessions.iterator(); itr.hasNext();)
        {
            Session s = (Session) itr.next();
            try
            {
                logger.debug("Closing open hibernate session.");
                s.close();
                itr.remove();
            }
            catch (HibernateException e)
            {
                logger.error("Couldn't close session!", e);
            }
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
            sessions.add(s);
        }
        return s;
    }

    public void closeSession() 
        throws HibernateException
    {
        logger.info("Closing session for thread.");
 
        Session s = (Session) session.get();
        if ( s != null )
        {
            sessions.remove(s);
            session.set(null);
            s.close();
        }
    }
}
