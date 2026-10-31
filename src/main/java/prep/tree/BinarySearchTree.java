package prep.tree;

import java.util.*;

/**
 *
 * @author Yousef ELHAID
 */
public class BinarySearchTree {

    private static class Node {
        private final int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public int size() { // T O(1) M O(1)
        return size;
    }

    public boolean isEmpty() { // T O(1) M O(1)
        return size == 0;
    }

    public void insert(int value) { // T O(h) M O(h)
        root = insert(root, value);
    }

    public boolean contains(int value) { // T O(h) M O(h)
        return contains(root, value);
    }

    public boolean containsIterative(int value) { // T O(h) M O(1)
        Node currentNode = root;
        while (currentNode != null) {
            if (value == currentNode.value) {
                return true;
            }
            if (value < currentNode.value) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return false;
    }

    public int min() { // T O(h) M O(1)
        checkNotEmpty();
        Node currentNode = root;
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode.value;
    }

    public int max() { // T O(h) M O(1)
        checkNotEmpty();
        Node currentNode = root;
        while (currentNode.right != null) {
            currentNode = currentNode.right;
        }
        return currentNode.value;
    }

    public int height() { // T O(n) M O(h)
        return height(root);
    }

    public List<Integer> inOrder() { // T O(n) M O(n)
        List<Integer> values = new ArrayList<>();
        inOrder(root, values);
        return values;
    }

    public List<Integer> preOrder() { // T O(n) M O(n)
        List<Integer> values = new ArrayList<>();
        preOrder(root, values);
        return values;
    }

    public List<Integer> postOrder() { // T O(n) M O(n)
        List<Integer> values = new ArrayList<>();
        postOrder(root, values);
        return values;
    }

    public double average() {  // T O(n) M O(h)
        if (root == null) {
            throw new NoSuchElementException();
        }
        long sum = 0;
        int count = 0;
        Deque<Node> visited = new ArrayDeque<>();
        visited.push(root);
        while (!visited.isEmpty()) {
            Node poped = visited.pop();
            sum += poped.value;
            count++;
            if (poped.left != null) {
                visited.push(poped.left);
            }
            if (poped.right != null) {
                visited.push(poped.right);
            }
        }
        return (double) sum / count;
    }

    public List<Integer> levelOrder() { // T O(n) M O(h)
        List<Integer> values = new ArrayList<>();
        if (root == null) {
            return values;
        }
        Queue<Node> visited = new ArrayDeque<>();
        visited.offer(root);
        while (!visited.isEmpty()) {
            Node polled = visited.poll();
            values.add(polled.value);
            if (polled.left != null) {
                visited.offer(polled.left);
            }
            if (polled.right != null) {
                visited.offer(polled.right);
            }
        }
        return values;
    }

    public int heightIterative() { // T O(n) M O(h)
        if (root ==  null) {
            return 0;
        }
        Queue<Node> visited = new ArrayDeque<>();
        visited.offer(root);
        int level = 0;
        while (!visited.isEmpty()) {
            int nodesInThisLevel = visited.size();
            for (int i = 0; i < nodesInThisLevel; i++) {
                Node polled = visited.poll();
                if (polled.right != null) {
                    visited.offer(polled.right);
                }
                if (polled.left != null) {
                    visited.offer(polled.left);
                }
            }
            level++;
        }
        return level;
    }

    public int countLeaves() { // T O(n) M O(h)
        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int count = 0;
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left == null && current.right == null) {
                count++;
            }
            if (current.left != null) {
                queue.offer(current.left);
            }
            if (current.right != null) {
                queue.offer(current.right);
            }
        }
        return count;
    }


    private Node insert(Node node, int value) {
        if (node == null) {
            size++;
            return new Node(value);
        }
        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    private boolean contains(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (value == node.value) {
            return true;
        }
        if (value < node.value) {
            return contains(node.left, value);
        }
        return contains(node.right, value);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private void inOrder(Node node, List<Integer> values) {
        if (node == null) {
            return;
        }
        inOrder(node.left, values);
        values.add(node.value);
        inOrder(node.right, values);
    }

    private void preOrder(Node node, List<Integer> values) {
        if (node == null) {
            return;
        }
        values.add(node.value);
        preOrder(node.left, values);
        preOrder(node.right, values);
    }

    private void postOrder(Node node, List<Integer> values) {
        if (node == null) {
            return;
        }
        postOrder(node.left, values);
        postOrder(node.right, values);
        values.add(node.value);
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("The tree is empty");
        }
    }
}