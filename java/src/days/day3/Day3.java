package days.day3;

import util.Util;

import java.util.List;

public class Day3 {

    public static Integer part1(final List<String> input) {
        final Schematic schematic = new Schematic(input);
        return schematic.countPartNumbers();
    }

    public static Integer part2(final List<String> input) {
        final Schematic schematic = new Schematic(input);
        return schematic.findGearRatio();
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(3);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
