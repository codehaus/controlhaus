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

import com.bea.xbean.values.XmlValueOutOfRangeException;
import org.controlhaus.schema.fedex.*;

import java.util.Collection;
import java.util.ArrayList;

public class FedexTrackReplyBean {
    private String customerTransactionIdentifier = "";
    private String moreData = "";
    private Collection trackProfileBeans = new ArrayList();

    public FedexTrackReplyBean(FDXTrackReplyDocument.FDXTrackReply fdxTrackReply) {
        if (fdxTrackReply != null) {
            ReplyHeader replyHeader = fdxTrackReply.getReplyHeader();
            if (replyHeader != null) {
                customerTransactionIdentifier = getValue(replyHeader.getCustomerTransactionIdentifier());
            }

            moreData = String.valueOf(fdxTrackReply.getMoreData());

            TrackProfile[] trackProfiles = fdxTrackReply.getTrackProfileArray();

            TrackProfileBean trackProfileBean = null;
            for (int i = 0; i < trackProfiles.length; i++) {
                TrackProfile trackProfile = trackProfiles[i];

                trackProfileBean = new TrackProfileBean();
                trackProfileBean.setTrackingNumber(getValue(trackProfile.getTrackingNumber()));

                if (trackProfile.getCarrierCode() != null) {
                    trackProfileBean.setCarrierCode(trackProfile.getCarrierCode().toString());
                }

                if (trackProfile.getOtherIdentifierCount() != null) {
                    trackProfileBean.setOtherIdentifierCount(String.valueOf(trackProfile.getOtherIdentifierCount()));
                }

                if (trackProfile.getShipDate() != null) {
                    trackProfileBean.setShipDate(trackProfile.getShipDate().toString());
                }

                Address address = trackProfile.getDestinationAddress();
                if (address != null) {
                    trackProfileBean.setDestinationCity(getValue(address.getCity()));
                    trackProfileBean.setDestinationStateOrProvinceCode(getValue(address.getStateOrProvinceCode()));
                    trackProfileBean.setDestinationPostalCode(getValue(address.getPostalCode()));
                    trackProfileBean.setDestinationCountryCode(getValue(address.getPostalCode()));
                }

                if (trackProfile.getDeliveredDate() != null) {
                    trackProfileBean.setDeliveredDate(trackProfile.getDeliveredDate().toString());
                }

                try{
                    if (trackProfile.getDeliveredTime() != null) {                    
                        trackProfileBean.setDeliveredTime(trackProfile.getDeliveredTime().toString());                    
                    }
                }catch(XmlValueOutOfRangeException e){
                        e.printStackTrace();
                        trackProfileBean.setDeliveredTime("");                        
                }

                trackProfileBean.setSignedForBy(getValue(trackProfile.getSignedForBy()));
                trackProfileBean.setDeliveredLocationCode(getValue(trackProfile.getDeliveredLocationCode()));
                trackProfileBean.setDeliveredLocationDescription(getValue(trackProfile.getDeliveredLocationDescription()));
                trackProfileBean.setService(getValue(trackProfile.getService()));

                if (trackProfile.getWeight() != null) {
                    trackProfileBean.setWeight(String.valueOf(trackProfile.getWeight()));
                }

                if (trackProfile.getWeightUnits() != null) {
                    trackProfileBean.setWeight(trackProfile.getWeightUnits().toString());
                }

                trackProfileBean.setPackagingDescription(getValue(trackProfile.getPackagingDescription()));

                if (trackProfile.getPackageSequenceNumber() != null) {
                    trackProfileBean.setPackageSequenceNumber(String.valueOf(trackProfile.getPackageSequenceNumber()));
                }

                if (trackProfile.getPackageCount() != null) {
                    trackProfileBean.setPackageCount(String.valueOf(trackProfile.getPackageCount()));
                }

                trackProfileBean.setFedExURL(getValue(trackProfile.getFedExURL()));

                if (trackProfile.getScanCount() != null) {
                    trackProfileBean.setScanCount(String.valueOf(trackProfile.getScanCount()));
                }

                Scan[] scans = trackProfile.getScanArray();

                for (int x = 0; x < scans.length; x++) {
                    Scan scan = scans[x];
                    ScanBean scanBean = new ScanBean();

                    if (scan.getDate() != null) {
                        scanBean.setScanDate(scan.getDate().toString());
                    }
                    
                    try{
                        if (scan.getTime() != null) {
                            scanBean.setScanTime(scan.getTime().toString());
                        }
                    }catch(XmlValueOutOfRangeException e){
                        e.printStackTrace();
                        scanBean.setScanTime("");
                    }

                    scanBean.setScanType(getValue(scan.getScanType()));
                    scanBean.setScanDescription(getValue(scan.getScanDescription()));
                    scanBean.setScanCity(getValue(scan.getCity()));
                    scanBean.setScanStateOrProvinceCode(getValue(scan.getStateOrProvinceCode()));
                    scanBean.setScanCountryCode(getValue(scan.getCountryCode()));

                    trackProfileBean.getScanBeans().add(scanBean);
                }

                trackProfileBeans.add(trackProfileBean);
            }
        }
    }

    private String getValue(String value) {
        return value == null ? "" : value;
    }

    public String getCustomerTransactionIdentifier() {
        return customerTransactionIdentifier;
    }

    public String getMoreData() {
        return moreData;
    }

    public void setMoreData(String moreData) {
        this.moreData = moreData;
    }

    public Collection getTrackProfileBeans() {
        return trackProfileBeans;
    }

    public void setTrackProfileBeans(Collection trackProfileBeans) {
        this.trackProfileBeans = trackProfileBeans;
    }

