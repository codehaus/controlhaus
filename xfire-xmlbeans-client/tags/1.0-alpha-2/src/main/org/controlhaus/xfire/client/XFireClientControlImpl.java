package org.controlhaus.xfire.client;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.beehive.controls.api.context.Context;
import org.apache.beehive.controls.api.context.ResourceContext;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.api.events.Client;
import org.apache.beehive.controls.api.events.EventHandler;
import org.apache.beehive.controls.api.bean.ControlImplementation;
import org.apache.beehive.controls.api.bean.Extensible;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.codehaus.xfire.client.ClientHandler;
import org.codehaus.xfire.client.http.SoapHttpClient;
import org.codehaus.xfire.fault.XFireFault;
import org.codehaus.xfire.xmlbeans.client.XMLBeansClientHandler;
import org.controlhaus.xfire.client.XFireClientControl.ServiceUrl;

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
    @Client EndInvokeCallback callback;
    
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
        return invoke(request, null);
    }
    
    public XmlObject[] invoke(XmlObject[] request, XmlObject[] reqHeaders) 
        throws IOException, XFireFault
    {
        XMLBeansClientHandler handler = new XMLBeansClientHandler(options);
        handler.setRequest(request);
        
        XMLBeansClientHandler headHandler = new XMLBeansClientHandler(options);
        headHandler.setRequest(reqHeaders);
        
        SoapHttpClient client = new SoapHttpClient(handler, headHandler, getServiceUrl());
        client.invoke();
        
        return handler.getResponse();
    }
    
    public void beginInvoke(XmlObject[] request, XmlObject[] reqHeaders) 
	{
        ServiceInvokerThread thread = 
            new ServiceInvokerThread(request, reqHeaders, options, getServiceUrl(), callback );
        
        thread.run();
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
        if ( getServiceUrl() == null )
        {
            ServiceUrl a = m.getDeclaringClass().getAnnotation(ServiceUrl.class);
            serviceUrl = a.value();
        }

        List<XmlObject> reqArgs = new ArrayList<XmlObject>(args.length);
        List<XmlObject> headerArgs = new ArrayList<XmlObject>();
        
        // Find the SoapHeaders
        for ( int i = 0; i < args.length; i++ )
        {
            if ( isSoapHeader(m, i) )
                headerArgs.add((XmlObject)args[i]);
            else
                reqArgs.add((XmlObject)args[i]);
        }

        XmlObject[] arr = (XmlObject[]) reqArgs.toArray(new XmlObject[reqArgs.size()]);
        XmlObject[] headers = (XmlObject[]) headerArgs.toArray(new XmlObject[headerArgs.size()]);
        
        if ( !m.isAnnotationPresent(Asynchronous.class) )
        {
            // Invoke synchronously
	        if ( m.getReturnType().isArray() )
	            return invoke( (XmlObject[]) arr, headers );
	        else
	            return invoke( (XmlObject[]) arr, headers )[0];
        }
        else
        {
            // Invoke asynchronously
            beginInvoke( (XmlObject[]) arr, headers );
            return null;
        }
    }
    
    private boolean isSoapHeader(Method m, int i)
    {
        Annotation[] annots = m.getParameterAnnotations()[i];
        
        for ( int j = 0; j < annots.length; j++ )
        {
            if ( annots[j].annotationType().equals(SoapHeader.class) )
                return true;
        }
        
        return false;
    }

    public class ServiceInvokerThread
    	extends Thread
	{
        private EndInvokeCallback callback;
        private SoapHttpClient client;
        private XMLBeansClientHandler handler;
        private XMLBeansClientHandler headHandler;
        
        public ServiceInvokerThread( XmlObject[] request, 
                                     XmlObject[] reqHeaders, 
                                     XmlOptions options,
                                     String serviceUrl,
                                     EndInvokeCallback callback )
        {
            if (callback == null)
                throw new IllegalArgumentException("Callback cannot be null.");

            this.callback = callback;
            
            handler = new XMLBeansClientHandler(options);
            handler.setRequest(request);
            
            headHandler = new XMLBeansClientHandler(options);
            headHandler.setRequest(reqHeaders);
            
            client = new SoapHttpClient(handler, headHandler, getServiceUrl());
        }
        
        public void run()
        {
            try
            {
	            client.invoke();
	            
	            callback.endInvoke(handler.getResponse(), headHandler.getResponse());
            }
            catch ( IOException e )
            {
                callback.handleFault( new XFireFault("Couldn't connect to the service.", e, XFireFault.SENDER) );
            }
            catch ( XFireFault fault )
            {
                callback.handleFault(fault);
            }
        }
	}
}
