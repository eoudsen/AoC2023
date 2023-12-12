package days.day12;

import util.Util;

import java.util.List;
import java.util.stream.Stream;

public class Day12 {

    public static Long part1(final List<String> input) {
        return input.stream()
            .map(SpringList::new)
            .map(SpringList::calculateArrangements)
            .reduce(0L, Long::sum);
    }

    public static Long part2(final List<String> input) {
        return input.stream()
                .map(Day12::expandInput)
                .map(SpringList2::new)
                .map(SpringList2::calculateArrangements)
                .reduce(0L, Long::sum);
    }

    private static String expandInput(String line) {
        String s = line.split(" ")[0];
        String s2 = s + "?" + s + "?" + s + "?" + s + "?" + s;
        String s3 = line.split(" ")[1];
        String s4 = s3 + "," + s3 + "," + s3 + "," + s3 + "," + s3;
        return s2 + " " + s4;
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(12);
//        var fileContent = Util.getTestFileContent(12);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
