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

public class AccessRequest {
    private final String accessCode;
    private final String username;
    private final String password;

    public AccessRequest(String accessCode, String username, String password) {
        this.accessCode = accessCode;
        this.username = username;
        this.password = password;
    }

    public String toUpsXmlRequest() {
        return "<?xml version=\"1.0\"?>" +
                "<AccessRequest xml:lang=\"en-US\">" +
                "<AccessLicenseNumber>" + accessCode + "</AccessLicenseNumber>" +
                "<UserId>" + username + "</UserId>" +
                "<Password>" + password + "</Password>" +
                "</AccessRequest>";
    }
}
