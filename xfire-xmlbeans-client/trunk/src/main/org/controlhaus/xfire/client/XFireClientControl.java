package org.controlhaus.xfire.client;

import org.apache.xmlbeans.XmlObject;
import org.codehaus.xfire.fault.XFireFault;

import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.properties.PropertySet;

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
 * @Encoding("UTF-8")
 * @ServiceUrl("http://some.service.com")
 * @Control XFireClientControl client;
 * </pre>
 * 
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
    XmlObject[] invoke( XmlObject[] request ) throws IOException, XFireFault;

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
}
