/*
 * XMethodsTestNet.java
 * 
 * Copyright 2004 BEA Systems, Inc.
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
package org.controlhaus.webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;

import net.xmethods.www.interfaces.query_xsd.IDNamePair;
import net.xmethods.www.interfaces.query_xsd.ServiceDetail;
import net.xmethods.www.interfaces.query_xsd.ServiceSummary;
import net.xmethods.www.interfaces.query_wsdl.XMethodsQuery;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class XMethodsTestNet extends ServiceControlTestCase {

    @Control public XMethodsQuery mXMethods;

    //private List<String> mPublishers = new ArrayList<String>();
    private List<String> mIDList = new ArrayList<String>();

    public void testServiceNames() throws Exception {
        IDNamePair[] serviceNames = mXMethods.getAllServiceNames();
        assertTrue(serviceNames.length > 0);
        System.out.println("XMethodsServiceNames");
        for (IDNamePair serviceName : serviceNames) {
            assertNotNull(serviceName);
            String name = serviceName.getName();
            assertNotNull(name);
            String id = serviceName.getId();
            assertNotNull(id);
            System.out.println(name + ' ' + id);
        }
    }


    public void testServiceSummaries() throws Exception {
        ServiceSummary[] serviceSummaries = mXMethods.getAllServiceSummaries();
        assertTrue(serviceSummaries.length > 0);
        System.out.println("XMethodsServiceSummaries");
        for (ServiceSummary serviceSummary : serviceSummaries) {
            assertNotNull(serviceSummary);

            String name = serviceSummary.getName();
            assertNotNull(name);
            String id = serviceSummary.getId();
            assertNotNull(id);
            mIDList.add(id);
            String desc = serviceSummary.getShortDescription();
            String wsdl = serviceSummary.getWsdlURL();
            assertNotNull(wsdl);
            String pub = serviceSummary.getPublisherID();
            assertNotNull(pub);
            //mPublishers.add(pub);
            System.out.println("Name: " + name);
            System.out.println("ID: " + id);
            System.out.println("Publisher ID: " + pub);
            System.out.println("WSDL URL: " + wsdl);
            System.out.println("Description: " + desc);
        }
    }

    public void testServiceDetails() throws Exception {
        for (String detailID : mIDList) {
            ServiceDetail detail = mXMethods.getServiceDetail(detailID);

            assertNotNull(detail);

            String name = detail.getName();
            assertNotNull(name);
            String id = detail.getId();
            assertNotNull(id);
            String desc = detail.getDescription();
            String wsdl = detail.getWsdlURL();
            assertNotNull(wsdl);
            String pub = detail.getPublisherID();
            assertNotNull(pub);
            System.out.println("Name: " + name);
            System.out.println("ID: " + id);
            System.out.println("Publisher ID: " + pub);
            System.out.println("WSDL URL: " + wsdl);
            System.out.println("Description: " + desc);
            System.out.println("Email: " + detail.getEmail());
            System.out.println("Info URL: " + detail.getInfoURL());
            System.out.println("Discussion URL: " + detail.getDiscussionURL());
            System.out.println("Notes: " + detail.getNotes());
            System.out.println("TmodelID: " + detail.getTmodelID());
            System.out.println("UUID: " + detail.getUuid());
        }
    }
}
