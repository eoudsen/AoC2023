package days.day17;

public class Navigator {

    public static MapPoint NORTH = getDir('N');
    public static MapPoint SOUTH = getDir('S');
    public static MapPoint EAST = getDir('E');
    public static MapPoint WEST = getDir('W');

    public static MapPoint turnRight(final MapPoint in) {
        return new MapPoint(-in.getY(), in.getX());
    }

    public static MapPoint turnLeft(final MapPoint in) {
        return new MapPoint(in.getY(), -in.getX());
    }

    public static MapPoint getDir(final char z) {
        if (z=='N') {
            return new MapPoint(-1,0);
        }
        if (z=='S') {
            return new MapPoint(1,0);
        }
        if (z=='E') {
            return new MapPoint(0,1);
        }
        if (z=='W') {
            return new MapPoint(0,-1);
        }
        throw new RuntimeException("Bad dir:" + z);
    }
}
