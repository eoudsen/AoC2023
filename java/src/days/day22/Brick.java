package days.day22;

import java.util.List;

public record Brick(int id, List<Coordinate3D> cubes) {
    public Brick(long x1, long y1, long z1, long x2, long y2, long z2) {
        this(Day22.idCounter++, new Coordinate3D(x1, y1, z1).lineTo(new Coordinate3D(x2, y2, z2)));
    }
}
