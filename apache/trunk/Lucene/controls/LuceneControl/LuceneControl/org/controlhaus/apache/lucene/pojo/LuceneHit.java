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


package org.controlhaus.apache.lucene.pojo;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class LuceneHit {
    private final float score;
    private final List fields;

    LuceneHit(float score) {
        this.score = score;
        fields = new ArrayList();
    }

    public LuceneField getField(int index) {
        return (LuceneField) fields.get(index);
    }

    public Map getFields() {
        Map result = new HashMap();
        for(Iterator i=fields.iterator();i.hasNext();) {
            LuceneField f = (LuceneField) i.next();
            result.put(f.getName(), f);
        }
        return result;
    }
    public float getScore() {
        return score;
    }

    void addField(LuceneField luceneField) {
        this.fields.add(luceneField);
    }
}
