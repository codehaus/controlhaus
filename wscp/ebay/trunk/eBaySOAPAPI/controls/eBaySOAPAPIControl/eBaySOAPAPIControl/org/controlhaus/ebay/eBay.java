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
