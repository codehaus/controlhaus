package org.controlhaus.xfire.client;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.apache.velocity.VelocityContext;
import org.codehaus.xfire.xmlbeans.generator.GenerationStrategy;
import org.codehaus.xfire.xmlbeans.generator.GeneratorTask;
import org.codehaus.xfire.xmlbeans.generator.VelocityGenerationStrategy;
import org.codehaus.xfire.xmlbeans.generator.WSDLInspector.Service;

/**
 * A strategy which generates XMLBeans client controls for a given WSDL.
 * 
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 2, 2004
 */
public class BeehiveClientStrategy
    extends VelocityGenerationStrategy
    implements GenerationStrategy
{

    /* (non-Javadoc)
     * @see org.codehaus.xfire.xmlbeans.generator.GenerationStrategy#write(org.codehaus.xfire.xmlbeans.generator.WSDLInspector.Service, java.io.File, org.codehaus.xfire.xmlbeans.generator.GeneratorTask)
     */
    public void write(Service service, File outputDir, GeneratorTask task)
        throws Exception
    {
        File dir = new File(outputDir + File.separator + task.getPackage().replace('.','/'));
        
        if ( !dir.exists() )
            dir.mkdirs();

        String type = "";
        if ( service.isRest() )
            type = "Rest";
                
        String intfName = service.getName() + type + "ClientControl";
        String implName = service.getName() + type + "ClientControlImpl";
        
        File intfFile = new File(dir, intfName + ".java" );
        File implFile = new File(dir, implName + ".java" );
        
        if ( !intfFile.exists() || !implFile.exists() || task.isOverwrite() )
        {
            FileWriter writer = new FileWriter(intfFile);
            FileWriter implWriter = new FileWriter(implFile);

            VelocityContext context = new VelocityContext();
            context.put("package", task.getPackage());
            context.put("service", service);
            context.put("interfaceType", intfName);
            context.put("implType", implName);
            
            generateStub(context, 
                         writer, 
                         new InputStreamReader(getClass().getResourceAsStream("ControlInterface.vm")));
            
            generateStub(context, 
                    implWriter, 
                         new InputStreamReader(getClass().getResourceAsStream("ControlImpl.vm")));
            
            writer.close();
        }
    }

}
