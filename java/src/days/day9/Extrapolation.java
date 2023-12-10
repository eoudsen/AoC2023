package days.day9;

import java.util.ArrayList;
import java.util.List;

public class Extrapolation {

    private final List<Long> rightMostValues = new ArrayList<>();
    private List<Long> currentList;

    public Extrapolation(final List<Long> input) {
        this.currentList = List.copyOf(input);
        this.rightMostValues.add(input.get(input.size() - 1));
    }

    public Long calculate() {
        while (true) {
            final List<Long> newList = new ArrayList<>();
            for (int i = 0; i < this.currentList.size() - 1; i++) {
                newList.add(this.currentList.get(i + 1) - this.currentList.get(i ));
            }
            if (newList.stream().allMatch(value -> value == 0)) {
                return this.rightMostValues.stream().reduce(0L, Long::sum);
            }
            this.rightMostValues.add(newList.get(newList.size() - 1));
            this.currentList = List.copyOf(newList);
        }
    }
}
