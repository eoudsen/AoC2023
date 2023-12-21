package days.day21;

import java.util.function.BiFunction;

public record Tuple(Long a, Long b) {

    public static Tuple tuple(final long a, final Long b) {
        return new Tuple(a, b);
    }

    public <C> C map(final BiFunction<Long, Long, C> function) {
        return function.apply(a(), b());
    }
}