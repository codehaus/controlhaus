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


/*
 * Created on May 5, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.controlhaus.sforce.workshop.ide;

import com.bea.ide.Application;
import com.bea.ide.workspace.IWorkspace;
import com.bea.ide.workspace.ServerSvc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.prefs.Preferences;

/**
 * Simple web cilent wich can send http get & post requests,
 * stores and retransmits received cookies.
 * 
 */
public class SimpleWebClient {

	private static final String HTML_META_REFRESH_TAG = "<meta http-equiv=\"Refresh\" content=\"0; URL=";  
	
	/**
	 * Stores the received cookies
	 */
	private HashMap cookies = new HashMap();
		
	/**
	 * 
	 */
	public SimpleWebClient() {
		super();	
	}
	
	/**
	 * Sends an http get request to urlStr 
	 * @param urlStr
	 * @return http response
	 * @throws IOException
	 */
	public WebResponse sendGetRequest(String urlStr) throws IOException{
		
        setSystemProperties();
		URL url = new URL(urlStr);			
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setInstanceFollowRedirects(true);
		conn.setRequestMethod("GET");		
		sendCookies(conn);
		parseHeaderFields(conn);		
		return new WebResponse(conn,url);

	}
	
	/**
	 * Sends an http get request to urlStr
	 * @param urlStr
	 * @param postParams - request parameters (key,value)
	 * @return
	 * @throws IOException
	 */
	public WebResponse sendPostRequest(String urlStr, HashMap postParams) throws IOException{
		
		URL url = new URL(urlStr);			
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setInstanceFollowRedirects(true);
		conn.setRequestMethod("POST");		
		
		sendCookies(conn);		

		// set content type
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");			 

		conn.setDoOutput(true);
								
		// send post parameters 
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		
		StringBuffer paramStr = new StringBuffer();
		Iterator paramIt = postParams.keySet().iterator();
		while(paramIt.hasNext()){
		   String param = (String)paramIt.next();
		   if(paramStr.length()!=0){
				paramStr.append("&");
		   }
		   paramStr.append(param).append("=").append(postParams.get(param)); 
		}								
				 
		wr.write(paramStr.toString());
		wr.close();
		
		// save cookies
		parseHeaderFields(conn);
		
		return new WebResponse(conn,url);
	}
	
	/**
	 * Saves received cookies in cookies 
	 * @param conn
	 */
	private void parseHeaderFields(URLConnection conn){
		
		Map headers = conn.getHeaderFields();
		Iterator keys = headers.keySet().iterator();
		while(keys.hasNext()){
		   
		   String key = (String)keys.next();		   		   
		   // add to cookies if it's a cookie
		   if(key!=null && key.equals("Set-Cookie")){
				String cookie = conn.getHeaderField(key);
				int i = cookie.indexOf("=");
				String ckey = cookie.substring(0,i);
				String cvalue = cookie.substring(i+1,cookie.indexOf(";"));
				System.out.println("Cookie received: "+ckey+" = "+cvalue);
		   		cookies.put(ckey,cvalue);
		   }
		   
		}
		
	}
	
	/**
	 * Sends back received cookies
	 * @param conn
	 */
	private void sendCookies(URLConnection conn){
		
		StringBuffer cookieStr = new StringBuffer();
		Iterator keys = cookies.keySet().iterator();
		while(keys.hasNext()){
		   String key = (String)keys.next();
		   System.out.println("Send Cookie: "+key+" = "+cookies.get(key));
		   cookieStr.append(key).append("=").append(cookies.get(key)).append("; "); 
		}		
		if(cookieStr.length()!=0){			
			conn.setRequestProperty("Cookie",cookieStr.toString());
		}
		
	}
		
	/**
	 * WebResponse holds the http response.
	 *		 
	 */
	public class WebResponse {
		
			private URL url;
			
			private URLConnection conn;
			/**
			 * Response saved
			 */
			private String responseStr;
			
			public WebResponse(URLConnection conn,
							   URL url){
							   	
				this.conn = conn;
				this.url = url; 
			}
			
			/**
			 * @return
			 */
			public URLConnection getConn() {
				return conn;
			}

			/**
			 * @param connection
			 */
			public void setConn(URLConnection connection) {
				conn = connection;
			}

			/**
			 * @return
			 */
			public URL getUrl() {
				return url;
			}

			/**
			 * @param url
			 */
			public void setUrl(URL url) {
				this.url = url;
			}
			
			/**
			 * Returns http response as a String
			 * @return 
			 * @throws IOException
			 */
			public String getResponseAsString() throws IOException {
			
				// load response form connection inputstream
				if(responseStr==null){
					BufferedReader r = new BufferedReader( new InputStreamReader(conn.getInputStream()));
					StringBuffer buf = new StringBuffer();
					String s = "";
					while( (s = r.readLine())!=null){					
						buf.append(s).append("\r\n");					
					}		
					responseStr = buf.toString();
					conn.getInputStream().close();					
				}				
				return responseStr;
			 
			}
			
			/**
			 * Return response content type
			 * @return
			 */
			public String getContentType(){
				String str = conn.getHeaderField("Content-Type");
				if(str==null){
					return "n/a";
				}
				int idx = str.indexOf(";");
				if(idx!=-1){
					str = str.substring(0,idx);
				}
				return str;
			}
			
			/**
			 * Return true if response content type is text/html
			 * @return
			 */
			public boolean isHtmlContent(){
				return this.getContentType().equals("text/html"); 
			}
			
			/**
			 * Parses refresh url from html head section. Returns null
			 * if response content type is not html, or the html doesn't
			 * contain refresh tag. 
			 * @return
			 * @throws IOException
			 */
			public String getRefreshURL() throws IOException{
				
				String refreshUrl = null;
				responseStr = getResponseAsString();
			
				int idx = responseStr.indexOf(SimpleWebClient.HTML_META_REFRESH_TAG); 
				if(idx!=-1){
					refreshUrl = responseStr.substring(idx+SimpleWebClient.HTML_META_REFRESH_TAG.length(),responseStr.lastIndexOf("\">"));														
					// if url starts with / (or doesn't starts with http://, https:// ) that means this is a relative url
					if( refreshUrl.startsWith("/") || 
							(!refreshUrl.startsWith("http://") && 
								!refreshUrl.startsWith("https://") ) ){
						refreshUrl = url.getProtocol()+"://"+url.getHost()+ (url.getPort()!=-1 ? ":"+url.getPort() : "") + refreshUrl; 
					}					
					System.out.println("RefreshUrl: "+refreshUrl);														
				}
				return refreshUrl;

			}
			
			/**
			 * Returns the value of header field for the given fieldName
			 * @param fieldName
			 * @return
			 */
			public String getHeaderField(String fieldName){
				return getConn().getHeaderField(fieldName);
			}

	}
    
    private void setSystemProperties() throws IOException 
    {
        IWorkspace w = Application.getWorkspace();
        if (null==w)
            throw new IOException("Can't continue without an active application");        
                                                        
        URI uriWlsHome = ServerSvc.get().getWlsHome(w);
        File fileWlsHome = new File(uriWlsHome);
        String strBeaHome = fileWlsHome.getParent();
         // beahome tells the libs where to find the  license file
        System.setProperty( "bea.home", strBeaHome);
        // a workaround for "bad certificate" errors on https                    
        System.setProperty("weblogic.webservice.client.ssl.strictcertchecking", "false");           
    }


	
}