    public static final class TrackProfileBean {
        private String trackingNumber = "";
        private String carrierCode = "";
        private String otherIdentifierCount = "";
        private String shipDate = "";
        private String destinationCity = "";
        private String destinationStateOrProvinceCode = "";
        private String destinationPostalCode = "";
        private String destinationCountryCode = "";
        private String deliveredDate = "";
        private String deliveredTime = "";
        private String signedForBy = "";
        private String deliveredLocationCode = "";
        private String deliveredLocationDescription = "";
        private String service = "";
        private String weight = "";
        private String weightUnits = "";
        private String packagingDescription = "";
        private String packageSequenceNumber = "";
        private String packageCount = "";
        private String fedExURL = "";
        private String scanCount = "";

        private Collection scanBeans = new ArrayList();

        public String getTrackingNumber() {
            return trackingNumber;
        }

        public void setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
        }

        public String getCarrierCode() {
            return carrierCode;
        }

        public void setCarrierCode(String carrierCode) {
            this.carrierCode = carrierCode;
        }

        public String getOtherIdentifierCount() {
            return otherIdentifierCount;
        }

        public void setOtherIdentifierCount(String otherIdentifierCount) {
            this.otherIdentifierCount = otherIdentifierCount;
        }

        public String getShipDate() {
            return shipDate;
        }

        public void setShipDate(String shipDate) {
            this.shipDate = shipDate;
        }

        public String getDestinationCity() {
            return destinationCity;
        }

        public void setDestinationCity(String destinationCity) {
            this.destinationCity = destinationCity;
        }

        public String getDestinationStateOrProvinceCode() {
            return destinationStateOrProvinceCode;
        }

        public void setDestinationStateOrProvinceCode(String destinationStateOrProvinceCode) {
            this.destinationStateOrProvinceCode = destinationStateOrProvinceCode;
        }

        public String getDestinationPostalCode() {
            return destinationPostalCode;
        }

        public void setDestinationPostalCode(String destinationPostalCode) {
            this.destinationPostalCode = destinationPostalCode;
        }

        public String getDestinationCountryCode() {
            return destinationCountryCode;
        }

        public void setDestinationCountryCode(String destinationCountryCode) {
            this.destinationCountryCode = destinationCountryCode;
        }

        public String getDeliveredDate() {
            return deliveredDate;
        }

        public void setDeliveredDate(String deliveredDate) {
            this.deliveredDate = deliveredDate;
        }

        public String getDeliveredTime() {
            return deliveredTime;
        }

        public void setDeliveredTime(String deliveredTime) {
            this.deliveredTime = deliveredTime;
        }

        public String getSignedForBy() {
            return signedForBy;
        }

        public void setSignedForBy(String signedForBy) {
            this.signedForBy = signedForBy;
        }

        public String getDeliveredLocationCode() {
            return deliveredLocationCode;
        }

        public void setDeliveredLocationCode(String deliveredLocationCode) {
            this.deliveredLocationCode = deliveredLocationCode;
        }

        public String getDeliveredLocationDescription() {
            return deliveredLocationDescription;
        }

        public void setDeliveredLocationDescription(String deliveredLocationDescription) {
            this.deliveredLocationDescription = deliveredLocationDescription;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getWeightUnits() {
            return weightUnits;
        }

        public void setWeightUnits(String weightUnits) {
            this.weightUnits = weightUnits;
        }

        public String getPackagingDescription() {
            return packagingDescription;
        }

        public void setPackagingDescription(String packagingDescription) {
            this.packagingDescription = packagingDescription;
        }

        public String getPackageSequenceNumber() {
            return packageSequenceNumber;
        }

        public void setPackageSequenceNumber(String packageSequenceNumber) {
            this.packageSequenceNumber = packageSequenceNumber;
        }

        public String getPackageCount() {
            return packageCount;
        }

        public void setPackageCount(String packageCount) {
            this.packageCount = packageCount;
        }

        public String getFedExURL() {
            return fedExURL;
        }

        public void setFedExURL(String fedExURL) {
            this.fedExURL = fedExURL;
        }

        public String getScanCount() {
            return scanCount;
        }

        public void setScanCount(String scanCount) {
            this.scanCount = scanCount;
        }

        public Collection getScanBeans() {
            return scanBeans;
        }

    }

    public static class ScanBean {
        private String scanDate = "";
        private String scanTime = "";
        private String scanType = "";
        private String scanDescription = "";
        private String scanCity = "";
        private String scanStateOrProvinceCode = "";
        private String scanCountryCode = "";

        public String getScanDate() {
            return scanDate;
        }

        public void setScanDate(String scanDate) {
            this.scanDate = scanDate;
        }

        public String getScanTime() {
            return scanTime;
        }

        public void setScanTime(String scanTime) {
            this.scanTime = scanTime;
        }

        public String getScanType() {
            return scanType;
        }

        public void setScanType(String scanType) {
            this.scanType = scanType;
        }

        public String getScanDescription() {
            return scanDescription;
        }

        public void setScanDescription(String scanDescription) {
            this.scanDescription = scanDescription;
        }

        public String getScanCity() {
            return scanCity;
        }

        public void setScanCity(String scanCity) {
            this.scanCity = scanCity;
        }

        public String getScanStateOrProvinceCode() {
            return scanStateOrProvinceCode;
        }

        public void setScanStateOrProvinceCode(String scanStateOrProvinceCode) {
            this.scanStateOrProvinceCode = scanStateOrProvinceCode;
        }

        public String getScanCountryCode() {
            return scanCountryCode;
        }

        public void setScanCountryCode(String scanCountryCode) {
            this.scanCountryCode = scanCountryCode;
        }
    }
}
