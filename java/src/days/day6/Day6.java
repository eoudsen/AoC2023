package days.day6;

import java.util.HashSet;
import java.util.Set;

public class Day6 {

    public static Long part1() {
        final Set<Race> races = new HashSet<>();
        races.add(new Race(49, 263));
        races.add(new Race(97, 1532));
        races.add(new Race(94, 1378));
        races.add(new Race(94, 1851));

        long ans = 1;
        for (final Race race : races) {
            int numWins = 0;
            for (int i = 0; i < race.time(); i++) {
                long distance = i * (race.time() - i);
                if (distance > race.distance()) {
                    numWins++;
                }
            }
            ans *= numWins;
        }
        return ans;
    }

    public static Long part2() {
        final Race race = new Race(49979494, 263153213781851L);
        long numWins = 0;
        for (int i = 0; i < race.time(); i++) {
            long distance = i * (race.time() - i);
            if (distance > race.distance()) {
                numWins++;
            }
        }
        return numWins;
    }

    public static void main(String... args) {
        System.out.println(part1());
        System.out.println(part2());
    }
}
