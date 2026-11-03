package prep.mock1;

import java.util.Objects;

/**
 *
 * @author Yousef ELHAID
 */
public class MyHashSet<E> {
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private static final class Node<E> {
        private final E element;
        private Node<E> next;

        private Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    private int size;
    private Node<E>[] buckets = (Node<E>[]) new Node[INITIAL_CAPACITY];

    public boolean add(E element) {
        int index = indexOf(element, buckets.length);
        Node<E> current = buckets[index];
        while (current != null) {
            if (Objects.equals(current.element, element)) {
                return false;
            }
            current = current.next;
        }
        buckets[index] = new Node<>(element, buckets[index]);
        size++;

        if (size > buckets.length * LOAD_FACTOR) {
            resize();
        }

        return true;
    }

    public boolean contains(E element) {
        int index =  indexOf(element, buckets.length);
        Node<E> current = buckets[index];
        while (current != null) {
            if (Objects.equals(current.element, element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean remove(E element) {
        int index = indexOf(element, buckets.length);
        Node<E> previous = null;
        Node<E> current = buckets[index];
        while (current != null) {
            if (Objects.equals(current.element, element)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    private void resize() {
        Node<E>[] oldBuckets = buckets;
        buckets = (Node<E>[]) new Node[buckets.length * 2];
        for (int i = 0; i < oldBuckets.length; i++) {
            Node<E> current = oldBuckets[i];
            while (current != null) {
                Node<E> nextNode = current.next;
                int index = indexOf(current.element, buckets.length);
                current.next = buckets[index];
                buckets[index] = current;
                current = nextNode;
            }
        }
    }


    private int indexOf(E element, int capacity) {
        int hash = Objects.hashCode(element);
        hash = hash ^ (hash >>> 16 );
        return hash & (capacity - 1);
    }
}
