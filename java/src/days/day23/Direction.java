package days.day23;

import util.Coordinate;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Coordinate move(Coordinate currentLocation) {
        return switch (this) {
            case SOUTH -> new Coordinate(currentLocation.x() + 1, currentLocation.y());
            case NORTH -> new Coordinate(currentLocation.x() - 1, currentLocation.y());
            case EAST -> new Coordinate(currentLocation.x(), currentLocation.y() + 1);
            case WEST -> new Coordinate(currentLocation.x(), currentLocation.y() - 1);
        };
    }
}
