package days.day17;

import util.Util;

import java.util.List;

public class Day17 {

    public static Double part1(final List<String> input) {
        final TwoDimensionalMap<Character> characterTwoDimensionalMap = new TwoDimensionalMap<>(' ');
        TwoDimensionalMap.load(input, characterTwoDimensionalMap);
        SearchState p1 = (SearchState) Searcher.search(List.of(new SearchState(new MapPoint(0,0), 0, Navigator.EAST, 0, characterTwoDimensionalMap)));
        return p1.getCost();
    }

    public static Double part2(final List<String> input) {
        final TwoDimensionalMap<Character> characterTwoDimensionalMap = new TwoDimensionalMap<>(' ');
        TwoDimensionalMap.load(input, characterTwoDimensionalMap);
        SearchState2 p1 = (SearchState2) Searcher.search(
                List.of(
                        new SearchState2(new MapPoint(0,0), 0, Navigator.EAST, 0, characterTwoDimensionalMap),
                        new SearchState2(new MapPoint(0,0), 0, Navigator.SOUTH, 0, characterTwoDimensionalMap))
        );
        return p1.getCost();
    }

    public static int getCostP(final MapPoint p, final TwoDimensionalMap<Character> map) {
        char z = map.get(p);
        if (z == ' ') return -1;
        return (int)(z - '0');
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(17);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
