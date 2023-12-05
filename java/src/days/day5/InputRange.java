package days.day5;

import java.util.TreeSet;

public record InputRange(Long sourceStart, Long destinationStart, Long range) {

    public void map(final TreeSet<Range> in, final TreeSet<Range> unmapped, final TreeSet<Range> mapped) {
        for (final Range r : in) {
            if (sourceStart + range - 1 < r.start || sourceStart > r.end) {
                unmapped.add(r);
                continue;
            }
            if (r.start < sourceStart) {
                unmapped.add(new Range(r.start, sourceStart - 1));
            }
            if (r.end > sourceStart + range - 1) {
                unmapped.add(new Range(sourceStart + range, r.end));
            }

            final long rStart = destinationStart + (Math.max(sourceStart, r.start) - sourceStart);
            final long rEnd = destinationStart + (Math.min(sourceStart + range - 1, r.end) - sourceStart);
            mapped.add(new Range(rStart, rEnd));
        }
    }
}
