package prep.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Yousef ELHAID
 */
public class TreeAlgorithmsTest {

    @Test
    public void emptyTreeIsAValidBst() {
        assertTrue(TreeAlgorithms.isValidBst(null));
    }

    @Test
    public void singleNodeIsAValidBst() {
        assertTrue(TreeAlgorithms.isValidBst(new TreeNode(5)));
    }

    @Test
    public void sampleTreeIsAValidBst() {
        TreeNode root = new TreeNode(8,
                new TreeNode(3, new TreeNode(1), new TreeNode(6, new TreeNode(4), new TreeNode(7))),
                new TreeNode(10, null, new TreeNode(14)));
        assertTrue(TreeAlgorithms.isValidBst(root));
    }

    @Test
    public void leftChildGreaterThanItsParentIsInvalid() {
        assertFalse(TreeAlgorithms.isValidBst(new TreeNode(8, new TreeNode(9), null)));
    }

    @Test
    public void nodeValidForItsParentButNotForItsGrandparentIsInvalid() {
        TreeNode root = new TreeNode(8,
                new TreeNode(3, null, new TreeNode(9)),
                new TreeNode(10));
        assertFalse(TreeAlgorithms.isValidBst(root));
    }

    @Test
    public void duplicateValueIsInvalid() {
        assertFalse(TreeAlgorithms.isValidBst(new TreeNode(8, new TreeNode(8), null)));
    }

    @Test
    public void extremeIntValuesAreAccepted() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE, null, new TreeNode(Integer.MAX_VALUE));
        assertTrue(TreeAlgorithms.isValidBst(root));
    }
}