/*
 * GoogleServlet.java
 * 
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 * Original author: Jonathan Colwell
 */
package stubby;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlThreadContext;
import org.apache.beehive.controls.api.context.ControlBeanContext;


/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class GoogleServlet extends HttpServlet {
    
    @Control public stubby.GoogleClient mGoogle;

    private String mGoogleWebServiceKey;

    public void init(ServletConfig conf) throws ServletException {
        mGoogleWebServiceKey = conf.getInitParameter("googleKey");
        if (mGoogleWebServiceKey == null) {
            throw new ServletException("a key is required to invoke the Google Web Service");
        }
    }

    public void service(HttpServletRequest req,
                        HttpServletResponse resp)
        throws ServletException {
        try {
            ServletOutputStream sos = resp.getOutputStream();

            if (mGoogle == null) {

                ControlBeanContext cont = ControlThreadContext.getContext();
                GoogleServletClientInitializer.initialize(cont, this);
                sos.println("initializing Google Client");
            }
            String task = (String)req.getParameter("task");
            if (task != null) {
                
                if (task.equals("cache")) {
                    sos.println("retrieving cache");
                    String url = (String)req.getParameter("url");
                    if (url != null) {
                        byte[] cachedPage = mGoogle
                            .getCachedPage(mGoogleWebServiceKey,
                                           url);
                  
                        sos.write(cachedPage);
                    }
                    else {
                        throw new ServletException
                            ("A url must be provided to view a cached page");
                    }
                }
                else if (task.equals("spell")) {

                    resp.setContentType("text/plain");
                    sos.println("spellcheck");
                    sos.println
                        (mGoogle.
                         suggestSpelling(mGoogleWebServiceKey,
                                         req.getParameter("phrase")));
                }
                else if (task.equals("search")) {
                 
                    resp.setContentType("text/plain");
                    int start = 0;
                    int maxResults = 10;
                    try {
                        start = Integer.parseInt(req.getParameter("start"));
                    }
                    catch (NumberFormatException nfe) {
                    }
                
                    try {
                        Integer.parseInt(req.getParameter("maxResults"));
                    }
                    catch (NumberFormatException nfe) {
                    }
                
                    GoogleSearch.GoogleSearchResult results = mGoogle
                        .search(mGoogleWebServiceKey,
                                req.getParameter("q"),
                                start,
                                maxResults,
                                Boolean.getBoolean(req.getParameter("filter")),
                                req.getParameter("restrict"),
                                Boolean.getBoolean(req
                                                   .getParameter("safeSearch")),
                                req.getParameter("lr"),
                                req.getParameter("ie"),
                                req.getParameter("oe"));

                    for (GoogleSearch.ResultElement re :results
                             .getResultElements()) {
                        sos.println(re.getTitle());
                        sos.println(re.getURL());
                        sos.println(re.getSummary() + "\n\n");
                    }
                }
            }
            
            // FIXME jcolwell@bea.com 2004-Nov-15 -- 
            // set up forms to call the API tasks
            sos.println(new java.util.Date().toString());
            sos.close();
        }
        catch (org.apache.axis.AxisFault af) {
            Throwable cause = af.getCause();
            if (cause != null) {
                if (cause instanceof org.xml.sax.SAXException) {
                    cause = ((org.xml.sax.SAXException)cause).getException();
                }
                throw new ServletException(cause);
            }
            else {
                throw new ServletException(af);
            }
        }
        catch (org.xml.sax.SAXException se) {
            throw new ServletException(se.getException());
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }  
}
