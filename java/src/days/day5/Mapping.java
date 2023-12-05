package days.day5;

import java.util.*;

public class Mapping {

    final Set<InputRange> inputRanges = new HashSet<>();

    public void addInput(final String line) {
        final String[] number = line.split(" ");
        final Long sourceStart = Long.parseLong(number[1]);
        final Long destinationStart = Long.parseLong(number[0]);
        final Long range = Long.parseLong(number[2]);
        inputRanges.add(new InputRange(sourceStart, destinationStart, range));
    }

    public Long getMapping(final Long number) {
        for (final InputRange inputRange : inputRanges) {
            if (number >= inputRange.sourceStart() && number <= inputRange.sourceStart() + inputRange.range() - 1) {
                long diff = number - inputRange.sourceStart();
                return inputRange.destinationStart() + diff;
            }
        }
        return number;
    }

    public TreeSet<Range> getMapping(TreeSet<Range> ranges) {
        TreeSet<Range> mapped = new TreeSet<>();
        TreeSet<Range> unmapped = ranges;
        for (final InputRange inRange : this.inputRanges) {
            if (unmapped.isEmpty()) {
                break;
            }
            TreeSet<Range> step = new TreeSet<>();
            inRange.map(unmapped, step, mapped);
            unmapped = step;
        }
        mapped.addAll(unmapped);
        Range.merge(mapped);
        return mapped;
    }
}
