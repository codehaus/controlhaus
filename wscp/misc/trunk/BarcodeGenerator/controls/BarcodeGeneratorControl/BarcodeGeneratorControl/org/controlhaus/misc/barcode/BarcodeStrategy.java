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


package org.controlhaus.misc.barcode; 

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BarcodeStrategy 
{ 
    private static final Map all = new HashMap();
    public static final BarcodeStrategy CODE_39 = new BarcodeStrategy("code39");
    public static final BarcodeStrategy EXTENDED_CODE_39 = new BarcodeStrategy("extendedcode39");
    public static final BarcodeStrategy CODABAR = new BarcodeStrategy("codabar");
    public static final BarcodeStrategy CODE_128 = new BarcodeStrategy("code128");
    public static final BarcodeStrategy INTERLEAVED_25 = new BarcodeStrategy("interleaved25");
    public static final BarcodeStrategy MSI = new BarcodeStrategy("msi");
        
    private final String code;
    

    private BarcodeStrategy(String code) {
        this.code = code.toUpperCase();
        all.put(this.code, this);
    }
    
    public static final BarcodeStrategy forCode(String code) {
        Contract.enforce(code != null && code.length() > 0, "code");
        return (BarcodeStrategy) all.get(code.toUpperCase());
    }
    
    public static final Collection getStrategyIds() {
        return all.keySet();
    }
} 
