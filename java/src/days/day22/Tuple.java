package days.day22;

import java.util.function.BiFunction;

public record Tuple<A, B>(A a, B b) implements Comparable<Tuple<A, B>> {

    public <C> C map(BiFunction<A, B, C> func) {
        return func.apply(a(), b());
    }

    @Override
    public int compareTo(Tuple<A, B> t) {
        if (a instanceof Comparable && t.a instanceof Comparable) {
            return ((Comparable) a).compareTo(t.a);
        }
        return 0;
    }
}