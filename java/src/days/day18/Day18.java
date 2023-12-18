package days.day18;

import util.Coordinate;
import util.Util;

import java.util.List;

public class Day18 {

    public static Double part1(final List<String> input) {
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 0);
        int edgePoints = 0;
        double internalArea = 0;
        for (final String line : input) {
            edgePoints += Integer.parseInt(line.split(" ")[1]);
            char direction = line.split(" ")[0].charAt(0);
            if (direction == 'L') {
                end = new Coordinate(start.x(), start.y() - Integer.parseInt(line.split(" ")[1]));
            }
            else if (direction == 'R') {
                end = new Coordinate(start.x(), start.y() + Integer.parseInt(line.split(" ")[1]));
            }
            else if (direction == 'U') {
                end = new Coordinate(start.x() - Integer.parseInt(line.split(" ")[1]), start.y());
            }
            else if (direction == 'D') {
                end = new Coordinate(start.x() + Integer.parseInt(line.split(" ")[1]), start.y());
            }
            internalArea += shoelace(start, end);
            start = end;
        }

        internalArea = Math.abs(internalArea) + 1 - (edgePoints / 2.);
        return edgePoints + internalArea;
    }

    public static long part2(final List<String> input) {
        LongCoordinate start = new LongCoordinate(0, 0);
        LongCoordinate end = new LongCoordinate(0, 0);
        long edgePoints = 0L;
        double internalArea = 0.;
        for (String line : input) {
            final String hex = line.split(" ")[2].substring(2, 8);
            char direction;
            if (hex.charAt(5) == '0') {
                direction = 'R';
            }
            else if (hex.charAt(5) == '1') {
                direction = 'D';
            }
            else if (hex.charAt(5) == '2') {
                direction = 'L';
            }
            else {
                direction = 'U';
            }
            long amount = Long.parseLong(hex.substring(0, 5),16);
            edgePoints += amount;
            if (direction == 'L') {
                end = new LongCoordinate(start.x(), start.y() - amount);
            }
            else if (direction == 'R') {
                end = new LongCoordinate(start.x(), start.y() + amount);
            }
            else if (direction == 'U') {
                end = new LongCoordinate(start.x() - amount, start.y());
            }
            else {
                end = new LongCoordinate(start.x() + amount, start.y());
            }
            internalArea += shoelace(start, end);
            start = end;
        }

        internalArea = Math.abs(internalArea) + 1 - (edgePoints / 2.);
        return (long) (edgePoints + internalArea);
    }

    private static double shoelace(final Coordinate start, final Coordinate end) {
        return (start.x() * end.y() - start.y() * end.x()) / 2.;
    }

    private static double shoelace(final LongCoordinate start, final LongCoordinate end) {
        return (start.x() * end.y() - start.y() * end.x()) / 2.;
    }


    public static void main(String... args) {
        var fileContent = Util.getFileContent(18);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
