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

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Searcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class LucenePojo {
    private final File index;

    public LucenePojo(File index) {
        this.index = index;
    }

    /**
     * Index a bunch of fields.  The supplied fields are considered to belong to the
     * same "Document", which means that if you do a search on one field and get a match,
     * all fields in that "Document" can be returned.
     */
    public void index(LuceneField[] fields) throws IOException {
        IndexWriter writer = new IndexWriter(index, new StandardAnalyzer(), !index.exists());
        final Document document = new Document();
        for (int i = 0; i < fields.length; i++) {
            document.add(Field.Text(fields[i].getName(), fields[i].getValue()));
        }
        writer.addDocument(document);
        writer.optimize();
        writer.close();
    }

    public LuceneHit[] search(String query, String searchFieldName, String[] returnFieldNames) throws IOException, ParseException {
        final Searcher searcher = new IndexSearcher(index.getAbsolutePath());
        final Analyzer analyzer = new StandardAnalyzer();
        final Query q = QueryParser.parse(query, searchFieldName, analyzer);
        Hits hits = searcher.search(q);
        Collection result = new ArrayList();
        for(int i=0;i<hits.length();i++) {
            Document doc = hits.doc(i);
            LuceneHit hit = new LuceneHit(hits.score(i));
            addFields(returnFieldNames, doc, hit);
            result.add(hit);
        }
        LuceneHit []array = new LuceneHit[result.size()];
        return (LuceneHit[]) result.toArray(array);
    }

    private void addFields(String[] returnFieldNames, Document doc, LuceneHit hit) {
        for(int j=0;j<returnFieldNames.length;j++) {
            Field field = doc.getField(returnFieldNames[j]);
            hit.addField(new LuceneField(field.name(), field.stringValue()));
        }
    }
}
