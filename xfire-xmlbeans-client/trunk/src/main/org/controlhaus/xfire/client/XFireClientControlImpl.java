package org.controlhaus.xfire.client;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.beehive.controls.api.context.Context;
import org.apache.beehive.controls.api.context.ResourceContext;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.api.events.EventHandler;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.beehive.controls.api.bean.Extensible;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.codehaus.xfire.client.ClientHandler;
import org.codehaus.xfire.client.http.SoapHttpClient;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.xmlbeans.client.XMLBeansClientHandler;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 5, 2004
 */
@ControlImplementation
public class XFireClientControlImpl
    implements XFireClientControl, Extensible
{
    @Context ControlBeanContext context;
    @Context ResourceContext resourceContext;
    
    private String encoding;
    private String serviceUrl;
    
    private XmlOptions options;
    private SoapHttpClient client;

    @EventHandler (field="resourceContext", 
                   eventSet=ResourceContext.ResourceEvents.class, 
                   eventName="onAcquire")
    public void onAcquire()
    {
        options = new XmlOptions();
        options.setCharacterEncoding( getEncoding() );
        
        ServiceUrl destProp = 
            (ServiceUrl) context.getControlPropertySet(XFireClientControl.ServiceUrl.class);
        if ( destProp != null )
        {
            serviceUrl = destProp.value();
        }
        
        Encoding encProp = 
            (Encoding) context.getControlPropertySet(XFireClientControl.Encoding.class);
        if ( encProp != null )
        {
            encoding = encProp.value();
        }
    }

    public XmlObject[] invoke(XmlObject[] request) 
        throws IOException, XFireFault
    {
        XMLBeansClientHandler handler = new XMLBeansClientHandler(options);
        handler.setRequest(request);
        
        SoapHttpClient client = new SoapHttpClient(handler, getHeaderHandler(), getServiceUrl());
        client.invoke();
        
        return handler.getResponse();
    }
    
    public String getServiceUrl()
    {
        return serviceUrl;
    }
    
    public String getEncoding()
    {
        return encoding;
    }
    
    /**
     * Gets the HeaderHandler.  Override this or fill this in
     * to provide your own HeaderHandler.
     * @return null by default.
     */
    public ClientHandler getHeaderHandler()
    {
        return null;
    }
    
    public Object invoke(Method m, Object [] args) throws Throwable
    {
        System.out.println(m.getName() + " " + m.getDeclaringClass().getName());
        System.out.println(this.getClass());
        return m.invoke(this, args);
    }
}
