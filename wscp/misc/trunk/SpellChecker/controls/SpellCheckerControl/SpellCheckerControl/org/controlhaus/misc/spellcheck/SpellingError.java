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

public interface SpellingError {
    public int getErrorIndex();

    public String getOrignalWord();

    public String[] getSuggestions();

    public String suggestionsAsString();

    static final class SpellingErrorImpl implements SpellingError{
        private int errorIndex;
        private String orignalWord ;
        private String[] suggestions;

        public int getErrorIndex() {
            return errorIndex;
        }

        public void setErrorIndex(int errorIndex) {
            this.errorIndex = errorIndex;
        }

        public String getOrignalWord() {
            return orignalWord;
        }

        public void setOrignalWord(String orignalWord) {
            this.orignalWord = orignalWord;
        }

        public String[] getSuggestions() {
            return suggestions;
        }

        public void setSuggestions(String[] suggestions) {
            this.suggestions = suggestions;
        }

        public String suggestionsAsString() {
            StringBuffer suggest = new StringBuffer();
            if (this.suggestions != null) {
                for (int x = 0 ; x < this.suggestions.length ; x++){
					if(x != 0){
						suggest.append(" , ");
					}
                    suggest.append(this.suggestions[x]);
                }
            }
            return suggest.toString();
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer("SpellingError : ");
            if(this.errorIndex != 0){
                buffer.append("\n Index  : "+this.errorIndex);
            }
            if(this.orignalWord != null){
                buffer.append("\n Orignal Word  : "+this.orignalWord);
            }

            buffer.append("\n Suggestions  : "+suggestionsAsString());

            return buffer.toString();
        }
    }
}
