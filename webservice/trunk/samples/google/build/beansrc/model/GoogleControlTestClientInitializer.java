
package model;

import java.lang.reflect.Field;
import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.bean.Controls;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.runtime.bean.ControlBean;
import org.apache.beehive.controls.runtime.bean.EventAdaptor;
import org.apache.beehive.controls.runtime.bean.AdaptorPersistenceDelegate;

public class GoogleControlTestClientInitializer
extends org.apache.beehive.controls.runtime.bean.ClientInitializer
{
    static final Field _googleField;
    static
    {
        try
        {
            _googleField = model.GoogleControlTest.class.getDeclaredField("google");
            _googleField.setAccessible(true);
        }
        catch (NoSuchFieldException nsfe)
        {
            throw new ExceptionInInitializerError(nsfe);
        }
    }
    
    
    private static void initializeFields(ControlBeanContext cbc,
    model.GoogleControlTest client)
    {
        try
        {
            //
            // Initialize any nested controls used by the client
            //
            model.GoogleClientBean _google = (model.GoogleClientBean)cbc.getBean("google");
            if (_google == null)
            _google = (model.GoogleClientBean) Controls.instantiate(model.GoogleClientBean.class, getAnnotationMap(cbc, _googleField), cbc, "google" );
            client.google = _google; 
            
        }
        catch (RuntimeException re) { throw re; }
        catch (Exception e)
        {
            throw new ControlException("Initializer failure", e);
        }
    }
    
    public static void initialize(ControlBeanContext cbc, model.GoogleControlTest client)
    {
        
        initializeFields( cbc, client );
    }
}
