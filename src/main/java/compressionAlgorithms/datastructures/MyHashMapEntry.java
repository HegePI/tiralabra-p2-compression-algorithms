package compressionAlgorithms.datastructures;

public class MyHashMapEntry<K, V> {

    private final K key;
    private final V value;
    private MyHashMapEntry<K, V> nextEntry;

    public MyHashMapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Returns key of an entry
     * 
     * @return
     */
    public K getKey() {
        return this.key;
    }

    /**
     * Returns value of an entry
     * 
     * @return
     */
    public V getValue() {
        return this.value;
    }

    /**
     * Returns the entry linked to this entry
     * 
     * @return
     */
    public MyHashMapEntry<K, V> getNextEntry() {
        return this.nextEntry;
    }

    /**
     * Links next entry to this entry
     * 
     * @param entry to be linked entry
     */
    public void setNextEntry(MyHashMapEntry<K, V> entry) {
        this.nextEntry = entry;
    }

    /**
     * Checks if this entries and other entries keys are the same
     * 
     * @param o
     */
    public boolean keysAreSame(K o) {
        if (o == null || this.key.getClass().getName() != o.getClass().getName()) {
            return false;
        }
        if (this.key.getClass().getName() == "java.lang.String") {
            String k = (String) o;
            String ek = (String) this.key;
            if (ek.equals(k)) {
                return true;
            }
        }
        if (this.key.getClass().getName() == "java.lang.Integer") {
            Integer k = (Integer) o;
            Integer ek = (Integer) this.key;
            if (ek.equals(k)) {
                return true;
            }
        }

        return false;


    }
}
