package days.day4;

import util.Util;

import java.util.*;

public class Day4 {

    public static Integer part1(final List<String> input) {
        return input.stream()
            .map(line -> line.replace("  ", " ").strip())
            .map(line -> line.split(":")[1].strip())
            .map(Day4::countNumMatch)
            .map(Day4::calculateScore)
            .reduce(0, Integer::sum);
    }

    private static int calculateScore(Integer numMatch) {
        if (numMatch == 1) {
            return 1;
        }
        if (numMatch > 1) {
            int ans = 1;
            for (int i = 1; i < numMatch; i++) {
                ans *= 2;
            }
            return ans;
        }
        return 0;
    }

    private static int countNumMatch(String line) {
        final String[] yourNumbers = line.split("\\|")[1].strip().split(" ");
        final Set<String> winningSet = new HashSet<>(Arrays.asList(line.split("\\|")[0].strip().split(" ")));
        int numMatch = 0;
        for (final String yourNumber : yourNumbers) {
            if (winningSet.contains(yourNumber)) {
                numMatch++;
            }
        }
        return numMatch;
    }

    public static Integer part2(final List<String> input) {
        List<Card> cards = input.stream().map(Card::new).toList();
        for (int i = 0; i < cards.size(); i++) {
            final Card currentCard = cards.get(i);
            int countNumMatch = countNumMatch(currentCard.getGame().replace("  ", " ").strip().split(":")[1].strip());
            for (int j = 1; j <= countNumMatch; j++) {
                cards.get(i + j).add(currentCard.getOccurenses());
            }
        }

        int sum = 0;
        for (final Card card : cards) {
            sum += card.getOccurenses();
        }
        return sum;
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(4);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
