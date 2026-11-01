package prep.stackqueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class IntStackTest {

    private final IntStack stack = new IntStack();

    @Test
    public void newStackIsEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    public void popReturnsTheLastPushedValue() {
        stack.push(3);
        stack.push(7);
        stack.push(9);
        assertEquals(9, stack.pop());
        assertEquals(7, stack.pop());
        assertEquals(1, stack.size());
    }

    @Test
    public void peekDoesNotRemoveTheValue() {
        stack.push(3);
        assertEquals(3, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    public void popOnAnEmptyStackThrows() {
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    public void peekOnAnEmptyStackThrows() {
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @Test
    public void pushBeyondTheInitialCapacityKeepsAllValues() {
        for (int value = 0; value < 100; value++) {
            stack.push(value);
        }
        for (int expected = 99; expected >= 0; expected--) {
            assertEquals(expected, stack.pop());
        }
    }
}