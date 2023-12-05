package days.day5;

import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Day5 {

    public static Long part1(final List<String> input) {
        final String[] seeds = input.get(0).split(":")[1].strip().split(" ");
        final List<Long> seedNumbers = new ArrayList<>();
        for (final String seed : seeds) {
            seedNumbers.add(Long.parseLong(seed));
        }
        final Almanac almanac = new Almanac(input.subList(1, input.size() - 1));
        return seedNumbers.stream()
            .map(almanac::seedToSoil)
            .map(almanac::soilToFertilizer)
            .map(almanac::fertilizerToWater)
            .map(almanac::waterToLight)
            .map(almanac::lightToTemperature)
            .map(almanac::temperatureToHumidity)
            .map(almanac::humidityToLocation)
            .reduce((x, y) -> x.compareTo(y) <= 0  ? x : y).get();
    }

    public static Long part2(final List<String> input) {
        final String[] seeds = input.get(0).split(":")[1].strip().split(" ");
        final List<Long> seedNumbers = new ArrayList<>();
        for (final String seed : seeds) {
            seedNumbers.add(Long.parseLong(seed));
        }
        TreeSet<Range> ranges = new TreeSet<>();
        for (int i = 0; i < seedNumbers.size() - 1; i += 2) {
            Long start = seedNumbers.get(i);
            ranges.add(new Range(start, start + seedNumbers.get(i + 1) - 1));
        }

        Range.merge(ranges);

        final Almanac almanac = new Almanac(input.subList(1, input.size() - 1));
        ranges = almanac.seedToSoil(ranges);
        ranges = almanac.soilToFertilizer(ranges);
        ranges = almanac.fertilizerToWater(ranges);
        ranges = almanac.waterToLight(ranges);
        ranges = almanac.lightToTemperature(ranges);
        ranges = almanac.temperatureToHumidity(ranges);
        ranges = almanac.humidityToLocation(ranges);
        return ranges.getFirst().start;
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(5);
//        var fileContent = Util.getTestFileContent(5);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
