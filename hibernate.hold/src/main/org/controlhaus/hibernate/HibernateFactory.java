package org.controlhaus.hibernate;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Oct 30, 2004
 */
public class HibernateFactory
{
    private static Logger logger = Logger.getLogger(HibernateFactory.class.getName());
    
    private Map<String,SessionFactory> factories = new HashMap<String,SessionFactory>();
    
    private static HibernateFactory factory = new HibernateFactory();

    public SessionFactory getSessionFactory(HibernateControl control)
    {
        String name = control.getHibernateInstance();
        
        SessionFactory factory = factories.get(name);
        if ( factory == null )
        {
            synchronized( factories )
            {
                factory = initializeHibernate(control);
                factories.put( name, factory );
            }
        }
        
        return factory;
    }
    
    private synchronized SessionFactory initializeHibernate(HibernateControl control)
    {
        logger.info( "Initializing Hibernate instance " + control.getHibernateInstance() + "." );
        Configuration hibConfig = new Configuration();
        
        try
        {
            String mapping = control.getConfigurationLocation();
            logger.debug("Configuration mapping " + mapping);
            File file = new File( mapping );
            
            if ( file.exists() )
                hibConfig.configure( file );
            else
            {
                URL url = getClass().getResource(mapping);
                if ( url != null )
                {
                    hibConfig.configure(url);
                }
                else
                {
                    logger.error("Couldn't find mapping file: " + mapping);
                    throw new RuntimeException("Couldn't find mapping file: " + mapping);
                }
            }
            
            return hibConfig.buildSessionFactory();
        }
        catch (HibernateException e)
        {
            logger.error("Mapping problem.", e);
            throw new RuntimeException( "Mapping problem.", e );
        }
    }
    
    public static HibernateFactory getInstance()
    {
        return factory;
    }
}
