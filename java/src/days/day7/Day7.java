package days.day7;

import util.Util;

import java.util.Collections;
import java.util.List;

public class Day7 {

    public static Long part1(final List<String> input) {
        final List<Hand> list = new java.util.ArrayList<>(input.stream()
                .map(line -> new Hand(line.split(" ")[0], Long.parseLong(line.split(" ")[1])))
                .toList());
        Collections.sort(list);
        long ans = 0;
        for (int i = 0; i < list.size(); i++) {
            ans += (i + 1) * list.get(i).getBid();
        }
        return ans;
    }

    public static Long part2(final List<String> input) {
        final List<Hand2> list = new java.util.ArrayList<>(input.stream()
                .map(line -> new Hand2(line.split(" ")[0], Long.parseLong(line.split(" ")[1])))
                .toList());
        Collections.sort(list);
        long ans = 0;
        for (int i = 0; i < list.size(); i++) {
            ans += (i + 1) * list.get(i).getBid();
        }
        return ans;
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(7);
//        var fileContent = Util.getTestFileContent(7);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
