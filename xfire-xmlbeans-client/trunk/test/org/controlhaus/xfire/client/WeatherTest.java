package org.controlhaus.xfire.client;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;
import org.controlhaus.xfire.weather.WeatherForecastClientControl;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 2, 2004
 */
public class WeatherTest 
    extends TestCase
{
    @Control WeatherForecastClientControl weather;
    
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
        assertNotNull(weather);
        
        // TODO run more tests?
    }
}
