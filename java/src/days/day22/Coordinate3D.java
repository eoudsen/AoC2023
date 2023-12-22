package days.day22;

import java.util.List;

import static java.util.stream.LongStream.rangeClosed;

public class Coordinate3D {
    
    public final long x;
    public final long y;
    public final long z;

    public Coordinate3D(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public List<Long> toList() {
        return List.of(x, y, z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Coordinate3D move(long dx, long dy, long dz) {
        return new Coordinate3D(x + dx, y + dy, z + dz);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate3D Coordinate3D = (Coordinate3D) o;

        if (x != Coordinate3D.x) return false;
        if (y != Coordinate3D.y) return false;
        return z == Coordinate3D.z;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        result = 31 * result + (int) (z ^ (z >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }

    public List<Coordinate3D> lineTo(Coordinate3D Coordinate3D) {
        if (x < Coordinate3D.x) {
            return rangeClosed(x, Coordinate3D.x).mapToObj(i -> new Coordinate3D(i, y, z)).toList();
        } else if (y < Coordinate3D.y) {
            return rangeClosed(y, Coordinate3D.y).mapToObj(i -> new Coordinate3D(x, i, z)).toList();
        } else if (z < Coordinate3D.z) {
            return rangeClosed(z, Coordinate3D.z).mapToObj(i -> new Coordinate3D(x, y, i)).toList();
        } else if (equals(Coordinate3D)) {
            return List.of(this);
        } else {
            throw new IllegalStateException("Invalid line: " + this + " to " + Coordinate3D);
        }
    }
}