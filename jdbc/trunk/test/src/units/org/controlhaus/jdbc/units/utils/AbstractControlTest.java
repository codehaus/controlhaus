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

import junit.framework.TestCase;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;

import java.lang.reflect.Method;

/**
 * @author <a href="mailto:dan@envoisolutions.com">Dan Diephouse</a>
 * @since Nov 5, 2004
 */
public abstract class AbstractControlTest extends TestCase {

    private ControlContainerContext context;

    public AbstractControlTest(String nm) {
        super(nm);
    }

    public void setUp() throws Exception {
        
        context = new TestContainerContext();

        try {
            context.beginContext();
            Class init = getClass().getClassLoader().loadClass(getClass().getName() + "ClientInitializer");

            Method m = init.getMethod("initialize",
                                      new Class[]
                                      {
                                          ControlBeanContext.class,
                                          getClass()
                                      });

            m.invoke(null, new Object[]{context, this});
        } catch (ClassNotFoundException cnfe) {
            // do nothing.
        }
    }

    public void tearDown() throws Exception {
        context.endContext();
    }

    public ControlContainerContext getContext() {
        return context;
    }
}

