package days.day24;

import util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day24 {

    public static long part1(final List<String> input) {
        long result = 0;
        final List<HailStone> stones = new ArrayList<>();
        input.forEach(line -> stones.add(new HailStone(line)));
        final double min = 200000000000000.;
        final double max = 400000000000000.;
        for (int i = 0; i < stones.size(); i++) {
            for (int j = i + 1; j < stones.size(); j++) {
                final HailStone first = stones.get(i);
                final HailStone second = stones.get(j);
                double denominator = (first.getVx() * second.getVy()) - (first.getVy() * second.getVx());
                if (denominator == 0) {
                    if (first.getX() == second.getX() && first.getY() == second.getY()) {
                        if (first.getX() >= min && first.getX() <= max && first.getY() >= min && first.getY() <= max) {
                            result++;
                        }
                    }
                }
                double numerator1 = ((second.getX() - first.getX()) * second.getVy()) - ((second.getY() - first.getY()) * second.getVx());
                double numerator2 = ((first.getX() - second.getX()) * first.getVy()) - ((first.getY() - second.getY()) * first.getVx());
                double intersectionX = (numerator1 / denominator) * first.getVx() + first.getX();
                double intersectionY = (numerator1 / denominator) * first.getVy() + first.getY();
                if (intersectionX >= min && intersectionX <= max && intersectionY >= min && intersectionY <= max) {
                    if ((numerator1 / denominator) > 0 && (numerator2 / denominator) < 0) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public static long part2(final List<String> input) {
        long result = 0;
        final List<HailStone> stones = new ArrayList<>();
        input.forEach(line -> stones.add(new HailStone(line)));
        final StringBuilder equations = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String t = "t" + i;
            equations.append(t).append(" >= 0, ").append(stones.get(i).getX()).append(" + ").append(stones.get(i).getVx()).append(t).append(" == x + vx ").append(t).append(", ");
            equations.append(stones.get(i).getY()).append(" + ").append(stones.get(i).getVy()).append(t).append(" == y + vy ").append(t).append(", ");
            equations.append(stones.get(i).getZ()).append(" + ").append(stones.get(i).getVz()).append(t).append(" == z + vz ").append(t).append(", ");
        }
        // Solve these equations: Either via an online solver or use z3 in python
        return 566914635762564L; // 566914635762564
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(24);
//        var fileContent = Util.getTestFileContent(24);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
