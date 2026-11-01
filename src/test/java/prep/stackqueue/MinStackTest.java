package prep.stackqueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.NoSuchElementException;
import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class MinStackTest {

    private final MinStack stack = new MinStack();

    @Test
    public void minFollowsThePushes() {
        stack.push(5);
        assertEquals(5, stack.min());
        stack.push(3);
        assertEquals(3, stack.min());
        stack.push(7);
        assertEquals(3, stack.min());
    }

    @Test
    public void minIsRestoredAfterPoppingTheMinimum() {
        stack.push(5);
        stack.push(3);
        stack.pop();
        assertEquals(5, stack.min());
    }

    @Test
    public void duplicateMinimumsSurviveASinglePop() {
        stack.push(2);
        stack.push(2);
        stack.pop();
        assertEquals(2, stack.min());
    }

    @Test
    public void minOnAnEmptyStackThrows() {
        assertThrows(NoSuchElementException.class, () -> stack.min());
    }

    @Test
    public void popOnAnEmptyStackThrows() {
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }
}