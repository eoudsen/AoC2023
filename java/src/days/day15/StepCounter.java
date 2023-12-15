package days.day15;

public class StepCounter {

    private final String step;

    public StepCounter(final String step) {
        this.step = step;
    }

    public int countStep() {
        int value = 0;
        for (final char c : step.toCharArray()) {
            value += c;
            value *= 17;
            value %= 256;
        }
        return value;
    }
}
