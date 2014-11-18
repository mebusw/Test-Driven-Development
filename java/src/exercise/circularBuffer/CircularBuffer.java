package exercise.circularBuffer;

public class CircularBuffer {
    private int index;
    private int outdex;
    private int values[];
    private int capacity;

    public CircularBuffer(int i) {
        this.capacity = i + 1;
        values = new int[capacity];
        index = 0;
        outdex = 0;
    }

    public void destroy() {

    }

    public boolean isEmpty() {
        return index == outdex;
    }

    public boolean isFull() {
        return capacity - count() == 1;
    }

    public boolean put(int i) {
        if (this.isFull())
            return false;
        values[index] = i;
        index++;
        index %= this.capacity;

        return true;
    }

    public int get() {
        if (this.isEmpty())
            return 0;
        int curr = values[outdex % this.capacity];
        outdex++;
        outdex %= this.capacity;
        return curr;
    }

    public int capacity() {
        return this.capacity - 1;
    }

    public String print() {
        StringBuffer sb = new StringBuffer("Circular buffer content:\n<");
        int curr = outdex;
        for (int i = 0; i < count(); i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(String.format("%d", values[curr++]));
            curr %= this.capacity;
        }
        return sb.append(">\n").toString();
    }

    private int count() {
        return (index + capacity - outdex) % capacity;
    }
}
