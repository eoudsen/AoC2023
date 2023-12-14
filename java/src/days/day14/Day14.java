package days.day14;

import util.Coordinate;
import util.Util;

import java.util.*;

public class Day14 {

    public static Integer part1(final List<String> input) {
        Set<TypedCoordinate> currentSet = new HashSet<>();
        for (int x = 0; x < input.size(); x++) {
            for (int y = 0; y < input.get(x).toCharArray().length; y++) {
                if (input.get(x).toCharArray()[y] == 'O') {
                    currentSet.add(new TypedCoordinate(GridType.ROUND, new Coordinate(x, y)));
                }
                else if (input.get(x).toCharArray()[y] == '#') {
                    currentSet.add(new TypedCoordinate(GridType.SQUARE, new Coordinate(x, y)));
                }
            }
        }


        while (true) {
            Set<TypedCoordinate> newSet = new HashSet<>();
            for (final TypedCoordinate current : currentSet) {
                if (current.gridType() == GridType.SQUARE) {
                    newSet.add(current);
                    continue;
                }
                TypedCoordinate newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(current.coordinate().x(), current.coordinate().y()));
                while (true) {
                    if (newLocation.coordinate().x() - 1 == -1 ||
                    currentSet.contains(new TypedCoordinate(GridType.SQUARE, new Coordinate(newLocation.coordinate().x() - 1, newLocation.coordinate().y()))) ||
                    currentSet.contains(new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x() - 1, newLocation.coordinate().y())))) {
                        newSet.add(newLocation);
                        break;
                    }
                    newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x() - 1, newLocation.coordinate().y()));
                }
            }

            if (currentSet.equals(newSet)) {
                break;
            }
            currentSet = new HashSet<>(newSet);
        }

        int result = 0;
        for (final TypedCoordinate typedCoordinate : currentSet) {
            if (typedCoordinate.gridType() == GridType.ROUND) {
                result += input.size() - typedCoordinate.coordinate().x();
            }
        }

        return result;
    }

    public static Integer part2(final List<String> input) {
        Set<TypedCoordinate> currentSet = new HashSet<>();
        for (int x = 0; x < input.size(); x++) {
            for (int y = 0; y < input.get(x).toCharArray().length; y++) {
                if (input.get(x).toCharArray()[y] == 'O') {
                    currentSet.add(new TypedCoordinate(GridType.ROUND, new Coordinate(x, y)));
                }
                else if (input.get(x).toCharArray()[y] == '#') {
                    currentSet.add(new TypedCoordinate(GridType.SQUARE, new Coordinate(x, y)));
                }
            }
        }
        final Map<Set<TypedCoordinate>, Set<TypedCoordinate>> cache = new HashMap<>();
        boolean loopStarted = false;
        int startCount = 0;
        Set<TypedCoordinate> startLoop = new HashSet<>();


        for (int i = 0; i < 1000000000; i++) {
            if (i % 10000 == 0) {
                System.out.println(i);
            }
            Set<TypedCoordinate> keySet = new HashSet<>(currentSet);
            if (!loopStarted && cache.containsKey(keySet)) {
                loopStarted = true;
                startCount = i;
                startLoop = new HashSet<>(keySet);
                currentSet = new HashSet<>(cache.get(keySet));
                continue;
            }
            if (loopStarted && startLoop.equals(keySet)) {
                int endCount = i;
                int loopSize = endCount - startCount;
                int res = (1000000000 - startCount) % loopSize;
                for (int j = 0; j < res; j ++) {
                    currentSet = new HashSet<>(cache.get(keySet));
                    keySet = new HashSet<>(currentSet);
                }
                break;
            }
            if (cache.containsKey(keySet)) {
                currentSet = new HashSet<>(cache.get(keySet));
                continue;
            }
            while (true) {
                Set<TypedCoordinate> newSet = new HashSet<>();
                for (final TypedCoordinate current : currentSet) {
                    if (current.gridType() == GridType.SQUARE) {
                        newSet.add(current);
                        continue;
                    }
                    TypedCoordinate newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(current.coordinate().x(), current.coordinate().y()));
                    while (true) {
                        if (newLocation.coordinate().x() - 1 == -1 ||
                                currentSet.contains(new TypedCoordinate(GridType.SQUARE, new Coordinate(newLocation.coordinate().x() - 1, newLocation.coordinate().y()))) ||
                                currentSet.contains(new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x() - 1, newLocation.coordinate().y())))) {
                            newSet.add(newLocation);
                            break;
                        }
                        newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x() - 1, newLocation.coordinate().y()));
                    }
                }

                if (currentSet.equals(newSet)) {
                    break;
                }
                currentSet = new HashSet<>(newSet);
            }

            while (true) {
                Set<TypedCoordinate> newSet = new HashSet<>();
                for (final TypedCoordinate current : currentSet) {
                    if (current.gridType() == GridType.SQUARE) {
                        newSet.add(current);
                        continue;
                    }
                    TypedCoordinate newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(current.coordinate().x(), current.coordinate().y()));
                    while (true) {
                        if (newLocation.coordinate().y() - 1 == -1 ||
                                currentSet.contains(new TypedCoordinate(GridType.SQUARE, new Coordinate(newLocation.coordinate().x(), newLocation.coordinate().y() - 1))) ||
                                currentSet.contains(new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x(), newLocation.coordinate().y() - 1)))) {
                            newSet.add(newLocation);
                            break;
                        }
                        newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x(), newLocation.coordinate().y() - 1));
                    }
                }

                if (currentSet.equals(newSet)) {
                    break;
                }
                currentSet = new HashSet<>(newSet);
            }


            while (true) {
                Set<TypedCoordinate> newSet = new HashSet<>();
                for (final TypedCoordinate current : currentSet) {
                    if (current.gridType() == GridType.SQUARE) {
                        newSet.add(current);
                        continue;
                    }
                    TypedCoordinate newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(current.coordinate().x(), current.coordinate().y()));
                    while (true) {
                        if (newLocation.coordinate().x() + 1 == input.size() ||
                                currentSet.contains(new TypedCoordinate(GridType.SQUARE, new Coordinate(newLocation.coordinate().x() + 1, newLocation.coordinate().y()))) ||
                                currentSet.contains(new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x() + 1, newLocation.coordinate().y())))) {
                            newSet.add(newLocation);
                            break;
                        }
                        newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x() + 1, newLocation.coordinate().y()));
                    }
                }

                if (currentSet.equals(newSet)) {
                    break;
                }
                currentSet = new HashSet<>(newSet);
            }


            while (true) {
                Set<TypedCoordinate> newSet = new HashSet<>();
                for (final TypedCoordinate current : currentSet) {
                    if (current.gridType() == GridType.SQUARE) {
                        newSet.add(current);
                        continue;
                    }
                    TypedCoordinate newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(current.coordinate().x(), current.coordinate().y()));
                    while (true) {
                        if (newLocation.coordinate().y() + 1 == input.get(0).length() ||
                                currentSet.contains(new TypedCoordinate(GridType.SQUARE, new Coordinate(newLocation.coordinate().x(), newLocation.coordinate().y() + 1))) ||
                                currentSet.contains(new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x(), newLocation.coordinate().y() + 1)))) {
                            newSet.add(newLocation);
                            break;
                        }
                        newLocation = new TypedCoordinate(GridType.ROUND, new Coordinate(newLocation.coordinate().x(), newLocation.coordinate().y() + 1));
                    }
                }

                if (currentSet.equals(newSet)) {
                    break;
                }
                currentSet = new HashSet<>(newSet);
            }

            cache.put(keySet, currentSet);
        }

        int result = 0;
        for (final TypedCoordinate typedCoordinate : currentSet) {
            if (typedCoordinate.gridType() == GridType.ROUND) {
                result += input.size() - typedCoordinate.coordinate().x();
            }
        }

        return result;
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(14);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
