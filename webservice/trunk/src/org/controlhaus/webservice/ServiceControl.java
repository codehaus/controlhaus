package org.controlhaus.webservice;

import org.apache.beehive.controls.api.bean.AnnotationConstraints.AllowExternalOverride;
import org.apache.beehive.controls.api.bean.AnnotationMemberTypes;
import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.properties.PropertySet;

import org.apache.xmlbeans.XmlObject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * Provides simplified access to web services. A Service control
 * provides an interface between your application and
 * a web service, which allows your application to invoke the methods and
 * handle the callbacks of that web service. Using a Web Service control, you
 * can connect to any web service for which a WSDL file is available, whether
 * or not it was built using WebLogic Workshop.
 * <br/><br/>
 * You typically use a Service control by creating the control from a
 * WSDL file, or from a web service you created with WebLogic Workshop, then add the
 * control to a design in your application. The target web service's operations
 * are exposed as methods of the control.
 * <br/><br/>
 */
@ControlInterface (defaultBinding = "org.controlhaus.webservice.ServiceControlImpl")
public interface ServiceControl
{

    // ----------------------------------------------
    // Class level annotations.
    // ----------------------------------------------

    /**
     * Location URL for target service.
     * Multiple URLs may be specified and first URL of the appropriate scheme
     * will be used.
     */
    @PropertySet(prefix="Location")
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})  // allowed on declaration
    @AllowExternalOverride
    public @interface Location
    {
        String[] urls();
    }

    /**
     * OperationName is only used when the target WSDL defines operations 
     * with names that are invalid as Java Method names.
     */
    @PropertySet(prefix="OperationName")
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface OperationName {

        String value();
    }

    /**
     * Path to WSDL.
     */
    @PropertySet(prefix="WSDL")
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})  // JCX only
    public @interface WSDL
    {
        @AnnotationMemberTypes.FilePath
        String path();
        String service() default "";
    }

    // Manual control over URL of the target service and
    // the ID to be used to identify the conversation instance.
    /**
     * Sets the callback URL that the Service control instance
     * will use as the base URL for callback invocations. While this is set
     * automatically by WebLogic Workshop, you can use this method
     * to override the callback URL if you wish callbacks to be sent
     * to a different destination.
     *
     * @param url The new destination for callbacks.
     */
    public void setEndPoint( URL url );

    /**
     * Gets the callback URL that the Service control instance
     * will use as the base URL for callback invocations.
     *
     * @return The callback URL that will be used.
     */
    public URL getEndPoint();



    public void setWsdlPort( QName wsdlPortName );

    public QName getWsdlPort();


    // Gets/sets credential information used for
    // calling over secure protocols (e.g. https)
    /**
     * Sets the username that will be sent with the next outgoing
     * Service control method invocation. Used if the Service control uses
     * HTTP basic authentication.
     *
     * @param username The username to send for authentication.
     */
    public void setUsername( String username );

    /**
     * Sets the password that will be sent with the next outgoing
     * Service control method invocation. Used if the Service control uses
     * HTTP basic authentication.
     *
     * @param password The password to send for authentication.
     */
    public void setPassword( String password );

    /**
     * Retrieves the username string that was set by the most recent
     * call to setUsername.
     *
     * @return The username set by the setUsername method.
     */
    public String getUsername();

    /**
     * Retrieves the password string that was set by the most recent
     * call to the setPassword method.
     *
     * @return The password set by the setPassword method.
     */
    public String getPassword();

    /**
     * Retrieves the SOAP headers that were included in the most recent
     * arriving callback from this Service control.
     *
     * @return An array of the SOAP input header elements for this
     * control's most recently receive callback.
     */
    public Element[] getInputHeaders();

    /**
     * Sets the SOAP headers that will be included in the next outgoing
     * method invocation message to the Service control.
     *
     * @param headers An array of the new SOAP output header
     * elements.
     */
    public void setOutputHeaders( Element[] headers );

    /**
     * Retrieves the SOAP headers that were included in the most recent
     * arriving callback from this Service control.
     *
     * @return An array of the SOAP input header elements for this
     * control's most recently receive callback.
     */
    public XmlObject getInputHeadersAsXmlBean();

    /**
     * Sets the SOAP headers that will be included in the next outgoing
     * method invocation message to the Service control.
     *
     * @param headers An array of the new SOAP output header
     * elements.
     */
    public void setOutputHeadersAsXmlBean( XmlObject headers );

    /**
     * Sets the timeout for the underlying HttpURLConnection (in millisecs,
     * default is 0 which means no timeout, negative values are converted
     * to 0).
     */
    public void setTimeout(int timeout);

    /**
     * Gets the timeout that has been set by setTimeout() for the
     * underlying HttpURLConnection (returned in millisecs, a value of
     * 0 means no timeout).
     */
    public int getTimeout();

    // Resets the conversational state of the proxy; this
    // could result in dropping an existing conversation.
    /**
     * Clears all parameters that were set by previous calls to the
     * setConversationID, setOutputHeaders, setPassword, or setUsername
     * methods.
     */
    public void reset();

}
