/*
 * GoogleControlTestCase.java
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
 * Original author: Jonathan Colwell
 */
package org.controlhaus.webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.beehive.controls.api.bean.Control;
import org.apache.beehive.controls.api.context.ControlContainerContext;
import org.apache.beehive.controls.api.context.ControlThreadContext;


/*******************************************************************************
 * 
 *
 * @author Jonathan Colwell
 */
public class GoogleControlTestCase extends ServiceControlTestCase {

    @Control public stubby.GoogleClient mGoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooogle;

    private String mKey;

    public void setUp() throws Exception {
        super.setUp();
        InputStream keyStream = getClass().getResourceAsStream("google.key");
        if (keyStream != null) {
            BufferedReader br = new BufferedReader
                (new InputStreamReader(keyStream, "UTF-8"));

            mKey = br.readLine();
            br.close();
        }
    }
        

    public void testSpelling() throws Exception {
        String thermonukular = mGoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooogle
            .suggestSpelling(mKey, "thermonukular");
        assertEquals("thermonuclear", thermonukular);
    }


    public void testCache() throws Exception {
        byte[] pageData = mGoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooogle.
            getCachedPage(mKey, "http://www.bea.com");
        assertTrue(pageData.length > 0);
        assertTrue(new String(pageData).indexOf("BEA") > 0);
    }

    public void testSearch() throws Exception {
        
        GoogleSearch.GoogleSearchResult results = mGoooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooogle
            .search(mKey,
                    "BEA",
                    0,
                    5,
                    false,
                    null,
                    false,
                    null,
                    null,
                    null);

        assertTrue(results.getSearchTime() > 0);
        assertTrue(results.getEstimatedTotalResultsCount() > 0x10000);

        for (GoogleSearch.ResultElement re :results
                 .getResultElements()) {
            assertNotNull(re.getTitle());
            assertNotNull(re.getURL());
            assertNotNull(re.getSummary());
        }
    }
}
