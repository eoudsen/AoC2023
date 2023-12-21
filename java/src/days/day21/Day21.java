package days.day21;

import util.Coordinate;
import util.NewGrid;
import util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static days.day21.Tuple.tuple;
import static java.util.stream.Collectors.toCollection;

public class Day21 {

    public static Integer part1(final List<String> input) {
        final NewGrid newGrid = new NewGrid(input);
        final Set<Coordinate> stones = new HashSet<>();
        Set<Coordinate> currentPoints = new HashSet<>();
        for (int x = 0; x < newGrid.getGrid().length; x++) {
            for (int y = 0; y < newGrid.getGrid()[x].length; y++) {
                if (newGrid.getGrid()[x][y] == '#') {
                    stones.add(new Coordinate(x, y));
                }
                else if (newGrid.getGrid()[x][y] == 'S') {
                    currentPoints.add(new Coordinate(x, y));
                }
            }
        }

        for (int i = 0; i < 64; i++) {
            final Set<Coordinate> newCoordinates = new HashSet<>();
            for (final Coordinate currentPoint : currentPoints) {
                Set<Coordinate> neighbours = currentPoint.getNeighbours();
                for (final Coordinate neighbour : neighbours) {
                    if (inBounds(neighbour, newGrid) && !stones.contains(neighbour)) {
                        newCoordinates.add(neighbour);
                    }
                }
            }
            currentPoints = new HashSet<>(newCoordinates);
        }
        return currentPoints.size();
    }

    public static Long part2(final List<String> input) {
        Coordinate start = new Coordinate(0, 0);
        final NewGrid newGrid = new NewGrid(input);
        for (int x = 0; x < newGrid.getGrid().length; x++) {
            for (int y = 0; y < newGrid.getGrid()[x].length; y++) {
                if (newGrid.getGrid()[x][y] == 'S') {
                    start = new Coordinate(x, y);
                }
            }
        }

        // Core algorithm by abnew123: https://github.com/abnew123/aoc2023/blob/main/src/solutions/Day21.java
        Set<Coordinate> reached = new HashSet<>();
        Builder<Set<Coordinate>> places = new Builder<>(HashSet::new);
        places.get().add(start);
        reached.add(start);
        List<Long> totals = new ArrayList<>();
        long totalReached = 0;
        for (int i = 1; ; i++) {
            places.get().stream().flatMap(l -> l.getNeighbours().stream()).forEach(l -> {
                if (!reached.contains(l)) {
                    if (newGrid.getGrid()[((l.x() % newGrid.getGrid().length) + newGrid.getGrid().length) % newGrid.getGrid().length][((l.y() % newGrid.getGrid().length) + newGrid.getGrid().length) % newGrid.getGrid().length] != '#') {
                        places.getNew().add(l);
                        reached.add(l);
                    }
                }
            });
            if (i % 2 == 1) {
                totalReached += places.getNew().size();
                if (i % 262 == 65) {
                    totals.add(totalReached);
                    List<Long> deltaDeltas = deltaAtLevel(totals, 2);
                    if (deltaDeltas.size() > 1) {
                        long neededLoopCount = 26501365 / 262 - 1;
                        long currentLoopCount = i / 262 - 1;
                        long deltaLoopCount = neededLoopCount - currentLoopCount;
                        long deltaLoopCountTriangular = (neededLoopCount * (neededLoopCount + 1)) / 2 - (currentLoopCount * (currentLoopCount + 1)) / 2;
                        long deltaDelta = deltaDeltas.get(deltaDeltas.size() - 1);
                        long initialDelta = deltaAtLevel(totals, 1).get(0);
                        return deltaDelta * deltaLoopCountTriangular + initialDelta * deltaLoopCount + totalReached;
                    }
                }
            }
            places.refresh();
        }
    }

    private static boolean inBounds(final Coordinate coordinate, final NewGrid newGrid) {
        if (coordinate.x() < 0) {
            return false;
        }
        if (coordinate.y() < 0) {
            return false;
        }

        if (coordinate.x() >= newGrid.getGrid().length) {
            return false;
        }

        if (coordinate.y() >= newGrid.getGrid()[0].length) {
            return false;
        }
        return true;
    }

    private static List<Long> deltaAtLevel(List<Long> deltas, int level) {
        for (int i = 0; i < level; i++) {
            deltas = connectedPairs(deltas).map(p -> p.b() - p.a()).collect(toCollection(ArrayList::new));
        }
        return deltas;
    }

    public static Stream<Tuple> connectedPairs(List<Long> l) {
        return IntStream.range(1, l.size()).mapToObj(i -> tuple(l.get(i - 1), l.get(i)));
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(21);
//        var fileContent = Util.getTestFileContent(21);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
