/*
 * Copyright 2004 BEA Systems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.controlhaus.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.beehive.controls.api.bean.AnnotationMemberTypes;
import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.bean.AnnotationConstraints.MembershipRule;
import org.apache.beehive.controls.api.bean.AnnotationConstraints.MembershipRuleValues;
import org.apache.beehive.controls.api.properties.PropertySet;

/**
 * Enterprise Java Bean Control base interface
 */
@ControlInterface (defaultBinding="org.controlhaus.ejb.EJBControlImpl")
public interface EJBControl
{

    /**
     * EJBHome specifies the target EJB's home interface for the EJB control
     * - jndiName specifies the JNDI name of the target EJB's home interface
     *   (e.g. EJBNameHome).  This value may also be an URL using the "JNDI:" 
     *   protocol (e.g. jndi://username:password@host:port/EJBNameHome).
     * - ejbLink specifies the name of the target EJB using the application
     *   relative path to the EJB JAR.  This syntax causes the runtime to
     *   use an application scoped name when locating the referenced EJB.  
     *   The naming syntax is BeanName#EJBJAR (e.g. CreditCard#CustomerData.jar).
     */
    @PropertySet
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})  // allow override on declaration
    @MembershipRule(MembershipRuleValues.EXACTLY_ONE)
    public @interface EJBHome
    {
        String jndiName() default "";
        String ejbLink()  default "";
    }

    /**
     * JNDIContextEnv specifies the environment properties for the JNDI context that will
     * be used to lookup the target EJB.  This attribute is optional.  If you are using 
     * an URL with the "JNDI:" protocol or if you want to use a JNDI context with the 
     * default envirnoment properties, you do not need a specify any values for this attribute. 
     */
    @PropertySet
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})  // allow override on declaration
    public @interface JNDIContextEnv
    {
        @AnnotationMemberTypes.Optional
        String contextFactory() default "";

        @AnnotationMemberTypes.Optional
        String providerURL() default "";

        @AnnotationMemberTypes.Optional
        String principal() default "";

        @AnnotationMemberTypes.Optional
        String credentials() default "";
    }

    
    /**
     * Returns an instance of the home interface associated with
     * the target bean component.
     */
    public Object getEJBHomeInstance();

    /**
     * Returns true if the EJB control currently has a target bean instance
     * upon which bean business interface methods may be invoked.  This will
     * be true after a successful create() or single select finder method
     * execution, or in cases where implicit creation or find has occurred
     * by the control on the control users behalf.  This provides a simple
     * way to procedurally check the status of explicit or implicit
     * bean instance creation or find operations.
     */
    public boolean  hasEJBBeanInstance();

    /**
     * Returns the current target instance of the bean business interface
     * used for business interface method invocations.  This API is
     * provided for advanced use cases were direct access to the local/
     * remote interfaces outside of the control is required.  It will
     * return <code>null</code> if no target instance is currently
     * selected.
     */
    public Object  getEJBBeanInstance();

    /**
     * Returns the last EJB exception serviced by the EJB control on the
     * developers behalf.  This can be used to discover or log additional
     * information, for example when a create or find method is unable to
     * locate a target bean instance.
     */
    public Throwable getEJBException();
}
