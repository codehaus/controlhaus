package org.controlhaus.xfire.client;

import org.apache.beehive.controls.api.bean.Control;
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
    @ServiceUrl("http://some.service")
    @Control XFireClientControl client;

   @ServiceUrl("http://some.service")
    @Control XFireClientControl noEncClient;

    public void testControl() 
        throws Exception
    {
        assertNotNull(client);
        
        XFireClientControlBean bean = (XFireClientControlBean) client;
        assertEquals("ENC", bean.getEncodingValue());
        assertEquals("http://some.service", bean.getServiceUrlValue());
        
        assertNotNull(noEncClient);
        
        bean = (XFireClientControlBean) noEncClient;
        assertEquals("UTF-8", bean.getEncodingValue());
        assertEquals("http://some.service", bean.getServiceUrlValue());
    }
}
