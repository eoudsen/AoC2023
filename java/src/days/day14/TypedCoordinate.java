package days.day14;

import util.Coordinate;

public record TypedCoordinate(GridType gridType, Coordinate coordinate) {


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypedCoordinate typedCoordinate)) {
            return false;
        }
        if (typedCoordinate.gridType != this.gridType) {
            return false;
        }
        if (typedCoordinate.coordinate.x() != this.coordinate.x()) {
            return false;
        }
        if (typedCoordinate.coordinate.y() != this.coordinate.y()) {
            return false;
        }
        return true;
    }
}
