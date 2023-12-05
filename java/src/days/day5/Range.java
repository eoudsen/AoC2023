package days.day5;

import java.util.Iterator;
import java.util.TreeSet;

public class Range implements Comparable<Range> {
    long start;
    long end;

    public Range(final long start, final long end) {
        this.start = start;
        this.end = end;
    }

    public boolean overlapOrAdjacent(final Range bigger) {
        return bigger.contains(start) || bigger.contains(end + 1) || contains(bigger.start);
    }

    public boolean contains(final long l) {
        return l >= start && l <= end;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Range range)) {
            return false;
        }
        if (start != range.start) {
            return false;
        }
        return end == range.end;
    }

    @Override
    public int compareTo(final Range o) {
        long r = start - o.start;
        if (r == 0) {
            r = end - o.end;
        }
        if (r < 0) {
            return -1;
        }
        if (r > 0) {
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "(" + start + "-" + end + ")";
    }

    public static void merge(final TreeSet<Range> ranges) {
        Range prev = null;
        for (final Iterator<Range> it = ranges.iterator(); it.hasNext(); ) {
            final Range cur = it.next();
            if (prev == null) {
                prev = cur;
                continue;
            }

            if (prev.overlapOrAdjacent(cur)) {
                prev.start = Math.min(prev.start, cur.start);
                prev.end = Math.max(prev.end, cur.end);
                it.remove();
            } else {
                prev = cur;
            }
        }
    }
}
