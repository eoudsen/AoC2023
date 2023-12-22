package days.day22;

import util.Util;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static days.day22.ListMap.toListMap;

public class Day22 {

    static int idCounter = 0;

    public static long part1(final List<String> input) {
        return solve(input, true);
    }

    public static long part2(final List<String> input) {
        return solve(input, false);
    }

    private static long solve(final List<String> input, final boolean part1) {
        final List<Brick> inputBricks = new ArrayList<>();
        for (final String line : input) {
            final String left = line.split("~")[0];
            final String right = line.split("~")[1];
            final String[] leftLeft = left.split(",");
            final String[] rightRight = right.split(",");
            inputBricks.add(new Brick(
                    Integer.parseInt(leftLeft[0]),
                    Integer.parseInt(leftLeft[1]),
                    Integer.parseInt(leftLeft[2]),
                    Integer.parseInt(rightRight[0]),
                    Integer.parseInt(rightRight[1]),
                    Integer.parseInt(rightRight[2])
            ));
        }

        final Set<Brick> bricks = dropBricks(inputBricks);
        final ListMap<Brick, Brick> supportedBy = findSupported(bricks, (b1, b2) -> new Tuple<>(b2, b1));
        final ListMap<Brick, Brick> supports = findSupported(bricks, Tuple::new);
        final Stream<Brick> stream = bricks.parallelStream().filter(b -> part1 == (!supports.containsKey(b) || supports.get(b).stream().allMatch(b2 -> supportedBy.get(b2).size() > 1)));
        return part1 ? stream.count() : stream.mapToLong(b -> collectAll(b, bricks)).sum();
    }

    private static ListMap<Brick, Brick> findSupported(final Set<Brick> bricks, final BiFunction<Brick, Brick, Tuple<Brick, Brick>> toPair) {
        return bricks
                .parallelStream()
                .flatMap(b ->
                        bricks.stream()
                                .filter(b2 -> !b.equals(b2))
                                .filter(b2 -> b.cubes().stream().anyMatch(c -> b2.cubes().contains(c.move(0, 0, 1))))
                                .map(b2 -> toPair.apply(b, b2))
                ).collect(toListMap(Tuple::a, Tuple::b));
    }

    private static long collectAll(final Brick brick, final Set<Brick> parentBricks) {
        final Set<Brick> bricksToFall = new HashSet<>(parentBricks);
        bricksToFall.remove(brick);
        return dropBricks(bricksToFall).stream().filter(b -> !parentBricks.contains(b)).count();
    }

    public static Set<Brick> dropBricks(final Collection<Brick> bricksToDrop) {
        final Builder<Set<Brick>> bricks = new Builder<>(HashSet::new);
        bricks.getNew().addAll(new HashSet<>(bricksToDrop));
        while (!bricks.get().equals(bricks.getNew())) {
            bricks.refresh();
            bricks.setNew(bricks.get().parallelStream().map(b -> b.cubes().stream().anyMatch(c -> c.z == 1 || bricks.get().stream().filter(b2 -> !b.equals(b2)).anyMatch(b2 -> b2.cubes().contains(c.move(0, 0, -1)))) ? b : new Brick(b.id(), b.cubes().stream().map(c -> c.move(0, 0, -1)).toList())).collect(Collectors.toSet()));
        }
        return bricks.get();
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(22);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
