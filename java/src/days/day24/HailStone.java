package days.day24;

public class HailStone {
    private final long x;
    private final long y;
    private final long z;
    private final int vx;
    private final int vy;
    private final int vz;

    public HailStone(final String line) {
        final String[] ends = line.split("@");
        x = Long.parseLong(ends[0].split(",")[0].trim());
        y = Long.parseLong(ends[0].split(",")[1].trim());
        z = Long.parseLong(ends[0].split(",")[2].trim());
        vx = Integer.parseInt(ends[1].split(",")[0].trim());
        vy = Integer.parseInt(ends[1].split(",")[1].trim());
        vz = Integer.parseInt(ends[1].split(",")[2].trim());
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    public int getVx() {
        return vx;
    }

    public int getVy() {
        return vy;
    }

    public int getVz() {
        return vz;
    }
}