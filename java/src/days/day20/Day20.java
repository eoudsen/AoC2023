package days.day20;

import util.Util;

import java.util.*;
import java.util.stream.LongStream;

import static java.lang.Long.MAX_VALUE;
import static java.util.stream.Collectors.toMap;

public class Day20 {

    public static long part1(final List<String> input) {
        final Map<String, Module> moduleMap = input.stream().map(Module::new).collect(toMap(Module::source, module -> module));
        final Map<String, Boolean> flipFlopState = moduleMap.keySet().stream()
                .filter(m -> moduleMap.get(m).t() == Type.FLIPFLOP)
                .collect(toMap(k -> k, k -> false));
        final Map<String, Map<String, Boolean>> conjunctionState = moduleMap.keySet().stream()
                .filter(m -> moduleMap.get(m).t() == Type.CONJUNCTION)
                .collect(toMap(key -> key, key -> moduleMap.entrySet().stream()
                        .filter(entry -> entry.getValue().targets().contains(key))
                        .map(Map.Entry::getKey)
                        .collect(toMap(key2 -> key2, key2 -> false))));
        return LongStream.range(0, 1000)
                .mapToObj(i -> sendPulse(moduleMap, flipFlopState, conjunctionState, new HashMap<>(), i))
                .reduce(new Tuple(0L, 0L),
                        (a, b) -> new Tuple(a.a() + b.a(), a.b() + b.b())).map((a, b) -> a * b);
    }

    public static long part2(final List<String> input) {
        final Map<String, Module> moduleMap = input.stream().map(Module::new).collect(toMap(Module::source, module -> module));
        final String rx = moduleMap.keySet().stream()
                .filter(m -> moduleMap.get(m).targets().contains("rx")).findFirst().get();
        final Map<String, Boolean> flipFlopState = moduleMap.keySet().stream()
                .filter(m -> moduleMap.get(m).t() == Type.FLIPFLOP)
                .collect(toMap(k -> k, k -> false));
        final Map<String, Map<String, Boolean>> conjunctionState = moduleMap.keySet().stream()
                .filter(m -> moduleMap.get(m).t() == Type.CONJUNCTION)
                .collect(toMap(k -> k, k -> moduleMap.entrySet().stream()
                        .filter(e -> e.getValue().targets().contains(k)).map(Map.Entry::getKey)
                        .collect(toMap(k2 -> k2, k2 -> false))));
        final Map<String, Long> lcms = conjunctionState.get(rx).keySet().stream().collect(toMap(e -> e, e -> 0L));
        return LongStream.range(1, MAX_VALUE)
                .map(i -> sendPulse(moduleMap, flipFlopState, conjunctionState, lcms, i).a())
                .filter(e -> e != 0L)
                .findFirst()
                .getAsLong();
    }

    private static Tuple sendPulse(final Map<String, Module> moduleMap, final Map<String, Boolean> flipFlopState, final Map<String, Map<String, Boolean>> conjunctionState, final Map<String, Long> lcms, final long i) {
        Tuple out = new Tuple(0L, 0L);
        final LinkedList<Signal> pulseQueue = new LinkedList<>();
        pulseQueue.add(new Signal("", "broadcaster", false));
        while (!pulseQueue.isEmpty()) {
            final Signal pulse = pulseQueue.pop();
            final boolean isHigh = pulse.isHigh();
            out = out.map((a, b) -> new Tuple(a + (isHigh ? 0 : 1), b + (isHigh ? 1 : 0)));
            if (isHigh && lcms.containsKey(pulse.from())) {
                lcms.put(pulse.from(), i);
                if (lcms.values().stream().allMatch(e -> e != 0L)) {
                    return new Tuple(lcm(lcms.values().stream().mapToLong(e -> e).toArray()), 0L);
                }
            }
            if (!moduleMap.containsKey(pulse.to())) {
                continue;
            }
            final Module module = moduleMap.get(pulse.to());
            if (module.t() == Type.BROADCAST) {
                module.targets().forEach(t -> pulseQueue.add(new Signal(pulse.to(), t, isHigh)));
            }
            else if (module.t() == Type.CONJUNCTION) {
                conjunctionState.get(pulse.to()).put(pulse.from(), isHigh);
                if (conjunctionState.get(pulse.to()).values().stream().allMatch(b -> b)) {
                    module.targets().forEach(t -> pulseQueue.add(new Signal(pulse.to(), t, false)));
                }
                else {
                    module.targets().forEach(t -> pulseQueue.add(new Signal(pulse.to(), t, true)));
                }
            }
            else {
                if (!isHigh) {
                    final boolean newState = !flipFlopState.get(module.source());
                    flipFlopState.put(module.source(), newState);
                    module.targets().forEach(t -> pulseQueue.add(new Signal(pulse.to(), t, newState)));
                }
            }
        }
        if (!lcms.isEmpty()) {
            return new Tuple(0L, 0L);
        }
        return out;
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long[] input) {
        long result = input[0];
        for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(20);
//        var fileContent = Util.getTestFileContent(20);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
