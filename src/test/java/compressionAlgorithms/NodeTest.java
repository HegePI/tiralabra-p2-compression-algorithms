package compressionAlgorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NodeTest {

    Node nodeA = new Node('a', 3);
    Node nodeB = new Node('b', 4);
    Node nodeC = new Node(nodeA, nodeB);

    @Test
    public void getCorrectChar() {
        assertEquals(Character.valueOf('a'), nodeA.getCharacter());
    }

    @Test
    public void getCorrectValue() {
        assertEquals(Integer.valueOf(4), nodeB.getValue());
    }

    @Test
    public void getCorrectLeftChild() {
        assertEquals(Character.valueOf('a'), nodeC.getLeftChildNode().getCharacter());
    }

    @Test
    public void getCorrectRightChildValue() {
        assertEquals(Character.valueOf('b'), nodeC.getRightChilNode().getCharacter());
    }
}