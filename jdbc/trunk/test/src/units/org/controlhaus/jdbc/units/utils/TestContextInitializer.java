
package org.controlhaus.jdbc.units.utils;

import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlContainerContext;

import java.lang.reflect.Method;

public class TestContextInitializer {


    public static ControlContainerContext initContext(Object caller)
            throws Exception {
        ControlContainerContext context = new TestContainerContext();
        final Class forClass = caller.getClass();

        context.beginContext();
        Class init = forClass.getClassLoader().loadClass(forClass.getName() + "ClientInitializer");

        Method m = init.getMethod("initialize",
                                  new Class[]
                                  {
                                      ControlBeanContext.class,
                                      forClass
                                  });

        m.invoke(null, new Object[]{context, caller});
        return context;
    }
}
