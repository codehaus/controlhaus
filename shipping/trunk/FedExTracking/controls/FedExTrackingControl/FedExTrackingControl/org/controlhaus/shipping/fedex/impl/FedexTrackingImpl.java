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


package org.controlhaus.shipping.fedex.impl;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

public class FedexTrackingImpl implements FedexTracking {
    private static final String XML_STND = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    private static final FedexTrackTemplate fedexTrackTemplate = new FedexTrackTemplate.FedexTrackTemplateImpl();

    public FedexResponse track(FedexTrackRequest fedexTrackRequest, FedexTracking.FedexConnection fedexConnection) throws IOException, UnknownHostException {
        Contract.enforce(fedexTrackRequest != null, "fedexTrackRequest, invalid trackRequest");
        Contract.enforce(fedexTrackRequest.getAccountNumber() != null, "fedexTrackRequest, invalid trackRequest");
        Contract.enforce(fedexTrackRequest.getMeterNumber() != null, "fedexTrackRequest, invalid trackRequest");
        Contract.enforce(fedexTrackRequest.getCarrierCode() != null, "fedexTrackRequest, invalid trackRequest");
        Contract.enforce(fedexTrackRequest.getPackageIdentifier() != null, "fedexTrackRequest, invalid trackRequest");
        Contract.enforce(fedexTrackRequest.getPackageIdentifierType() != null, "fedexTrackRequest, invalid trackRequest");

        Contract.enforce(fedexConnection.getServer() != null, "fedexConnection, invalid Server");
        Contract.enforce(fedexConnection.getPort() > 0, "fedexConnection, invalid port");

        String trackRequest = fedexTrackTemplate.fill(fedexTrackRequest);

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket(fedexConnection.getServer(), fedexConnection.getPort());

        socket.startHandshake();

        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

        out.println("POST /GatewayDC HTTP/1.0");
        out.println("Referer: Loosely Coupled Resources");
        out.println("Host: gatewaybeta.fedex.com");
        out.println("Accept: image/gif, image/jpeg, image/pjpeg, text/plain, text/html, */*");
        out.println("Content-type: text/plain");
        out.println("Content-length: " + String.valueOf(trackRequest.length()));
        out.println();

        out.println(trackRequest);
        out.flush();

        if (out.checkError())
            System.out.println("Could not write : java.io.PrintWriter error");

        /* make response */
        FedexResponseImpl fedexResponse = new FedexResponseImpl();
        boolean responseBodyStarted = false;
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String responseLine = in.readLine();
        while (responseLine != null) {

            if (!responseLine.startsWith(XML_STND)) {
                if (responseBodyStarted) {
                    fedexResponse.addToBody(responseLine);
                } else {
                    fedexResponse.addHeader(responseLine);
                }
            } else {
                responseBodyStarted = true;
                fedexResponse.addToBody(responseLine);
            }

            responseLine = in.readLine();
        }

        in.close();
        out.close();
        socket.close();

        return fedexResponse;
    }

    public static final class FedexResponseImpl implements FedexResponse {
        private Collection headers = new ArrayList();
        private StringBuffer bodyBuffer = new StringBuffer();

        public void addHeader(String header) {
            this.headers.add(header);
        }

        public void addToBody(String body) {
            this.bodyBuffer.append(body);
        }

        public String[] headers() {
            return (String[]) this.headers.toArray(new String[0]);
        }

        public String body() {
            return this.bodyBuffer.toString();
        }
    }
}
