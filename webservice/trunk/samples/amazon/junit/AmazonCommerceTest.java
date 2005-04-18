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
import org.apache.beehive.wsm.databinding.GenericHolder;
import org.apache.xmlbeans.XmlCursor;
import org.controlhaus.controlunit.ControlTestCase;
import amazonWS.AWSECommerceService;

import com.amazon.webservices.awsAlexa.x20040915.OperationRequestDocument;
import com.amazon.webservices.awseCommerceService.x20050323.ErrorsDocument.Errors;
import com.amazon.webservices.awseCommerceService.x20050323.ItemsDocument.Items;
import com.amazon.webservices.awseCommerceService.x20050323.OperationRequestDocument.OperationRequest;
import com.amazon.webservices.awseCommerceService.x20050323.ItemSearchDocument;
import com.amazon.webservices.awseCommerceService.x20050323.ItemSearchResponseDocument;
import com.amazon.webservices.awseCommerceService.x20050323.ItemSearchDocument.ItemSearch;
import com.amazon.webservices.awseCommerceService.x20050323.ItemSearchResponseDocument.ItemSearchResponse;
import com.amazon.webservices.awseCommerceService.x20050323.ItemSearchRequest;

/*******************************************************************************
 * 
 * 
 * @author Jonathan Colwell
 */
public class AmazonCommerceTest extends ControlTestCase {
	String subId = "1QZP1NHPQW2XT7SJ9AG2";

	String validate = "False";

	@Control
	public AWSECommerceService mAmazonECS;

	public void testItemSearch() throws Exception {

		String subId = "1QZP1NHPQW2XT7SJ9AG2";
		String validate = "False";

		ItemSearchRequest isr = ItemSearchRequest.Factory.newInstance();
		isr.setSearchIndex("SportingGoods");
		isr.setManufacturer("Callaway");

		GenericHolder<Items[]> itemsHolder = new GenericHolder<Items[]>(
				new Items[0]);
		GenericHolder<OperationRequest> operationRequestHolder = new GenericHolder<OperationRequest>(
				OperationRequest.Factory.newInstance());

		mAmazonECS.ItemSearch(subId, "", "", validate, isr, null, itemsHolder,
				operationRequestHolder);

		Items[] items = itemsHolder.value;
		// OperationRequest opReq = searchResults.getOperationRequest();
		assertNotNull(items);
		// assertNotNull(opReq);
		assertTrue("Invalid Item length... Make sure your WSDL is up to date!",
				items.length > 0);
		if (items[0].getRequest().isSetErrors()) {
			Errors errs = items[0].getRequest().getErrors();
			Errors.Error[] errArray = errs.getErrorArray();
			for (Errors.Error err : errArray) {
				System.out.println(err.getMessage());
			}
		}
		assertEquals("True", items[0].getRequest().getIsValid());

	}

	public void testBatchedItemSearch() throws Exception {

		ItemSearchRequest shared = ItemSearchRequest.Factory.newInstance();
		ItemSearchRequest item1 = ItemSearchRequest.Factory.newInstance();
		ItemSearchRequest item2 = ItemSearchRequest.Factory.newInstance();

		shared.setSearchIndex("Books");
		item1.setAuthor("Larson");
		item2.setTitle("Horton Hearts");

		ItemSearchRequest[] requests = new ItemSearchRequest[2];
		requests[0] = item1;
		requests[1] = item2;

		GenericHolder<Items[]> itemsHolder = new GenericHolder<Items[]>(
				new Items[0]);
		GenericHolder<OperationRequest> operationRequestHolder = new GenericHolder<OperationRequest>(
				OperationRequest.Factory.newInstance());

		mAmazonECS.ItemSearch(subId, "", "", validate, shared, requests,
				itemsHolder, operationRequestHolder);

		Items[] items = itemsHolder.value;
		// OperationRequest opReq = searchResults.getOperationRequest();
		assertNotNull(items);
		// assertNotNull(opReq);
		assertTrue("Invalid Item length... Make sure your WSDL is up to date!",
				items.length > 0);
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
