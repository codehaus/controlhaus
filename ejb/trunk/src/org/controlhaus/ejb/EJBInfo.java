package org.controlhaus.ejb;

import org.apache.beehive.controls.api.ControlException;

import javax.ejb.EJBLocalHome;
import javax.ejb.FinderException;
import java.util.HashMap;
import java.util.Vector;
import java.lang.reflect.Method;

/**
 * The EJBInfo class is a support class that derives EJB information by
 * reflecting on an EJB control interface.  This is implemented by a
 * static inner class to make this functionality accesible in both static
 * and non-static contexts
 */
public class EJBInfo
{
    public String _refName;
    public Class _homeInterface;
    public Class _beanInterface;
    public String _beanType;
    public boolean _isLocal;

    public String toString()
    {
        return "{refname=" + _refName + " home=" + _homeInterface.getName() + " remote=" + _beanInterface.getName() +
               " type=" + _beanType + " local=" + _isLocal + "}";
    }

    /**
     * Derives bean attributes from the control interface
     */
    public EJBInfo(Class controlInterface)
    {
        _refName = getEJBRefName( controlInterface );

        Class localHome = null;
        Class localBean = null;
        Class remoteHome = null;
        Class remoteBean = null;

        //
        // To identify the identify home and bean interfaces, we
        // must reflect the interface hierarchy of the provided
        // class.
        //
        Vector checkList = new Vector();
        Class [] subintfs = controlInterface.getInterfaces();
        for (int i = 0; i < subintfs.length; i++)
            checkList.add(subintfs[i]);

        HashMap derivesFrom = new HashMap();
        for (int i = 0; i < checkList.size(); i++)
        {
            Class intf = (Class)checkList.elementAt(i);

            if (javax.ejb.EJBHome.class.isAssignableFrom(intf))
                remoteHome = intf;
            else if (javax.ejb.EJBLocalHome.class.isAssignableFrom(intf))
                localHome = intf;
            else if (javax.ejb.EJBObject.class.isAssignableFrom(intf))
                remoteBean = intf;
            else if (javax.ejb.EJBLocalObject.class.isAssignableFrom(intf))
                localBean = intf;
            else
            {
                //
                // If none of the above, add any new subinterfaces to
                // the search list.
                //
                subintfs = intf.getInterfaces();
                for (int j = 0; j < subintfs.length; j++)
                {
                    if (!checkList.contains(subintfs[j]))
                    {
                        checkList.add(subintfs[j]);
                        derivesFrom.put(subintfs[j], intf);
                    }
                }
            }
        }

        //
        // From the located methods, identify the home/bean interfaces.
        //
        if (remoteHome != null)
        {
            if (localHome != null)
            {
                throw new ControlException(controlInterface +
                                           " extends multiple EJB home interfaces.");
            }
            _homeInterface = getRoot(remoteHome, derivesFrom);
        }
        else if (localHome != null)
        {
            _homeInterface = getRoot(localHome, derivesFrom);
        }
        else
        {
            throw new ControlException(controlInterface +
                " does not extend the EJBHome or EJBLocalHome interfaces");
        }

        if (remoteBean != null)
        {
            if (localBean != null)
            {
                throw new ControlException("Interface " + controlInterface +
                            " extends multiple EJB object interfaces.");
            }
            _beanInterface = getRoot(remoteBean, derivesFrom);
        }
        else if (localBean != null)
        {
            _beanInterface = getRoot(localBean, derivesFrom);
        }
        else
        {
            throw new ControlException("Interface " + controlInterface +
             " does not extend the EJBObject or EJBLocalObject interfaces");
        }

        // Identify the bean type via bean interface reflection
        _beanType = "Session";
        Method [] homeMethods = _homeInterface.getMethods();
        for (int i = 0; i < homeMethods.length; i++)
        {
            if (isFinderMethod(homeMethods[i]))
            {
                _beanType = "Entity";
                break;
            }
        }

        _isLocal = (EJBLocalHome.class.isAssignableFrom(_homeInterface));
    }

    /**
     * Unwinds the results of reflecting through the interface inheritance
     * hierachy to find the original root class from a derived class
     */
    public Class getRoot(Class clazz, HashMap derivesFrom)
    {
        while (derivesFrom.containsKey(clazz))
            clazz = (Class)derivesFrom.get(clazz);
        return clazz;
    }

    static protected boolean isFinderMethod(Method m)
    {
        if (!m.getName().startsWith("find")) // EJB enforced pattern
            return false;
        return methodThrows(m, FinderException.class);
    }

    static protected boolean methodThrows(Method m, Class exceptionClass)
    {
        Class [] exceptions = m.getExceptionTypes();
        for (int j = 0; j < exceptions.length; j++)
            if (exceptionClass.isAssignableFrom(exceptions[j]))
                return true;
        return false;
    }

    /**
     * Computes a unique local ejb ref name based upon the JCX class name
     */
    static public String getEJBRefName(Class jcxClass)
    {
        return jcxClass.getName() + ".jcx";
    }

}

