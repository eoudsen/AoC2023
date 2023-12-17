package days.day17;

import java.util.*;

public class Searcher {

    public static State search(final List<State> starts) {
        final TreeMap<StateSort, State> queue = new TreeMap<>();
        long sort_idx = 0;
        final HashSet<String> visited = new HashSet<>();

        for (final State s : starts) {
            queue.put(new StateSort(s.getCost(), s.getLean(), sort_idx), s);
            sort_idx++;
        }

        while (!queue.isEmpty()) {
            final State s = queue.pollFirstEntry().getValue();
            if (s.isTerm()) {
                return s;
            }

            final String hash = s.getHash();
            if (!visited.contains(hash)) {
                visited.add(hash);

                for(final State n : s.next()) {
                    queue.put(new StateSort(n.getCost() + n.getEstimate(), n.getLean(), sort_idx), n);
                    sort_idx++;
                }
            }
        }
        return null;
    }

    public static class StateSort implements Comparable<StateSort> {
        private final double cost;
        private final double lean;
        private final long idx;

        public StateSort(final double cost, final double lean, final long idx) {
            this.cost = cost;
            this.lean = lean;
            this.idx = idx;
        }

        public int compareTo(final StateSort ss) {
            if (cost < ss.cost) {
                return -1;
            }
            if (cost > ss.cost) {
                return 1;
            }
            if (lean < ss.lean) {
                return -1;
            }
            if (lean > ss.lean) {
                return 1;
            }
            if (idx < ss.idx) {
                return -1;
            }
            if (idx > ss.idx) {
                return 1;
            }
            return 0;
        }
    }
}
