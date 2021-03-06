package org.controlhaus.xfire.client;

import org.apache.xmlbeans.XmlObject;
import org.codehaus.xfire.fault.XFireFault;

import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.properties.PropertySet;
import org.apache.beehive.controls.api.events.EventSet;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

/**
 * An XFire client control.  Included are two annotations which
 * can be used to configure the control.  Encoding, which will specify
 * the encoding. It defaults to UTF-8.  Also the ServiceUrl annotation
 * will specify which url to invoke for the service.
 * <p>
 * A typical usage would look like so:
 * <pre>
 *  {@literal @}Encoding("UTF-8")
 *  {@literal @}ServiceUrl("http://some.service.com")
 *  {@literal @}Control XFireClientControl client;
 * </pre>
 * <p>
 * It is possible to set the service url on a per invocation basis
 * if you declare the control "Bean" instead of just XFireClientControl. For
 * example in your class you would declare,
 * <pre>
 * {@literal @}Control XFireClientControlBean xfire;
 * </pre>
 * <p>
 * You could then set the URL like so:
 * <pre>
 * xfire.setServiceUrl("http://your/url");
 * </pre>
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 5, 2004
 */
@ControlInterface
public interface XFireClientControl
{

    /**
     * Invoke a SOAP service.
     * 
     * @param request The request as XMLBeans.
     * @return The response as XMLBeans.
     * @throws XFireFault
     */    
    XmlObject[] invoke( XmlObject[] request ) 
		throws IOException, XFireFault;

    /**
     * Invoke a SOAP service.
     * 
     * @param request The request as XMLBeans.
     * @param requestHeaders The SOAP Headers, if there are any. Otherwise <code>null</code>.
     * @return The response as XMLBeans.
     * @throws XFireFault
     */
    XmlObject[] invoke( XmlObject[] request, XmlObject[] reqHeaders ) 
    	throws IOException, XFireFault;
    
    /**
     * Invoke a SOAP service. Listen for the response using the Control's
     * Event mechanism.
     * 
     * @param request The request as XMLBeans.
     * @param requestHeaders The SOAP Headers, if there are any. Otherwise <code>null</code>.
     * @return The response as XMLBeans.
     * @throws XFireFault
     */
    @Asynchronous
    void beginInvoke( XmlObject[] request, XmlObject[] reqHeaders );
    
    @EventSet
    public interface EndInvokeCallback 
    {
        /**
         * Called at the end of an asynchronous request which has completed successfully
         * (i.e. there was no fault).
         * 
         * @param response
         * @param responseHeaders
         */
        public void endInvoke( XmlObject[] response, XmlObject[] responseHeaders );
        
        /**
         * Called if a fault occurs during the service invocation.
         * 
         * @param fault The fault.
         */
        public void handleFault( XFireFault fault );
    }
    
    @PropertySet(prefix="Encoding")
    @Target( {ElementType.TYPE, ElementType.FIELD, ElementType.METHOD} )
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Encoding
    {
        String value() default "UTF-8";
    }
    
    @PropertySet(prefix="ServiceUrl")
    @Target( {ElementType.TYPE, ElementType.FIELD, ElementType.METHOD} )
    @Retention(RetentionPolicy.RUNTIME)
    public @interface ServiceUrl
    {
        String value();
    }
    
    /**
     * Declares a parameter in an control a Soap Header in a control
     * which extends the XFireClientControl.
     */
    @PropertySet(prefix="SoapHeader")
    @Target( {ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD} )
    @Retention(RetentionPolicy.RUNTIME)
    public @interface SoapHeader
    {
    }

    /**
     * Marks a method on a control which extends the XFireClientControl
     * as ansynchronous.  As a result, a new thread to invoke the service
     * will be launced and the method will return immediately.
     */
    @Target( {ElementType.TYPE, ElementType.FIELD, ElementType.METHOD} )
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Asynchronous
    {
    }
}
