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

import com.swabunga.spell.engine.SpellDictionary;
import com.swabunga.spell.engine.SpellDictionaryHashMap;
import com.swabunga.spell.engine.Word;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
import com.swabunga.spell.event.SpellChecker;
import com.swabunga.spell.event.StringWordTokenizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SpellCheckImpl implements SpellCheck {
    private SpellCheckResources resources = new SpellCheckResources.SpellCheckResourcesImpl();

    public SpellingError[] checkSpelling(List words) throws IOException {
        Contract.enforce(words != null, "words");
        Contract.enforce(resources != null, "resources");

        File dictionaryFile = resources.getDictionary();
        Contract.enforce(dictionaryFile != null,"resources");
        File phoneticFile = resources.getPhonetic();
        Contract.enforce(phoneticFile != null,"resources");

        SpellDictionary dictionary = new SpellDictionaryHashMap(dictionaryFile,phoneticFile);
        SpellChecker spellChecker = new SpellChecker(dictionary);

        CustomSpellCheckListener spellCheckListener = new CustomSpellCheckListener();
        spellChecker.addSpellCheckListener(spellCheckListener);

        Collection errors = new ArrayList();
        int index = 1;
        for (Iterator i = words.iterator() ; i.hasNext() ; ){
            spellCheckListener.clearSuggestions();

            String word = (String) i.next();
            spellChecker.checkSpelling(new StringWordTokenizer(word));

            if(spellCheckListener.getSuggestions() != null && !spellCheckListener.getSuggestions().isEmpty()){
                SpellingError.SpellingErrorImpl spellingError = new SpellingError.SpellingErrorImpl();
                spellingError.setErrorIndex(index);
                spellingError.setOrignalWord(word);
                String[] suggestions = (String[]) spellCheckListener.getSuggestions().toArray(new String[0]);
                spellingError.setSuggestions(suggestions);
                errors.add(spellingError);
            }

            index++;
        }

        return (SpellingError[]) errors.toArray(new SpellingError[0]);
    }

    private static final class CustomSpellCheckListener implements SpellCheckListener{
        private List suggestions ;

        public void spellingError(SpellCheckEvent event) {
            suggestions = new ArrayList();
            if (event != null) {
                List wordOptions = event.getSuggestions();
                for (Iterator i = wordOptions.iterator() ; i.hasNext() ; ){
                    suggestions.add(((Word)i.next()).getWord());
                }
            }
        }

        public List getSuggestions() {
            return suggestions;
        }

        public void clearSuggestions(){
            if (this.suggestions != null) {
                this.suggestions.clear();
            }
        }
    }
}
