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

public interface FedexTrackTemplate {

    public String fill(FedexTrackRequest fedexTrackRequest);

    public static final class FedexTrackTemplateImpl implements FedexTrackTemplate {
        private static String TRANSACTION_ID = "TRANSACTION_ID";
        private static String ACCOUNT_NUMBER = "ACCOUNT_NUMBER";
        private static String METER_NUMBER = "METER_NUMBER";
        private static String CARRIER_CODE = "CARRIER_CODE";
        private static String PACKAGE_ID = "PACKAGE_ID";
        private static String PACKAGE_ID_TYPE = "PACKAGE_ID_TYPE";

        private static final String TEMPLATE = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                "<FDXTrackRequest xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"FDXTrackRequest.xsd\">" +
                "<RequestHeader>" +
                "<CustomerTransactionIdentifier>" + TRANSACTION_ID + "</CustomerTransactionIdentifier>" +
                "<AccountNumber>" + ACCOUNT_NUMBER + "</AccountNumber>" +
                "<MeterNumber>" + METER_NUMBER + "</MeterNumber>" +
                "<CarrierCode>" + CARRIER_CODE + "</CarrierCode>" +
                "</RequestHeader>" +
                "<PackageIdentifier>" +
                "<Value>" + PACKAGE_ID + "</Value>" +
                "<Type>" + PACKAGE_ID_TYPE + "</Type>" +
                "</PackageIdentifier>" +
                "</FDXTrackRequest>";

        public String fill(FedexTrackRequest fedexTrackRequest) {
            String result = new String(TEMPLATE);
            String transactionId = fedexTrackRequest.getTransactionId();
            if (transactionId == null) {
                transactionId = "";
            }
            result = result.replaceFirst(TRANSACTION_ID, transactionId);
            result = result.replaceFirst(ACCOUNT_NUMBER, fedexTrackRequest.getAccountNumber());
            result = result.replaceFirst(METER_NUMBER, fedexTrackRequest.getMeterNumber());
            result = result.replaceFirst(CARRIER_CODE, fedexTrackRequest.getCarrierCode());
            result = result.replaceFirst(PACKAGE_ID, fedexTrackRequest.getPackageIdentifier());
            result = result.replaceFirst(PACKAGE_ID_TYPE, fedexTrackRequest.getPackageIdentifierType());

            System.out.println("Track Request :" + "\n" + result);

            return result;
        }

    }

}
