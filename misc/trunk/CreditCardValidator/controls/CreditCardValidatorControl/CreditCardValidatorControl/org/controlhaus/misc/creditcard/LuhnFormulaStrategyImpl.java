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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LuhnFormulaStrategyImpl implements LuhnFormulaStrategy {
    private final CreditCardValidator.NumberTrimmer trimmer = new CreditCardValidatorImpl.NumberTrimmerImpl();
    private final LuhnFormulaStrategy.NumberParser parser = new NumberParserImpl(trimmer);
    private final LuhnFormulaStrategy.NumberCollectionMultiplier multiplier = new NumberCollectionMultiplierImpl();
    private final LuhnFormulaStrategy.NumberCollectionAdder adder = new NumberCollectionAdderImpl();
    private final int DIVIDER = 10;

    public boolean isValid(String number) {
        if (number == null || number.equals("")) {
            throw new IllegalArgumentException("number is not isValid");
        }

        boolean result = false;

        String numb = trimmer.trim(number);

        Collection nosToDouble = new ArrayList();
        Collection remainingNos = new ArrayList();
        parser.parse(numb, nosToDouble, remainingNos);

        Collection doubledNos = multiplier.multiplyAll(nosToDouble, 2);

        int addition1 = adder.addEveryDigit(doubledNos);
        int addition2 = adder.addEveryDigit(remainingNos);

        int mod = (addition1 + addition2) % DIVIDER;

        if (mod == 0) {
            result = true;
        }

        return result;
    }

    public static final class NumberParserImpl implements LuhnFormulaStrategy.NumberParser {
        private CreditCardValidator.NumberTrimmer trimmer;

        public NumberParserImpl(CreditCardValidator.NumberTrimmer trimmer) {
            this.trimmer = trimmer;
        }

        public void parse(String number, Collection nosToDouble, Collection remainingNos) {
            String numb = trimmer.trim(number);

            boolean oddStartPosition = true;
            for (int x = numb.length() - 1; x >= 0; x--) {
                char c = numb.charAt(x);
                if (!Character.isDigit(c)) {
                    throw new IllegalArgumentException("number consists of character, other than digits");
                }

                Integer no = new Integer(Character.getNumericValue(c));

                if (oddStartPosition) {
                    remainingNos.add(no);
                } else {
                    nosToDouble.add(no);
                }

                oddStartPosition = !oddStartPosition;
            }
        }
    }

    public static final class NumberCollectionMultiplierImpl implements LuhnFormulaStrategy.NumberCollectionMultiplier {
        public Collection multiplyAll(Collection numbers, int multiplyFactor) {
            Collection result = new ArrayList();
            for (Iterator i = numbers.iterator(); i.hasNext();) {
                Number number = (Number) i.next();
                result.add(new Integer(number.intValue() * multiplyFactor));
            }
            return result;
        }
    }

    public static final class NumberCollectionAdderImpl implements LuhnFormulaStrategy.NumberCollectionAdder {
        public int addEveryDigit(Collection numbers) {
            if (numbers == null) {
                throw new IllegalArgumentException("numbers cannot be null");
            }
            int result = 0;

            Number numb;
            String number;
            char[] digits;
            for (Iterator i = numbers.iterator(); i.hasNext();) {
                numb = (Number) i.next();
                number = numb.toString();
                digits = number.toCharArray();
                char digit;
                for (int y = 0; y < digits.length; y++) {
                    digit = digits[y];
                    result = result + Character.getNumericValue(digit);
                }
            }
            return result;
        }

    }
}
