package compressionAlgorithms.dataStructures;

public class MyHashMap<K, V> {

    private int DEFAULT_CAPACITY = 1001;
    private int entries;
    private MyHashMapEntry<K, V>[] entryTable;

    public MyHashMap() {
        this.entryTable = new MyHashMapEntry[DEFAULT_CAPACITY];
        this.entries = 0;
    }

    /**
     * Function which returns amount of entries in hashmap
     * 
     * @return amount of entries
     */
    public int getSize() {
        return this.entries;
    }

    /**
     * Checks whether given key is present in hashmap
     * 
     * @param key
     * @return true if present, false otherwise
     */
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }

        try {
            MyHashMapEntry<K, V> entry = entryTable[getIndexOf(key)];
            if (entry != null) {
                while (true && entry != null) {
                    if (entry.keysAreSame(key)) {
                        return true;
                    } else {
                        entry = entry.getNextEntry();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return false;
    }

    /**
     * Inerts given entry into table. Index is determined by entrys key. If entry is present at
     * index, old entry is set as next entry for new entry and new entry is stored in table.
     * 
     * @param newEntry
     */
    public void insertEntry(MyHashMapEntry<K, V> newEntry) {
        int index = getIndexOf(newEntry.getKey());
        if (entryTable[index] == null) {
            entryTable[index] = newEntry;
            entries++;
        } else {
            MyHashMapEntry<K, V> previousEntry = entryTable[index];
            newEntry.setNextEntry(previousEntry);
            entryTable[index] = newEntry;
            entries++;
        }
    }

    /**
     * returns given keys value
     * 
     * @param key
     * @return given keys value
     */
    public V getValue(K key) {
        int index = getIndexOf(key);
        try {
            MyHashMapEntry<K, V> entry = entryTable[index];
            while (true) {
                if (entry.keysAreSame(key)) {
                    return entry.getValue();
                } else {
                    entry = entry.getNextEntry();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new Error();
        }
    }

    /**
     * Helper function to define index of given key in table
     * 
     * @param key
     * @return keys index in table
     */
    private int getIndexOf(K key) {
        if (key != null) {
            int index = key.hashCode() % DEFAULT_CAPACITY;
            return index >= 0 ? index : -index;

        }
        throw new Error("entry is null");
    }

}
