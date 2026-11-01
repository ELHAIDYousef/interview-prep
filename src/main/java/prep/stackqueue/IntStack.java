package prep.stackqueue;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * @author Yousef ELHAID
 */
public class IntStack {
    private static final int INITIAL_CAPACITY = 8;

    private int[] values =  new int[INITIAL_CAPACITY];
    private int size;

    public void push(int value) { // T O(1) amortized M O(1)
        if (size == values.length) {
            values = Arrays.copyOf(values,size * 2);
        }
        values[size++] = value;
    }

    public int pop() { // T O(1) M O(1)
        checkNotEmpty();
        return values[--size];
    }

    public int peek() { // T O(1) M O(1)
        checkNotEmpty();
        return values[size - 1];
    }

    public int size() { // T O(1) M O(1)
        return size;
    }

    public boolean isEmpty() { // T O(1) M O(1)
        return size == 0;
    }


    private void checkNotEmpty() { // T O(1) M O(1)
        if (isEmpty()) {
            throw new NoSuchElementException("The stack is empty");
        }
    }
}
