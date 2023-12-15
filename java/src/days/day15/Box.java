package days.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Box {

    private final List<Lens> lensList = new ArrayList<>();
    private final int index;

    public Box(final int i) {
        this.index = i;
    }

    public long calculateFocalStrength() {
        long result = 0L;
        for (int i = 0; i < this.lensList.size(); i++) {
            result += (long) (index + 1) * (i + 1) * lensList.get(i).getFocalStrength();
        }
        return result;
    }

    public void performAction(final StepCalculatorResult result) {
        if (result.action() == Action.DASH) {
            this.performDashAction(result);
        }
        else {
            this.performEqualsAction(result);
        }
    }

    private void performDashAction(final StepCalculatorResult result) {
        this.lensList.removeIf(lens -> lens.getLabel().equals(result.label()));
    }

    private void performEqualsAction(final StepCalculatorResult result) {
        Optional<Lens> first = this.lensList.stream().filter(lens -> lens.getLabel().equals(result.label())).findFirst();
        if (first.isPresent()) {
            int index = this.lensList.indexOf(first.get());
            this.lensList.set(index, new Lens(result.label(), result.focalLength()));
        }
        else {
            this.lensList.add(new Lens(result.label(), result.focalLength()));
        }
    }
}
