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

public class TrackRequest {
    private final String trackingNumber;
    private final String context;

    public TrackRequest(String context, String trackingNumber) {
        this.context = context;
        this.trackingNumber = trackingNumber;
    }

    public String toUpsXmlRequest() {
        return "<?xml version=\"1.0\"?>" +
                "<TrackRequest xml:lang=\"en-US\">" +
                "<Request>" +
                "<TransactionReference>" +
                "<CustomerContext>" + context + "</CustomerContext>" +
                "<XpciVersion>1.0001</XpciVersion>" +
                "</TransactionReference>" +
                "<RequestAction>Track</RequestAction>" +
                "<RequestOption>activity</RequestOption>" +
                "</Request>" +
                "<TrackingNumber>" + trackingNumber + "</TrackingNumber>" +
                "</TrackRequest>";
    }
}
