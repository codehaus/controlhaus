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

package org.controlhaus.sforce.workshop.ide; 

import com.bea.ide.Application;
import com.bea.ide.filesystem.FileSvc;
import com.bea.ide.util.URIUtil;
import com.bea.ide.workspace.IProject;
import com.bea.ide.workspace.WorkspaceSvc;
import com.bea.ide.workspace.project.IProjectInfo;
import com.bea.ide.workspace.project.IProjectTemplate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.controlhaus.sforce.workshop.ide.SimpleWebClient.WebResponse;
import org.controlhaus.sforce.workshop.sforceSettings;
import workshop.workspace.Workspace;
import workshop.workspace.WorkspaceSvcImpl;

/**
 * Util functions for checking, installing extensions
 * 
 */
public class ExtensionUtils 
{ 
    /*
     * 
     */
    private static final int BUF_SIZE = 32768;
	/*
	 * 
	 */
    private static String SCHEMA_PROJECT_TYPE = "urn:com-bea-ide:project.type:Schema";
    
    private static String FILE_SEPARATOR = System.getProperty("file.separator");
    
    /*
     * 
     */
    static String[] EXTENSION_JARS = new String[]{"sforceExtension.jar"};
    /*
     * 
     */  
    static String[] SCHEMA_FILES = new String[]{"enterprise.wsdl"};  
    /*
     * 
     */
    private String m_ExtensionsDir=null;
    /*
     * 
     */
    private String m_SchemasDir=null;
    
    public ExtensionUtils() {
        // load extension dir path       
        m_ExtensionsDir = Application.get().getProperty(Application.PROP_WebLogicHome) + 
                            FILE_SEPARATOR + "workshop"+ FILE_SEPARATOR + "extensions" + FILE_SEPARATOR;            
        
        // load schema project dir path       
        //   search for 'Schemas' project
        IProject proj = Application.getWorkspace().findProject("sforceSchemas");        
        if (null!=proj && proj.getProjectInfo().getTypeId().equals(SCHEMA_PROJECT_TYPE))
        {
            m_SchemasDir = proj.getDirectory().toString() + FILE_SEPARATOR;
            return;
        }        
        else 
        //   search for any schema project
        {
            IProject [] aProj = Application.getWorkspace().getProjects();
            for (int i=0;i<aProj.length;i++)
            {                   
                proj = aProj[i];
                if (proj.getProjectInfo().getTypeId().equals(SCHEMA_PROJECT_TYPE))
                {             
                    m_SchemasDir = proj.getDirectory().toString() + FILE_SEPARATOR;            
                    return;
                }    
            }
            // if not found, create 'Schemas' project                        
            createSchemaProject();
        }

    }
    
    /**
     * Creates a schema type project based on default template
     */
    private void createSchemaProject(){
                
        Workspace workspace = (Workspace)Application.getWorkspace();
        IProjectInfo schemaProjInfo = ((WorkspaceSvcImpl)WorkspaceSvc.get()).getProjectInfo(SCHEMA_PROJECT_TYPE);
        if(schemaProjInfo!=null){
            IProjectTemplate template = schemaProjInfo.getTemplate("default");
            if(template!=null){
                IProject projNew = workspace.newProject("sforceSchemas", template);            
                m_SchemasDir = projNew.getDirectory().toString() + FILE_SEPARATOR;
            }
        }
            
    }
    
    /**
     * Checks whether extension & schema project directories exists
     * @return String - Error message or null
 s    */
    public String checkDirectories()
    {
        StringBuffer sBuf = new StringBuffer();
        if (null==m_ExtensionsDir)
            sBuf.append("\nCan't find extensions directory.");
        if (null==m_SchemasDir)
            sBuf.append("\nCan't find sforceSchemas project.");
        
        if (sBuf.length()>0)    
            return sBuf.toString();
        return null;
    }
    
    /**
     * Checks whether extension jars exist
     * @return String - Error message or null
     */
    public String checkExtensions() 
    {
        StringBuffer sBuf = new StringBuffer();
        for (int i=0;i<EXTENSION_JARS.length;i++)
        {
            String msg = checkForExtension(EXTENSION_JARS[i]);
            if (null != msg)
                sBuf.append(msg);
        }    
        if (sBuf.length()>0)    
            return sBuf.toString();
        return null;
    }   

    /**
     * Checks whether schema files exist
     * @return String - Error message or null
     */
    public String checkSchemas() 
    {
        StringBuffer sBuf = new StringBuffer();
        for (int i=0;i<SCHEMA_FILES.length;i++)
        {
            String msg = checkForSchema(SCHEMA_FILES[i]);
            if (null != msg)
                sBuf.append(msg);
        }    

        if (sBuf.length()>0)    
            return sBuf.toString();
        return null;
    }   

    /**
     * Checks whether extension jar exist
     * @return String - Error message or null
     */    
    private String checkForExtension(String sExtensionJar)
    {
        try {                   
            String sInstalledExtension = m_ExtensionsDir + sExtensionJar;
            File f = new File(sInstalledExtension);
            URI uriInstalledExtension= URIUtil.fromFile(f);
            if (!FileSvc.get().exists(uriInstalledExtension))
                return "\nExtension not installed:  " + sExtensionJar;
            else 
                return null;
        }            
        catch (Exception ex) {            
            return "\nError checking for Extension: " + sExtensionJar + " Error: " + ex.getMessage();
        }   
    }   

