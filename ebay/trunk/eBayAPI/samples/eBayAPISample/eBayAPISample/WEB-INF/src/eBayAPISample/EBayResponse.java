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


package eBayAPISample;

import org.controlhaus.ebay.EBayXmlResponse;
import org.controlhaus.ebay.CategoryDocument;
import org.controlhaus.ebay.SellerDocument;
import org.controlhaus.ebay.TimeLeftDocument;
import org.controlhaus.ebay.UserDocument;

public class EBayResponse 
{
    private org.controlhaus.ebay.EBayDocument.EBay eBay ;
        
    private org.controlhaus.ebay.ErrorsDocument.Errors errors;
    private org.controlhaus.ebay.ItemDocument.Item item ;
    private String eBayTime ;
    
    /* Error Details */
    private org.controlhaus.ebay.ErrorDocument.Error error ;
    private boolean hasErrors ;
    
    private String errorCode = "";
    private String errorSeverityCode = "";
    private String errorSeverity = "";
    private String errorShortMessage = "";
    private String errorLongMessage = "";
    
    // Item properties
    private String itemId = "";
    private String title = "";
    private String pictureURL = "";

    // category info
    private String categoryId = "";
    private String categoryFullName = "";

    /*Bid related information  */
    private String bidCount = "";
    private String bidIncrement = "";
    private String buyItNowPrice = "";

    // pricing info
    private String currenyId = "";
    private String currentPrice = "";
    private String endTime = "";


    // seller
    private String sellerLevel = "";
    private String userid = "";
    private String quantity;

    private String startPrice = "";
    private String startTime = "";

    private String daysLeft = "";
    private String hoursLeft = "";
    
    
        
    public EBayResponse(EBayXmlResponse xmlResponse){
        if(xmlResponse == null || xmlResponse.getEBay() == null) throw new RuntimeException("argument xmlResponse is invalid");
        this.eBay = xmlResponse.getEBay();
        populate();        
    }
    
    private void populate(){        
        errors = eBay.getErrors();
        item = eBay.getItem();
        eBayTime = eBay.getEBayTime();
        
        // Error Details
        if(errors != null){
            error = errors.getError();
            if(error != null){
                hasErrors = true;
                errorCode = extract(error.getCode());
                errorSeverityCode = extract(error.getSeverityCode());
                errorSeverity = extract(error.getSeverity());
                errorShortMessage = extract(error.getShortMessage());
                errorLongMessage = extract(error.getLongMessage());                
            }           
        }
        
        // Item Details
        
        if (item != null){
            itemId = extract(item.getId());
            title = extract (item.getTitle());
            pictureURL = extract (item.getPictureURL());
            
            CategoryDocument.Category category = item.getCategory();            
            if(category != null){
                categoryId = extract (category.getCategoryId());
                categoryFullName = extract(category.getCategoryFullName());                
            }
            
            bidCount = extract (item.getBidCount());
            bidIncrement = extract (item.getBidIncrement());
            buyItNowPrice = extract (item.getBuyItNowPrice());
            
            currenyId = extract(item.getCurrencyId());
            currentPrice = extract(item.getCurrentPrice());
            endTime = extract(item.getEndTime());
            
            SellerDocument.Seller seller = item.getSeller();
            if(seller != null && seller.getUser() != null){
                UserDocument.User user = seller.getUser();
                sellerLevel = extract (user.getSellerLevel());
                userid = extract(user.getUserId());
            }
            
            quantity = extract(item.getQuantity());
            startPrice = extract(item.getStartPrice());
            startTime = extract(item.getStartTime());
            
            TimeLeftDocument.TimeLeft timeLeft = item.getTimeLeft();
            if(timeLeft != null){
                daysLeft = timeLeft.getDays();
                hoursLeft = timeLeft.getHours();
            }      
            
        }        
        
    }
    
    private String extract(String value){
        if(value == null){
            return "";
        }else{
            return value;
        }        
    }
    
    public String getEBayTime(){
        return this.eBayTime;
    }
    
     public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorSeverityCode() {
        return errorSeverityCode;
    }

    public void setErrorSeverityCode(String errorSeverityCode) {
        this.errorSeverityCode = errorSeverityCode;
    }

    public String getErrorSeverity() {
        return errorSeverity;
    }

    public void setErrorSeverity(String errorSeverity) {
        this.errorSeverity = errorSeverity;
    }

    public String getErrorShortMessage() {
        return errorShortMessage;
    }

    public void setErrorShortMessage(String errorShortMessage) {
        this.errorShortMessage = errorShortMessage;
    }

    public String getErrorLongMessage() {
        return errorLongMessage;
    }

    public void setErrorLongMessage(String errorLongMessage) {
        this.errorLongMessage = errorLongMessage;
    }
    
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryFullName() {
        return categoryFullName;
    }

    public void setCategoryFullName(String categoryFullName) {
        this.categoryFullName = categoryFullName;
    }

    public String getBidCount() {
        return bidCount;
    }

    public void setBidCount(String bidCount) {
        this.bidCount = bidCount;
    }

    public String getBidIncrement() {
        return bidIncrement;
    }

    public void setBidIncrement(String bidIncrement) {
        this.bidIncrement = bidIncrement;
    }

    public String getBuyItNowPrice() {
        return buyItNowPrice;
    }

    public void setBuyItNowPrice(String buyItNowPrice) {
        this.buyItNowPrice = buyItNowPrice;
    }

    public String getCurrenyId() {
        return currenyId;
    }

    public void setCurrenyId(String currenyId) {
        this.currenyId = currenyId;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(String sellerLevel) {
        this.sellerLevel = sellerLevel;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(String daysLeft) {
        this.daysLeft = daysLeft;
    }

    public String getHoursLeft() {
        return hoursLeft;
    }

    public void setHoursLeft(String hoursLeft) {
        this.hoursLeft = hoursLeft;
    }
    
    public boolean responseHasErrors(){
        return this.hasErrors;
    }
    
} 
