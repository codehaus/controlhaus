package org.controlhaus.xfire.client;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.xmlbeans.XmlObject;
import org.controlhaus.xfire.client.XFireClientControl.Encoding;
import org.controlhaus.xfire.client.XFireClientControl.ServiceUrl;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 5, 2004
 */
public class XFireClientControlTest
    extends AbstractControlTest
{
    @Encoding("ENC")
    @ServiceUrl("http://soap.amazon.com/onca/soap?Service=AWSECommerceService")
    @Control XFireClientControl client;

    @ServiceUrl("http://soap.amazon.com/onca/soap?Service=AWSECommerceService")
    @Control XFireClientControl noEncClient;

    public void testProperties() 
        throws Exception
    {
        assertNotNull(client);
        
        XFireClientControlBean bean = (XFireClientControlBean) client;
        assertEquals("ENC", bean.getEncodingValue());
        assertEquals("http://soap.amazon.com/onca/soap?Service=AWSECommerceService", bean.getServiceUrlValue());
        
        assertNotNull(noEncClient);
        
        bean = (XFireClientControlBean) noEncClient;
        assertEquals("UTF-8", bean.getEncodingValue());
        assertEquals("http://soap.amazon.com/onca/soap?Service=AWSECommerceService", 
                     bean.getServiceUrlValue());
        
    }
    
    public void testInvoke() 
        throws Exception
    {
        assertNotNull(client);

        XmlObject request = XmlObject.Factory.parse( getClass().getResourceAsStream("amazon.xml") );
        
        XmlObject[] response = client.invoke( new XmlObject[] { request } );
        assertNotNull(response);
        assertEquals(1, response.length);
        assertEquals("ItemLookupResponse", response[0].getDomNode().getFirstChild().getLocalName());
    }
}
