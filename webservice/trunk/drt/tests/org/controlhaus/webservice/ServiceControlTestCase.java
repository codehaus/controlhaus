/*
 * ServiceControlTestCase.java
 * 
 * Copyright 2004 BEA Systems, Inc. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 * Original author: Jonathan Colwell
 */
package org.controlhaus.webservice;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public abstract class ServiceControlTestCase extends TestCase {

    private ControlContainerContext mContext;

    public void setUp() throws Exception {
        mContext = ControlThreadContext.getContext();
        if(mContext == null) {
            mContext = new org.apache.beehive.controls.runtime.bean.
                ControlContainerContext();
            mContext.beginContext();
        }

        /*
         * Now that the context is in place, try to initialize the service
         * control client subclass.
         */
        Class cls = getClass();
        Class clientInitializer =
            cls.getClassLoader().loadClass(cls.getName()
                                           + "ClientInitializer");
        
        Method init =
            clientInitializer.getMethod("initialize",
                                        ControlBeanContext.class, cls);
        init.invoke(null, mContext, this);
        
    }

    public void tearDown() throws Exception {
        if(mContext != null) {
            mContext.endContext();
        }
    }
}
