package days.day9;

import util.Util;

import java.util.Arrays;
import java.util.List;

public class Day9 {

    public static Long part1(final List<String> input) {
        return input.stream()
            .map(line -> new Extrapolation(stringToListLong(line)))
            .map(Extrapolation::calculate)
            .reduce(0L, Long::sum);
    }

    public static Long part2(final List<String> input) {
        return input.stream()
                .map(line -> new Extrapolation(stringToListLong(line).reversed()))
                .map(Extrapolation::calculate)
                .reduce(0L, Long::sum);
    }

    private static List<Long> stringToListLong(final String input) {
        return Arrays.stream(input.split(" ")).map(Long::parseLong).toList();
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(9);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
