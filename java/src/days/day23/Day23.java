package days.day23;

import util.Coordinate;
import util.NewGrid;
import util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static days.day23.Direction.*;
import static java.util.stream.Collectors.toSet;

public class Day23 {

    public static long part1(final List<String> input) {
        return solve(input, false);
    }

    public static long part2(final List<String> input) {
        return solve(input, true);
    }

    private static long solve(final List<String> input, final boolean part2) {
        final NewGrid newGrid = new NewGrid(input);
        final Coordinate start = new Coordinate(0, 1);
        final Builder<Set<Path>> curr = new Builder<>(HashSet::new);
        curr.get().add(new Path(new HashSet<>(List.of(start)), start));
        final Coordinate target = new Coordinate(newGrid.getGrid()[0].length - 1, newGrid.getGrid().length - 2);
        long longest = 0;
        while (!curr.get().isEmpty()) {
            curr.setNew(curr.get().parallelStream().flatMap(path -> {
                final Coordinate loc = path.currentLoc();
                return Stream.of(NORTH, SOUTH, EAST, WEST)
                        .filter(d -> checkDirection(d, newGrid.getGrid()[loc.x()][loc.y()], part2) && checkDirection(d, safeCheck(d.move(loc), newGrid), part2))
                        .map(d -> d.move(loc))
                        .filter(l -> !path.visited().contains(l))
                        .map(l -> new Path(new HashSet<>(path.visited()) {{
                            add(l);
                        }}, l));
            }).collect(toSet()));
            longest = Math.max(longest, curr.getNew().stream().mapToInt(p -> p.visited().size() - 1).max().getAsInt());
            curr.getNew().removeIf(p -> p.currentLoc().equals(target));
            curr.refresh();
        }
        return longest;
    }

    private static char safeCheck(final Coordinate location, final NewGrid newGrid) {
        if (location.x() < 0) {
            return '#';
        }
        if (location.y() < 0) {
            return '#';
        }
        if (location.x() >= newGrid.getGrid().length) {
            return '#';
        }
        if (location.y() >= newGrid.getGrid()[0].length) {
            return '#';
        }
        return newGrid.getGrid()[location.x()][location.y()];
    }

    private static boolean checkDirection(final Direction d, final char c, final boolean part2) {
        return switch (c) {
            case '>' -> part2 || d == EAST;
            case '<' -> part2 || d == WEST;
            case '^' -> part2 || d == NORTH;
            case 'v' -> part2 || d == SOUTH;
            case '#', 0 -> false;
            default -> true;
        };
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(23);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
