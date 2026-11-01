package prep.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class BinarySearchTreeTest {

    private final BinarySearchTree tree = new BinarySearchTree();

    @Test
    public void newTreeIsEmpty() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertEquals(0, tree.height());
    }

    @Test
    public void insertIgnoresDuplicates() {
        insertValues(5, 5);
        assertEquals(1, tree.size());
    }

    @Test
    public void containsOnAnEmptyTreeIsFalse() {
        assertFalse(tree.contains(3));
    }

    @Test
    public void containsFindsOnlyInsertedValues() {
        insertSampleTree();
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(14));
        assertFalse(tree.contains(5));
    }

    @Test
    public void minAndMaxAreTheExtremeValues() {
        insertSampleTree();
        assertEquals(1, tree.min());
        assertEquals(14, tree.max());
    }

    @Test
    public void minOnAnEmptyTreeThrows() {
        assertThrows(NoSuchElementException.class, () -> tree.min());
    }

    @Test
    public void maxOnAnEmptyTreeThrows() {
        assertThrows(NoSuchElementException.class, () -> tree.max());
    }

    @Test
    public void heightOfASingleNodeIsOne() {
        insertValues(8);
        assertEquals(1, tree.height());
    }

    @Test
    public void heightCountsTheLevels() {
        insertSampleTree();
        assertEquals(4, tree.height());
    }

    @Test
    public void sortedInsertionsBuildADegenerateTree() {
        insertValues(1, 2, 3, 4, 5);
        assertEquals(5, tree.height());
    }

    @Test
    public void traversalsOfAnEmptyTreeAreEmpty() {
        assertTrue(tree.inOrder().isEmpty());
        assertTrue(tree.preOrder().isEmpty());
        assertTrue(tree.postOrder().isEmpty());
    }

    @Test
    public void inOrderOfABstIsSorted() {
        insertSampleTree();
        assertEquals(List.of(1, 3, 4, 6, 7, 8, 10, 14), tree.inOrder());
    }

    @Test
    public void preOrderVisitsTheRootFirst() {
        insertSampleTree();
        assertEquals(List.of(8, 3, 1, 6, 4, 7, 10, 14), tree.preOrder());
    }

    @Test
    public void postOrderVisitsTheRootLast() {
        insertSampleTree();
        assertEquals(List.of(1, 4, 7, 6, 3, 14, 10, 8), tree.postOrder());
    }

    @Test
    public void averageOfAnEmptyTreeThrows() {
        assertThrows(NoSuchElementException.class, () -> tree.average());
    }

    @Test
    public void averageOfTheSampleTree() {
        insertSampleTree();
        assertEquals(6.625, tree.average(), 1e-9);
    }

    @Test
    public void averageIsNotAnIntegerDivision() {
        insertValues(1, 2);
        assertEquals(1.5, tree.average(), 1e-9);
    }

    @Test
    public void averageOfHugeValuesDoesNotOverflow() {
        insertValues(Integer.MAX_VALUE, Integer.MAX_VALUE - 1);
        assertEquals(2147483646.5, tree.average(), 1e-6);
    }

    @Test
    public void levelOrderOfAnEmptyTreeIsEmpty() {
        assertTrue(tree.levelOrder().isEmpty());
    }

    @Test
    public void levelOrderVisitsTheTreeLevelByLevel() {
        insertSampleTree();
        assertEquals(List.of(8, 3, 10, 1, 6, 14, 4, 7), tree.levelOrder());
    }

    @Test
    public void heightIterativeOfAnEmptyTreeIsZero() {
        assertEquals(0, tree.heightIterative());
    }

    @Test
    public void heightIterativeCountsTheLevels() {
        insertSampleTree();
        assertEquals(4, tree.heightIterative());
    }

    @Test
    public void heightIterativeOfADegenerateTreeIsItsSize() {
        insertValues(1, 2, 3, 4, 5);
        assertEquals(5, tree.heightIterative());
    }

    @Test
    public void countLeavesOfAnEmptyTreeIsZero() {
        assertEquals(0, tree.countLeaves());
    }

    @Test
    public void countLeavesOfTheSampleTree() {
        insertSampleTree();
        assertEquals(4, tree.countLeaves());
    }

    private void insertSampleTree() {
        insertValues(8, 3, 10, 1, 6, 14, 4, 7);
    }

    private void insertValues(int... values) {
        for (int value : values) {
            tree.insert(value);
        }
    }
}