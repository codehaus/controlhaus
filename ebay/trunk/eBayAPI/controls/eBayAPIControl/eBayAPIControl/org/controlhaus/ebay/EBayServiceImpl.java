/*   Copyright 2004 BEA Systems, Inc.
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


package org.controlhaus.ebay;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class EBayServiceImpl implements EBayService {
    private final RequestFactory requestFactory = new RequestFactoryImpl();
    private final ConnectionSetup connectionSetup = new ConnectionSetup.ConnectionSetupImpl();


    public String getItem(ConnectionData connectionData, RequestData requestData) throws IOException {

        Contract.enforce(connectionData != null, "connectionData");
        Contract.enforce(connectionData.getUserId() != null, "connectionData username");
        Contract.enforce(connectionData.getPassword() != null, "connectionData password");
        Contract.enforce(connectionData.getURL() != null, "connectionData URL");
        Contract.enforce(connectionData.getDevID() != null, "connectionData DevID");
        Contract.enforce(connectionData.getAppID() != null, "connectionData AppID");
        Contract.enforce(connectionData.getCertID() != null, "connectionData CertID");

        Contract.enforce(requestData != null, "requestData");
        Contract.enforce(requestData.getItemId() != null, "requestData ItemId");
        Contract.enforce(requestData.getSiteId() != null, "requestData siteId");
        Contract.enforce(requestData.getErrorLevel() != null, "requestData errorLevel");
        Contract.enforce(requestData.getDetailLevel() != null, "requestData detailLevel");
        Contract.enforce(requestData.getCompatibilityLevel() != null, "requestData compatibilityLevel");

        String request = requestFactory.getRequest(requestData, connectionData);

        URL url = new URL(connectionData.getURL());
        URLConnection connection = url.openConnection();
        connectionSetup.setup(connection, connectionData, requestData, request);

        PrintWriter pw = new PrintWriter(connection.getOutputStream());
        pw.write(request);
        pw.close();

        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String responseLine = reader.readLine();

        StringBuffer responseBuffer = new StringBuffer();
        while (responseLine != null && !responseLine.equals("")) {
            responseBuffer.append(responseLine);
            responseLine = reader.readLine();
        }
        reader.close();
        inputStream.close();

        return responseBuffer.toString();

    }
}
