package org.controlhaus.xfire.client;

import java.io.File;

import junit.framework.TestCase;

import org.codehaus.xfire.xmlbeans.generator.GeneratorTask;


/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Oct 27, 2004
 */
public class GeneratorTest
    extends TestCase
{
    public void testGeneration() throws Exception
    {
        File weather = new File("src/test-schemas/WeatherForecast.wsdl");
        
        GeneratorTask task = new GeneratorTask();
        
        task.setStrategy(BeehiveClientStrategy.class.getName());
        task.setWsdl(weather.toURL().toString());
        task.setOverwrite(true);
        File output = new File("target/generated-test");
        output.mkdir();
        
        task.setOutputDir( output.getAbsolutePath() );
        
        task.execute();
    }
}
