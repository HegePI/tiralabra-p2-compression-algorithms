package compressionAlgorithms.dataStructures;

public class Node implements Comparable<Node> {

    Character character;
    Integer value;
    Node leftChildNode;
    Node rightChildNode;

    public Node(Character character, Integer frequency) {
        this.character = character;
        this.value = frequency;
        this.leftChildNode = null;
        this.rightChildNode = null;
    }

    public Node(Node leftNode, Node rightNode) {
        this.leftChildNode = leftNode;
        this.rightChildNode = rightNode;
        this.value = leftNode.value + rightNode.value;
    }

    public Integer getValue() {
        return this.value;
    }

    public Character getCharacter() {
        return this.character;
    }

    public Node getLeftChildNode() {
        return this.leftChildNode;
    }

    public Node getRightChildNode() {
        return this.rightChildNode;
    }

    public Boolean isLeaf() {
        if (this.character != null) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Node node) {
        if (this.value >= node.getValue()) {
            return 1;
        }
        return -1;
    }
}
