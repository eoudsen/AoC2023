package days.day16;

import util.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LightBeam {

    private final Coordinate coordinate;
    private final Direction direction;

    public LightBeam(final Coordinate coordinate, final Direction direction) {
        this.direction = direction;
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public Coordinate getNextCoordinate() {
        switch (direction) {
            case DOWN -> {
                return new Coordinate(coordinate.x() + 1, coordinate.y());
            }
            case UP -> {
                return new Coordinate(coordinate.x() - 1, coordinate.y());
            }
            case LEFT -> {
                return new Coordinate(coordinate.x(), coordinate.y() - 1);
            }
            case RIGHT -> {
                return new Coordinate(coordinate.x(), coordinate.y() + 1);
            }
        }
        return new Coordinate(-1, -1);
    }

    public List<LightBeam> determineNextLightBeams(final char mirror) {
        if (mirror == '.') {
            return List.of(new LightBeam(getNextCoordinate(), direction));
        }
        if (mirror == '/') {
            if (direction == Direction.DOWN) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.LEFT));
            }
            if (direction == Direction.UP) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.RIGHT));
            }
            if (direction == Direction.LEFT) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.DOWN));
            }
            if (direction == Direction.RIGHT) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.UP));
            }
        }
        if (mirror == '\\') {
            if (direction == Direction.DOWN) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.RIGHT));
            }
            if (direction == Direction.UP) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.LEFT));
            }
            if (direction == Direction.LEFT) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.UP));
            }
            if (direction == Direction.RIGHT) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.DOWN));
            }
        }
        if (mirror == '|') {
            if (direction == Direction.DOWN) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.DOWN));
            }
            if (direction == Direction.UP) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.UP));
            }
            if (direction == Direction.LEFT || direction == Direction.RIGHT) {
                final List<LightBeam> list = new ArrayList<>();
                list.add(new LightBeam(getNextCoordinate(), Direction.DOWN));
                list.add(new LightBeam(getNextCoordinate(), Direction.UP));
                return list;
            }
        }
        if (mirror == '-') {
            if (direction == Direction.LEFT) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.LEFT));
            }
            if (direction == Direction.RIGHT) {
                return List.of(new LightBeam(getNextCoordinate(), Direction.RIGHT));
            }
            if (direction == Direction.DOWN || direction == Direction.UP) {
                List<LightBeam> list = new ArrayList<>();
                list.add(new LightBeam(getNextCoordinate(), Direction.LEFT));
                list.add(new LightBeam(getNextCoordinate(), Direction.RIGHT));
                return list;
            }
        }
        return List.of();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LightBeam beam)) {
            return false;
        }
        if (beam.direction != this.direction) {
            return false;
        }
        if (beam.coordinate.x() != this.coordinate.x()) {
            return false;
        }
        if (beam.coordinate.y() != this.coordinate.y()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, direction);
    }
}
