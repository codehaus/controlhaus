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


package org.controlhaus.net.http.pojo;

import org.controlhaus.net.http.pojo.DataAdapter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;

public class HttpPojo {
    public void post(URL target, DataAdapter data) throws IOException {
        URLConnection urlConn = target.openConnection();
        urlConn.setDoOutput(true);
        urlConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        DataOutputStream printout = new DataOutputStream(urlConn.getOutputStream());
        final StringBuffer content = new StringBuffer();
        data.foreachDataItem(new DataAdapter.ItemCommand() {
            public void onDataItem(Object name, Object value) throws UnsupportedEncodingException {
                if (content.length() > 0) {
                    content.append("&");
                }
                content.append(name).append("=").append(URLEncoder.encode(value.toString(), "UTF-8"));
            }
        });

        printout.writeBytes(content.toString());
        printout.flush();
        printout.close();

        DataInputStream input = new DataInputStream(urlConn.getInputStream());
        String str;
        while (null != ((str = input.readLine()))) {
            System.out.println(str);
        }
        input.close();
    }

    public int get(URL target, DataAdapter data, StringBuffer resultCollector) throws IOException {
        final StringBuffer content = new StringBuffer(target.toString()).append("?");
        data.foreachDataItem(new DataAdapter.ItemCommand() {
            public void onDataItem(Object name, Object value) throws UnsupportedEncodingException {
                if (content.length() > 0) {
                    content.append("&");
                }
                content.append(name).append("=").append(URLEncoder.encode(value.toString(), "UTF-8"));
            }
        });

        URL newTarget = new URL(content.toString());
        HttpURLConnection urlConn = (HttpURLConnection) newTarget.openConnection();
        urlConn.setDoInput(true);

        DataInputStream input = new DataInputStream(urlConn.getInputStream());
        String str;
        while (null != ((str = input.readLine()))) {
            resultCollector.append(str);
        }
        input.close();
        return urlConn.getResponseCode();
    }
}
