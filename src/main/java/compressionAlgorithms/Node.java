package compressionAlgorithms;

public class Node implements Comparable<Node> {

    Character character;
    Integer value;
    Node leftChildNode;
    Node rightChildNode;

    Node(Character character, Integer frequency) {
        this.character = character;
        this.value = frequency;
        this.leftChildNode = null;
        this.rightChildNode = null;
    }

    Node(Node leftNode, Node rightNode) {
        this.leftChildNode = leftNode;
        this.rightChildNode = rightNode;
        this.value = leftNode.value + rightNode.value;
    }

    Integer getValue() {
        return this.value;
    }

    Character getCharacter() {
        return this.character;
    }

    Node getLeftChildNode() {
        return this.leftChildNode;
    }

    Node getRightChildNode() {
        return this.rightChildNode;
    }

    Boolean isLeaf() {
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
