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
    extends AbstractControlTest
{
    @Control WeatherForecastClientControl weather;

    public void testControl() 
        throws Exception
    {
        assertNotNull(weather);
        
        // TODO run more tests?
    }
}
