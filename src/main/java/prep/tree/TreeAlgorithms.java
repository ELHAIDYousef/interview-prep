package prep.tree;

/**
 *
 * @author Yousef ELHAID
 */
public class TreeAlgorithms {

    private TreeAlgorithms() {
    }

    public static boolean isValidBst(TreeNode root) {
        return isWithinBounds(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }


    private static boolean isWithinBounds(TreeNode root, long lowerValue, long upperValue) {  // T O(n) M O(n)
        if (root == null) {
            return true;
        }
        if (root.value <= lowerValue || root.value >= upperValue) {
            return false;
        }

        return isWithinBounds(root.left, lowerValue , root.value) && isWithinBounds(root.right, root.value, upperValue);
    }


}
