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

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CreditCardValidatorImpl implements CreditCardValidator {
    private final CreditCardValidator.NumberTrimmer trimmer = new NumberTrimmerImpl();
    private final LuhnFormulaStrategy luhnStrategy = new LuhnFormulaStrategyImpl();
    private final DateCheckStrategy dateStrategy = new DateCheckStrategy.DateCheckStrategyImpl();
    private final CardTypeStrategy typeStrategy = new CardTypeStrategy.CardTypeStrategyImpl();

    public Collection validate(String number, Date expiryDate, CardType type) {
        Contract.enforce(number != null, "number");
        Contract.enforce(!number.equals(""), "number");
        Contract.enforce(expiryDate != null, "expiryDate");
        Contract.enforce(type != null, "type");

        String creditCardNo = trimmer.trim(number);
        Collection errors = new ArrayList();
        boolean valid = luhnStrategy.isValid(creditCardNo);

        if (!valid) {
            errors.add(CreditCardError.NUMBER_INVALID);
        }

        valid = dateStrategy.isValid(expiryDate);
        if (!valid) {
            errors.add(CreditCardError.EXPIRED);
        }

        valid = typeStrategy.isValid(creditCardNo, type);
        if (!valid) {
            errors.add(CreditCardError.INVALID_TYPE);
        }

        return errors;
    }

    public static final class NumberTrimmerImpl implements CreditCardValidator.NumberTrimmer {
        public String trim(String number) {
            return StringUtils.deleteWhitespace(number);
        }
    }

}
