package model;

import java.beans.BeanDescriptor;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.runtime.bean.BeanPersistenceDelegate;
import org.apache.beehive.controls.runtime.packaging.ControlEventSetDescriptor;

public class GoogleClientBeanBeanInfo 
extends org.controlhaus.webservice.ServiceControlBeanBeanInfo
{
    static final Method _searchMethod;
    static final Method _suggestSpellingMethod;
    static final Method _getCachedPageMethod;
    
    //
    // This HashMap will map from a Method to the array of names for parameters of the
    // method.  This is necessary because parameter name data isn't carried along in the
    // class file, but if available can enable ease of use by referencing parameters by
    // the declared name (vs. by index).
    //
    // This map should be read-only after its initialization in the static block, hence
    // using a plain HashMap is thread-safe.
    //
    static HashMap<Method, String []> _methodParamMap = new HashMap<Method, String []>();
    
    static
    {
        try
        {
            _searchMethod = model.GoogleClient.class.getMethod("search", new Class [] {java.lang.String.class, java.lang.String.class, int.class, int.class, boolean.class, java.lang.String.class, boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_searchMethod, new String [] { "key", "q", "start", "maxResults", "filter", "restrict", "safeSearch", "lr", "ie", "oe" });
            _suggestSpellingMethod = model.GoogleClient.class.getMethod("suggestSpelling", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_suggestSpellingMethod, new String [] { "key", "phrase" });
            _getCachedPageMethod = model.GoogleClient.class.getMethod("getCachedPage", new Class [] {java.lang.String.class, java.lang.String.class});
            _methodParamMap.put(_getCachedPageMethod, new String [] { "key", "url" });
        }
        catch (NoSuchMethodException nsme)
        {
            throw new ExceptionInInitializerError(nsme);
        }
    }
    
    public BeanDescriptor getBeanDescriptor()
    {
        BeanDescriptor bd = new BeanDescriptor(model.GoogleClientBean.class);
        bd.setName("GoogleClientBean");
        bd.setDisplayName("GoogleClientBean");
        
        //
        // The org.apache.beehive.controls.runtime.bean.BeanPersistenceDelegate class
        // implements the XMLDecode delegation model for all Control JavaBean types. It
        // provides optimized XML persistance based upon the Control runtime architecture.
        // The 'persistenceDelegate' attribute of a BeanDescriptor is used by XMLEncoder to 
        // locate a delegate for a particular JavaBean type.
        //
        bd.setValue("persistenceDelegate", new BeanPersistenceDelegate());
        
        return bd;
    }
    
    /**
    * Stores MethodDescriptor descriptors for this bean and its superclasses into
    * an array, starting at the specified index
    */
    protected void initMethodDescriptors(MethodDescriptor [] methodDescriptors, int index)
    throws java.beans.IntrospectionException
    {
        String [] paramNames;
        ParameterDescriptor [] paramDescriptors;
        MethodDescriptor md;
        
        //
        // Declare MethodDescriptor for search(key, q, start, maxResults, filter, restrict, safeSearch, lr, ie, oe)
        //
        paramNames = _methodParamMap.get(_searchMethod);
        paramDescriptors = new ParameterDescriptor[paramNames.length];
        for (int j = 0; j < paramNames.length; j++)
        {
            paramDescriptors[j] = new ParameterDescriptor();
            paramDescriptors[j].setName(paramNames[j]);
            paramDescriptors[j].setDisplayName(paramNames[j]);
        }
        md = new MethodDescriptor(_searchMethod, paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for suggestSpelling(key, phrase)
        //
        paramNames = _methodParamMap.get(_suggestSpellingMethod);
        paramDescriptors = new ParameterDescriptor[paramNames.length];
        for (int j = 0; j < paramNames.length; j++)
        {
            paramDescriptors[j] = new ParameterDescriptor();
            paramDescriptors[j].setName(paramNames[j]);
            paramDescriptors[j].setDisplayName(paramNames[j]);
        }
        md = new MethodDescriptor(_suggestSpellingMethod, paramDescriptors);
        methodDescriptors[index++] = md;
        
        //
        // Declare MethodDescriptor for getCachedPage(key, url)
        //
        paramNames = _methodParamMap.get(_getCachedPageMethod);
        paramDescriptors = new ParameterDescriptor[paramNames.length];
        for (int j = 0; j < paramNames.length; j++)
        {
            paramDescriptors[j] = new ParameterDescriptor();
            paramDescriptors[j].setName(paramNames[j]);
            paramDescriptors[j].setDisplayName(paramNames[j]);
        }
        md = new MethodDescriptor(_getCachedPageMethod, paramDescriptors);
        methodDescriptors[index++] = md;
        
        
        //
        // Add method descriptors from parent BeanInfo
        //
        super.initMethodDescriptors(methodDescriptors, index); 
    }
    
    public MethodDescriptor [] getMethodDescriptors()
    {
        MethodDescriptor [] methodDescriptors = new MethodDescriptor[16];
        try
        {
            initMethodDescriptors(methodDescriptors, 0);
        }
        catch (java.beans.IntrospectionException ie)
        {
            throw new ControlException("Unable to create MethodDescriptor", ie);
        }
        return methodDescriptors;
    }
    
    /**
    * Stores PropertyDescriptor descriptors for this bean and its superclasses into
    * an array, starting at the specified index
    */
    protected void initPropertyDescriptors(PropertyDescriptor [] propDescriptors, int index)
    throws java.beans.IntrospectionException
    {
        PropertyDescriptor pd;
        
        pd = new PropertyDescriptor("controlImplementation", model.GoogleClientBean.class, "getControlImplementation", null );
        propDescriptors[index++] = pd;
        
        //
        // Add property descriptors from parent BeanInfo
        //
        super.initPropertyDescriptors(propDescriptors, index); 
    }
    
    public PropertyDescriptor [] getPropertyDescriptors()
    {
        PropertyDescriptor [] propDescriptors = new PropertyDescriptor[6];
        try
        {
            initPropertyDescriptors(propDescriptors, 0);
        }
        catch (java.beans.IntrospectionException ie)
        {
            throw new ControlException("Unable to create PropertyDescriptor", ie);
        }
        return propDescriptors;
    }
    
    
    protected void initEventSetDescriptors(EventSetDescriptor [] eventSetDescriptors, int index)
    throws java.beans.IntrospectionException
    {
        int eventIndex = 0;
        MethodDescriptor [] eventDescriptors;
        EventSetDescriptor esd;
        Method addListener, removeListener, getListeners;
        Class eventIntf;
        
        
        //
        // Add event set descriptors from parent BeanInfo
        //
        super.initEventSetDescriptors(eventSetDescriptors, index); 
    }
    
    public EventSetDescriptor [] getEventSetDescriptors()
    {
        EventSetDescriptor [] eventSetDescriptors = new EventSetDescriptor[0];
        try
        {
            initEventSetDescriptors(eventSetDescriptors, 0);
        }
        catch (java.beans.IntrospectionException ie)
        {
            throw new ControlException("Unable to create EventSetDescriptor", ie);
        }
        return eventSetDescriptors;
    }
}
