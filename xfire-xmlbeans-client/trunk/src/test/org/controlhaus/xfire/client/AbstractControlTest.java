package org.controlhaus.xfire.client;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 5, 2004
 */
public class AbstractControlTest
    extends TestCase
{
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

    public ControlContainerContext getContext()
    {
        return context;
    }
}
