package org.controlhaus.ejb;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.properties.PropertySet;

//import org.apache.beehive.controls.api.bean.AnnotationConstraints.MembershipRule;
//import org.apache.beehive.controls.api.bean.AnnotationConstraints.MembershipRuleValues;

/**
 * Enterprise Java Bean Control base interface
 */
@ControlInterface (defaultBinding="org.controlhaus.ejb.EJBControlImpl")
public interface EJBControl
{

    /**
     * EJB Control attributes.
     */
    @PropertySet
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})  // allow override on declaration
    //@MembershipRule(MembershipRuleValues.EXACTLY_ONE)
    public @interface EJBHome
    {
        String JNDIName() default "";
        String EJBLink()  default "";
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
