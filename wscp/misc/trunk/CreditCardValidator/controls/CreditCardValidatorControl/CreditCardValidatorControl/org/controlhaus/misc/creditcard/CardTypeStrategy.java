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


package org.controlhaus.misc.creditcard;

import java.util.Collection;
import java.util.Iterator;

public interface CardTypeStrategy {
    public boolean isValid(String number, CardType type);

    public static class CardTypeStrategyImpl implements CardTypeStrategy {
        public boolean isValid(String number, CardType type) {
            Contract.enforce(number != null, "number");
            Contract.enforce(!number.equals(""), "number");
            Contract.enforce(type != null, "type");

            boolean result = false;

            Collection combinations = type.getCombinations();
            CardType.Combination combination;
            Collection prefixes;
            Collection lengths;
            for (Iterator i = combinations.iterator(); i.hasNext();) {
                combination = (CardType.Combination) i.next();
                prefixes = combination.getPrefixes();
                lengths = combination.getLengths();

                String prefix;
                for (Iterator prefixIter = prefixes.iterator(); prefixIter.hasNext();) {
                    prefix = (String) prefixIter.next();
                    if (number.startsWith(prefix)) {
                        String length;
                        for (Iterator lengthIter = lengths.iterator(); lengthIter.hasNext();) {
                            length = (String) lengthIter.next();
                            int intLength = Integer.parseInt(length);
                            if (number.length() == intLength) {
                                result = true;
                            }
                        }
                    }
                }
            }

            return result;
        }
    }
}
