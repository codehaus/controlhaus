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


package org.controlhaus.net.http.pojo;

import org.controlhaus.net.http.pojo.DataAdapter;

import java.util.Map;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;

public class MapDataAdapter implements DataAdapter {
    private final Map map;

    public MapDataAdapter(Map map) {
        this.map = map;
    }

    public void foreachDataItem(DataAdapter.ItemCommand command) throws UnsupportedEncodingException {
        for(Iterator i=map.keySet().iterator();i.hasNext();) {
            Object key = i.next();
            Object value = map.get(key);
            command.onDataItem(key, value);
        }
    }
}
