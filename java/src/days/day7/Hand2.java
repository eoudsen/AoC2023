package days.day7;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand2 implements Comparable<Hand2> {

    private final Long bid;
    private final List<Card2> cards = new ArrayList<>();

    public Hand2(final String cards, final long bid) {
        this.bid = bid;
        for (final char card : cards.toCharArray()) {
            this.cards.add(new Card2(card));
        }
    }

    public long getBid() {
        return this.bid;
    }

    private int determineTypeOfHand() {
        Map<Card2, Integer> occurenceMap = new HashMap<>();
        for (final Card2 card : this.cards) {
            if (occurenceMap.containsKey(card)) {
                occurenceMap.put(card, occurenceMap.get(card) + 1);
            }
            else {
                occurenceMap.put(card, 1);
            }
        }

        int highest = 0;
        for (final Map.Entry<Card2, Integer> entry : occurenceMap.entrySet()) {
            if (entry.getValue() > highest) {
                highest = entry.getValue();
            }
        }
        if (!occurenceMap.containsKey(new Card2('J'))) {
            if (highest == 5) {
                return 6;
            }
            if (highest == 4) {
                return 5;
            }
            if (highest == 3) {
                for (final Map.Entry<Card2, Integer> entry : occurenceMap.entrySet()) {
                    if (entry.getValue() == 2) {
                        return 4;
                    }
                }
                return 3;
            }
            if (highest == 2) {
                int occurences = 0;
                for (final Map.Entry<Card2, Integer> entry : occurenceMap.entrySet()) {
                    if (entry.getValue() == 2) {
                        occurences += 1;
                    }
                }
                if (occurences == 2) {
                    return 2;
                }
                return 1;
            }
            return 0;
        }
        else {
            if (highest == 5 || highest == 4) {
                return 6; // 5 of a kind of type J
            }
            if (highest == 3) {
                int occurences = 0;
                for (final Map.Entry<Card2, Integer> entry : occurenceMap.entrySet()) {
                    if (entry.getValue() == 2) {
                        occurences += 1;
                    }
                }
                if (occurenceMap.get(new Card2('J')).equals(3) && occurences == 1) {
                    return 6; // 5 of a kind
                }
                else if (occurenceMap.get(new Card2('J')).equals(3)) {
                    return 5; // 4 of a kind
                }
                else if (occurenceMap.get(new Card2('J')).equals(2)) {
                    return 6; // 5 of a kind
                }
                return 5; // 4 of a kind
            }
            if (highest == 2) {
                int occurences = 0;
                for (final Map.Entry<Card2, Integer> entry : occurenceMap.entrySet()) {
                    if (entry.getValue() == 2) {
                        occurences += 1;
                    }
                }
                if (occurenceMap.get(new Card2('J')).equals(2) && occurences == 2) {
                    return 5; // 4 of a kind
                }
                else if (occurenceMap.get(new Card2('J')).equals(2) && occurences == 1) {
                    return 3; // Three pair
                }
                else if (occurences == 2) {
                    return 4; // Full House
                }
                return 3;
            }
            return 1;
        }
    }

    @Override
    public int compareTo(Hand2 o) {
        if (this.determineTypeOfHand() - o.determineTypeOfHand() != 0) {
            return this.determineTypeOfHand() - o.determineTypeOfHand();
        }
        for (int i = 0; i < this.cards.size(); i++) {
            if (this.cards.get(i).compareTo(o.cards.get(i)) != 0) {
                return this.cards.get(i).compareTo(o.cards.get(i));
            }
        }
        return 0;
    }
}
