package org.controlhaus.ejb;

import org.apache.beehive.controls.api.bean.ControlInterface;

/**
 * As part of the EJB control, this interface simplifies access to 
 * session Enterprise JavaBeans (EJBs). You do not need to use 
 * this interface directly. 
 * <br/><br/>
 * The EJB control is actually made up of two main interfaces, 
 * one for access to entity EJBs
 * and another for access to session EJBs. The presence of these
 * two interfaces is invisible when you use the EJB control; their
 * methods are called behind the scenes.
 * <br/><br/>
 * Typically, you use the EJB control by adding the control to a 
 * component design (such as a web service or pageflow design), 
 * then calling the methods it provides. Those methods are not 
 * exposed by these control interfaces, but rather are extensions 
 * of the EJB itself that are generated when you add
 * the EJB control.
 * <br/><br/>
 * For more information about using the EJB control, see 
 * <a href="../../../../guide/controls/ejb/navEJBControl.html">EJB Control</a>.
 */
@ControlInterface (defaultBinding="com.bea.wlw.runtime.core.control.SessionEJBControlImpl")
public interface SessionEJBControl extends EJBControl
{

}
