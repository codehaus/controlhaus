package org.controlhaus.amazon;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;
import org.controlhaus.amazon.AmazonClientControl;

import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupRequest;
import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupResponseDocument;
import com.amazon.webservices.awseCommerceService.x20041019.ItemLookupDocument.ItemLookup;
import com.amazon.webservices.awseCommerceService.x20041019.ItemsDocument.Items;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 2, 2004
 */
public class AmazonClientTest 
    extends AbstractControlTest
{
    @Control AmazonClientControl amazon;

    public void testControl() 
        throws Exception
    {
        assertNotNull(amazon);
        
        ItemLookupDocument doc = ItemLookupDocument.Factory.newInstance();
        ItemLookup lookup = doc.addNewItemLookup();
        lookup.setAssociateTag("dandiephosblo-20");
        lookup.setSubscriptionId("1E5AY4ZG53H4AMC8QH82");
        
        ItemLookupRequest request = lookup.addNewRequest();
        request.addItemId("0486411214");
        request.setIdType(ItemLookupRequest.IdType.ASIN);
        
        ItemLookupResponseDocument response = amazon.ItemLookup(doc);
        
        Items[] itemsArr = response.getItemLookupResponse().getItemsArray();
        assertEquals(1, itemsArr.length);
        System.out.println(response.xmlText());
        assertEquals("0486411214", itemsArr[0].getItemArray()[0].getASIN());
    }
}
