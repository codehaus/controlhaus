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
package org.controlhaus.webservice.utils;

import java.lang.reflect.Field;

import javax.xml.rpc.holders.Holder;

public class HolderUtils {

	public static void stuffHolderValue(Holder holder, Object value)
			throws NoSuchFieldException, IllegalAccessException {

		Field valueField = holder.getClass().getField("value");
		Object curValueInHolder = valueField.get(holder);
		Class classOfValueFieldInHolder;
		if (curValueInHolder == null) {
			classOfValueFieldInHolder = valueField.getType();
		} else {
			classOfValueFieldInHolder = curValueInHolder.getClass();
		}

		if (null == value) {
			setFieldInObject(valueField, holder, null);
			return;
		}
		if (classOfValueFieldInHolder.isAssignableFrom(value.getClass())) {
			setFieldInObject(valueField, holder, value);

		} else { // we need to convert the class to the holder type

			try {
				Object convertedValue = org.apache.axis.utils.JavaUtils
						.convert(value, classOfValueFieldInHolder);
				setFieldInObject(valueField, holder, convertedValue);
			} catch (IllegalArgumentException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}
		}

	}

	private static void setFieldInObject(Field valueField, Object destObject,
			Object value) throws IllegalArgumentException,
			IllegalAccessException {
		if (valueField.getType().isPrimitive()) {
			if (value == null)
				; // Don't need to set anything
			else
				valueField.set(destObject, value);
		} else {
			valueField.set(destObject, value);
		}
	}

}
