/*
 * 
 * Copyright 2004 BEA Systems, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * 
 * Original author: Daryoush Mehrtash
 */

import javax.xml.rpc.ServiceException;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.controlhaus.controlunit.ControlTestCase;
import org.apache.beehive.addressbook.Address;
import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;

public class SimpleWebSericeTest extends ControlTestCase {
  static Logger logger = Logger.getLogger(SimpleWebSericeTest.class);

  @Control org.apache.beehive.addressbook.SimpleAddressBook client;

  public void setUp() throws Exception {
    super.setUp(); // initialize my control instance....

  }
  public void testAddressBook() throws Exception {
    Address address = Address.Factory.newInstance();
    address.setCity("Seattle");
    address.setZip(98119);
    client.addEntry("daryoush", address);
    Address res = client.getAddressFromName("daryoush");
    assertTrue("Result address is not as expected!:  Result: " + res.getCity()
        + " Expected: " + "Seattle", 0 == "Seattle".compareTo(res.getCity()));
    assertTrue("Result zipcode is not as expected!:  Result: " + res.getZip()
        + " Expected: " + "98119", 98119 == res.getZip());

  }

}
