package org.controlhaus.jms;

import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.AnnotationMemberTypes;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.bean.AnnotationConstraints;
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
@ControlInterface (defaultBinding="org.controlhaus.jms.impl.JMSControlImpl")
public interface JMSControl
{
    /**
     * The destination type. 
     */
    enum DestinationType 
    { 
        /** The destination is set from the object obtained from JNDI. */
        Auto, 
        /** The destination must be a javax.jms.QueueSender. */
        Queue, 
        /** The destination must be a javax.jms.TopicPublisher. */
        Topic 
    };
    
    /**
     * The header type. Corresponds to the JMS* bean properties on a JMS message.
     */
    enum HeaderType 
    { 
        /** see javax.jms.Message.getJMSCorrelationID() */
        JMSCorrelationID, 
        /** see javax.jms.Message.getJMSDeliveryMode */
        JMSDeliveryMode, 
        /** see javax.jms.Message.getJMSPriority */
        JMSPriority, 
        /** see javax.jms.Message.getJMSExpiration */
        JMSExpiration, 
        /** see javax.jms.Message.getJMSMessageID */
        JMSMessageID, 
        /** see javax.jms.Message.getJMSType */
        JMSType, 
        /** see javax.jms.Message.getJMSRedelivered */
        JMSRedelivered, 
        /** see javax.jms.Message.getJMSTimestamp */
        JMSTimestamp 
    };
    
    /**
     * The message type. 
     */
    enum MessageType 
    { 
        /** Message is determined from the body instance class. If the method is not annotated with Body, then the message type is Map. */
        Auto, 
        /** Message is a javax.jms.TextMessage */
        Text, 
        /** Message is a javax.jms.BytesMessage */
        Bytes, 
        /** Message is a javax.jms.ObjectMessage */
        Object, 
        /** Message is a javax.jms.MapMessage */
        Map, 
        /** Message is a javax.jms.Message as given by the Body parameter */
        JMSMessage 
    };

    /**
     * The delivery mode.
     */
    enum DeliveryMode 
    { 
        /** see javax.jms.DeliveryMode.NON_PERSISTENT */
        NonPersistent, 
        /** see javax.jms.DeliveryMode.PERSISTENT */
        Persistent,
        /** The default for the provider */
        Auto
    };
    
    /**
     * The acknowledge mode.
     */   
    enum AcknowledgeMode 
    { 
        /** see javax.jms.Session.AUTO_ACKNOWLEDGE */
        Auto, 
        /** see javax.jms.Session.CLIENT_ACKNOWLEDGE */
        Client, 
        /** see javax.jms.Session.DUPS_OK_ACKNOWLEDGE */
        DupsOk 
    };

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
     * Get the JMS Session object.
     * @return the session.
     */
    public Session getSession() throws ControlException;
    
    /**
     * Get the JMS Connection.
     * @return the connection.
     */
    public javax.jms.Connection getConnection() throws ControlException;
    
    /**
     * Get the jms-destination.
     * 
     * @return an instance destination object.
     */
    public javax.jms.Destination getDestination() throws ControlException;


    /**
     * Sets the JMS headers to be assigned to the next JMS message
     * sent. Note that these headers are set only on the next message,
     * subsequent messages will not get these headers. Also note that
     * if the body is a message itself,
     * then any header set through this map will override headers set
     * in the message.
     * 
     * @param headers A map of header names (Strings or HeaderType) to header values.
     */
    public void setHeaders(Map headers);
    
    /**
     * Sets a JMS header to be assigned to the next JMS message
     * sent. Note that this headers is set only on the next message,
     * subsequent messages will not get this header. Also note that
     * if the body is a message itself,
     * then the header set here will override the header set
     * in the message.
     * 
     * @param type the header type.
     * @param value the value for the header.
     */
    public void setHeader(JMSControl.HeaderType type,Object value);
    
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
     * Set the given JMS property to be assigned to the next JMS message
     * sent. Note that this property is set only on the next
     * message, subsequent messages will not get this
     * property. Also note that
     * if the body is a message itself,
     * then the property set here will override the property set
     * in the message.
     * 
     * @param name the property name.
     * @param value the property value.
     */
    public void setProperty(String name,Object value); 

    /**
     * The message type used by the method. The default is
     * to use the type of the body parameter.
     */
    @PropertySet(prefix="Message")
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Message
    {             
        @FeatureInfo(shortDescription="The message type")
    	public JMSControl.MessageType value() default JMSControl.MessageType.Auto;
    }

    /**
     * The method parameter representing a message property with the given name.
     * see javax.jms.Message.getProperty()/setProperty().
     */    
    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Property
    {
        /**
         * The property name.
         */
        public String name();
    }
    
