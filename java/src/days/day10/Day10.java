package days.day10;

import util.Coordinate;
import util.Grid;
import util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10 {

    public static Integer part1(final List<String> input) {
        final TunnelGrid grid = new TunnelGrid(input);
        final Set<Coordinate> visited = new HashSet<>();
        visited.add(grid.getStart());

        List<Coordinate> toVisitCoordinates = grid.getPossibleCoordinates(grid.getStart());

        while (true) {
            List<Coordinate> filteredToVisitCoordinates = toVisitCoordinates.stream().filter(coord -> !visited.contains(coord)).toList();
            if (filteredToVisitCoordinates.isEmpty()) {
                return visited.size() / 2;
            }
            visited.addAll(filteredToVisitCoordinates);
            toVisitCoordinates = filteredToVisitCoordinates.stream().flatMap(coord -> grid.getPossibleCoordinates(coord).stream()).toList();
        }
    }

    public static Integer part2(final List<String> input) {
        final TunnelGrid grid = new TunnelGrid(input);
        final Set<Coordinate> visited = new HashSet<>();
        visited.add(grid.getStart());

        List<Coordinate> toVisitCoordinates = grid.getPossibleCoordinates(grid.getStart());

        while (true) {
            List<Coordinate> filteredToVisitCoordinates = toVisitCoordinates.stream().filter(coord -> !visited.contains(coord)).toList();
            if (filteredToVisitCoordinates.isEmpty()) {
                break;
            }
            visited.addAll(filteredToVisitCoordinates);
            toVisitCoordinates = filteredToVisitCoordinates.stream().flatMap(coord -> grid.getPossibleCoordinates(coord).stream()).toList();
        }

        int enclosed = 0;
        int width = input.get(0).length();
        int height = input.size();

        for (int x = 0; x < input.size(); x++) {
            for (int y = 0; y < input.get(x).length(); y++) {
                if (visited.contains(new Coordinate(x, y))) {
                    continue;
                }

                int crosses = 0;
                int x2 = x;
                int y2 = y;
                while (x2 < height && y2 < width) {
                    int c2 = grid.getSafeSymbol(x2, y2);
                    if (visited.contains(new Coordinate(x2, y2)) && c2 != 'L' && c2 != '7') {
                        crosses += 1;
                    }
                    x2 += 1;
                    y2 += 1;
                }
                if (crosses % 2 == 1) {
                    enclosed += 1;
                }
            }
        }

        return enclosed;
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(10);
//        var fileContent = Util.getTestFileContent(10);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
