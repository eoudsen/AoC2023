package days.day2;

import java.util.*;

public class Game {

    private final int gameId;
    private final List<Show> showings;

    public Game(final String input) {
        this.gameId = Integer.parseInt(input.split(":")[0].split(" ")[1]);
        this.showings = Arrays.stream(input.split(":")[1].split(";")).map(Show::new).toList();
    }

    public int getGameId() {
        return this.gameId;
    }

    public boolean validShowings(final int red, final int green, final int blue) {
        return showings.stream()
                .filter(show -> show.getRed() > red || show.getGreen() > green || show.getBlue() > blue)
                .findAny()
                .isEmpty();
    }

    public int calculatePower() {
        int minBlue = Integer.MIN_VALUE;
        int minGreen = Integer.MIN_VALUE;
        int minRed = Integer.MIN_VALUE;
        for (final Show showing : showings) {
            if (showing.getRed() > minRed) {
                minRed = showing.getRed();
            }
            if (showing.getGreen() > minGreen) {
                minGreen = showing.getGreen();
            }
            if (showing.getBlue() > minBlue) {
                minBlue = showing.getBlue();
            }
        }
        return minBlue * minGreen * minRed;
    }


    private static class Show {

        private int blue = 0;
        private int red = 0;
        private int green = 0;

        Show(final String show) {
            final String[] colors = show.strip().split(",");
            for (final String color : colors) {
                if (color.strip().endsWith("red")) {
                    this.red = Integer.parseInt(color.strip().split(" ")[0]);
                }
                else if (color.strip().endsWith("green")) {
                    this.green = Integer.parseInt(color.strip().split(" ")[0]);
                }
                else if (color.strip().endsWith("blue")) {
                    this.blue = Integer.parseInt(color.strip().split(" ")[0]);
                }
                else {
                    System.out.println("WRONG ANSWER");
                }
            }
        }

        public int getBlue() {
            return this.blue;
        }

        public int getRed() {
            return this.red;
        }

        public int getGreen() {
            return this.green;
        }
    }
}
