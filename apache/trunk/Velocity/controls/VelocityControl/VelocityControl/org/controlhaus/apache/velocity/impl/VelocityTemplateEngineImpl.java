/*   Copyright 2004 BEA Systems, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */


package org.controlhaus.apache.velocity.impl;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.log.SimpleLog4JLogSystem;

import java.util.Map;
import java.io.StringWriter;

public class VelocityTemplateEngineImpl implements VelocityTemplateEngine{
    public String evaluate(String template, Map context) throws Exception {
        Contract.enforce(template != null , "template");
        Contract.enforce(! template.equals(""), "template");
        Contract.enforce(context != null , "context");

        StringWriter writer = new StringWriter();
        VelocityContext velocityContext = new VelocityContext(context);
        String logTag = "[Velocity Template Engine Control]";

        Velocity.init();
        boolean success = Velocity.evaluate(velocityContext , writer , logTag , template);

        if( !success ){
            throw new RuntimeException("There is some problem handling the request. Please see logs");
        }

        StringBuffer result = writer.getBuffer();

        if(result != null){
            return result.toString();
        }

        return null;
    }

    public class CustomLogging extends SimpleLog4JLogSystem{

        public void logVelocityMessage(int i, String s) {
            super.logVelocityMessage(i, s);
            System.out.println("Velocity Message : "+s);
        }
    }
}
