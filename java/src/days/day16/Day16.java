package days.day16;

import util.Coordinate;
import util.NewGrid;
import util.Util;

import java.util.*;

public class Day16 {

    public static Integer part1(final List<String> input) {
        final NewGrid mirrorGrid = new NewGrid(input);
        final Set<Coordinate> energizedCoordinates = new HashSet<>();
        Set<LightBeam> currentLightBeams = new HashSet<>();
        Set<LightBeam> newLightBeams = new HashSet<>();
        Set<LightBeam> lightBeamCache = new HashSet<>();
        currentLightBeams.add(new LightBeam(new Coordinate(0, -1), Direction.RIGHT));
        energizedCoordinates.add(new Coordinate(0, 0));
        int currentEnergizedTiles = energizedCoordinates.size();
        int previousEnergizedTiles = energizedCoordinates.size();
        int same = 0;

        while (true) {
            if (currentEnergizedTiles == previousEnergizedTiles) {
                same++;
                if (same > 100) {
                    break;
                }
            }
            previousEnergizedTiles = currentEnergizedTiles;
            for (final LightBeam lightBeam : currentLightBeams) {
                final Coordinate nextCoordinate = lightBeam.getNextCoordinate();
                if (isValidCoordinate(nextCoordinate, mirrorGrid)) {
                    energizedCoordinates.add(nextCoordinate);
                    List<LightBeam> lightBeams = lightBeam.determineNextLightBeams(mirrorGrid.getGrid()[nextCoordinate.x()][nextCoordinate.y()]);
                    for (final LightBeam beam : lightBeams) {
                        if (!lightBeamCache.contains(beam)) {
                            newLightBeams.add(beam);
                        }
                    }
                }
            }
            if (newLightBeams.isEmpty()) {
                break;
            }
            currentEnergizedTiles = energizedCoordinates.size();
            lightBeamCache.addAll(newLightBeams);
            currentLightBeams = new HashSet<>(newLightBeams);
            newLightBeams = new HashSet<>();
        }

        return energizedCoordinates.size();
    }

    public static Integer part2(final List<String> input) {
        final NewGrid mirrorGrid = new NewGrid(input);
        final List<LightBeam> edgeLightBeams = generateEdgeLightBeams(mirrorGrid);

        List<Integer> results = new ArrayList<>();

        for (final LightBeam startingLightBeam : edgeLightBeams) {
            final Set<Coordinate> energizedCoordinates = new HashSet<>();
            Set<LightBeam> currentLightBeams = new HashSet<>();
            Set<LightBeam> newLightBeams = new HashSet<>();
            Set<LightBeam> lightBeamCache = new HashSet<>();
            currentLightBeams.add(startingLightBeam);
            energizedCoordinates.add(startingLightBeam.getNextCoordinate());
            int currentEnergizedTiles = energizedCoordinates.size();
            int previousEnergizedTiles = energizedCoordinates.size();
            int same = 0;

            while (true) {
                if (currentEnergizedTiles == previousEnergizedTiles) {
                    same++;
                    if (same > 100) {
                        break;
                    }
                }
                previousEnergizedTiles = currentEnergizedTiles;
                for (final LightBeam lightBeam : currentLightBeams) {
                    final Coordinate nextCoordinate = lightBeam.getNextCoordinate();
                    if (isValidCoordinate(nextCoordinate, mirrorGrid)) {
                        energizedCoordinates.add(nextCoordinate);
                        List<LightBeam> lightBeams = lightBeam.determineNextLightBeams(mirrorGrid.getGrid()[nextCoordinate.x()][nextCoordinate.y()]);
                        for (final LightBeam beam : lightBeams) {
                            if (!lightBeamCache.contains(beam)) {
                                newLightBeams.add(beam);
                            }
                        }
                    }
                }
                if (newLightBeams.isEmpty()) {
                    break;
                }
                currentEnergizedTiles = energizedCoordinates.size();
                lightBeamCache.addAll(newLightBeams);
                currentLightBeams = new HashSet<>(newLightBeams);
                newLightBeams = new HashSet<>();
            }

            results.add(energizedCoordinates.size());
        }
        return Collections.max(results);
    }

    private static boolean isValidCoordinate(final Coordinate coordinate, final NewGrid newGrid) {
        if (coordinate.x() < 0 || coordinate.y() < 0) {
            return false;
        }
        if (coordinate.x() > newGrid.getGrid().length - 1) {
            return false;
        }
        if (coordinate.y() > newGrid.getGrid()[0].length - 1) {
            return false;
        }
        return true;
    }

    private static List<LightBeam> generateEdgeLightBeams(final NewGrid mirrorGrid) {
        final List<LightBeam> edgeLightBeams = new ArrayList<>();
        for (int i = 0; i < mirrorGrid.getGrid().length; i++) {
            edgeLightBeams.add(new LightBeam(new Coordinate(i, -1), Direction.RIGHT));
            edgeLightBeams.add(new LightBeam(new Coordinate(i, mirrorGrid.getGrid()[i].length), Direction.LEFT));
        }
        for (int i = 0; i < mirrorGrid.getGrid()[0].length; i++) {
            edgeLightBeams.add(new LightBeam(new Coordinate(-1, i), Direction.DOWN));
            edgeLightBeams.add(new LightBeam(new Coordinate(mirrorGrid.getGrid().length, i), Direction.UP));
        }
        return edgeLightBeams;
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(16);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