    /**
     * The method parameter representing a message property with the given name and value.
     * see javax.jms.Message.getProperty()/setProperty().
     */  
    @PropertySet(prefix="Property")
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PropertyValue
    {
        /**
         * The property name.
         */
        public String name();
        
       /**
        * The property value.
        */
        public String value();
        
        /**
         * The property type.
         */
        public Class type() default String.class;
        
    }
    
    /**
     * The method/parameter annotation representing a message priority. If not given
     * then the default for the JMS provider is used.
     */ 
    @PropertySet(prefix="Priority")
    @Target({ElementType.PARAMETER,ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @AnnotationConstraints.AllowExternalOverride
    public @interface Priority
    {
        @AnnotationMemberTypes.Optional
        public int value() default -1;
    }
    /**
     * The method/parameter representing the message JMS type. 
     */ 
    @PropertySet(prefix="Type")
    @Target({ElementType.PARAMETER,ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Type
    {
        public String value() default "";
    } 
    /**
     * The method/parameter representing the message JMS CorrelationID. 
     */ 
    @PropertySet(prefix="CorrelationId")
    @Target({ElementType.PARAMETER,ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CorrelationId
    {
        public String value() default "";
    }  
    /**
     * The method parameter representing a message expiration in milliseconds. 
     * If not given then the default for the JMS provider is used.
     */ 
    @PropertySet(prefix="Expiration")
    @Target({ElementType.PARAMETER,ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @AnnotationConstraints.AllowExternalOverride
    public @interface Expiration
    {
        @AnnotationMemberTypes.Optional
        public long value() default -1L;
    }
    /**
     * The method parameter representing a message delivery mode. 
     * If not given then the default for the JMS provider is used.
     */ 
    @PropertySet(prefix="Delivery")
    @Target({ElementType.PARAMETER,ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Delivery
    {
        public JMSControl.DeliveryMode value() default JMSControl.DeliveryMode.Auto;
    }
    /**
     * The method parameter representing one or more properties. 
     */ 
    @PropertySet(prefix="Properties")
    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Properties
    {
        public PropertyValue[] value();
    }
    /**
     * The JMS destination annotation for a extended class method.
     */     
    @PropertySet
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE,ElementType.FIELD})
    @AnnotationConstraints.AllowExternalOverride
    public @interface Destination
    {
        /**
         * The JNDI name of the queue or topic.
         */
        // BUG: There should be a JMS_TOPIC_OR_QUEUE resource type.
        @FeatureInfo(shortDescription="JNDI name of the queue or topic")
        @AnnotationMemberTypes.JndiName( resourceType = AnnotationMemberTypes.JndiName.ResourceType.OTHER )
    	public String sendJndiName();
        
        /**
         * The Correlation-Id for messages.
         */
        @FeatureInfo(shortDescription="Correlation-Id for messages")
        @AnnotationMemberTypes.Optional
    	public String sendCorrelationProperty() default "";
        
        /**
         * The JNDI name of queue connection factory.
         */
        @FeatureInfo(shortDescription="JNDI name of queue connection factory")
    	public String jndiConnectionFactory();
        
        /**
         * The destination type (DestinationType). The default is to use the type of the destination object named by the JNDI name.
         */
        @FeatureInfo(shortDescription="The destination type (DestinationType). The default is to use the type of the destination object named by the JNDI name")
        @AnnotationMemberTypes.Optional
        public JMSControl.DestinationType sendType() default JMSControl.DestinationType.Auto; 
        
        /**
         * True if send is transacted. The default is transacted.
         */
        @FeatureInfo(shortDescription="True if send is transacted. The default is transacted")
        @AnnotationMemberTypes.Optional
        public boolean transacted() default true;
        
        /**
         * The acknowledge mode. The default is to use auto-acknowledge.
         */
        @FeatureInfo(shortDescription="The acknowledge mode. The default is to use auto-acknowledge")
        @AnnotationMemberTypes.Optional
        public JMSControl.AcknowledgeMode acknowledgeMode() default JMSControl.AcknowledgeMode.Auto;
        
        /**
         * The JNDI context factory.
         */
        @FeatureInfo(shortDescription="JNDI context factory")
        @AnnotationMemberTypes.Optional
    	public String jndiContextFactory() default "";
        
        /**
         * The JNDI provider URL.
         */
        @FeatureInfo(shortDescription="JNDI provider URL")      
        @AnnotationMemberTypes.Optional
        @AnnotationMemberTypes.URI
    	public String jndiProviderURL() default "";
        
        /**
         * The JNDI security principal.
         */
        @FeatureInfo(shortDescription="JNDI security principal")      
        @AnnotationMemberTypes.Optional
    	public String jndiUsername() default "";
        
        
        /**
         * The JNDI security credentials.
         */
        @FeatureInfo(shortDescription="JNDI security credentials")      
        @AnnotationMemberTypes.Optional
    	public String jndiPassword() default "";
    }


}
