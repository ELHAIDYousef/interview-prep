package prep.linkedlist;

import java.util.NoSuchElementException;

/**
 *
 * @author Yousef ELHAID
 */
public class MyLinkedList {

    private static class Node {
        private int value;
        private Node next;

        private Node(int value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public int size() { // T O(1) M O(1)
        return size;
    }

    public boolean isEmpty() { // T O(1) M O(1)
        return size == 0;
    }

    public void addLast(int value) { // T O(1) M O(1)
        Node newNode = new Node(value);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void addFirst(int value) { // T O(1) M O(1)
        Node newNode = new Node(value);
        if (isEmpty()) {
            tail = newNode;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }

    public int get(int index) { // T O(n) M O(1)
        return nodeAt(index).value;
    }

    public void set(int index, int value) { // T O(n) M O(1)
        nodeAt(index).value = value;
    }

    public int indexOf(int value) { // T O(n) M O(1)
        Node currentNode = head;
        for (int i = 0; i < size; i++) {
            if (currentNode.value == value) {
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }

    public boolean contains(int value) { // T O(n) M O(1)
        return indexOf(value) != -1;
    }

    public int removeFirst() { // T O(1) M O(1)
        checkNotEmpty();
        Node removed = head;
        head = removed.next;
        if (head == null) {
            tail = null;
        }
        removed.next = null;
        size--;
        return removed.value;
    }

    public int removeLast() { // T O(n) M O(1)
        checkNotEmpty();
        if (size == 1) {
            return removeFirst();
        }
        Node removed = tail;
        tail = nodeAt(size - 2);
        tail.next = null;
        size--;
        return removed.value;
    }

    public int removeAt(int index) { // T O(n) M O(1)
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node previous = nodeAt(index - 1);
        Node removed = previous.next;
        previous.next = removed.next;
        removed.next = null;
        size--;
        return removed.value;
    }

    public boolean removeValue(int value) { // T O(n) M O(1)
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        removeAt(index);
        return true;
    }

    public int countGreaterThanRecursive(int threshold) { // T O(n) M O(n)
        return countGreaterThanRecursive(head, threshold);
    }

    public int countGreaterThanIterative(int threshold) { // T O(n) M O(1)
        int count = 0;
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.value > threshold) {
                count++;
            }
            currentNode = currentNode.next;
        }
        return count;
    }

    public void reverseIterative() { // T O(n) M O(1)
        Node previousNode = null;
        Node currentNode = head;
        tail = head;
        while (currentNode != null) {
            Node nextNode = currentNode.next;
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        head = previousNode;
    }

    public void reverseRecursive() { // T O(n) M O(n)
        tail = head;
        head = reverseFrom(head);
    }

    @Override
    public String toString() { // T O(n) M O(n)
        StringBuilder result = new StringBuilder("[");
        Node currentNode = head;
        while (currentNode != null) {
            result.append(currentNode.value);
            if (currentNode.next != null) {
                result.append(", ");
            }
            currentNode = currentNode.next;
        }
        return result.append("]").toString();
    }

    private Node reverseFrom(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node newHead = reverseFrom(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }

    private int countGreaterThanRecursive(Node node, int threshold) {
        if (node == null) {
            return 0;
        }
        int countForThisNode = node.value > threshold ? 1 : 0;
        return countForThisNode + countGreaterThanRecursive(node.next, threshold);
    }

    private Node nodeAt(int index) {
        checkIndex(index);
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty");
        }
    }
}