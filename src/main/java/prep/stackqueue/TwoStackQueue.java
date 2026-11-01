package prep.stackqueue;

import java.util.NoSuchElementException;

/**
 *
 * @author Yousef ELHAID
 */
public class TwoStackQueue {
    private final IntStack inbox = new IntStack();
    private final IntStack outbox = new IntStack();

    public void enqueue(int value) { // T O(1) M O(1)
        inbox.push(value);
    }

    public int dequeue() { // T O(1) M O(1)
        checkNotEmpty();
        fillOutboxIfEmpty();
        return outbox.pop();
    }

    public int peek() { // T O(1) M O(1)
        checkNotEmpty();
        fillOutboxIfEmpty();
        return outbox.peek();
    }

    public int size() { // T O(1) M O(1)
        return inbox.size() + outbox.size();
    }

    public boolean isEmpty() { // T O(1) M O(1)
        return size() == 0;
    }


    private void fillOutboxIfEmpty() { // T O(n) M O(1)
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
    }

    private void checkNotEmpty() { // T O(1) M O(1)
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        }
    }


}
