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

public class RequestFactoryImpl implements RequestFactory {
    public String getRequest(RequestData requestData, ConnectionData connectionData) {
        String result = new String(REQUEST_TEMPLATE);
        result = result.replaceFirst(PLACEHOLDER_USER_ID, connectionData.getUserId());
        result = result.replaceFirst(PLACEHOLDER_PASSWORD, connectionData.getPassword());
        result = result.replaceFirst(PLACEHOLDER_ITEM_ID, requestData.getItemId());
        result = result.replaceFirst(PLACEHOLDER_DETAIL_LEVEL, requestData.getDetailLevel());
        result = result.replaceFirst(PLACEHOLDER_SITE_ID, requestData.getSiteId());
        result = result.replaceFirst(PLACEHOLDER_ERROR_LEVEL, requestData.getErrorLevel());
        return result;
    }
}
