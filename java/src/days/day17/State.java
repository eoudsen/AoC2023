package days.day17;


import java.util.List;

public abstract class State implements Comparable<State> {

    public abstract double getCost();
    public abstract List<State> next();
    public abstract String toString();
    public abstract boolean isTerm();

    public String getHash() {
        return HashUtil.getHash(toString());
    }

    public double getEstimate() {
        return 0.0;
    }

    public double getLean() {
        return 0.0;
    }

    @Override
    public int compareTo(State o) {
        return getHash().compareTo(o.getHash());
    }
}
