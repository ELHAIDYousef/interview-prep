package prep.stackqueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.NoSuchElementException;
import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class TwoStackQueueTest {

    private final TwoStackQueue queue = new TwoStackQueue();

    @Test
    public void dequeueReturnsValuesInArrivalOrder() {
        enqueueValues(1, 2, 3);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    public void enqueueAfterADequeueKeepsTheArrivalOrder() {
        enqueueValues(1, 2, 3);
        queue.dequeue();
        queue.enqueue(4);
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
    }

    @Test
    public void peekShowsTheOldestValueWithoutRemovingIt() {
        enqueueValues(1, 2);
        assertEquals(1, queue.peek());
        assertEquals(2, queue.size());
    }

    @Test
    public void sizeCountsTheValuesOfBothStacks() {
        enqueueValues(1, 2, 3);
        queue.dequeue();
        queue.enqueue(4);
        assertEquals(3, queue.size());
    }

    @Test
    public void dequeueOnAnEmptyQueueThrows() {
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    private void enqueueValues(int... values) {
        for (int value : values) {
            queue.enqueue(value);
        }
    }
}