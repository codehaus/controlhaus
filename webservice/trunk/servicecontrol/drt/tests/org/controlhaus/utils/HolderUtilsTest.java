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
package org.controlhaus.utils;





import java.util.ArrayList;

import javax.xml.rpc.holders.Holder;
import javax.xml.rpc.holders.IntHolder;

import org.apache.beehive.wsm.databinding.GenericHolder;
import org.controlhaus.webservice.utils.HolderUtils;

import junit.framework.TestCase;

public class HolderUtilsTest extends TestCase {

	public void testStuffingHolders() throws NoSuchFieldException, IllegalAccessException {
		 GenericHolder<MyClass> myHolder = new GenericHolder<MyClass>(new MyClass());
		 Holder aHolder = myHolder;

		MyClass theValue = new MyClass("AAA");
		Object anObject = theValue;

				
		HolderUtils.stuffHolderValue(myHolder, anObject);
		assertTrue("AAA".equals(myHolder.value.name));
				
		// // stuff null
		HolderUtils.stuffHolderValue(myHolder, null);
		 System.out.println("After stuffing null to straight holder my name is: " + myHolder.value);

		// stuff object to holder of static array
		MyClassArrayHolder myStaticArrayHolder = new MyClassArrayHolder();
		Holder anStaticArrayHolder = myStaticArrayHolder;
		HolderUtils.stuffHolderValue(anStaticArrayHolder, anObject);
		MyClass[] myStaticArrayValueResult = myStaticArrayHolder.value;
		assertTrue("AAA".equals(myStaticArrayValueResult[0].name));

		// holder of array stuff object
		GenericHolder<MyClass[]> myArrayHolder = new GenericHolder<MyClass[]>(new MyClass[0]);
		Holder anArrayHolder = myArrayHolder;
		HolderUtils.stuffHolderValue(anArrayHolder, anObject);
		MyClass[] myArrayValueResult = myArrayHolder.value;
		assertTrue("AAA".equals(myArrayValueResult[0].name));

		// holder of array stuff array list of objects.
		myArrayHolder = new GenericHolder<MyClass[]>(new MyClass[0]);
		anArrayHolder = myArrayHolder;
		ArrayList myClassList = new ArrayList();
		myClassList.add(new MyClass("aaaa"));
		myClassList.add(new MyClass("bbbb"));
		HolderUtils.stuffHolderValue(anArrayHolder, myClassList);
		myArrayValueResult = myArrayHolder.value;
		assertTrue("aaaa".equals(myArrayValueResult[0].name));
		assertTrue("bbbb".equals( myArrayValueResult[1].name));
		

		// stuff null to my holder of primitive type
		IntHolder ih = new IntHolder();
		HolderUtils.stuffHolderValue(ih, null);
		assertTrue(0 == ih.value);
		

	}
}



