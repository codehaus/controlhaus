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
package org.controlhaus.ejb.sample.client;

import java.io.IOException;

import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.context.ControlHandle;
import org.apache.beehive.controls.api.bean.ControlBean;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;
import org.apache.beehive.controls.runtime.bean.ControlBeanContext;

/**
 * The DefaultContainerContext provides a ControlContainerContext implementation to be used
 * for simple testing of controls.
 */
public class DefaultContainerContext extends ControlContainerContext
{

    /* Public Constructor(s) */
    /**
     * Construct a container-context for a standalone
     * application use.
     */
    public DefaultContainerContext()
    {
        super();
    }
    /* Public Method(s) */

    /**
     * @see ControlContainerContext#getControlHandle(org.apache.beehive.controls.runtime.bean.ControlBean)
     */
    public ControlHandle getControlHandle(ControlBean bean)
    {
        return new DefaultControlHandle(this,bean);
    }
    
    /* Private Constant(s) */
    private static final long serialVersionUID = -7967007783133727017L;

}
