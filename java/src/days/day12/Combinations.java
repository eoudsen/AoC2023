package days.day12;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public static List<int[]> getCombinations(final Integer[] inputList, final int combinationSize) {
        final List<int[]> subsets = new ArrayList<>();
        int[] s = new int[combinationSize];

        if (combinationSize <= inputList.length) {
            for (int i = 0; (s[i] = i) < combinationSize - 1; i++);
            subsets.add(getSubset(inputList, s));
            for(;;) {
                int i;
                for (i = combinationSize - 1; i >= 0 && s[i] == inputList.length - combinationSize + i; i--);
                if (i < 0) {
                    break;
                }
                s[i]++;
                for (++i; i < combinationSize; i++) {
                    s[i] = s[i - 1] + 1;
                }
                subsets.add(getSubset(inputList, s));
            }
        }
        return subsets;
    }

    private static int[] getSubset(final Integer[] input, final int[] subset) {
        int[] result = new int[subset.length];
        for (int i = 0; i < subset.length; i++)
            result[i] = input[subset[i]];
        return result;
    }
}
