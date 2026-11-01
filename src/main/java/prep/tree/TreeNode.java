package prep.tree;

/**
 *
 * @author Yousef ELHAID
 */
public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
    }

    TreeNode(int value, TreeNode left, TreeNode right) {
        this(value);
        this.left = left;
        this.right = right;
    }
}