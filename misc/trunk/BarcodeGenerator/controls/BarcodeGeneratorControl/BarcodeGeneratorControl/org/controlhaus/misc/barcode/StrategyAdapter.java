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

import java.util.HashMap;
import java.util.Map;
import jbarcodebean.Codabar;
import jbarcodebean.Code128;
import jbarcodebean.Code39;
import jbarcodebean.ExtendedCode39;
import jbarcodebean.Interleaved25;
import jbarcodebean.MSI;

public class StrategyAdapter 
{ 
    private static final Map strategyMap = new HashMap();
    static {
        strategyMap.put(BarcodeStrategy.CODABAR, new Codabar());
        strategyMap.put(BarcodeStrategy.CODE_128, new Code128());
        strategyMap.put(BarcodeStrategy.CODE_39, new Code39());
        strategyMap.put(BarcodeStrategy.EXTENDED_CODE_39, new ExtendedCode39());
        strategyMap.put(BarcodeStrategy.INTERLEAVED_25, new Interleaved25());
        strategyMap.put(BarcodeStrategy.MSI, new MSI());        
    }
    
    public static final jbarcodebean.BarcodeStrategy adapt(BarcodeStrategy strategy) {
        return (jbarcodebean.BarcodeStrategy) strategyMap.get(strategy);
    }
} 
