package org.controlhaus.webservice.testmodel.server;

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

public class StateType implements Serializable
{
    private static final long serialVersionUID = 1L;
    String state;
   
    /**
     * 
     */
    public StateType() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @param state
     */
    public StateType(String state) {
        super();
        this.state = state;
    }
    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }

}