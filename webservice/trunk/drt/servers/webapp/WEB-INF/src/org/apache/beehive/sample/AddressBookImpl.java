package org.apache.beehive.sample;

/*
 * Copyright 2004 The Apache Software Foundation
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
 * $Header:$
 */


import java.util.Hashtable;
import java.util.Map;

import javax.naming.InvalidNameException;

public class AddressBookImpl implements AddressBook
{
    private Map<String, Address> addresses = new Hashtable<String, Address>();

    public void addEntry(String name, Address address) throws InvalidNameException
    {
        System.out.println("addEntry is called." );
        this.addresses.put(name, address);
    }
    
    public Address getAddressFromName(String name) throws InvalidNameException
    {
        System.out.println("getAddressFromName is called.");
        return (Address) this.addresses.get(name);
    }
}
