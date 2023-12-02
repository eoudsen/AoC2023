package days.day2;

import util.Util;

import java.util.List;

public class Day2 {

    public static Integer part1(final List<String> input) {
        return input.stream()
                .map(Game::new)
                .filter(game -> game.validShowings(12, 13, 14))
                .map(Game::getGameId)
                .reduce(0 , Integer::sum);
    }

    public static Integer part2(final List<String> input) {
        return input.stream()
                .map(Game::new)
                .map(Game::calculatePower)
                .reduce(0, Integer::sum);
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(2);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
