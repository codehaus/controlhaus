package org.controlhaus.hibernate;

import java.lang.reflect.Method;

import junit.framework.TestCase;
import net.sf.hibernate.SessionFactory;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Oct 28, 2004
 */
public class HibernateControlTest
    extends TestCase
{
    @Control HibernateControl hib;
    
    private ControlContainerContext context;
    
    public void setUp() throws Exception
    {
        context = new ControlContainerContext();
        
        try
        {
            Class init = getClass().getClassLoader().loadClass( 
                    getClass().getName() + "ClientInitializer" );
            
            Method m = init.getMethod("initialize", 
                                      new Class[] 
                                      { 
                                            ControlBeanContext.class, 
                                            getClass() 
                                      } );
            
            m.invoke( null, new Object[] { context, this } );
        }
        catch ( ClassNotFoundException cnfe )
        {
            // do nothing.
        }
    }

    public void testControl() 
        throws Exception
    {
        assertNotNull(hib);

        SessionFactory factory = hib.getSessionFactory();
        assertNotNull(factory);
    }
}
