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

package org.controlhaus.jdbc.units.utils;

import org.apache.beehive.controls.api.properties.PropertyMap;
import org.apache.beehive.controls.runtime.bean.ControlBean;
import org.apache.beehive.controls.runtime.bean.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;
import org.apache.beehive.controls.runtime.bean.ResourceContextImpl;

import java.beans.beancontext.BeanContextChild;
import java.beans.beancontext.BeanContextServiceRevokedListener;
import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;

/**
 * The TestBeanContext is a simple standalone bean context intended to be used as a test harness
 * for control drt tests.  The goal is to enable controls to be instantiated and used within
 * a standalone VM, and to give the client control of ControlContainerContext behavior, such
 * as resource scoping.
 */
public class TestContainerContext extends ControlContainerContext {


    /**
     * Called by BeanContextSupport superclass during construction and deserialization to
     * initialize subclass transient state
     */
    public void initialize() {
        super.initialize();
    }

    /**
     * Override default getBeanAnnotationMap to not depend on ControlBean
     *
     * @param bean
     * @param annotElem
     * @return
     */
    protected PropertyMap getBeanAnnotationMap(ControlBean bean, AnnotatedElement annotElem) {
        return super.getBeanAnnotationMap(bean, annotElem);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Maps children by the local (relative) ID of the child to the actual bean instance.
     * This needs to be synchronized, because adds/removes/gets are not necessarily guaranteed
     * to happen within the scope of the global hierarchy lock.   It would be relatively easy
     * to synchronize add/remove, since setBeanContext on the child is inside this lock scope,
     * but gets on the map are another story.
     */
    private Map<String, Object> _childMap =
            Collections.synchronizedMap(new HashMap<String, Object>());
    static String beanID = null;

    // targetChild - the child object to nest within this context
    public boolean add(Object targetChild) {
        //
        // The context can contain ControlBeans and other types of objects, such as a control
        // factory.
        //
//        String beanID = null;
        if (targetChild instanceof ControlBean) {
            //
            //
            //
            ControlBean bean = (ControlBean) targetChild;
//            beanID = bean.getLocalID();
            beanID = null;

            //
            // The bean is anonymous, so we must generate a new unique name within this context.
            //
            if (beanID == null) {
                beanID = generateUniqueID(bean.getClass());
//                bean.setLocalID(beanID);
            }

            ControlBean existingBean = (ControlBean) _childMap.get(beanID);
            if (existingBean != null && existingBean != targetChild) {
                throw new IllegalArgumentException("Attempting to add control with duplicate ID: " +
                                                   beanID);
            }
        }
////        boolean added = super.add(targetChild);
//        children.put(targetChild, this);
////        if (added && beanID != null)
        _childMap.put(beanID, targetChild);
        try {
            ControlBeanContext cbc = ((ControlBean) targetChild).getControlBeanContext();
            cbc.setBeanContext(this);
//            children.put(cbc, createBCSChild(cbc, targetChild));
//     need this?       children.put(targetChild, createBCSChild(targetChild, cbc));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        return added;
        return true;
    }

    /**
     * Overrides the BeanContextSupport.remove() method to perform additional post-processing
     * on child removal.
     */
    public boolean remove(Object targetChild) {
        assert targetChild instanceof ControlBean;  // should be guaranteed above
        boolean removed = super.remove(targetChild);
        if (removed) {
            //
            // Remove from the locally maintained child map
            //
//            String localID = ((ControlBean)targetChild).getLocalID();
//            Object removedChild = _childMap.remove(localID);
//            assert removedChild == targetChild;     // just being safe
        }
        return removed;
    }

    /**
     * Returns a ControlBean instance nested the current BeanContext.
     *
     * @param id the identifier for the target control, relative to the current
     *           context.
     */
    public ControlBean getBean(String id) {
        // If no control id separator found, the bean is a direct child of this context
        int delim = id.indexOf(ControlBean.IDSeparator);
        if (delim < 0)  // child is a direct descendent
            return (ControlBean) _childMap.get(id);

        // Find the child referenced by the first element in the path
        ControlBean bean = (ControlBean) _childMap.get(id.substring(0, delim));
        if (bean == null)
            return bean;

        // Get the BeanContext associated with the found child, and then ask it
        // to resolve the rest of the path
        return ((ControlBeanContext) bean.getBeanContextProxy()).getBean(id.substring(delim + 1));
    }

    // overrides BeanContextServicesSupport.getService()
    public Object getService(BeanContextChild child, Object requestor, Class serviceClass, Object serviceSelector, BeanContextServiceRevokedListener bcsrl) throws TooManyListenersException {
        return new ResourceContextImpl(this, (ControlBean) requestor);
    }
}
