package days.day4;

public class Card {

    private final String game;
    private int occurenses;

    public Card(final String game) {
        this.game = game;
        this.occurenses = 1;
    }

    public String getGame() {
        return this.game;
    }

    public int getOccurenses() {
        return this.occurenses;
    }

    public void add(final int number) {
        this.occurenses += number;
    }
}
