package days.day20;

import java.util.function.BiFunction;

public record Tuple(Long a, Long b) {

    public <C> C map(final BiFunction<Long, Long, C> function) {
        return function.apply(a(), b());
    }
}