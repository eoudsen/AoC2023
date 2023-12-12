package days.day12;

import java.util.ArrayList;
import java.util.List;

public class SpringList {

    private final List<Character> original;
    private final List<Integer> continuous;

    public SpringList(final String input) {
        this.original = new ArrayList<>();
        for (char c : input.split(" ")[0].toCharArray()) {
            this.original.add(c);
        }
        this.continuous = new ArrayList<>();
        for (final String s : input.split(" ")[1].split(",")) {
            this.continuous.add(Integer.parseInt(s));
        }
    }

    public long calculateArrangements() {
        final Integer[] unknownList = this.determineList('?');
        final Integer[] brokenList = this.determineList('#');

        final Integer totalBroken = this.continuous.stream().reduce(0, Integer::sum);
        if (totalBroken - brokenList.length == 0) {
            return 1L;
        }

        return Combinations.getCombinations(unknownList, totalBroken - brokenList.length)
                .stream()
                .map(this::createPermutation)
                .filter(this::isValidSpringString)
                .count();
    }

    private String createPermutation(final int[] permutation) {
        List<Character> permutationCharacters = new ArrayList<>(this.original.stream().toList());
        for (int i : permutation) {
            permutationCharacters.set(i, '#');
        }
        for (int i = 0; i < permutationCharacters.size(); i++) {
            if (permutationCharacters.get(i) == '?') {
                permutationCharacters.set(i, '.');
            }
        }
        return permutationCharacters.stream()
                .map(Object::toString)
                .reduce((acc, e) -> acc  + e)
                .get();
    }

    private Integer[] determineList(final Character c) {
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < original.size(); i++) {
            if (original.get(i) == c) {
                list.add(i);
            }
        }
        return list.toArray(Integer[]::new);
    }

    private boolean isValidSpringString(final String springString) {
        final List<Integer> counts = new ArrayList<>();
        boolean inBroken = false;
        int currentCount = 0;
        char[] chars = springString.toCharArray();
        for (int i = 0; i < springString.length(); i++) {
            if (chars[i] == '#') {
                if (!inBroken) {
                    inBroken = true;
                }
                currentCount++;
            }
            else if (chars[i] == '.') {
                if (!inBroken) {
                    continue;
                }
                counts.add(currentCount);
                currentCount = 0;
                inBroken = false;
            }
        }
        if (inBroken) {
            counts.add(currentCount);
        }
        return counts.equals(this.continuous);
    }
}
