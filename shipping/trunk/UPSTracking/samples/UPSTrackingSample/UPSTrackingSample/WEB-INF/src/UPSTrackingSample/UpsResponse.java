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


package UPSTrackingSample; 

import org.controlhaus.ups.ActivityType;
import org.controlhaus.ups.AddressType;
import org.controlhaus.ups.PackageType;
import org.controlhaus.ups.ResponseType;
import org.controlhaus.ups.ShipmentType;
import org.controlhaus.ups.TrackResponseDocument.TrackResponse;
import java.util.ArrayList;
import java.util.Collection;



public class UpsResponse 
{
    private TrackResponse trackResponse;
    private ResponseType responseType;    
    private ShipmentType[] shipmentTypeArray;
    private ShipmentType shipmentType;
    private PackageType[] packageTypeArray;
    private PackageType packageType;
    private ActivityType[] activityTypeArray;    
    private Collection activities = new ArrayList();
    
    private String customerContext = "";
    private String responseStatusCode = "";
    private String responseStatusDescription = "";
    private String shipperNumber = "";
    private String shipmentIdentificationNumber = "";
    private String packageTrackingNumber = "";
    
    public UpsResponse(TrackResponse trackResponse){
        this.trackResponse = trackResponse;
        populate();        
    }
    
    private void populate(){
        if(trackResponse != null){
            responseType = trackResponse.getResponse();
            shipmentTypeArray = trackResponse.getShipmentArray();
            
            if (shipmentTypeArray.length > 0){
                shipmentType = shipmentTypeArray[0];
                packageTypeArray = shipmentType.getPackageArray();
                if(packageTypeArray.length > 0){
                    packageType = packageTypeArray[0];
                    activityTypeArray = packageType.getActivityArray();
                }
            }
            
            if (responseType != null){
                if(responseType.getTransactionReference() != null && responseType.getTransactionReference().getCustomerContext() != null){
                    customerContext = responseType.getTransactionReference().getCustomerContext().toString();
                }
                
                if(responseType.getResponseStatusCode() != null){
                    responseStatusCode = responseType.getResponseStatusCode();
                }
                
                if(responseType.getResponseStatusDescription() != null){
                    responseStatusDescription = responseType.getResponseStatusDescription();
                }            
            }
            
            if(shipmentType != null){
                if(shipmentType.getShipper() != null && shipmentType.getShipper().getShipperNumber() != null){
                    shipperNumber = shipmentType.getShipper().getShipperNumber();
                }
                
                if(shipmentType.getShipmentIdentificationNumber() != null){
                    shipmentIdentificationNumber = shipmentType.getShipmentIdentificationNumber();
                }
            }
            
            if(packageType != null){
                if(packageType.getTrackingNumber() != null){
                    packageTrackingNumber = packageType.getTrackingNumber();
                }                
                
            }
            
            if(activityTypeArray != null){
                ActivityType activityType ;
                for (int x=0 ; x<activityTypeArray.length ; x++){
                    activityType = activityTypeArray[x];
                    activities.add(new Activity(activityType));                    
                }                
            }                   
        }
    }
    
    public String getCustomerContext(){        
        return customerContext;
    }
    
    public String getResponseStatusCode(){        
        return responseStatusCode;
    }
    
    public String getResponseStatusDescription(){        
        return responseStatusDescription;
    }
    
    public String getShipperNumber(){
        return shipperNumber;        
    }
    
    public String getShipmentIdentificationNumber(){
        return shipmentIdentificationNumber;        
    }
    
    public String getPackageTrackingNumber(){
        return packageTrackingNumber;        
    }
    
    public Collection getActivities(){
        return activities;
    }
    
    public static final class Activity{
        private ActivityType activityType;                
        private AddressType addressType;
        
        private String strCity = "";
        private String strPostalCode = "";
        private String strCountryCode = "";
        private String strAddress = "";
        private String strStatusDescription = "";
        private String strDate = "";
        private String strTime = "";
        
        public Activity(ActivityType activityType){
            this.activityType = activityType;
            
            if(this.activityType != null){
                if(activityType.getActivityLocation() != null && activityType.getActivityLocation().getAddress() != null){
                    addressType = activityType.getActivityLocation().getAddress();
                    
                    if(addressType.getCity() != null){
                        strCity = addressType.getCity();                         
                    }
                    
                    if(addressType.getPostalCode() != null){
                        strPostalCode = addressType.getPostalCode();
                    }
                    
                    if(addressType.getCountryCode() != null){
                        strCountryCode = addressType.getCountryCode();
                    }
                }
                
                strAddress = (strCity.equals("") ? "" : strCity + ", ") + (strPostalCode.equals("") ? "" : strPostalCode + ", ") + (strCountryCode.equals("") ? "" : strCountryCode);
                
                if(activityType.getStatus() != null && activityType.getStatus().getStatusType() != null 
                    && activityType.getStatus().getStatusType().getDescription() != null){
                        strStatusDescription = activityType.getStatus().getStatusType().getDescription();
                    }
                
                if(activityType.getDate() != null){
                    strDate = activityType.getDate();
                }
                
                if(activityType.getTime() != null){
                    strTime = activityType.getTime();
                }                
            }            
        }
        
        public String getAddress(){
            return strAddress;
        }
        
        public String getStatusDescription(){
            return strStatusDescription;
        }
        
        public String getDate(){
            return strDate;
        }
        
        public String getTime(){
            return strTime;
        }
    }
} 
