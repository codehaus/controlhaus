/*
 * AxisTypeGeneratorTask.java
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

import java.io.File;

import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;

/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class AxisTypeGeneratorTask extends Task {

    File mWSDL, mOutDir;
     
    public void setWSDLDir(File wsdl) {
        mWSDL = wsdl;
    }

    public void setOutputDir(File outputDir) {
        mOutDir = outputDir;
    }

    public void execute() throws BuildException {
        try {
            AntClassLoader acl = (AntClassLoader)getClass().getClassLoader();
            System.out.println(acl.getClasspath());
            if (mOutDir != null && mWSDL != null && mWSDL.isDirectory()) {
                AxisTypeGenerator atg = new AxisTypeGenerator();
                for (File f : mWSDL.listFiles(new WSDLFilter())) {
                    atg.generateTypes(f.getPath(),
                                      mOutDir.getPath());
                }
            }
            else {
                throw new BuildException("Both a valid wsdl directory and an output directory must be provided.");
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
            if (e instanceof BuildException) {
                throw (BuildException)e;
            }
            else {
                throw new BuildException(e.toString(), e);
            }
        }
    }
}
