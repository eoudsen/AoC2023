package days.day7;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card2 implements Comparable<Card2> {

    private static final Map<Character, Integer> cardMapping = new HashMap<>(){{
        put('J', 1);
        put('2', 2);
        put('3', 3);
        put('4', 4);
        put('5', 5);
        put('6', 6);
        put('7', 7);
        put('8', 8);
        put('9', 9);
        put('T', 10);
        put('Q', 12);
        put('K', 13);
        put('A', 14);
    }};

    private final int cardValue;

    public Card2(final char card) {
        this.cardValue = cardMapping.get(card);
    }

    @Override
    public int compareTo(Card2 o) {
        return this.cardValue - o.cardValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card2 card = (Card2) o;
        return cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue);
    }
}
