/*
 * AmazonTestNet.java
 * 
 * Copyright 2004 BEA Systems, Inc.
 * 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 * Original author: Jonathan Colwell
 */


import java.util.ArrayList;
import java.util.List;

import org.apache.beehive.controls.api.bean.Control;
import org.controlhaus.controlunit.ControlTestCase;
import amazonWS.AWSECommerceService;
import com.amazon.webservices.awseCommerceService.x20041110.ErrorsDocument.Errors;
import com.amazon.webservices.awseCommerceService.x20041110.ItemsDocument.Items;
import com.amazon.webservices.awseCommerceService.x20041110.OperationRequestDocument.OperationRequest;
import com.amazon.webservices.awseCommerceService.x20041110.ItemSearchDocument;
import com.amazon.webservices.awseCommerceService.x20041110.ItemSearchDocument.ItemSearch;
import com.amazon.webservices.awseCommerceService.x20041110.ItemSearchResponseDocument.ItemSearchResponse;
import com.amazon.webservices.awseCommerceService.x20041110.ItemSearchRequest;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class AmazonCommerceTest extends ControlTestCase {

    @Control public AWSECommerceService mAmazonECS;

    public void testItemSearch() throws Exception {

        String subId = "1QZP1NHPQW2XT7SJ9AG2";
        String validate = "False";

        ItemSearchDocument isd = ItemSearchDocument.Factory.newInstance();
        ItemSearch is = isd.addNewItemSearch();
        is.setSubscriptionId(subId);
        is.setValidate(validate);
        ItemSearchRequest isr = is.addNewRequest();
        isr.setArtist("Sting");
        isr.setSearchIndex("Music");
        //  isr.setResponseGroupArray(new String[]{"Request"});
        
        ItemSearchResponse searchResults = mAmazonECS
            .ItemSearch(is);


        System.out.println(searchResults + " serachresult Type: " + searchResults.getClass().getCanonicalName());

        Items[] items = searchResults.getItemsArray();
        System.out.println("Size of the array....:  " + items.length);
        //OperationRequest opReq = searchResults.getOperationRequest();
        assertNotNull(items);
        //assertNotNull(opReq);
        assertTrue(items.length > 0);
        if (items[0].getRequest().isSetErrors()) {
            Errors errs = items[0].getRequest().getErrors();
            Errors.Error[] errArray = errs.getErrorArray();
            for (Errors.Error err : errArray) {
                System.out.println(err.getMessage());
            }
        }
        assertEquals("True", items[0].getRequest().getIsValid());

    }

}
