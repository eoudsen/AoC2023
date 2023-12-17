package days.day17;


public class MapPoint implements Comparable<MapPoint> {

    private final long x;
    private final long y;

    public MapPoint(final long x, final long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return this.x;
    }

    public long getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("(%d %d)", x, y);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        return toString().equals(o.toString());
    }

    @Override
    public int compareTo(final MapPoint p) {
        if (x < p.x) return -1;
        if (x > p.x) return 1;

        if (y < p.y) return -1;
        if (y > p.y) return 1;

        return 0;
    }

    public MapPoint add(final MapPoint p) {
        return new MapPoint(x + p.x, y +  p.y);
    }
}
