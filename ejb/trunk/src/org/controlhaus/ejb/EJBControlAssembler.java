/*
 * Copyright 2004 BEA Systems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.controlhaus.ejb;

import org.apache.beehive.controls.api.assembly.ControlAssembler;
import org.apache.beehive.controls.api.assembly.ControlAssemblyException;
import org.apache.beehive.controls.api.assembly.ControlAssemblyContext;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;

import org.controlhaus.schemas.j2ee.*;

import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.String;

/**
 * The EJBControl needs to inject EJB reference entries into the
 * DD of its containing module for cases where ejb-link is used.
 */
public class EJBControlAssembler implements ControlAssembler
{
    public void assemble(ControlAssemblyContext cac)
        throws ControlAssemblyException
    {
        System.err.println("EJBControlAssembler.assemble() called");

        Class controlInterface = cac.getControlType();
        EJBInfo ei = new EJBInfo( controlInterface );

        EJBControl.EJBHome ea = cac.getControlAnnotation(EJBControl.EJBHome.class);
        if ( ea == null )
        {
            System.err.println( "Missing EJBHome annotation on control?!");
            return;
        }

        String ejbLinkValue = ea.ejbLink();
        if ( ejbLinkValue == null || ejbLinkValue.length() == 0 )
        {
            // Not using ejb-link, so no ejb-ref injection needed
            return;
        }

        if (cac instanceof ControlAssemblyContext.EJBModule)
        {
            // insert any required <ejb-ref> entries into the deployment descriptor
        	updateEJBJar( (ControlAssemblyContext.EJBModule)cac, ei, ejbLinkValue );
        }
        else if (cac instanceof ControlAssemblyContext.WebAppModule)
        {
        	updateWebApp( (ControlAssemblyContext.WebAppModule)cac, ei, ejbLinkValue );
        }
        else
        {
            System.err.println("EJBControlAssembler - no work to do, assembly context is not EJB.");
        }
    }

