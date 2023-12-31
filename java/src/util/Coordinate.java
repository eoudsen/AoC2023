package util;

import java.util.HashSet;
import java.util.Set;

public record Coordinate(int x, int y) {

    public Set<Coordinate> getNeighbours() {
        final Set<Coordinate> neighbours = new HashSet<>();
        neighbours.add(new Coordinate(x - 1, y));
        neighbours.add(new Coordinate(x + 1, y));
        neighbours.add(new Coordinate(x, y - 1));
        neighbours.add(new Coordinate(x, y + 1));
        return neighbours;
    }
}
