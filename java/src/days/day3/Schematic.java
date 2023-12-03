package days.day3;

import java.util.*;

import static java.lang.Character.isDigit;

public class Schematic {

    private final char[][] grid;

    public Schematic(final List<String> input) {
        this.grid = new char[input.get(0).length()][input.size()];
        for (int i = 0; i < input.size(); i++) {
            System.arraycopy(input.get(i).toCharArray(), 0, this.grid[i], 0, input.get(i).toCharArray().length);
        }
    }

    public int countPartNumbers() {
        int sum = 0;
        boolean numberStarted = false;
        Set<Coordinate> coordinates = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            if (!builder.isEmpty()) {
                Optional<Coordinate> first = coordinates.stream()
                        .filter(coord -> isAdjacentSymbol(coord.i, coord.j))
                        .findFirst();
                if (first.isPresent()) {
                    sum += Integer.parseInt(builder.toString());
                }
            }
            numberStarted = false;
            coordinates = new HashSet<>();
            builder = new StringBuilder();
            for (int j = 0; j < grid[i].length; j++) {
                if (!numberStarted && isNumber(this.grid[i][j])) {
                    numberStarted = true;
                    builder.append(this.grid[i][j]);
                    coordinates.add(new Coordinate(i, j));
                }
                else if (numberStarted && isNumber(this.grid[i][j])) {
                    builder.append(this.grid[i][j]);
                    coordinates.add(new Coordinate(i, j));
                }
                else if (numberStarted && !isNumber(this.grid[i][j])) {
                    Optional<Coordinate> first = coordinates.stream()
                            .filter(coord -> isAdjacentSymbol(coord.i, coord.j))
                            .findFirst();
                    if (first.isPresent()) {
                        sum += Integer.parseInt(builder.toString());
                    }

                    numberStarted = false;
                    coordinates = new HashSet<>();
                    builder = new StringBuilder();
                }
            }
        }
        return sum;
    }

    private boolean isAdjacentSymbol(final int i, final int j) {
        if (i != 0 && j != 0 && isCharacter(this.grid[i - 1][j - 1])) {
            return true;
        }
        if (i != 0 && isCharacter(this.grid[i - 1][j])) {
            return true;
        }
        if (i != 0 && j != this.grid[i].length - 1 && isCharacter(this.grid[i - 1][j + 1])) {
            return true;
        }
        if (j != 0 && isCharacter(this.grid[i][j - 1])) {
            return true;
        }
        if (j != this.grid[i].length - 1 && isCharacter(this.grid[i][j + 1])) {
            return true;
        }
        if (i != this.grid.length - 1 && j != 0 && isCharacter(this.grid[i + 1][j - 1])) {
            return true;
        }
        if (i != this.grid.length - 1 && isCharacter(this.grid[i + 1][j])) {
            return true;
        }
        if (i != this.grid.length - 1 && j != this.grid[i].length - 1 && isCharacter(this.grid[i + 1][j + 1])) {
            return true;
        }
        return false;
    }

    private Coordinate isAdjacentGear(final int i, final int j) {
        if (i != 0 && j != 0 && this.grid[i - 1][j - 1] == '*') {
            return new Coordinate(i - 1, j - 1);
        }
        if (i != 0 && this.grid[i - 1][j] == '*') {
            return new Coordinate(i - 1, j);
        }
        if (i != 0 && j != this.grid[i].length - 1 && this.grid[i - 1][j + 1] == '*') {
            return new Coordinate(i - 1, j + 1);
        }
        if (j != 0 && this.grid[i][j - 1] == '*') {
            return new Coordinate(i, j - 1);
        }
        if (j != this.grid[i].length - 1 && this.grid[i][j + 1] == '*') {
            return new Coordinate(i, j + 1);
        }
        if (i != this.grid.length - 1 && j != 0 && this.grid[i + 1][j - 1] == '*') {
            return new Coordinate(i + 1, j - 1);
        }
        if (i != this.grid.length - 1 && this.grid[i + 1][j] == '*') {
            return new Coordinate(i + 1, j);
        }
        if (i != this.grid.length - 1 && j != this.grid[i].length - 1 && this.grid[i + 1][j + 1] == '*') {
            return new Coordinate(i + 1, j + 1);
        }
        return null;
    }

    private boolean isPeriod(final char chr) {
        return chr == '.';
    }

    private boolean isNumber(final char chr) {
        return isDigit(chr);
    }

    private boolean isCharacter(final char chr) {
        return !(isPeriod(chr) || isNumber(chr));
    }

    public Integer findGearRatio() {
        Map<Coordinate, List<Integer>> gearCoordinates = new HashMap<>();
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                if (this.grid[i][j] == '*') {
                    gearCoordinates.put(new Coordinate(i, j), new ArrayList<>());
                }
            }
        }

        boolean numberStarted = false;
        Set<Coordinate> coordinates = new HashSet<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            if (!builder.isEmpty()) {
                List<Coordinate> gears = coordinates.stream()
                        .map(coord -> isAdjacentGear(coord.i, coord.j))
                        .filter(Objects::nonNull)
                        .distinct()
                        .toList();
                for (final Coordinate gear : gears) {
                    gearCoordinates.get(gear).add(Integer.parseInt(builder.toString()));
                }
            }
            numberStarted = false;
            coordinates = new HashSet<>();
            builder = new StringBuilder();
            for (int j = 0; j < grid[i].length; j++) {
                if (!numberStarted && isNumber(this.grid[i][j])) {
                    numberStarted = true;
                    builder.append(this.grid[i][j]);
                    coordinates.add(new Coordinate(i, j));
                }
                else if (numberStarted && isNumber(this.grid[i][j])) {
                    builder.append(this.grid[i][j]);
                    coordinates.add(new Coordinate(i, j));
                }
                else if (numberStarted && !isNumber(this.grid[i][j])) {
                    List<Coordinate> gears = coordinates.stream()
                            .map(coord -> isAdjacentGear(coord.i, coord.j))
                            .filter(Objects::nonNull)
                            .distinct()
                            .toList();
                    for (final Coordinate gear : gears) {
                        gearCoordinates.get(gear).add(Integer.parseInt(builder.toString()));
                    }

                    numberStarted = false;
                    coordinates = new HashSet<>();
                    builder = new StringBuilder();
                }
            }
        }

        return gearCoordinates.values().stream()
                .filter(integers -> integers.size() == 2)
                .map(integers -> integers.get(0) * integers.get(1))
                .reduce(0, Integer::sum);
    }

    private record Coordinate(int i, int j) {}
}
