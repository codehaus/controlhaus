package org.controlhaus.jms;

import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.properties.PropertySet;
import org.apache.beehive.controls.api.packaging.*;
import org.apache.beehive.controls.api.events.EventSet;
import java.lang.annotation.*;
import java.util.Date;
import java.util.Map;

import javax.jms.Session;

/**
 * The control interface for the jms control.
 */
@ControlInterface (defaultBinding="org.controlhaus.jms.impl.JmsControlImpl")
public interface JMSControl
{
    /**
     * The destination type. If Auto then the type is set
     * from the send-jndi-name.
     */
    enum DestinationType { Auto, Queue, Topic };
    /**
     * The header type. Corresponds to the JMS* bean properties on a JMS message.
     */
    enum HeaderType { JMSCorrelationID, JMSDeliveryMode, JMSPriority, JMSExpiration, JMSMessageID, JMSType, JMSRedelivered, JMSTimestamp };
    
    /**
     * The message type. If Auto then the type is set
     * from the body.
     */
    enum MessageType { Auto, Text, Bytes, Object, Map, JMSMessage };

    /**
     * The delivery mode.
     */
    enum DeliveryMode { NonPersistent, Persistent };
    
    /**
     * The acknowledge mode.
     */   
    enum AcknowledgeMode { Auto, Client, DupsOk };

    /**
     * Indicates the JMSCorrelationID message header. 
     *
     * @deprecated
     * @see HeaderType
     */
    public static final String HEADER_CORRELATIONID = HeaderType.JMSCorrelationID.toString();
    /**
     * Indicates the JMSDeliveryMode message header. 
     * 
     * @deprecated
     * @see HeaderType
     */
    public static final String HEADER_DELIVERYMODE = HeaderType.JMSDeliveryMode.toString();
    /**
     * Indicates the JMSExpiration message header. 
     * Use with the getHeaders and setHeaders methods.
     * 
     * @deprecated
     * @see HeaderType
     */
    public static final String HEADER_EXPIRATION = HeaderType.JMSExpiration.toString();
    /**
     * Indicates the JMSMessageID message header. 
     * 
     * @deprecated
     * @see HeaderType
     */
    public static final String HEADER_MESSAGEID = HeaderType.JMSMessageID.toString();
    /**
     * Indicates the JMSPriority message header. 
     * 
     * @deprecated
     * @see HeaderType
     */
    public static final String HEADER_PRIORITY = HeaderType.JMSPriority.toString();
    /**
     * Indicates the JMSRedelivered message header. 
     * 
     * @deprecated
     * @see HeaderType
     */
    public static final String HEADER_REDELIVERED = HeaderType.JMSRedelivered.toString();
    /**
     * Indicates the JMSTimestamp message header. 
     * 
     * @deprecated
     * @see HeaderType
     */
    public static final String HEADER_TIMESTAMP = HeaderType.JMSTimestamp.toString();
    /**
     * Indicates the JMSType message header. 
     * 
     * @deprecated
     * @see HeaderType
     */
    public static final String HEADER_TYPE = HeaderType.JMSType.toString();
    
    /**
     * Resets the timer. Any pending events are canceled. 
     * The timer will subsequently expire after the repeats-every 
     * period has elapsed after this call.
     */
    public Session getSession() throws ControlException;

    /**
     * Sets the JMS headers to be assigned to the next JMS message
     * sent. Note that these headers are set only on the next message,
     * subsequent messages will not get these headers. Also note that
     * if the next message is sent through a publish method,
     * then any header set through this map will override headers set
     * in the message itself.
     * 
     * @param headers A map of header names (Strings or HeaderType) to header values.
     */
    public void setHeaders(Map headers);
    
    /**
     * Sets the JMS properties to be assigned to the next JMS message
     * sent. Note that these properties are set only on the next
     * message, subsequent messages will not get these
     * properties. Also note that if the next message is sent through
     * a publish method, then any property set through this
     * map will override properties set in the message itself.
     * 
     * @param properties A map of property names (Strings) to property
     * values.
     */
    public void setProperties(Map properties); 

    /**
     * The message type used by the method. The default is
     * to use the type of the body parameter.
     */
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Message
    {             
        @FeatureInfo(shortDescription="The message type")
    	public JMSControl.MessageType value() default JMSControl.MessageType.Auto;
    }
    /**
     * The method parameter representing the message body.
     */
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Body
    {
    }
    /**
     * The method parameter representing a message property with the given name.
     */    
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Property
    {
        public String name();
    }
    
    /**
     * The method parameter representing a message priority. If not given
     * then the default for the JMS provider is used.
     */ 
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Priority
    {
        public int value();
    }
    
    /**
     * The method parameter representing a message expiration in milliseconds. 
     * If not given then the default for the JMS provider is used.
     */ 
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Expiration
    {
        public long value();
    }
    /**
     * The method parameter representing a message delivery mode. 
     * If not given then the default for the JMS provider is used.
     */ 
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Delivery
    {
        public JMSControl.DeliveryMode value();
    }
    /**
     * The JMS destination annotation for a extended class method.
     */     
    @PropertySet
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface Destination
    {
        @FeatureInfo(shortDescription="JNDI name of the queue or topic")
    	public String sendJndiName();
        
        @FeatureInfo(shortDescription="Correlation-Id for messages")
    	public String sendCorrelationProperty();
        
        @FeatureInfo(shortDescription="JNDI name of queue connection factory")
    	public String jndiConnectionFactory();
        
        @FeatureInfo(shortDescription="The destination type (DestinationType). The default is to use the type of the destination object named by the JNDI name")
        public JMSControl.DestinationType sendType() default JMSControl.DestinationType.Auto; 
        
        @FeatureInfo(shortDescription="True if send is transacted. The default is transacted")
        public boolean transacted() default true;
        
        @FeatureInfo(shortDescription="The acknowledge mode. The default is to use auto-acknowledge")
        public JMSControl.AcknowledgeMode acknowledgeMode() default JMSControl.AcknowledgeMode.Auto;
    }


}
