package days.day13;

import util.Util;

import java.util.*;

public class Day13 {

    public static Integer part1(final List<String> input) {
        final List<GroundPattern> groundPatterns = new ArrayList<>();
        List<String> currentLines = new ArrayList<>();
        for (final String line : input) {
            if (line.isEmpty()) {
                groundPatterns.add(new GroundPattern(currentLines));
                currentLines = new ArrayList<>();
                continue;
            }
            currentLines.add(line);
        }
        groundPatterns.add(new GroundPattern(currentLines));

        final List<ReflectionResult> reflectionResultList = groundPatterns.stream().map(GroundPattern::findReflection).toList();
        int vertical = 0;
        int horizontal = 0;
        for (final ReflectionResult result : reflectionResultList) {
            if (result.reflectionType() == ReflectionType.VERTICAL) {
                vertical += result.reflectionLocation();
            }
            else if (result.reflectionType() == ReflectionType.HORIZONTAL) {
                horizontal += result.reflectionLocation();
            }
        }

        return 100 * horizontal + vertical;
    }

    public static Integer part2(final List<String> input) {
        final List<GroundPattern> groundPatterns = new ArrayList<>();
        List<String> currentLines = new ArrayList<>();
        for (final String line : input) {
            if (line.isEmpty()) {
                groundPatterns.add(new GroundPattern(currentLines));
                currentLines = new ArrayList<>();
                continue;
            }
            currentLines.add(line);
        }
        groundPatterns.add(new GroundPattern(currentLines));

        final List<ReflectionResult> originalReflectionResults = groundPatterns.stream().map(GroundPattern::findReflection).toList();
        final List<ReflectionResult> newResults = new ArrayList<>();
        for (int i = 0; i < originalReflectionResults.size(); i++) {
            final GroundPattern currentGroundPattern = groundPatterns.get(i);
            final Set<ReflectionResult> newResultSet = new HashSet<>();
            for (int x = 0; x < currentGroundPattern.getGrid().getGrid().length; x++) {
                for (int y = 0; y < currentGroundPattern.getGrid().getGrid()[x].length; y++) {
                    final GroundPattern adjustedGroundPattern = groundPatterns.get(i).adjustedGroundPattern(x, y);
                    final List<Integer> allHorizontalReflection = adjustedGroundPattern.findAllHorizontalReflection();
                    final List<Integer> allVerticalReflections = adjustedGroundPattern.findAllVerticalReflections();
                    final List<ReflectionResult> horizontalReflections = new ArrayList<>();
                    final List<ReflectionResult> verticalReflections = new ArrayList<>();
                    for (final Integer location : allHorizontalReflection) {
                        horizontalReflections.add(new ReflectionResult(ReflectionType.HORIZONTAL, location));
                    }
                    for (final Integer location : allVerticalReflections) {
                        verticalReflections.add(new ReflectionResult(ReflectionType.VERTICAL, location));
                    }

                    for (final ReflectionResult horizontal : horizontalReflections) {
                        if (horizontal.reflectionLocation() != -1) {
                            int currentGroundLength = currentGroundPattern.getGrid().getGrid().length;
                            int returnPoint = horizontal.reflectionLocation();
                            int maxDiff = Math.min(returnPoint, Math.abs(currentGroundLength - returnPoint));
                            if (x - maxDiff < returnPoint && x + maxDiff > returnPoint) {
                                newResultSet.add(horizontal);
                            }
                        }
                    }

                    for (final ReflectionResult vertical : verticalReflections) {
                        if (vertical.reflectionLocation() != -1) {
                            int currentGroundLength = currentGroundPattern.getGrid().getGrid()[0].length;
                            int returnPoint = vertical.reflectionLocation();
                            int maxDiff = Math.min(returnPoint, Math.abs(currentGroundLength - returnPoint));
                            if (y - maxDiff < returnPoint && y + maxDiff > returnPoint) {
                                newResultSet.add(vertical);
                            }
                        }
                    }
                }
            }
            newResults.addAll(newResultSet);
        }

        int vertical = 0;
        int horizontal = 0;
        for (final ReflectionResult result : newResults) {
            if (result.reflectionType() == ReflectionType.VERTICAL) {
                vertical += result.reflectionLocation();
            }
            else if (result.reflectionType() == ReflectionType.HORIZONTAL) {
                horizontal += result.reflectionLocation();
            }
        }

        return 100 * horizontal + vertical;
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(13);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
