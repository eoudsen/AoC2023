package days.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpringList2 {

    private final List<Character> original;
    private final List<Integer> continuous;
    private int currentGroupSize = 0;
    private int originalId = 0;
    private int continuousId = 0;
    private final HashMap<SpringList2, Long> cache;

    public SpringList2(final String input) {
        this.original = new ArrayList<>();
        for (char c : input.split(" ")[0].toCharArray()) {
            this.original.add(c);
        }
        this.original.add('.');
        this.continuous = new ArrayList<>();
        for (final String s : input.split(" ")[1].split(",")) {
            this.continuous.add(Integer.parseInt(s));
        }
        this.cache = new HashMap<>();
    }

    private SpringList2(final List<Character> original, final List<Integer> continuous, final int currentGroup, final int originalId, final int continuousId, final HashMap<SpringList2, Long> cache) {
        this.original = original;
        this.continuous = continuous;
        this.originalId = originalId;
        this.continuousId = continuousId;
        this.currentGroupSize = currentGroup;
        this.cache = cache;
    }

    public long calculateArrangements() {
        if (cache.containsKey(this)) {
            return cache.get(this);
        }
        long returnValue = -1;
        if (endOfSpringList()) {
            if (currentGroupSize == 0 && continuousId == continuous.size()) {
                returnValue = 1;
            }
            else {
                returnValue = 0;
            }
        }
        else {
            switch (this.original.get(this.originalId)) {
                case '.' -> returnValue = stepFunctionalSpring();
                case '#' -> returnValue = stepBrokenSpring();
                case '?' -> returnValue = stepBrokenSpring() + stepFunctionalSpring();
            }

        }
        cache.put(this, returnValue);
        return returnValue;
    }

    private long stepBrokenSpring() {
        if (tooManyInGroup()) {
            return 0;
        }
        return new SpringList2(original, continuous, currentGroupSize + 1, originalId + 1, continuousId, cache).calculateArrangements();
    }

    private long stepFunctionalSpring() {
        if (currentGroupSize > 0) {
            if (groupSizeMatches()) {
                return new SpringList2(original, continuous, 0, originalId + 1, continuousId + 1, cache).calculateArrangements();
            }
            return 0;
        }
        return new SpringList2(original, continuous, 0, originalId + 1, continuousId, cache).calculateArrangements();
    }

    private boolean groupSizeMatches() {
        return continuous.get(continuousId) == currentGroupSize;
    }

    private boolean endOfSpringList() {
        return this.originalId == this.original.size();
    }

    private boolean tooManyInGroup() {
        return continuousId == continuous.size() || currentGroupSize >= continuous.get(continuousId);
    }

    @Override
    public int hashCode() {
        return (((31 * originalId) + continuousId) * 31) + currentGroupSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SpringList2 springList2)) {
            return false;
        }

        if (originalId != springList2.originalId) {
            return false;
        }
        if (continuousId != springList2.continuousId) {
            return false;
        }
        return currentGroupSize == springList2.currentGroupSize;
    }
}
