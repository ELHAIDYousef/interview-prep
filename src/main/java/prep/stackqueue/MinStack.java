package prep.stackqueue;

/**
 *
 * @author Yousef ELHAID
 */
public class MinStack {
    private final IntStack values = new IntStack();
    private final IntStack minimums = new IntStack();

    public void push(int value) { // T O(1) M O(1)
        values.push(value);
        if (minimums.isEmpty() || value <= minimums.peek()){
            minimums.push(value);
        }
    }

    public int pop() { // T O(1) M O(1)
        int removed = values.pop();
        if (minimums.peek() == removed) {
            minimums.pop();
        }
        return removed;
    }

    public int peek() { // T O(1) M O(1)
        return values.peek();
    }

    public int min() { // T O(1) M O(1)
        return minimums.peek();
    }

    public boolean isEmpty() { // T O(1) M O(1)
        return values.isEmpty();
    }
}
