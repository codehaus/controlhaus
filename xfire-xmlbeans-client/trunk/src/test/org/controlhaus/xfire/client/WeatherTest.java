package org.controlhaus.xfire.client;

import java.io.File;
import java.lang.reflect.Method;

import net.webservicex.GetWeatherByZipCodeDocument;
import net.webservicex.GetWeatherByZipCodeResponseDocument;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;
import org.apache.xmlbeans.XmlObject;
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
        
        assertTrue(weather instanceof WeatherForecastClientControl);
        
        GetWeatherByZipCodeDocument doc = GetWeatherByZipCodeDocument.Factory.newInstance();
        doc.addNewGetWeatherByZipCode().setZipCode("49506");
        
        // It would be great if this service actually worked. It always returns status 500
        // GetWeatherByZipCodeResponseDocument response = weather.GetWeatherByZipCode( doc );
    }
}
