package days.day8;

import util.Util;

import java.util.*;
import java.util.function.Function;

public class Day8 {

    public static Long part1(final List<String> input) {
        final char[] rLInstructions = input.get(0).toCharArray();
        final Map<String, Node> nodeMap = initializeNodes(input);

        return calculateAToZ(rLInstructions, nodeMap.get("AAA"), nodeMap, Node::equalsZzz);
    }

    public static Long part2(final List<String> input) {
        final char[] rLInstructions = input.get(0).toCharArray();
        final Map<String, Node> nodeMap = initializeNodes(input);

        final Long[] cycleLenghts = nodeMap.entrySet().stream()
                .filter(entry -> entry.getKey().endsWith("A"))
                .map(Map.Entry::getValue)
                .map(node -> calculateAToZ(rLInstructions, node, nodeMap, Node::endsWithZ))
                .toArray(Long[]::new);
        return leastCommonMultiple(cycleLenghts);
    }

    private static long calculateAToZ(char[] rLInstructions, Node currentNode, Map<String, Node> nodeMap, Function<Node, Boolean> stopFunction) {
        int steps = 0;
        while (true) {
            for (char rLInstruction : rLInstructions) {
                currentNode = nodeMap.get(getStringNodeFromInstruction(rLInstruction, currentNode));
                steps += 1;
                if (stopFunction.apply(currentNode)) {
                    return steps;
                }
            }
        }
    }

    private static Map<String, Node> initializeNodes(final List<String> input) {
        final Map<String, Node> nodeMap = new HashMap<>();
        final List<Node> nodeList = input.subList(2, input.size()).stream().map(Node::new).toList();
        nodeList.forEach(node -> nodeMap.put(node.getName(), node));
        return nodeMap;
    }

    private static String getStringNodeFromInstruction(final char instruction, final Node currentNode) {
        if (instruction == 'L') {
            return currentNode.getLeftNode();
        }
        return currentNode.getRightNode();
    }

    private static long greatestCommonDenominator(final long x, final long y) {
        return (y == 0) ? x : greatestCommonDenominator(y, x % y);
    }

    public static long leastCommonMultiple(final Long[] numbers) {
        return Arrays.stream(numbers).reduce(1L, (x, y) -> x * (y / greatestCommonDenominator(x, y)));
    }

    public static void main(String... args) {
        var fileContent = Util.getFileContent(8);
        System.out.println(part1(fileContent));
        System.out.println(part2(fileContent));
    }
}
