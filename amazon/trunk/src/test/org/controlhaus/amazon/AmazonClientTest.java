package org.controlhaus.amazon;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;
import org.apache.beehive.controls.api.events.EventHandler;
import org.apache.xmlbeans.XmlObject;
import org.codehaus.xfire.fault.XFireFault;
import org.controlhaus.amazon.AmazonClientControl;
import org.controlhaus.xfire.client.XFireClientControl;

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

    private boolean receivedResponse;
    private ItemLookupResponseDocument response;
    
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
        
        response = amazon.ItemLookup(doc);
        
        Items[] itemsArr = response.getItemLookupResponse().getItemsArray();
        assertEquals(1, itemsArr.length);
        System.out.println(response.xmlText());
        assertEquals("0486411214", itemsArr[0].getItemArray()[0].getASIN());
    }
    
    /*
     * Won't work until BEEHIVE-97 is fixed.
    public void testAsyncControl() 
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
	    
	    amazon.beginItemLookup(doc);
	    
	    for ( int i = 0; i < 60; i++ )
	    {
	        if ( !receivedResponse )
	            wait(500);
	        else
	            break;
	    }
	    
	    if ( !receivedResponse )
	        fail("Didn't receive async response.");
	    
	    Items[] itemsArr = response.getItemLookupResponse().getItemsArray();
        assertEquals(1, itemsArr.length);
        System.out.println(response.xmlText());
        assertEquals("0486411214", itemsArr[0].getItemArray()[0].getASIN());
	}
    
    @EventHandler(field="client",eventSet=XFireClientControl.EndInvokeCallback.class,eventName="endInvoke")
    public void endInvoke( XmlObject[] response, XmlObject[] responseHeaders )
    {
        receivedResponse = true;
        this.response = (ItemLookupResponseDocument) response[0];
    }
    
    @EventHandler(field="client",eventSet=XFireClientControl.EndInvokeCallback.class,eventName="handleFault")
    public void handleFault( XFireFault fault )
    {
        receivedResponse = true;
    }*/
}
