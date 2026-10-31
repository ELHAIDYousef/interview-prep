package prep.linkedlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class ListAlgorithmsTest {

    @Test
    public void emptyListHasNoCycle() {
        assertFalse(ListAlgorithms.hasCycle(null));
    }

    @Test
    public void singleNodeWithoutLoopHasNoCycle() {
        assertFalse(ListAlgorithms.hasCycle(chain(1)));
    }

    @Test
    public void straightListHasNoCycle() {
        assertFalse(ListAlgorithms.hasCycle(chain(1, 2, 3)));
    }

    @Test
    public void nodePointingToItselfIsACycle() {
        ListNode single = chain(1);
        single.next = single;
        assertTrue(ListAlgorithms.hasCycle(single));
    }

    @Test
    public void tailLinkedBackToTheSecondNodeIsACycle() {
        ListNode head = chain(1, 2, 3, 4);
        ListNode second = head.next;
        ListNode fourth = second.next.next;
        fourth.next = second;
        assertTrue(ListAlgorithms.hasCycle(head));
    }

    @Test
    public void middleOfAnEmptyListIsNull() {
        assertNull(ListAlgorithms.middle(null));
    }

    @Test
    public void middleOfAnOddLengthListIsTheCentralNode() {
        assertEquals(2, ListAlgorithms.middle(chain(1, 2, 3)).value);
    }

    @Test
    public void middleOfAnEvenLengthListIsTheSecondMiddle() {
        assertEquals(3, ListAlgorithms.middle(chain(1, 2, 3, 4)).value);
    }

    @Test
    public void reverseOfAnEmptyListIsNull() {
        assertNull(ListAlgorithms.reverse(null));
    }

    @Test
    public void reverseOfASingleNodeReturnsTheSameNode() {
        ListNode single = chain(5);
        assertSame(single, ListAlgorithms.reverse(single));
        assertNull(single.next);
    }

    @Test
    public void reverseReturnsTheOldTailAsTheNewHead() {
        ListNode newHead = ListAlgorithms.reverse(chain(1, 2, 3));
        assertEquals(3, newHead.value);
        assertEquals(2, newHead.next.value);
        assertEquals(1, newHead.next.next.value);
        assertNull(newHead.next.next.next);
    }

    private ListNode chain(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }
}