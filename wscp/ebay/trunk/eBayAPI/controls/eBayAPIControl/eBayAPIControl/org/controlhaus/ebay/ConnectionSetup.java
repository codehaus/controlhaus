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

import java.net.URLConnection;

public interface ConnectionSetup {

    public void setup(URLConnection connection, ConnectionData connectionData, RequestData requestData, String request);

    public static class ConnectionSetupImpl implements ConnectionSetup {
        public static final String API_COMPATIBILITY_LEVEL = "X-EBAY-API-COMPATIBILITY-LEVEL";
        public static final String API_SESSION_CERTIFICATE = "X-EBAY-API-SESSION-CERTIFICATE";
        public static final String API_DEV_NAME = "X-EBAY-API-DEV-NAME";
        public static final String API_APP_NAME = "X-EBAY-API-APP-NAME";
        public static final String API_CERT_NAME = "X-EBAY-API-CERT-NAME";
        public static final String API_CALL_NAME = "X-EBAY-API-CALL-NAME";
        public static final String API_SITE_ID = "X-EBAY-API-SITEID";
        public static final String API_DETAIL_LEVEL = "X-EBAY-API-DETAIL-LEVEL";
        public static final String CONTENT_TYPE = "Content-Type";
        public static final String CONTENT_LENGTH = "Content-Length";

        public static final String VALUE_API_CALL_NAME = "GetItem";
        public static final String VALUE_CONTENT_TYPE = "text/xml";


        public void setup(URLConnection connection, ConnectionData connectionData, RequestData requestData, String request) {
            Contract.enforce(connection != null, "connection");
            Contract.enforce(request != null, "request");

            StringBuffer sessionCertificate = new StringBuffer(connectionData.getDevID() + ";");
            sessionCertificate.append(connectionData.getAppID() + ";");
            sessionCertificate.append(connectionData.getCertID());

            connection.setDoOutput(true);
            connection.setRequestProperty(API_COMPATIBILITY_LEVEL, requestData.getCompatibilityLevel());
            connection.setRequestProperty(API_SESSION_CERTIFICATE, sessionCertificate.toString());
            connection.setRequestProperty(API_DEV_NAME, connectionData.getDevID());
            connection.setRequestProperty(API_APP_NAME, connectionData.getAppID());
            connection.setRequestProperty(API_CERT_NAME, connectionData.getCertID());
            connection.setRequestProperty(API_CALL_NAME, VALUE_API_CALL_NAME);
            connection.setRequestProperty(API_SITE_ID, requestData.getSiteId());
            connection.setRequestProperty(API_DETAIL_LEVEL, requestData.getDetailLevel());
            connection.setRequestProperty(CONTENT_TYPE, VALUE_CONTENT_TYPE);
            connection.setRequestProperty(CONTENT_LENGTH, String.valueOf(request.length()));
        }
    }
}
