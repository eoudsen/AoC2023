package days.day1;

import util.Util;

import java.util.List;
import java.util.Optional;

public class Day1 {

    public static Integer part1(final List<String> input) {
        return input.stream()
                .map(Day1::extractNumber)
                .reduce(0, Integer::sum);
    }

    public static Integer part2(final List<String> input) {
        return input.stream()
                .map(Day1::replaceLetters)
                .map(Day1::extractNumber)
                .reduce(0, Integer::sum);
    }

    private static int extractNumber(String line) {
        final StringBuilder builder = new StringBuilder();
        for (final char aChar : line.toCharArray()) {
            getNumber(aChar).ifPresent(builder::append);
        }
        final String numbers = builder.toString();
        final char first = numbers.charAt(0);
        final char last = numbers.charAt(numbers.length() - 1);
        return Integer.parseInt(String.valueOf(new char[]{first, last}));
    }

    private static String replaceLetters(String line) {
        return line.replace("one", "one1one")
                .replace("two", "two2two")
                .replace("three", "three3three")
                .replace("four", "four4four")
                .replace("five", "five5five")
                .replace("six", "six6six")
                .replace("seven", "seven7seven")
                .replace("eight", "eight8eight")
                .replace("nine", "nine9nine");
    }

    private static Optional<Integer> getNumber(final char aChar) {
        try {
            return Optional.of(Integer.parseInt(String.valueOf(aChar)));
        }
        catch (final NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(1);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
