package days.day11;

import util.Coordinate;
import util.ListGrid;
import util.Util;

import java.util.List;

public class Day11 {

    private static final int EXPANSION = 1000000 - 1;

    public static Integer part1(final List<String> input) {
        final ListGrid listGrid = new ListGrid(input);
        listGrid.expandX();
        listGrid.expandY();
        final List<Coordinate> galaxies = listGrid.getGalaxies();
        int distances = 0;
        for (int i = 0; i < galaxies.size() - 1; i ++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                distances += Math.abs(galaxies.get(i).x() - galaxies.get(j).x()) + Math.abs(galaxies.get(i).y() - galaxies.get(j).y());
            }
        }

        return distances;
    }

    public static Long part2(final List<String> input) {
        final ListGrid listGrid = new ListGrid(input);
        final List<Integer> xList = listGrid.getXList();
        final List<Integer> yList = listGrid.getYList();
        listGrid.getYList();
        final List<Coordinate> galaxies = listGrid.getGalaxies();
        long distances = 0;
        for (int i = 0; i < galaxies.size() - 1; i ++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                long result = Math.abs(galaxies.get(i).x() - galaxies.get(j).x()) + Math.abs(galaxies.get(i).y() - galaxies.get(j).y());

                for (final int x : xList) {
                    if ((x > galaxies.get(i).x() && x < galaxies.get(j).x()) || (x < galaxies.get(i).x() && x > galaxies.get(j).x())) {
                        result += EXPANSION;
                    }
                }
                for (final int y : yList) {
                    if ((y > galaxies.get(i).y() && y < galaxies.get(j).y()) || (y < galaxies.get(i).y() && y > galaxies.get(j).y())) {
                        result += EXPANSION;
                    }
                }

                distances += result;
            }
        }

        return distances;
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(11);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
