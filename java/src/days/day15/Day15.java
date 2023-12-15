package days.day15;

import util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day15 {

    public static Integer part1(final List<String> input) {
        return Arrays.stream(input.get(0).split(","))
                .map(StepCounter::new)
                .map(StepCounter::countStep)
                .reduce(0, Integer::sum);
    }

    public static Long part2(final List<String> input) {
        final Box[] boxes = new Box[256];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box(i);
        }
        for (final String step : input.get(0).split(",")) {
            final StepCalculatorResult stepCalculatorResult = calculateStepResult(step);
            int box = new StepCounter(stepCalculatorResult.label()).countStep();
            boxes[box].performAction(stepCalculatorResult);
        }

        return Arrays.stream(boxes)
                .map(Box::calculateFocalStrength)
                .reduce(0L, Long::sum);
    }

    private static StepCalculatorResult calculateStepResult(final String step) {
        String label;
        if (step.contains("-")) {
            label = step.split("-")[0];
            return new StepCalculatorResult(Action.DASH, label, 0);
        }
        else {
            label = step.split("=")[0];
            int focalLength = Integer.parseInt(step.split("=")[1]);
            return new StepCalculatorResult(Action.EQUALS, label, focalLength);
        }
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(15);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
