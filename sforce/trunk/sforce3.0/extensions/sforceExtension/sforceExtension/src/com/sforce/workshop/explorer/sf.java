/*   Copyright 2004 Salesforce.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.sforce.workshop.explorer; 

import com.bea.ide.Application;
import com.bea.ide.core.MessageSvc;
import com.bea.ide.core.ResourceSvc;
import com.bea.ide.filesystem.FileSvc;
import com.bea.ide.filesystem.FileSvc.IFileSaver;
import com.bea.ide.ui.output.OutputSvc;
import com.bea.ide.workspace.IWorkspace;
import com.bea.ide.workspace.ServerSvc;
import com.bea.wlw.runtime.core.util.CryptUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Properties;
import java.util.prefs.Preferences;
import javax.security.auth.login.LoginException;
import javax.swing.JOptionPane;
import javax.xml.rpc.ServiceException;
import rpctypes.DescribeGlobalResult;
import rpctypes.DescribeSObjectResult;
import rpctypes.LoginFault;
import rpctypes.LoginResult;
import rpctypes.SessionHeader;
import rpctypes.SforceService_Impl;
import rpctypes.Soap_Stub;
import rpctypes.UnexpectedErrorFault;
import weblogic.webservice.client.SSLAdapter;
import weblogic.webservice.client.SSLAdapterFactory;

public class sf  {

	private HashMap entities = new HashMap();
	
	private  Soap_Stub service;
	private SessionHeader sessionHeader = null;
	private LoginResult loginResult=null;
    private String endpoint;
    private String _sTempFile=null;
    
	public sf()
	{
	}

    public void setEndPoint(String newURL) throws Exception {
        try {
            service._setTargetEndpoint(new URL(newURL));
        } catch (Exception ex) {
            throw new Exception("Error setting end point", ex);
        }
    }
    private void getPreferences() throws Exception 
    {
        IWorkspace w = Application.getWorkspace();
        if (null==w)
            throw new Exception("Must have an open application to run the sforce Explorer");        
        
        Preferences prefs = Application.get().systemNodeForPackage(SFDCPrefsPanel.class);
        endpoint = prefs.get(SforceSettings.SERVER_URL, null);
                                                
        URI uriWlsHome = ServerSvc.get().getWlsHome(w);
        File fileWlsHome = new File(uriWlsHome);
        String strBeaHome = fileWlsHome.getParent();
         // beahome tells the libs where to find the  license file
        System.setProperty( "bea.home", strBeaHome);
        // a workaround for "bad certificate" errors on https                    
        System.setProperty("weblogic.webservice.client.ssl.strictcertchecking", "false");           

    }

	public LoginResult login(String userId, String password) throws Exception
	{
		try {
            getPreferences();			        
			service = (Soap_Stub) new SforceService_Impl().getSoap();

            // use a different endpoint than the  one in the partner.wsdl if set in prefs panel
            if (null!=endpoint)
            {
                URL loginURL = new URL(endpoint);
                service._setTargetEndpoint(loginURL);
            }
              
			loginResult = service.login(userId, password, null);
            
            sessionHeader = new SessionHeader();
			sessionHeader.setSessionId(loginResult.getSessionId());
            
            if (loginResult.getServerUrl() != null)             
                setEndPoint(loginResult.getServerUrl());

            return loginResult;            
		}
		catch (LoginFault e) {
            e.printStackTrace();
			throw new LoginFault(e);
		} catch (UnexpectedErrorFault e) {
            e.printStackTrace();
            throw new UnexpectedErrorFault(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
	}

    public DescribeGlobalResult describeGlobal() throws Exception
    {
        DescribeGlobalResult result=null;
        boolean fRetry=false;
        try {
            result = getService().describeGlobal(sessionHeader,null);
            return result;
        }     
		catch (Exception e) {        
			throw new Exception("describe global failure", e);
		} 
    }
    
	public DescribeSObjectResult describe(String type) 
	{
		try {
			DescribeSObjectResult result = getService().describeSObject(type, sessionHeader, null);

			return result;
		}
		catch (Exception e) {
            return null;
		}
	}

    
	Soap_Stub getService() throws Exception
	{
		if (service == null)
			try {
                if (loginResult.getServerUrl() != null)
                    service = (Soap_Stub) new SforceService_Impl().getSoap();
                    
                else
                    service = (Soap_Stub) new SforceService_Impl().getSoap();
			}
			catch (Exception e) {
				System.out.println("\ngetServiceStub Exception=" + e.getMessage());
				throw new Exception("Failure Getting Stub", e);
			}
		return service;
	}
    
    public DescribeSObjectResult getObjectDefinition(String objectName)  {
        try {
            DescribeSObjectResult dr = getService().describeSObject(objectName, sessionHeader, null);
            return dr;
        }
        catch (RemoteException ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public String[] getObjects() throws Exception {
        try {
            DescribeGlobalResult dr = describeGlobal();
            String[] result = new String[dr.getTypes().length];
            for (int i = 0;i<dr.getTypes().length;i++)
                result[i] = dr.getTypes()[i];
            return result;
        }
        catch (Exception e) {
            throw new Exception("Failure Getting Objects", e);
        }
        
    }

    
}