/*
 * AxisTypeGenerator.java
 * 
 * Copyright 2004 BEA Systems, Inc.
 * 
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
 * Original author: Jonathan Colwell
 */
package org.controlhaus.webservice.util;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.wsdl.Message;
import javax.wsdl.PortType;
import javax.wsdl.Service;

import org.apache.axis.wsdl.gen.Generator;
import org.apache.axis.wsdl.gen.NoopGenerator;
import org.apache.axis.wsdl.symbolTable.SymbolTable;
import org.apache.axis.wsdl.toJava.Emitter;
import org.apache.axis.wsdl.toJava.JavaGeneratorFactory;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class AxisTypeGenerator extends JavaGeneratorFactory {

    public AxisTypeGenerator() {
        super();
        Emitter e = new Emitter();
        setEmitter(e);
        emitter.setFactory(this);
    }

    public void generateTypes(String wsdl, String outputDir) throws Exception {
   
        emitter.setOutputDir(outputDir);
        emitter.run(wsdl);
    }
    
    public Generator getGenerator(Message message, SymbolTable symbolTable) {
        return new NoopGenerator();
    }

    public Generator getGenerator(PortType portType, SymbolTable symbolTable) {
        return new NoopGenerator();
    }

    public Generator getGenerator(Binding binding, SymbolTable symbolTable) {
        return new NoopGenerator();
    }

    public Generator getGenerator(Service service, SymbolTable symbolTable) {
        return new NoopGenerator();
    }

    public Generator getGenerator(Definition definition,
                                  SymbolTable symbolTable) {
        return new NoopGenerator();
    }
}
