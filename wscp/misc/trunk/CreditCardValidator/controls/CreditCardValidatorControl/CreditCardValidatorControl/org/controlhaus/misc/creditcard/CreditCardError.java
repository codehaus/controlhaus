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

public class CreditCardError {
    private String message;

    public CreditCardError(String message) {
        this.message = message;
    }

    public static final CreditCardError NUMBER_INVALID = new CreditCardError("Credit Card Number is not a valid number");
    public static final CreditCardError EXPIRED = new CreditCardError("Credit Card has been expired");
    public static final CreditCardError INVALID_TYPE = new CreditCardError("Credit Card Number is not valid for the Card Type specified");

    public String getMessage() {
        return message;
    }

    public boolean equals(Object o) {
        if (this == o) return true;

        return false;
    }

    public int hashCode() {
        return (message != null ? message.hashCode() : 0);
    }
}
