package compressionAlgorithms.dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MyListTest {

    @Test
    public void testCorrectSizeNewList() {
        MyList<Integer> testList = new MyList<Integer>();
        assertEquals(0, testList.getSize());
    }

    @Test
    public void testCorrectSize() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals(3, testList.getSize());
    }

    @Test
    public void testContainsTrue() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals(true, testList.contains(11));
    }

    @Test
    public void testContainsFalse() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals(false, testList.contains(22));
    }

    @Test
    public void testGetIndexOfWhenInList() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals(1, testList.getIndexOf(11));
    }

    @Test
    public void testGetIndexOfWhenNotInList() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals(-1, testList.getIndexOf(22));
    }

    @Test
    public void testToStringEmpty() {
        MyList<Integer> testList = new MyList<Integer>();
        assertEquals("[]", testList.toString());

    }

    @Test
    public void testToString() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals("[1, 11, 111]", testList.toString());
    }

}
