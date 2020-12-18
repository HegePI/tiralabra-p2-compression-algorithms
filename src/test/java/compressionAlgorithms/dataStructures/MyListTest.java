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
    public void testContainsIntegerTrue() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals(true, testList.contains(11));
    }

    @Test
    public void testContainsIntegerFalse() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals(false, testList.contains(22));
    }

    @Test
    public void testGetIndexOfIntegerWhenInList() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals(1, testList.getIndexOf(11));
    }

    @Test
    public void testGetIndexOfIntegerWhenNotInList() {
        MyList<Integer> testList = new MyList<Integer>();
        testList.append(1);
        testList.append(11);
        testList.append(111);
        assertEquals(-1, testList.getIndexOf(22));
    }

    @Test
    public void testContainsStringTrue() {
        MyList<String> testList = new MyList<>();
        testList.append("hei");
        testList.append("hei hei");
        testList.append("hei hei hei");
        assertEquals(true, testList.contains("hei hei"));
    }

    @Test
    public void testContainsStringFalse() {
        MyList<String> testList = new MyList<>();
        testList.append("hei");
        testList.append("hei hei");
        testList.append("hei hei hei");
        assertEquals(false, testList.contains("moi moi"));
    }

    @Test
    public void testGetIndexOfStringWhenInList() {
        MyList<String> testList = new MyList<>();
        testList.append("hei");
        testList.append("hei hei");
        testList.append("hei hei hei");
        assertEquals(1, testList.getIndexOf("hei hei"));
    }

    @Test
    public void testGetIndexOfStringWhenNotInList() {
        MyList<String> testList = new MyList<>();
        testList.append("hei");
        testList.append("hei hei");
        testList.append("hei hei hei");
        assertEquals(-1, testList.getIndexOf("moi moi"));
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