    protected void updateEJBJar( ControlAssemblyContext.EJBModule ejbCtx,
        EJBInfo ei, String ejbLinkValue )
        throws ControlAssemblyException
    {
        System.err.println("EJBControlAssembler.updateEJBJar() called");
        System.err.println(" ei=" + ei );
        System.err.println(" ejbLinkValue=" + ejbLinkValue );

        File ejbJarFile = ejbCtx.getEjbJarXml();
        FileInputStream ejbJarStream = null;
        try
        {
            ejbJarStream = new FileInputStream( ejbJarFile );
        }
        catch (FileNotFoundException fnfe)
        {
            System.err.println("*** Warning *** EJBControlAssembler aborted: " +
                "caught FileNotFoundException attempting to read file " +
                ejbJarFile.getAbsolutePath() + ". Message: " +
                fnfe.getMessage());
            return;
        }

        try
        {
            // get name of EJB reference to be inserted
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
                
                if (ei._isLocal)
                	insertEJBLocalRefInEJBJar(sessionBean, ei, ejbLinkValue);
                else
                	insertEJBRefInEJBJar(sessionBean, ei, ejbLinkValue);
                
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
    
    protected void insertEJBRefInEJBJar(SessionBeanType sessionBean, EJBInfo ei, String ejbLinkValue)
    {
        System.err.println("EJBControlAssembler.insertEJBRefInEJBJar() called");
        System.err.println("ejbLinkValue =" + ejbLinkValue );
        EjbRefType[] ejbRefArray = sessionBean.getEjbRefArray();
        String insertedEjbRefName = ei._refName;
        XmlCursor ejbJarXmlCursor = null;
        
        try {
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
		    EjbRefType insertedEjbRef = sessionBean.insertNewEjbRef(0);

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
        finally
        {
            if (ejbJarXmlCursor != null)
            {
                ejbJarXmlCursor.dispose();
            }
        }
    	
    }

    protected void insertEJBLocalRefInEJBJar(SessionBeanType sessionBean, EJBInfo ei, String ejbLinkValue)
    {
        System.err.println("EJBControlAssembler.insertEJBLocalRefInEJBJar() called");
        System.err.println("ejbLinkValue =" + ejbLinkValue );
        EjbLocalRefType[] ejbLocalRefArray = sessionBean.getEjbLocalRefArray();
        String insertedEjbRefName = ei._refName;
        XmlCursor ejbJarXmlCursor = null;
        
        try {
			for (int j=ejbLocalRefArray.length-1; j>=0; j--)
			{
			    EjbLocalRefType ejbLocalRef = ejbLocalRefArray[j];
			    ejbJarXmlCursor = ejbLocalRef.newCursor();
			    String ejbRefName = ejbLocalRef.getEjbRefName().getStringValue();
			    if (insertedEjbRefName.equals(ejbRefName))
			    {
			        sessionBean.removeEjbLocalRef(j);
			        break;
			    }
			}
			// insert a new <ejb-local-ref> entry and fill in the values
        	EjbLocalRefType insertedEJBLocalRef = sessionBean.insertNewEjbLocalRef(0);

            EjbRefNameType ejbRefName = insertedEJBLocalRef.addNewEjbRefName();
            ejbRefName.setStringValue(insertedEjbRefName);

            EjbRefTypeType ejbRefType = insertedEJBLocalRef.addNewEjbRefType();
            ejbRefType.setStringValue(ei._beanType);

            LocalHomeType homeType = insertedEJBLocalRef.addNewLocalHome();
            homeType.setStringValue(ei._homeInterface.getName());
            LocalType localType = insertedEJBLocalRef.addNewLocal();
            localType.setStringValue(ei._beanInterface.getName());

            EjbLinkType ejbLink = insertedEJBLocalRef.addNewEjbLink();
            ejbLink.setStringValue(ejbLinkValue);
		}         
        finally
        {
            if (ejbJarXmlCursor != null)
            {
                ejbJarXmlCursor.dispose();
            }
        }
    	
    }

    protected void updateWebApp( ControlAssemblyContext.WebAppModule webAppCcc,
            EJBInfo ei, String ejbLinkValue )
            throws ControlAssemblyException
        {
	        System.err.println("EJBControlAssembler.updateWebApp() called");
	        System.err.println("ei =" + ei);
	        System.err.println("ejbLinkValue =" + ejbLinkValue );
            File webXmlFile = webAppCcc.getWebXml();
            FileInputStream webXmlStream = null;
            try
            {
                webXmlStream = new FileInputStream( webXmlFile );
            }
            catch (FileNotFoundException fnfe)
            {
                System.err.println("EJBControlAssembler: " +
                        "caught FileNotFoundException attempting to read file " +
                        webXmlFile.getAbsolutePath() + ". Message: " +
                        fnfe.getMessage());
                return;
            }

            try
            {
                // get the existing <web-app> XBean from the stream
                WebAppDocument webAppDoc = WebAppDocument.Factory.parse(webXmlStream);

                // copy webAppDoc XmlObject so can makes changes to it
                webAppDoc = (WebAppDocument)webAppDoc.copy();
                WebAppType webAppType = webAppDoc.getWebApp();

                if (ei._isLocal)
                	insertEJBLocalRefInWebApp(webAppType, ei, ejbLinkValue);
                else
                	insertEJBRefInWebApp(webAppType, ei, ejbLinkValue);

                // overwrite existing web.xml file with new document
                XmlOptions xmlOpts = new XmlOptions();
                xmlOpts.setSavePrettyPrint();
                webAppDoc.save(webXmlFile, xmlOpts);
            }
            catch(IOException ioe)
            {
                System.err.println("ServiceControlAssembler: caught IOException " +
                    "attempting to write to file " + webXmlFile.getAbsolutePath() +
                    ". Message: " + ioe.getMessage());
            }
            catch(XmlException xe)
            {
                System.err.println("ServiceControlAssembler: caught XmlException " +
                    "attempting xml manipulations. Message: " +
                    xe.getMessage());
            }
            finally
            {
                try
                {
                    if (webXmlStream != null)
                    {
                        webXmlStream.close();
                    }
                }
                catch(IOException e)
                {
                    // do nothing
                }
            }

        }

    protected void insertEJBRefInWebApp(WebAppType webAppType, EJBInfo ei, String ejbLinkValue)
    {
        System.err.println("EJBControlAssembler.insertEJBRefInWebApp() called");
        System.err.println("ejbLinkValue =" + ejbLinkValue );

        EjbRefType[] ejbRefArray = webAppType.getEjbRefArray();
        String insertedEjbRefName = ei._refName;
        XmlCursor webXmlCursor = null;
        
        try {
			for (int j=ejbRefArray.length-1; j>=0; j--)
			{
			    EjbRefType ejbRef = ejbRefArray[j];
			    webXmlCursor = ejbRef.newCursor();
			    String ejbRefName = ejbRef.getEjbRefName().getStringValue();
			    if (insertedEjbRefName.equals(ejbRefName))
			    {
			    	webAppType.removeEjbRef(j);
			        break;
			    }
			}
			// insert a new <ejb-ref> entry and fill in the values
		    EjbRefType insertedEjbRef = webAppType.insertNewEjbRef(0);

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
        finally
        {
            if (webXmlCursor != null)
            {
                webXmlCursor.dispose();
            }
        }
    	
    }

    protected void insertEJBLocalRefInWebApp(WebAppType webAppType, EJBInfo ei, String ejbLinkValue)
    {
        System.err.println("EJBControlAssembler.insertEJBLocalRefInWebApp() called");
        System.err.println("ejbLinkValue =" + ejbLinkValue );

        EjbLocalRefType[] ejbLocalRefArray = webAppType.getEjbLocalRefArray();
        String insertedEjbRefName = ei._refName;
        XmlCursor webXmlCursor = null;
        
        try {
			for (int j=ejbLocalRefArray.length-1; j>=0; j--)
			{
			    EjbLocalRefType ejbLocalRef = ejbLocalRefArray[j];
			    webXmlCursor = ejbLocalRef.newCursor();
			    String ejbRefName = ejbLocalRef.getEjbRefName().getStringValue();
			    if (insertedEjbRefName.equals(ejbRefName))
			    {
			    	webAppType.removeEjbLocalRef(j);
			        break;
			    }
			}
			// insert a new <ejb-local-ref> entry and fill in the values
        	EjbLocalRefType insertedEJBLocalRef = webAppType.insertNewEjbLocalRef(0);

            EjbRefNameType ejbRefName = insertedEJBLocalRef.addNewEjbRefName();
            ejbRefName.setStringValue(insertedEjbRefName);

            EjbRefTypeType ejbRefType = insertedEJBLocalRef.addNewEjbRefType();
            ejbRefType.setStringValue(ei._beanType);

            LocalHomeType homeType = insertedEJBLocalRef.addNewLocalHome();
            homeType.setStringValue(ei._homeInterface.getName());
            LocalType localType = insertedEJBLocalRef.addNewLocal();
            localType.setStringValue(ei._beanInterface.getName());

            EjbLinkType ejbLink = insertedEJBLocalRef.addNewEjbLink();
            ejbLink.setStringValue(ejbLinkValue);
		}         
        finally
        {
            if (webXmlCursor != null)
            {
            	webXmlCursor.dispose();
            }
        }
    	
    }
}
