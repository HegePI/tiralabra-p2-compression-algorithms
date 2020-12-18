package compressionAlgorithms.dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

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
        assertEquals(Character.valueOf('b'), nodeC.getRightChildNode().getCharacter());
    }

    @Test
    public void testCompareNodesBigger() {
        assertEquals(1, nodeB.compareTo(nodeA));
    }

    @Test
    public void testCompareNodesSmaller() {
        assertEquals(-1, nodeA.compareTo(nodeB));
    }

    @Test
    public void testToString() {
        assertEquals("(leftchild: (Char: a, value: 3), value: 7, rightchild: (Char: b, value: 4))",
                nodeC.toString());
    }
}
