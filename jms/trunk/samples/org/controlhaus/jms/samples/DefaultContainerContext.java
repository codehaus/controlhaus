package org.controlhaus.jms.samples;


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
