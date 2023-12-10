package days.day10;

import util.Coordinate;
import util.Grid;

import java.util.List;

public class TunnelGrid extends Grid {

    private Coordinate start;

    public TunnelGrid(List<String> input) {
        super(input);
        this.start = this.determineStart();
    }

    public Coordinate getStart() {
        return this.start;
    }

    public List<Coordinate> getPossibleCoordinates(final Coordinate coordinate) {
        char currSymbol = this.grid[coordinate.x()][coordinate.y()];
        if (currSymbol == 'S') {
            currSymbol = determineCurrSymbolForStart();
        }
        if (currSymbol == '-') {
            return List.of(
                    new Coordinate(coordinate.x(), coordinate.y() - 1),
                    new Coordinate(coordinate.x(), coordinate.y() + 1)
            );
        }
        else if (currSymbol == '|') {
            return List.of(
                    new Coordinate(coordinate.x() - 1, coordinate.y()),
                    new Coordinate(coordinate.x() + 1, coordinate.y())
            );
        }
        else if (currSymbol == 'J') {
            return List.of(
                    new Coordinate(coordinate.x() - 1, coordinate.y()),
                    new Coordinate(coordinate.x(), coordinate.y() - 1)
            );
        }
        else if (currSymbol == 'F') {
            return List.of(
                    new Coordinate(coordinate.x(), coordinate.y() + 1),
                    new Coordinate(coordinate.x() + 1, coordinate.y())
            );
        }
        else if (currSymbol == 'L') {
            return List.of(
                    new Coordinate(coordinate.x(), coordinate.y() + 1),
                    new Coordinate(coordinate.x() - 1, coordinate.y())
            );
        }
        else if (currSymbol == '7') {
            return List.of(
                    new Coordinate(coordinate.x() + 1, coordinate.y()),
                    new Coordinate(coordinate.x(), coordinate.y() - 1)
            );
        }
        throw new IllegalStateException("Should not happen");
    }

    private char determineCurrSymbolForStart() {
        final char topSymbol = getSafeSymbol(start.x() - 1, start.y());
        final char rightSymbol = getSafeSymbol(start.x(), start.y() + 1);
        final char leftSymbol = getSafeSymbol(start.x(), start.y() - 1);
        final char bottomSymbol = getSafeSymbol(start.x() + 1, start.y());

        if (matchingTopSymbol(topSymbol)) { // Top connecting
            if (matchingRightSymbol(rightSymbol)) {
                return 'L';
            }
            if (matchingLeftSymbol(leftSymbol)) {
                return 'J';
            }
            if (matchingBottomSymbol(bottomSymbol)) {
                return '|';
            }
        }
        else if (matchingRightSymbol(rightSymbol)) {
            if (matchingLeftSymbol(leftSymbol)) {
                return '-';
            }
            if (matchingBottomSymbol(bottomSymbol)) {
                return 'F';
            }
        }
        else if (matchingLeftSymbol(leftSymbol)) {
            if (matchingBottomSymbol(bottomSymbol)) {
                return '7';
            }
        }
        throw new IllegalStateException("Should not happen");
    }

    private boolean matchingTopSymbol(final char topSymbol) {
        return topSymbol == 'F' || topSymbol == '7' || topSymbol == '|';
    }

    private boolean matchingRightSymbol(final char rightSymbol) {
        return rightSymbol == '-' || rightSymbol == 'J' || rightSymbol == '7';
    }

    private boolean matchingLeftSymbol(final char leftSymbol) {
        return leftSymbol == 'F' || leftSymbol == '-' || leftSymbol == 'L';
    }

    public boolean matchingBottomSymbol(final char bottomSymbol) {
        return bottomSymbol == '|' || bottomSymbol == 'L' || bottomSymbol == 'J';
    }

    public char getSafeSymbol(final int x, final int y) {
        if (x < 0 || y < 0 || x > this.grid.length || y > this.grid[0].length) {
            return '.';
        }
        return this.grid[x][y];
    }

    private Coordinate determineStart() {
        for (int x = 0; x < this.grid.length; x++) {
            for (int y = 0; y < this.grid[x].length; y++) {
                if (this.grid[x][y] == 'S') {
                    return new Coordinate(y, x);
                }
            }
        }
        throw new IllegalStateException("Not supposed to be possible");
    }
}
