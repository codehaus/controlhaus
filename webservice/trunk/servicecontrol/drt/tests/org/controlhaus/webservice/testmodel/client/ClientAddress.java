
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
package org.controlhaus.webservice.testmodel.client;

import java.io.Serializable;

public class ClientAddress  implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int streetNum;
    private java.lang.String streetName;
    private java.lang.String city;
    private ClinetStateType state;
    private int zip;
    private ClientPhone phoneNumber;

 
 
    /**
     * @param streetNum
     * @param streetName
     * @param city
     * @param state
     * @param zip
     * @param phoneNumber
     */
    public ClientAddress(int streetNum, java.lang.String streetName,
            java.lang.String city, ClinetStateType state, int zip, ClientPhone phoneNumber) {
        super();
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
    }
    /**
     * 
     */
    public ClientAddress() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @return Returns the city.
     */
    public java.lang.String getCity() {
        return city;
    }
    /**
     * @param city The city to set.
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }
    /**
     * @return Returns the phoneNumber.
     */
    public ClientPhone getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * @param phoneNumber The phoneNumber to set.
     */
    public void setPhoneNumber(ClientPhone phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    /**
     * @return Returns the state.
     */
    public ClinetStateType getState() {
        return state;
    }
    /**
     * @param state The state to set.
     */
    public void setState(ClinetStateType state) {
        this.state = state;
    }
    /**
     * @return Returns the streetName.
     */
    public java.lang.String getStreetName() {
        return streetName;
    }
    /**
     * @param streetName The streetName to set.
     */
    public void setStreetName(java.lang.String streetName) {
        this.streetName = streetName;
    }
    /**
     * @return Returns the streetNum.
     */
    public int getStreetNum() {
        return streetNum;
    }
    /**
     * @param streetNum The streetNum to set.
     */
    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }
    /**
     * @return Returns the zip.
     */
    public int getZip() {
        return zip;
    }
    /**
     * @param zip The zip to set.
     */
    public void setZip(int zip) {
        this.zip = zip;
    }
}
