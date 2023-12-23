package days.day23;

import util.Coordinate;

import java.util.Set;

public record Path(Set<Coordinate> visited, Coordinate currentLoc) {
}