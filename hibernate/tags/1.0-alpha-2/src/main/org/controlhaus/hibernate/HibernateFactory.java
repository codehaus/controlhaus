package org.controlhaus.hibernate;

import java.io.File;
import java.net.URL;

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

    private static HibernateFactory factory = new HibernateFactory();

    private String location = "/hibernate.cfg.xml";
    
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory(HibernateControl control)
    {
        if ( sessionFactory == null )
        {
            sessionFactory = createSessionFactory();
        }
        
        return sessionFactory;
    }
    
    private SessionFactory createSessionFactory()
    {
        logger.info( "Initializing Hibernate." );
        Configuration hibConfig = new Configuration();
        
        try
        {
            String mapping = System.getProperty("hibernate.cfg.xml");
            if ( mapping == null || mapping.equals("") )
                mapping = location;
            
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
