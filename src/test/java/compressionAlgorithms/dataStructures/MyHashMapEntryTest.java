package compressionAlgorithms.dataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MyHashMapEntryTest {

    @Test
    public void testGetKey() {
        MyHashMapEntry<String, Integer> entry = new MyHashMapEntry<String, Integer>("Abba", 259);
        assertEquals("Abba", entry.getKey());
    }

    @Test
    public void testGetValue() {
        MyHashMapEntry<String, Integer> entry = new MyHashMapEntry<String, Integer>("Abba", 259);
        assertEquals(259, entry.getValue());
    }

    @Test
    public void testGetNextEntry() {
        MyHashMapEntry<String, Integer> entryA = new MyHashMapEntry<String, Integer>("Abba", 259);
        MyHashMapEntry<String, Integer> entryB = new MyHashMapEntry<String, Integer>("Baab", 260);
        entryA.setNextEntry(entryB);

        assertEquals("Baab", entryA.getNextEntry().getKey());
        assertEquals(260, entryA.getNextEntry().getValue());
    }

    @Test
    public void testKeysAreSameTrue() {
        MyHashMapEntry<String, Integer> entry = new MyHashMapEntry<String, Integer>("Abba", 259);
        assertEquals(true, entry.keysAreSame("Abba"));
    }

    @Test
    public void testKeysAreSameFalse() {
        MyHashMapEntry<String, Integer> entry = new MyHashMapEntry<String, Integer>("Abba", 259);
        assertEquals(false, entry.keysAreSame("Baab"));
    }

}
