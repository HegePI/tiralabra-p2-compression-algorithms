package compressionAlgorithms.dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MyMinHeapTest {

    @Test
    public void testInsertNode() {
        MyMinHeap nodes = new MyMinHeap();

        for (int i = 0; i < 10; i++) {
            nodes.insertNode(new Node((char) i, i));
        }

        assertEquals(10, nodes.getSize());


    }

    @Test
    public void testRemoveNode() {
        MyMinHeap nodes = new MyMinHeap();

        for (int i = 0; i < 10; i++) {
            nodes.insertNode(new Node((char) i, i));
        }

        for (int i = 0; i < 10; i++) {
            nodes.getFirstNode();
        }

        assertEquals(0, nodes.getSize());
    }

    @Test
    public void testConstructMinHeap() {
        MyMinHeap nodes = new MyMinHeap();

        for (int i = 0; i < 10; i++) {
            nodes.insertNode(new Node((char) i, i));
        }

        nodes.minHeap();

        Node n = nodes.getFirstNode();

        assertEquals(n.getCharacter().charValue(), (char) 0);
    }

    @Test
    public void testConstructMinHeap2() {
        MyMinHeap nodes = new MyMinHeap();

        nodes.insertNode(new Node('a', 2));
        nodes.insertNode(new Node('b', 3));
        nodes.insertNode(new Node('c', 4));

        Node first = nodes.getFirstNode();
        Node second = nodes.getFirstNode();

        assertEquals('a', first.getCharacter());
        assertEquals('b', second.getCharacter());

        nodes.insertNode(new Node(first, second));

        Node third = nodes.getFirstNode();

        assertEquals(third.getCharacter(), 'c');
    }

}
