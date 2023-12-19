package days.day19;

import java.util.function.BiFunction;

public class Rule {

    private final char charToCheck;
    private final String original;
    private int checkValue;
    private String nextLabel;
    private BiFunction<Integer, Integer, Boolean> checkFunction;

    public Rule(final String rule) {
        this.original = rule;
        this.charToCheck = rule.charAt(0);
        if (rule.length() > 3) {
            if (rule.charAt(1) == '>') {
                this.checkFunction = this::greaterThan;
            } else {
                this.checkFunction = this::lessThan;
            }
            this.checkValue = Integer.parseInt(rule.substring(2).split(":")[0]);
            this.nextLabel = rule.substring(2).split(":")[1];
        }
    }

    public BiFunction<Integer, Integer, Boolean> getCheckFunction() {
        return this.checkFunction;
    }

    public String getOriginal() {
        return this.original;
    }

    public char getCharToCheck() {
        return this.charToCheck;
    }

    public int getCheckValue() {
        return this.checkValue;
    }

    public String getNextLabel() {
        return this.nextLabel;
    }

    private Boolean greaterThan(final Integer partValue, final Integer checkValue) {
        return partValue > checkValue;
    }

    private Boolean lessThan(final Integer partValue, final Integer checkValue) {
        return partValue < checkValue;
    }
}
