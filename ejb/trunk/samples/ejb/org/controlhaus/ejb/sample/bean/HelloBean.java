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
package org.controlhaus.ejb.sample.bean;

import java.rmi.RemoteException;
import javax.ejb.*;

public class HelloBean implements SessionBean
{
    public HelloBean()
    {
    }

    public void ejbCreate()
    {
    }

    public void ejbRemove()
        throws EJBException, RemoteException
    {
    }

    public void ejbActivate()
        throws EJBException, RemoteException
    {
        
    }

    public void ejbPassivate()
        throws EJBException, RemoteException
    {
    }

    public void setSessionContext(SessionContext ctx)
    {
        this.ctx = ctx;
    }

    public String hello()
        throws RemoteException
    {
        return "Hello";
    }

   private static final long serialVersionUID = 0xa0aa87e44ca8f7c2L;
    private SessionContext ctx;
}