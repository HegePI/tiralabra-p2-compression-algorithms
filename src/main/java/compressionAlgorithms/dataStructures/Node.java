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

    public Node(Integer value) {
        this.value = value;
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

    @Override
    public String toString() {
        if (this.character != null && this.value != null) {
            return "(Char: " + this.character + ", value: " + this.value + ")";
        } else if (this.character == null && this.value != null && this.leftChildNode != null
                && this.rightChildNode != null) {
            return "(leftchild: " + this.leftChildNode.toString() + ", value: " + this.value
                    + ", rightchild: " + this.rightChildNode.toString() + ")";
        }
        return "()";
    }
}