    /**
     * Checks whether schema file exist
     * @return String - Error message or null
     */    
    private String checkForSchema(String sSchemaFile)
    {
        try {                       
            String sInstalledSchema = m_SchemasDir + sSchemaFile;
            File f = new File(sInstalledSchema);
            URI uriInstalledSchema= URIUtil.fromFile(f);
            if (!FileSvc.get().exists(uriInstalledSchema))
                return "\nSchema not installed:  " + sSchemaFile;
            else 
                return null;
        }            
        catch (Exception ex) {            
            return "\nError checking for Schema: " + ex.getMessage();
        }
   
    }   
    
    /**
     * @return extenstion components folder (BEA_HOME/ext_components)
     */
    private String getExtensionComponentsFolder()
    {
        // get weblogic home
        String extFolder = (String)Application.get().getProperty(Application.PROP_WebLogicHome);
        int idx = extFolder.lastIndexOf("weblogic");
        
        if(idx!=0){
            extFolder = extFolder.substring(0,idx);
        }
        extFolder += "ext_components";
        return extFolder;
    }
    
    /**
     * Installs sExtensionJar. Searches for jar file in SForceWLWControl.zip & copies to WEBLOGIC_HOME/workshop/extensions  
     */
    private void installExtension(String sExtensionJar) throws InstallExtensionException
    {        
        String extensionZipFile = getExtensionComponentsFolder()+System.getProperty("file.separator")+sforceSettings.CONTROL_ZIPFILE;                        
        ZipFile zipFile;
        try{
            zipFile = new ZipFile(extensionZipFile);            
        }catch(IOException e){
            throw new InstallExtensionException("Error reading from zipfile : "+e);
        }
        
        String extensionJar = m_ExtensionsDir + sExtensionJar;            
            
        ZipEntry entry = new ZipEntry("extensions/"+sExtensionJar);
        // check if(entry == null)
        if(entry == null){
            throw new InstallExtensionException("Error "+sExtensionJar+" not found in zipfile!");
        }
            
        File f = new File(extensionJar);            
        OutputStream out;
        try{
            out = new FileOutputStream(f);
        }catch(FileNotFoundException e){
            throw new InstallExtensionException(e);
        }
        
        try{
            InputStream in = zipFile.getInputStream(entry);
                        
            byte[] buf = new byte[BUF_SIZE];
            int c = 0;
            while( (c = in.read(buf,0,BUF_SIZE) ) != -1  ){
                out.write(buf,0,c);
            }
            in.close();
            out.close();                    
        }catch(IOException e){
            throw new InstallExtensionException("Error installing "+sExtensionJar+" : "+e);
        }        

    }   

    /**
     * Installs extension jar files to extenstion folder
     */    
    public void installExtensions() throws InstallExtensionException
    {
        for (int i=0;i<EXTENSION_JARS.length;i++)
        {
            installExtension(EXTENSION_JARS[i]);
        }            
    }   

	/**
	 * Logins to salesforce.com and downloads the enterprise.wsdl
	 * @param userName
	 * @param password
	 * @throws SForceLoginException 
	 * @throws SForceDownloadException
	 */    
    public void downloadWsdl(String userName, String password) throws sforceLoginException, sforceDownloadException{
        try{
			SimpleWebClient httpClient = new SimpleWebClient();	
			 
			HashMap params = new HashMap();
	                params.put("un",userName);			
			params.put("pw",password);
			params.put("startURL",sforceSettings.GET_WSDL_AUTO_URL);
			
			WebResponse response = httpClient.sendPostRequest(sforceSettings.SFORCE_LOGIN_URL,params);			
									                
			if( response.isHtmlContent() ){
				// it must be a refresh url
				String refreshUrl = response.getRefreshURL();
				if(refreshUrl==null){					
					if(response.getResponseAsString().indexOf("Invalid User Name or Password")!=-1){
						throw new sforceLoginException("Invalid username or password!");
					}
					throw new sforceDownloadException("Error invalid html!");
				}	
				response = httpClient.sendGetRequest(refreshUrl);				
			}
			
			if( response.isHtmlContent() ){
				// it must be a refresh url
				String refreshUrl = response.getRefreshURL();
				if(refreshUrl==null){
					throw new sforceDownloadException("Error invalid html!");
				}	
				response = httpClient.sendGetRequest(refreshUrl);				
			}
																	
			String conentDisposition = response.getHeaderField("Content-Disposition");
			if( response.getContentType().equals("text/xml") &&
					conentDisposition!=null && conentDisposition.indexOf("enterprise.wsdl")!=-1
			  ){
				
				PrintWriter w = new PrintWriter(new OutputStreamWriter(new FileOutputStream(m_SchemasDir+"\\enterprise.wsdl")));
				InputStream in = response.getConn().getInputStream();
				BufferedReader r = new BufferedReader( new InputStreamReader(in));
				String s = "";
				while( (s = r.readLine())!=null){
					//System.out.println(s);
					w.println(s);
				}
				w.close();
				in.close();
					
			} else {
				throw new sforceDownloadException("Error, wsdl not found!");	
			}				 			            

		}catch(IOException e){
			throw new sforceDownloadException("Error downloading wsdl : "+e.getMessage());
		}

    }


} 
