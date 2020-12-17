package compressionAlgorithms.dataStructures;

import java.io.Serializable;

public class MyList<K> implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    private K[] list;
    private final int DEFAULT_SIZE = 20;
    private int entries = 0;

    public MyList() {
        this.list = (K[]) new Object[DEFAULT_SIZE];

    }

    public MyList(int size) {
        this.list = (K[]) new Object[size];
    }

    public K get(int index) {
        return this.list[index];
    }

    public void append(K object) {
        if (object != null) {
            if (entries == this.list.length - 1) {
                this.list = createNewList();
                this.list[entries] = object;
                entries++;
            } else {
                this.list[entries] = object;
                entries++;
            }
        }
    }

    public boolean contains(K object) {
        if (this.list instanceof String[] && object instanceof String) {
            for (String s : (String[]) this.list) {
                if (stringEquality((String) object, s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int getIndexOf(K object) {
        if (this.list instanceof String[] && object instanceof String) {
            for (int i = 0; i < this.entries; i++) {
                if (stringEquality((String) this.list[i], (String) object)) {
                    return i;
                }
            }
            return -1;
        }
        throw new Error();
    }

    public int getSize() {
        return this.entries;
    }

    private K[] createNewList() {
        int newSize = this.list.length + DEFAULT_SIZE;
        K[] newList = (K[]) new Object[newSize];

        for (int i = 0; i < this.list.length; i++) {
            newList[i] = this.list[i];
        }

        return newList;
    }

    private boolean stringEquality(String arg1, String arg2) {
        return arg1.equals(arg2);
    }

    @Override
    public String toString() {
        String result = "[";
        if (this.entries == 0) {
            result = result + "]";
            return result;
        }

        for (int i = 0; i < this.entries - 1; i++) {
            result = result + this.list[i] + ", ";
        }
        result = result + this.list[this.entries - 1] + "]";
        return result;

    }

}
