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

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.controlhaus.ups.PackageActivity;

public class TrackResponse {
    private final String xml;
    private int returnCode = 1;
    private String errorDescription = "";
    private int errorCode = 0;
    private PackageActivity[] packageActivities;

    public TrackResponse(String xml) throws UpsTrackingException {
        this.xml = xml;
        parseXml();
    }

    public boolean isSuccessful() {
        return returnCode == 1;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    private void parseXml() throws FactoryConfigurationError, UpsTrackingException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
            parseDom(doc);
        } catch (Exception e) {
            throw new UpsTrackingException("Exception parsing XML response from UPS server", e);
        }
    }

    private void parseDom(Document doc) {
        this.returnCode = parseReturnCode(doc);
        if ( returnCode != 1) {
            errorDescription = parseReturnDescription(doc);
            errorCode = parseErrorCode(doc);
        } else {
            packageActivities = parsePackageActivities(doc);
        }
    }

    private PackageActivity[] parsePackageActivities(Document doc) {
        List activities = new ArrayList();
        parsePackageActivities(doc.getElementsByTagName("Activity"), activities);
        PackageActivity []result = new PackageActivity[activities.size()];
        return (PackageActivity[]) activities.toArray(result);
    }

    private void parsePackageActivities(NodeList activityNodes, List activityCollector) {
        for(int i=0;i<activityNodes.getLength();i++) {
            activityCollector.add(parseActivity(activityNodes.item(i)));
        }
    }

    private PackageActivity parseActivity(Node activityNode) {
        PackageActivity result = new PackageActivity();
        NodeList children = activityNode.getChildNodes();
        for(int i=0;i<children.getLength();i++) {
            Node child = children.item(i);
            if ( "ActivityLocation".equalsIgnoreCase(child.getNodeName())) {
                parseActivityLocation(result, child);
            } else if ( "Status".equalsIgnoreCase(child.getNodeName())) {
                parseStatus(result, child);
            } else if ("Date".equalsIgnoreCase(child.getNodeName())) {
                result.setDate(child.getFirstChild().getNodeValue());
            } else if ( "Time".equalsIgnoreCase(child.getNodeName())) {
                result.setTime(child.getFirstChild().getNodeValue());
            }
        }
        return result;
    }

    private void parseActivityLocation(PackageActivity activity, Node activityLocatioNode) {
        NodeList children = activityLocatioNode.getChildNodes();
        for(int i=0;i<children.getLength();i++) {
            Node child = children.item(i);
            if ( "Address".equalsIgnoreCase(child.getNodeName())) {
                parseAddress(activity, child);
            }
        }
    }

    private void parseStatus(PackageActivity activity, Node statusNode) {
        NodeList children = statusNode.getChildNodes();
        for(int i=0;i<children.getLength();i++) {
            Node child = children.item(i);
            if ( "StatusType".equalsIgnoreCase(child.getNodeName())) {
                parseStatusType(activity, child);
            }
        }

    }

    private void parseStatusType(PackageActivity activity, Node statusTypeNode) {
        NodeList children = statusTypeNode.getChildNodes();
        for(int i=0;i<children.getLength();i++) {
            Node child = children.item(i);
            if ( "Description".equalsIgnoreCase(child.getNodeName())) {
                activity.setStatus(child.getFirstChild().getNodeValue());
            }
        }

    }

    private void parseAddress(PackageActivity activity, Node addressNode) {
        NodeList children = addressNode.getChildNodes();
        for(int i=0;i<children.getLength();i++) {
            Node child = children.item(i);
            if ( "City".equalsIgnoreCase(child.getNodeName())) {
                activity.setCity(child.getFirstChild().getNodeValue());
            } else if ( "CountryCode".equalsIgnoreCase(child.getNodeName())) {
                activity.setCountryCode(child.getFirstChild().getNodeValue());
            }
        }
    }

    private int parseErrorCode(Document doc) {
        return Integer.parseInt(getNamedNodeValue(doc, "ErrorCode"));
    }

    private String parseReturnDescription(Document doc) {
        return getNamedNodeValue(doc, "ErrorDescription");
    }

    private int parseReturnCode(Document doc) {
        return Integer.parseInt(getNamedNodeValue(doc, "ResponseStatusCode"));
    }

    private String getNamedNodeValue(Document doc, final String nodeName) {
        String result = "";
        NodeList nodes = doc.getElementsByTagName(nodeName);
        if ( nodes.getLength() > 0 ) {
            Node node = nodes.item(0);
            result = node.getFirstChild().getNodeValue();
        }
        return result;
    }

    public PackageActivity[] getPackageActivities() {
        return packageActivities;
    }
}
