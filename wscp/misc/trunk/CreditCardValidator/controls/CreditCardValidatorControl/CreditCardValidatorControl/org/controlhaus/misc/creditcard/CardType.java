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

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;

public class CardType {
    private final String id;
    private final String value;
    private final Combination[] combinations;
    private static Collection allCardTypes = new ArrayList();

    private CardType(String id, String value ,Combination[] combinations) {
        this.id = id;
        this.value = value;
        this.combinations = combinations;
        allCardTypes.add(this);
    }

    private static final Combination masterCardComb = new Combination(new String[]{"51", "52", "53", "54", "55"}, new String[]{"16"});
    public static final CardType MASTER_CARD = new CardType("MasterCard", "Master Card" ,new Combination[]{masterCardComb});

    private static final Combination visaComb = new Combination(new String[]{"4"}, new String[]{"13", "16"});
    public static final CardType VISA = new CardType("Visa", "Visa" , new Combination[]{visaComb});

    private static final Combination americanExpressComb = new Combination(new String[]{"34", "37"}, new String[]{"15"});
    public static final CardType AMERICAN_EXPRESS = new CardType("AmericanExpress", "American Express",new Combination[]{americanExpressComb});

    private static final Combination dinersClubComb = new Combination(new String[]{"300", "305", "36", "38"}, new String[]{"14"});
    public static final CardType DINERS_CLUB = new CardType("DinersClub", "Diners Club",new Combination[]{dinersClubComb});

    private static final Combination carteBlancheComb = new Combination(new String[]{"300", "305", "36", "38"}, new String[]{"14"});
    public static final CardType CARTE_BLANCHE = new CardType("CarteBlanche", "Carte Blanche", new Combination[]{carteBlancheComb});

    private static final Combination enrouteComb = new Combination(new String[]{"2014", "2149"}, new String[]{"15"});
    public static final CardType ENROUTE = new CardType("Enroute", "Enroute",new Combination[]{enrouteComb});

    private static final Combination discoverComb = new Combination(new String[]{"6011"}, new String[]{"16"});
    public static final CardType DISCOVER = new CardType("Discover", "Discover",new Combination[]{discoverComb});

    private static final Combination jcbComb1 = new Combination(new String[]{"3"}, new String[]{"16"});
    private static final Combination jcbComb2 = new Combination(new String[]{"2131", "1800"}, new String[]{"15"});
    public static final CardType JCB = new CardType("Jcb", "Jcb", new Combination[]{jcbComb1, jcbComb2});

    public Collection getCombinations() {
        return Arrays.asList(combinations);
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public static CardType forId (String id){
        for (Iterator i = allCardTypes.iterator() ; i.hasNext() ; ){
            CardType cardType = (CardType) i.next();
            if (cardType.getId().equals(id)){
                return cardType;
            }
        }

        return null;
    }

    public static Collection getAll(){
        return allCardTypes;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CardType)) return false;

        final CardType cardType = (CardType) o;

        if (id != null ? !id.equals(cardType.id) : cardType.id != null) return false;

        return true;
    }

    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    protected static final class Combination {
        private final String[] prefixes;
        private final String[] lengths;

        private Combination(String[] prefixes, String[] lengths) {
            this.prefixes = prefixes;
            this.lengths = lengths;
        }

        protected Collection getPrefixes() {
            return Arrays.asList(prefixes);
        }

        protected Collection getLengths() {
            return Arrays.asList(lengths);
        }
    }
}
