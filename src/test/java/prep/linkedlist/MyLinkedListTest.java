package prep.linkedlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class MyLinkedListTest {

    private final MyLinkedList list = new MyLinkedList();

    @Test
    public void newListIsEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void addLastKeepsInsertionOrder() {
        list.addLast(3);
        list.addLast(7);
        list.addLast(9);
        assertEquals(3, list.size());
        assertEquals(3, list.get(0));
        assertEquals(7, list.get(1));
        assertEquals(9, list.get(2));
    }

    @Test
    public void addFirstPutsTheValueAtIndexZero() {
        list.addLast(7);
        list.addFirst(3);
        assertEquals(3, list.get(0));
        assertEquals(7, list.get(1));
    }

    @Test
    public void addLastAfterAddFirstOnAnEmptyListAppendsAfterIt() {
        list.addFirst(3);
        list.addLast(7);
        assertEquals(2, list.size());
        assertEquals(7, list.get(1));
    }

    @Test
    public void getOutsideTheBoundsThrows() {
        list.addLast(3);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void setReplacesTheValueAtTheIndex() {
        addValues(3, 7, 9);
        list.set(1, 5);
        assertEquals("[3, 5, 9]", list.toString());
    }

    @Test
    public void indexOfOnAnEmptyListReturnsMinusOne() {
        assertEquals(-1, list.indexOf(3));
    }

    @Test
    public void indexOfReturnsTheFirstOccurrence() {
        addValues(7, 3, 3);
        assertEquals(1, list.indexOf(3));
    }

    @Test
    public void indexOfAnAbsentValueReturnsMinusOne() {
        addValues(7, 3);
        assertEquals(-1, list.indexOf(9));
    }

    @Test
    public void containsIsTrueOnlyForPresentValues() {
        addValues(7, 3);
        assertTrue(list.contains(3));
        assertFalse(list.contains(9));
    }

    @Test
    public void removeFirstOnAnEmptyListThrows() {
        assertThrows(NoSuchElementException.class, () -> list.removeFirst());
    }

    @Test
    public void removingTheOnlyElementEmptiesTheList() {
        list.addLast(3);
        assertEquals(3, list.removeFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    public void removeLastOnAnEmptyListThrows() {
        assertThrows(NoSuchElementException.class, () -> list.removeLast());
    }

    @Test
    public void removeLastReturnsTheLastValueAndMovesTheTail() {
        addValues(3, 7, 9);
        assertEquals(9, list.removeLast());
        list.addLast(4);
        assertEquals("[3, 7, 4]", list.toString());
    }

    @Test
    public void removeAtTheMiddleLinksTheNeighbours() {
        addValues(3, 7, 9);
        assertEquals(7, list.removeAt(1));
        assertEquals(2, list.size());
        assertEquals("[3, 9]", list.toString());
    }

    @Test
    public void addLastAfterRemovingTheTailAppendsToTheNewTail() {
        addValues(3, 7);
        list.removeAt(1);
        list.addLast(9);
        assertEquals("[3, 9]", list.toString());
    }

    @Test
    public void removeAtOutsideTheBoundsThrows() {
        addValues(3);
        assertThrows(IndexOutOfBoundsException.class, () -> list.removeAt(1));
    }

    @Test
    public void removeValueOnAnEmptyListReturnsFalse() {
        assertFalse(list.removeValue(3));
    }

    @Test
    public void removeValueOfAnAbsentValueKeepsTheList() {
        addValues(3, 7);
        assertFalse(list.removeValue(9));
        assertEquals("[3, 7]", list.toString());
    }

    @Test
    public void removeValueOfTheHeadMakesTheNextNodeTheHead() {
        addValues(3, 7, 9);
        assertTrue(list.removeValue(3));
        assertEquals("[7, 9]", list.toString());
    }

    @Test
    public void removeValueRemovesOnlyTheFirstOccurrence() {
        addValues(7, 3, 9, 3);
        assertTrue(list.removeValue(3));
        assertEquals("[7, 9, 3]", list.toString());
        assertEquals(3, list.size());
    }

    @Test
    public void addLastAfterRemovingTheTailValueAppendsToTheNewTail() {
        addValues(3, 7);
        list.removeValue(7);
        list.addLast(9);
        assertEquals("[3, 9]", list.toString());
    }

    @Test
    public void toStringOfAnEmptyListIsEmptyBrackets() {
        assertEquals("[]", list.toString());
    }

    @Test
    public void toStringListsTheValuesInOrder() {
        addValues(3, 7, 9);
        assertEquals("[3, 7, 9]", list.toString());
    }

    @Test
    public void countGreaterThanOnAnEmptyListIsZero() {
        assertEquals(0, list.countGreaterThanRecursive(10));
        assertEquals(0, list.countGreaterThanIterative(10));
    }

    @Test
    public void countGreaterThanIgnoresValuesEqualToTheThreshold() {
        addValues(5, 12, 10, 20);
        assertEquals(2, list.countGreaterThanRecursive(10));
        assertEquals(2, list.countGreaterThanIterative(10));
    }

    @Test
    public void reverseIterativeOfAnEmptyListStaysEmpty() {
        list.reverseIterative();
        assertEquals("[]", list.toString());
    }

    @Test
    public void reverseIterativeInvertsTheOrderAndKeepsTheTail() {
        addValues(1, 2, 3);
        list.reverseIterative();
        list.addLast(4);
        assertEquals("[3, 2, 1, 4]", list.toString());
    }

    @Test
    public void reverseRecursiveOfAnEmptyListStaysEmpty() {
        list.reverseRecursive();
        assertEquals("[]", list.toString());
    }

    @Test
    public void reverseRecursiveInvertsTheOrderAndKeepsTheTail() {
        addValues(1, 2, 3);
        list.reverseRecursive();
        list.addLast(4);
        assertEquals("[3, 2, 1, 4]", list.toString());
    }

    @Test
    public void reversingASingleElementListChangesNothing() {
        addValues(5);
        list.reverseIterative();
        list.reverseRecursive();
        list.addLast(6);
        assertEquals("[5, 6]", list.toString());
    }

    private void addValues(int... values) {
        for (int value : values) {
            list.addLast(value);
        }
    }
}