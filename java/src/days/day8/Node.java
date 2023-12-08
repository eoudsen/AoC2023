package days.day8;

public class Node {

    private final String name;
    private final String leftNode;
    private final String rightNode;

    public Node(final String line) {
        this.name = line.split("=")[0].strip();
        this.leftNode = line.split("=")[1].strip().substring(1, 4);
        this.rightNode = line.split(",")[1].strip().substring(0, 3);
    }

    public String getName() {
        return this.name;
    }

    public String getLeftNode() {
        return leftNode;
    }

    public String getRightNode() {
        return rightNode;
    }

    public static boolean endsWithZ(final Node node) {
        return node.getName().endsWith("Z");
    }

    public static boolean equalsZzz(final Node node) {
        return node.getName().equals("ZZZ");
    }
}
