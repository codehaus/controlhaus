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


package org.controlhaus.ups;

import org.controlhaus.ups.AccessRequest;
import org.controlhaus.ups.TrackRequest;

import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UpsTracker {
    private final URL server;

    public UpsTracker(URL serverUrl) {
        this.server = serverUrl;
    }

    public String executeRequest(AccessRequest accessRequest, TrackRequest trackRequest) throws IOException, UpsTrackingException {
        URLConnection conn = server.openConnection();
        conn.setDoOutput(true);
        BufferedOutputStream out = new BufferedOutputStream(conn.getOutputStream());
        out.write((accessRequest.toUpsXmlRequest() + trackRequest.toUpsXmlRequest()).getBytes("UTF-8"));
	out.flush();
        System.out.println("wrote " + (accessRequest.toUpsXmlRequest() + trackRequest.toUpsXmlRequest()));
        BufferedInputStream in = new BufferedInputStream(conn.getInputStream());

        ByteArrayOutputStream response = new ByteArrayOutputStream();
        copy(in, response);

        final String responseXml = response.toString("UTF-8");
        System.out.println(responseXml);
        return responseXml;
        //return new TrackResponse(responseXml);
    }

    private static final void copy(InputStream from, OutputStream to) throws IOException {
        int chunk = 1024;
        final byte[] buff = new byte[chunk];
        int bytesRead = -1;
        while (-1 != (bytesRead = from.read(buff, 0, buff.length))) {
            to.write(buff, 0, bytesRead);
        }
    }
}
