package days.day17;

import java.util.TreeMap;
import java.util.List;

public class TwoDimensionalMap<V> {

    public TreeMap<Long, TreeMap<Long, V> > map;
    private V default_value;

    protected long high_x = -1000000000L;
    protected long high_y= -1000000000L;

    protected long low_x = 1000000000L;
    protected long low_y = 1000000000L;

    public TwoDimensionalMap(final V def) {
        this.default_value = def;
        this.map = new TreeMap<>();
    }

    public V get(final MapPoint p) {
        return get(p.getX(), p.getY());
    }

    public V get(final long x, final long y) {
        if (this.map.get(x) == null) {
            return (V) this.default_value;
        }
        if (this.map.get(x).get(y) == null) {
            return (V) this.default_value;
        }
        return this.map.get(x).get(y);
    }

    public V set(final MapPoint p, final V val) {
        return set(p.getX(), p.getY(), val);
    }

    public V set(final long x, final long y, final V val) {
        final V old = get(x,y);

        if (this.map.get(x) == null) {
            this.map.put(x, new TreeMap<>());
        }
        this.map.get(x).put(y,val);

        this.high_x = Math.max(high_x, x);
        this.high_y = Math.max(high_y, y);

        this.low_x = Math.min(low_x, x);
        this.low_y = Math.min(low_y, y);
        return old;
    }

    public static void load(final List<String> input, final TwoDimensionalMap<Character> map) {
        for (int x = 0; x < input.size(); x++) {
            for (int y = 0; y < input.get(x).length(); y++) {
                map.set(x, y, input.get(x).charAt(y));
            }
        }
    }
}
