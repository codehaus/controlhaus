package org.controlhaus.ebay; 

import com.bea.control.Control;

public interface eBay extends Control
{ 


    ebayApiEBayAPI.GetUserResponseDocument getUser(java.lang.String userID, java.lang.String itemID);

    ebayApiEBayAPI.GetCategoriesResponseDocument getCategories(java.lang.String categorySiteID);

    ebayApiEBayAPI.GetItemResponseDocument getItem(java.lang.String itemID);

    ebayApiEBayAPI.GetItemTransactionsResponseDocument getItemTransactions(java.lang.String itemID, java.util.Calendar modFrom, java.util.Calendar modTo);

    ebayApiEBayAPI.GetSellerEventsResponseDocument getSellerEvents(java.lang.String userID);

    ebayApiEBayAPI.GetSellerListResponseDocument getSellerList(java.lang.String userID);

    ebayApiEBayAPI.GetSellerTransactionsResponseDocument getSellerTransactions(java.util.Calendar modFrom, java.util.Calendar modTo);

    ebayApiEBayAPI.RelistItemResponseDocument relistItem(ebayApisEBLBaseComponents.ItemType item, ebayApisEBLBaseComponents.ModifiedFieldType[] modifiedFields);

    ebayApiEBayAPI.VerifyAddItemResponseDocument verifyAddItem(ebayApisEBLBaseComponents.ItemType item);

    ebayApiEBayAPI.AddItemResponseDocument addItem(ebayApisEBLBaseComponents.ItemDocument itemDoc);
} 
