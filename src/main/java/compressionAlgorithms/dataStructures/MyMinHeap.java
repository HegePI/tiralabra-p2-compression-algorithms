package compressionAlgorithms.dataStructures;

public class MyMinHeap {

    private Node[] heap;
    private int nodes;

    private final int DEFAULT_CAPACITY = 1000;

    public MyMinHeap() {
        this.heap = new Node[DEFAULT_CAPACITY];
        this.nodes = 0;
        this.heap[0] = new Node(Integer.MIN_VALUE);
    }

    public void insertNode(Node n) {
        if (n == null) {
            return;
        }
        this.nodes = this.nodes + 1;
        this.heap[this.nodes] = n;
        int current = this.nodes;

        while (this.heap[current].getValue().intValue() < this.heap[parent(current)].getValue()
                .intValue()) {
            swap(current, parent(current));
            current = parent(current);
        }

        if (this.nodes + 1 == this.DEFAULT_CAPACITY) {
            lenghthen();
        }
    }

    public Node getFirstNode() {
        Node root = this.heap[1];
        this.heap[1] = this.heap[this.nodes];
        this.heap[this.nodes] = null;
        this.nodes = this.nodes - 1;
        if (this.nodes > 1) {
            constructMinHeap(1);
        }
        return root;
    }

    public void minHeap() {
        for (int pos = (this.nodes / 2); pos >= 1; pos--) {
            constructMinHeap(pos);
        }
    }

    public int getSize() {
        return this.nodes;
    }

    private boolean nodeIsLeaf(int pos) {
        if (this.heap[getLeftChild(pos)] != null || this.heap[getRightChild(pos)] != null) {
            return false;
        }
        return true;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int getLeftChild(int pos) {
        return (2 * pos);
    }

    private int getRightChild(int pos) {
        return (2 * pos) + 1;
    }

    private void swap(int first, int second) {
        Node tmp = this.heap[first];
        this.heap[first] = this.heap[second];
        this.heap[second] = tmp;
    }

    private void constructMinHeap(int pos) {
        if (!nodeIsLeaf(pos)) {
            if (this.heap[getLeftChild(pos)] != null && this.heap[getRightChild(pos)] != null) {
                if (this.heap[pos].getValue().intValue() > this.heap[getLeftChild(pos)].getValue()
                        .intValue()
                        || this.heap[pos].getValue().intValue() > this.heap[getRightChild(pos)]
                                .getValue().intValue()) {
                    if (this.heap[getLeftChild(pos)].getValue()
                            .intValue() < this.heap[getRightChild(pos)].getValue().intValue()) {
                        swap(pos, getLeftChild(pos));
                        constructMinHeap(getLeftChild(pos));
                    } else {
                        swap(pos, getRightChild(pos));
                        constructMinHeap(getRightChild(pos));
                    }
                }
            }
            if (this.heap[getRightChild(pos)] == null && this.heap[getLeftChild(pos)] != null) {
                if (this.heap[getLeftChild(pos)].getValue().intValue() < this.heap[pos].getValue()
                        .intValue()) {
                    swap(pos, getLeftChild(pos));
                    constructMinHeap(getLeftChild(pos));
                }
            }
        }
    }

    private void lenghthen() {
        Node[] newHeap = (Node[]) new Object[this.nodes + (this.DEFAULT_CAPACITY / 2)];
        for (int i = 0; i < this.heap.length; i++) {
            newHeap[i] = this.heap[i];
        }
        this.heap = newHeap;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i = 1; i <= this.nodes; i++) {
            result = result + this.heap[i].toString() + "\n";
        }
        return result;
    }
}
