package days.day7;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card implements Comparable<Card> {

    private static final Map<Character, Integer> cardMapping = new HashMap<>(){{
        put('2', 2);
        put('3', 3);
        put('4', 4);
        put('5', 5);
        put('6', 6);
        put('7', 7);
        put('8', 8);
        put('9', 9);
        put('T', 10);
        put('J', 11);
        put('Q', 12);
        put('K', 13);
        put('A', 14);
    }};

    private final int cardValue;

    public Card(final char card) {
        this.cardValue = cardMapping.get(card);
    }

    @Override
    public int compareTo(Card o) {
        return this.cardValue - o.cardValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardValue == card.cardValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue);
    }
}
