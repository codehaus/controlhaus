
package model;

import java.beans.*;

import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashMap;
import java.util.Map;

import org.apache.beehive.controls.api.bean.*;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.apache.beehive.controls.api.properties.PropertyKey;
import org.apache.beehive.controls.api.properties.PropertyMap;
import org.apache.beehive.controls.api.versioning.*;

public class GoogleClientBean 
extends org.controlhaus.webservice.ServiceControlBean 
implements model.GoogleClient
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
    
    
    
    static
    {
        
    }
    
    
    static
    {
        /*
        * Enforce VersionRequired
        */
        Class controlIntf = org.apache.beehive.controls.runtime.bean.ControlBean.getMostDerivedInterface(model.GoogleClient.class);
        
        Version versionPresent = (Version)controlIntf.getAnnotation(Version.class);
        VersionRequired versionRequired = (GoogleClientBean.class).getAnnotation(VersionRequired.class);
        
        org.apache.beehive.controls.runtime.bean.ControlBean.enforceVersionRequired("model.GoogleClient", versionPresent, versionRequired);
    }
    
    /**
    * This is the public constructor for the class.  A client-defined control ID may
    * be provided.  This ID must be unique within the nesting ControlBeanContext.
    * @param context The containing ControlBeanContext
    * @param id The control identifier (or null to autogenerate a unique value)
    * @param props The initialization Properties for the new instance (or null for defaults)
    */
    public GoogleClientBean(ControlBeanContext context, String id, PropertyMap props)
    {
        this(context, id, props, model.GoogleClient.class);
    }
    
    /**
    * This is the public null-arg constructor for this ControlBean.  If a control id
    * is not provided, a unique value will be auto-generated.
    */
    public GoogleClientBean()
    {
        this(null, null, null);
    }
    
    /**
    * This is the protected version that is used by any ControlBean subclass
    */
    protected GoogleClientBean(ControlBeanContext context, String id, PropertyMap props,
    Class controlClass)
    {
        super(context, id, props, controlClass);
        
    }
    
    
    /**
    * Returns an array of parameter names for the request method, or null if no parameter
    * data is available.
    */
    protected String [] getParameterNames(Method m)
    {
        // Check the local map for operations on this bean type
        if (_methodParamMap.containsKey(m)) 
        {
            return _methodParamMap.get(m); 
        }
        
        // Delegate up if not found locally
        return super.getParameterNames(m);
    }
    
    /**
    * Implements model.GoogleClient.search
    */
    public  GoogleSearch.GoogleSearchResult search(java.lang.String key, java.lang.String q, int start, int maxResults, boolean filter, java.lang.String restrict, boolean safeSearch, java.lang.String lr, java.lang.String ie, java.lang.String oe) throws java.lang.Exception
    {
        Object [] argArray = new Object[] { key, q, start, maxResults, filter, restrict, safeSearch, lr, ie, oe };
        Throwable thrown = null;
        Extensible target = (Extensible)ensureControl();
        GoogleSearch.GoogleSearchResult retval = null;
        
        try
        {
            preInvoke(_searchMethod, argArray);
            
            retval = 
            (GoogleSearch.GoogleSearchResult)
            target.invoke(_searchMethod, argArray)
            ;
        }
        catch (Throwable t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will 
            // be rethrown.
            //
            thrown = t;
            
            if (t instanceof Error) throw (Error)t;
            else if (t instanceof RuntimeException) throw (RuntimeException)t;
            else if (t instanceof java.lang.Exception) throw (java.lang.Exception)t;
            
            throw new UndeclaredThrowableException(t);
        }
        finally
        {
            Object rv = retval;
            postInvoke(_searchMethod, argArray, rv, thrown);
        }
        return retval;
    }
    
    /**
    * Implements model.GoogleClient.suggestSpelling
    */
    public  java.lang.String suggestSpelling(java.lang.String key, java.lang.String phrase) throws java.lang.Exception
    {
        Object [] argArray = new Object[] { key, phrase };
        Throwable thrown = null;
        Extensible target = (Extensible)ensureControl();
        java.lang.String retval = null;
        
        try
        {
            preInvoke(_suggestSpellingMethod, argArray);
            
            retval = 
            (java.lang.String)
            target.invoke(_suggestSpellingMethod, argArray)
            ;
        }
        catch (Throwable t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will 
            // be rethrown.
            //
            thrown = t;
            
            if (t instanceof Error) throw (Error)t;
            else if (t instanceof RuntimeException) throw (RuntimeException)t;
            else if (t instanceof java.lang.Exception) throw (java.lang.Exception)t;
            
            throw new UndeclaredThrowableException(t);
        }
        finally
        {
            Object rv = retval;
            postInvoke(_suggestSpellingMethod, argArray, rv, thrown);
        }
        return retval;
    }
    
    /**
    * Implements model.GoogleClient.getCachedPage
    */
    public  byte[] getCachedPage(java.lang.String key, java.lang.String url) throws java.lang.Exception
    {
        Object [] argArray = new Object[] { key, url };
        Throwable thrown = null;
        Extensible target = (Extensible)ensureControl();
        byte[] retval = null;
        
        try
        {
            preInvoke(_getCachedPageMethod, argArray);
            
            retval = 
            (byte[])
            target.invoke(_getCachedPageMethod, argArray)
            ;
        }
        catch (Throwable t)
        {
            //
            // All exceptions are caught here, so postInvoke processing has visibility into
            // the exception status.  Errors, RuntimExceptions, or declared checked exceptions will 
            // be rethrown.
            //
            thrown = t;
            
            if (t instanceof Error) throw (Error)t;
            else if (t instanceof RuntimeException) throw (RuntimeException)t;
            else if (t instanceof java.lang.Exception) throw (java.lang.Exception)t;
            
            throw new UndeclaredThrowableException(t);
        }
        finally
        {
            Object rv = retval;
            postInvoke(_getCachedPageMethod, argArray, rv, thrown);
        }
        return retval;
    }
    
    
    /**
    * A PropertyKey that can be used to access the controlImplementation property of the
    * BaseProperties PropertySet
    */
    public static final PropertyKey ControlImplementationKey = new PropertyKey(org.apache.beehive.controls.api.properties.BaseProperties.class, "controlImplementation");
    
    
    public java.lang.String getControlImplementation()
    {
        return (java.lang.String)getControlProperty(ControlImplementationKey);
    }
    
    
    
    
    
    /**
    * The _annotCache maintains a lookup cache from AnnotatedElements to an associated
    * PropertyMap.  This enables these maps to be shared across multiple beans.
    */
    static private HashMap _annotCache = new HashMap();
    
    protected Map getPropertyMapCache() { return _annotCache; }
}
