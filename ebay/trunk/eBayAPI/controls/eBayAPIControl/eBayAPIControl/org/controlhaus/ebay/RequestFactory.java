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

public interface RequestFactory {

    public static final String HEADER = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" + "<request xmlns=\"urn:eBayAPIschema\">";
    public static final String FOOTER = "<Verb>GetItem</Verb>" + "</request>";

    public static final String PLACEHOLDER_USER_ID = "PH_USER_ID";
    public static final String PLACEHOLDER_PASSWORD = "PH_PASSWORD";

    public static final String PLACEHOLDER_DETAIL_LEVEL = "PH_DETAIL_LEVEL";
    public static final String PLACEHOLDER_ERROR_LEVEL = "PH_ERROR_LEVEL";
    public static final String PLACEHOLDER_SITE_ID = "PH_SITE_ID";
    public static final String PLACEHOLDER_ITEM_ID = "PH_ITEM_ID";

    public static final String REQUEST_TEMPLATE = HEADER +
            "<RequestUserId>" + PLACEHOLDER_USER_ID + "</RequestUserId>" +
            "<RequestPassword>" + PLACEHOLDER_PASSWORD + "</RequestPassword>" +
            "<DetailLevel>" + PLACEHOLDER_DETAIL_LEVEL + "</DetailLevel>" +
            "<ErrorLevel>" + PLACEHOLDER_ERROR_LEVEL + "</ErrorLevel>" +
            "<SiteId>" + PLACEHOLDER_SITE_ID + "</SiteId>" +
            "<Id>" + PLACEHOLDER_ITEM_ID + "</Id>" +
            FOOTER;

    public String getRequest(RequestData requestData, ConnectionData connectionData);
}
