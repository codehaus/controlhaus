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


package org.controlhaus.misc.spellcheck;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public interface SpellCheckResources {
    public File getDictionary() throws IOException;

    public File getPhonetic() throws IOException;

    public class SpellCheckResourcesImpl implements SpellCheckResources {
        private static final String dictionaryFileName = "english.0";
        private static final String phoneticFileName = "phonet.en";

        public File getDictionary() throws IOException {
            InputStream dictInputStream = this.getClass().getClassLoader().getResourceAsStream(dictionaryFileName);
            if(dictInputStream == null) throw new RuntimeException("Could not find resource : "+dictionaryFileName);

            File dictionayFile = File.createTempFile("english","0");
            FileOutputStream dictionaryOutputStream = new FileOutputStream(dictionayFile);
            int byteValue = dictInputStream.read();
            while (byteValue != -1){
                dictionaryOutputStream.write(byteValue);
                byteValue = dictInputStream.read();
            }

            return dictionayFile;
        }

        public File getPhonetic() throws IOException {
            InputStream phoneInputStream = this.getClass().getClassLoader().getResourceAsStream(phoneticFileName);
            if(phoneInputStream == null) throw new RuntimeException("Could not find resource : "+phoneticFileName);

            File phoneticFile = File.createTempFile("phonet","en");
            FileOutputStream phoneticOutputStream = new FileOutputStream(phoneticFile);
            int byteValue = phoneInputStream.read();
            while (byteValue != -1){
                phoneticOutputStream.write(byteValue);
                byteValue = phoneInputStream.read();
            }

            return phoneticFile;
        }
    }
}
