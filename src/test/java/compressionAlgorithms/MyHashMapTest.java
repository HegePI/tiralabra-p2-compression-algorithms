package compressionAlgorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import compressionAlgorithms.datastructures.MyHashMap;
import compressionAlgorithms.datastructures.MyHashMapEntry;

public class MyHashMapTest {

    @Test
    public void testInsertEntry() {
        MyHashMap<Integer, String> map = new MyHashMap<Integer, String>();
        System.out.println(1);
        map.insertEntry(new MyHashMapEntry<Integer, String>(1, "Hello, map"));
        System.out.println(2);
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
}
