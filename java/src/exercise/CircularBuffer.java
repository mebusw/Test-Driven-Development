package exercise;

public class CircularBuffer {
    private int index;
    private int outdex;
    private int m[];
    private int capacity;

    public CircularBuffer(int i) {
        this.capacity = i + 1;
        m = new int[capacity];
        index = 0;
        outdex = 0;
    }

    public void destroy() {

    }

    public boolean isEmpty() {
        return index == outdex;
    }

    public boolean isFull() {
        return (outdex + capacity - index) % capacity == 1;
    }

    public boolean put(int i) {
        if (this.isFull())
            return false;
        m[index] = i;
        index++;
        index %= this.capacity;

        return true;
    }

    public int get() {
        if (this.isEmpty())
            return 0;
        int curr = m[outdex % this.capacity];
        outdex++;
        outdex %= this.capacity;
        return curr;
    }

    public int capacity() {
        return this.capacity - 1;
    }

}
