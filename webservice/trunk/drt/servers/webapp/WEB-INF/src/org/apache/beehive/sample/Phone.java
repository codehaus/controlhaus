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

import java.io.Serializable;

public class Phone  implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private int areaCode;
    private java.lang.String exchange;
    private java.lang.String number;

 

    /**
     * @param areaCode
     * @param exchange
     * @param number
     */
    public Phone(int areaCode, java.lang.String exchange,
            java.lang.String number) {
        super();
        this.areaCode = areaCode;
        this.exchange = exchange;
        this.number = number;
    }
    /**
     * 
     */
    public Phone() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @return Returns the areaCode.
     */
    public int getAreaCode() {
        return areaCode;
    }
    /**
     * @param areaCode The areaCode to set.
     */
    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }
    /**
     * @return Returns the exchange.
     */
    public java.lang.String getExchange() {
        return exchange;
    }
    /**
     * @param exchange The exchange to set.
     */
    public void setExchange(java.lang.String exchange) {
        this.exchange = exchange;
    }
    /**
     * @return Returns the number.
     */
    public java.lang.String getNumber() {
        return number;
    }
    /**
     * @param number The number to set.
     */
    public void setNumber(java.lang.String number) {
        this.number = number;
    }
}
