package org.controlhaus.ejb;

import org.apache.beehive.controls.api.assembly.ControlAssembler;
import org.apache.beehive.controls.api.assembly.ControlAssemblyException;
import org.apache.beehive.controls.api.assembly.ControlAssemblyContext;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;

import com.sun.java.xml.ns.j2Ee.*;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.String;

/**
 * The EJBControl needs to inject <ejb-ref> entries into the
 * DD of its containing module.
 */
public class EJBControlAssembler implements ControlAssembler
{
    public void assemble(ControlAssemblyContext cac)
        throws ControlAssemblyException
    {
        System.err.println("EJBControlAssembler.assemble() called");

        // TODO: support WebAppModule
        if (cac instanceof ControlAssemblyContext.EJBModule)
        {
            assembleEJBModule( cac );
        }
        else
        {
            System.err.println("EJBControlAssembler - no work to do, assembly context is not EJB.");
        }
    }

    private void assembleEJBModule( ControlAssemblyContext cac )
        throws ControlAssemblyException
    {
        System.err.println("EJBControlAssembler.assembleEJBModule() called");

        ControlAssemblyContext.EJBModule ejbCtx = (ControlAssemblyContext.EJBModule)cac;

        Class controlInterface = cac.getControlType();
        EJBInfo ei = new EJBInfo( controlInterface );

        EJBControl.EJBHome ea = cac.getControlAnnotation(EJBControl.EJBHome.class);
        if ( ea == null )
        {
            System.err.println( "Missing EJBHome annotation on control?!");
            return;
        }

        String ejbLinkValue = ea.EJBLink();
        if ( ejbLinkValue == null || ejbLinkValue.length() == 0 )
        {
            // Not using ejb-link, so no ejb-ref injection needed
            return;
        }

        // insert any required <ejb-ref> entries into the deployment descriptor
        updateEjbRefs( ejbCtx, ei, ejbLinkValue );
    }

    protected void updateEjbRefs( ControlAssemblyContext.EJBModule ejbCtx,
        EJBInfo ei, String ejbLinkValue )
        throws ControlAssemblyException
    {
        System.err.println("EJBControlAssembler.updateEjbRefs() called");
        System.err.println(" ei=" + ei );
        File ejbJarFile = ejbCtx.getEjbJarXml();
        FileInputStream ejbJarStream = null;
        try
        {
            ejbJarStream = new FileInputStream( ejbJarFile );
        }
        catch (FileNotFoundException fnfe)
        {
            System.err.println("EJBControlAssembler: " +
                "caught FileNotFoundException attempting to read file " +
                ejbJarFile.getAbsolutePath() + ". Message: " +
                fnfe.getMessage());
            throw new ControlAssemblyException("Unable to read ejb-jar.xml " +
                "file " + ejbJarFile.getAbsolutePath(), fnfe);
        }

        // insert new ejbRef
        XmlCursor ejbJarXmlCursor = null;
        try
        {
            // get name of <service-ref> to be inserted
            String insertedEjbRefName = ei._refName;

            // get the existing <ejb-jar> XBean from the stream
            EjbJarDocument ejbDoc = EjbJarDocument.Factory.parse(ejbJarStream);

            // copy ejbDoc XmlObject so can makes changes to it
            ejbDoc = (EjbJarDocument)ejbDoc.copy();
            EjbJarType ejbJarType = ejbDoc.getEjbJar();
            EnterpriseBeansType entBeansType = ejbJarType.getEnterpriseBeans();

            // TODO: support Entity & other bean types
            SessionBeanType[] sessionArray = entBeansType.getSessionArray();
            for (int i=0; i<sessionArray.length; i++)
            {
                SessionBeanType sessionBean = sessionArray[i];
                EjbRefType[] ejbRefArray = sessionBean.getEjbRefArray();
                for (int j=ejbRefArray.length-1; j>=0; j--)
                {
                    EjbRefType ejbRef = ejbRefArray[j];
                    ejbJarXmlCursor = ejbRef.newCursor();
                    String ejbRefName = ejbRef.getEjbRefName().getStringValue();
                    if (insertedEjbRefName.equals(ejbRefName))
                    {
                        sessionBean.removeEjbRef(j);
                        break;
                    }
                }

                // insert a new <ejb-ref> entry and fill in the values
                EjbRefType insertedEjbRef =
                    sessionBean.insertNewEjbRef(0);

                EjbRefNameType ejbRefName = insertedEjbRef.addNewEjbRefName();
                ejbRefName.setStringValue(insertedEjbRefName);

                EjbRefTypeType ejbRefType = insertedEjbRef.addNewEjbRefType();
                ejbRefType.setStringValue(ei._beanType);

                HomeType homeType = insertedEjbRef.addNewHome();
                homeType.setStringValue(ei._homeInterface.getName());

                RemoteType remoteType = insertedEjbRef.addNewRemote();
                remoteType.setStringValue(ei._beanInterface.getName());

                EjbLinkType ejbLink = insertedEjbRef.addNewEjbLink();
                ejbLink.setStringValue(ejbLinkValue);
            }

            // overwrite existing ejb-jar.xml file with new document
            XmlOptions xmlOpts = new XmlOptions();
            xmlOpts.setSavePrettyPrint();
            ejbDoc.save(ejbJarFile, xmlOpts);
        }
        catch(IOException ioe)
        {
            System.err.println("EJBControlAssembler: caught IOException " +
                "attempting to write to file " + ejbJarFile.getAbsolutePath() +
                ". Message: " + ioe.getMessage());
        }
        catch(XmlException xe)
        {
            System.err.println("EJBControlAssembler: caught XmlException " +
                "attempting xml manipulations. Message: " +
                xe.getMessage());
        }
        finally
        {
            if (ejbJarXmlCursor != null)
            {
                ejbJarXmlCursor.dispose();
            }

            try
            {
                if (ejbJarStream != null)
                {
                    ejbJarStream.close();
                }
            }
            catch(IOException e)
            {
                // do nothing
            }
        }
    }
}
