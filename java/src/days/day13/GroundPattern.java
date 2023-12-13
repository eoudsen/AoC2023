package days.day13;

import util.Grid;
import util.NewGrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroundPattern {

    private final NewGrid grid;

    public GroundPattern(final List<String> input) {
        this.grid = new NewGrid(input);
    }

    public GroundPattern(final NewGrid newGrid) {
        this.grid = newGrid;
    }

    public NewGrid getGrid() {
        return this.grid;
    }

    public GroundPattern adjustedGroundPattern(final int x, final int y) {
        final char[][] newGrid = new char[this.grid.getGrid().length][this.grid.getGrid()[0].length];
        for (int i = 0; i < this.grid.getGrid().length; i++) {
            for (int j = 0; j < this.grid.getGrid()[i].length; j++) {
                if (i == x && j == y) {
                    if (this.grid.getGrid()[i][j] == '.') {
                        newGrid[i][j] = '#';
                    }
                    else {
                        newGrid[i][j] = '.';
                    }
                }
                else {
                    newGrid[i][j] = this.grid.getGrid()[i][j];
                }
            }
        }
        return new GroundPattern(new NewGrid(newGrid));
    }

    public ReflectionResult findReflection() {
        int horizontal = findHorizontalReflection();
        if (horizontal != -1) {
            return new ReflectionResult(ReflectionType.HORIZONTAL, horizontal);
        }
        int vertical = findVerticalReflection();
        if (vertical != -1){
            return new ReflectionResult(ReflectionType.VERTICAL, vertical);
        }
        return new ReflectionResult(ReflectionType.NONE, 0);
    }

    public int findHorizontalReflection() {
        MIRROR: for (int i = 0; i < this.grid.getGrid().length - 1; i++) {
            int up = i;
            int down = i + 1;
            while (up >= 0 && down < this.grid.getGrid().length) {
                if (!Arrays.equals(this.grid.getGrid()[up], this.grid.getGrid()[down])) {
                    continue MIRROR;
                }
                up--;
                down++;
            }
            return i + 1;
        }
        return -1;
    }

    public List<Integer> findAllHorizontalReflection() {
        final List<Integer> locations = new ArrayList<>();
        MIRROR: for (int i = 0; i < this.grid.getGrid().length - 1; i++) {
            int up = i;
            int down = i + 1;
            while (up >= 0 && down < this.grid.getGrid().length) {
                if (!Arrays.equals(this.grid.getGrid()[up], this.grid.getGrid()[down])) {
                    continue MIRROR;
                }
                up--;
                down++;
            }
            locations.add(i + 1);
        }
        return locations;
    }

    public int findVerticalReflection() {
        MIRROR: for (int i = 0; i < this.grid.getGrid()[0].length - 1; i++) {
            int left = i;
            int right = i + 1;
            while (left >= 0 && right < this.grid.getGrid()[0].length) {
                if (!Arrays.equals(this.grid.getYColumn(left), this.grid.getYColumn(right))) {
                    continue MIRROR;
                }
                left--;
                right++;
            }
            return i + 1;
        }
        return -1;
    }

    public List<Integer> findAllVerticalReflections() {
        final List<Integer> locations = new ArrayList<>();
        MIRROR: for (int i = 0; i < this.grid.getGrid()[0].length - 1; i++) {
            int left = i;
            int right = i + 1;
            while (left >= 0 && right < this.grid.getGrid()[0].length) {
                if (!Arrays.equals(this.grid.getYColumn(left), this.grid.getYColumn(right))) {
                    continue MIRROR;
                }
                left--;
                right++;
            }
            locations.add(i + 1);
        }
        return locations;
    }
}
