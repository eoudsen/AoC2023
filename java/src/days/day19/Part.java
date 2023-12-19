package days.day19;

import java.util.Map;
import java.util.TreeMap;

public class Part {

    private int x = 0;
    private int m = 0;
    private int a = 0;
    private int s = 0;

    private TreeMap<String, Long> vals = new TreeMap<>();

    public Part(final int x, final int m, final int a, final int s) {
        this.x = x;
        this.m = m;
        this.a = a;
        this.s = s;
    }

    public int x() {
        return this.x;
    }

    public int m() {
        return this.m;
    }

    public int a() {
        return this.a;
    }

    public int s() {
        return this.s;
    }
}
