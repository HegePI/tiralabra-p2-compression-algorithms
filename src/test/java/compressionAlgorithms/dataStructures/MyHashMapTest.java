package compressionAlgorithms.dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MyHashMapTest {

    @Test
    public void testInsertEntry() {
        MyHashMap<Integer, String> map = new MyHashMap<Integer, String>();
        map.insertEntry(new MyHashMapEntry<Integer, String>(1, "Hello, map"));
        assertEquals(true, map.containsKey(1));
        assertEquals("Hello, map", map.getValue(1));
    }

    @Test
    public void testInsertMany() {
        String[] entries = {"Abba", "Judas priest", "Gorillaz", "Turmion kätilöt"};

        MyHashMap<String, Integer> map = new MyHashMap<>();

        for (String s : entries) {
            map.insertEntry(new MyHashMapEntry<String, Integer>(s, 10));
        }

        boolean success = true;
        for (String s : entries) {
            if (map.getValue(s) != 10) {
                success = false;
                break;
            }
        }

        assertEquals(true, success);
    }

    @Test
    public void testGetSize() {
        String[] entries = {"Abba", "Judas priest", "Gorillaz", "Turmion kätilöt"};

        MyHashMap<String, Integer> map = new MyHashMap<>();

        for (String s : entries) {
            map.insertEntry(new MyHashMapEntry<String, Integer>(s, 10));
        }

        assertEquals(4, map.getSize());
    }

    @Test
    public void testContainsKeyTrue() {
        String[] entries = {"Abba", "Judas priest", "Gorillaz", "Turmion kätilöt"};

        MyHashMap<String, Integer> map = new MyHashMap<>();

        for (String s : entries) {
            map.insertEntry(new MyHashMapEntry<String, Integer>(s, 10));
        }

        assertEquals(true, map.containsKey("Gorillaz"));
    }
}
