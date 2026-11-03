package prep.mock1;

import java.util.Objects;

/**
 *
 * @author Yousef ELHAID
 */
//final class MyHashSet1<E> {
//
//    private static final int INITIAL_CAPACITY = 16;
//    private static final double LOAD_FACTOR = 0.75;
//
//    private static final class Node<E> {
//
//        private final E element;
//        private Node<E> next;
//
//        private Node(E element, Node<E> next) {
//            this.element = element;
//            this.next = next;
//        }
//    }
//
//    private Node<E>[] buckets = newBuckets(INITIAL_CAPACITY);
//    private int size;
//
//    boolean add(E element) {
//        int index = indexFor(element, buckets.length);
//        Node<E> current = buckets[index];
//        while (current != null) {
//            if (Objects.equals(current.element, element)) {
//                return false;
//            }
//            current = current.next;
//        }
//        buckets[index] = new Node<>(element, buckets[index]);
//        size++;
//        if (size > buckets.length * LOAD_FACTOR) {
//            resize();
//        }
//        return true;
//    }
//
//    boolean contains(Object element) {
//        int index = indexFor(element, buckets.length);
//        Node<E> current = buckets[index];
//        while (current != null) {
//            if (Objects.equals(current.element, element)) {
//                return true;
//            }
//            current = current.next;
//        }
//        return false;
//    }
//
//    boolean remove(Object element) {
//        int index = indexFor(element, buckets.length);
//        Node<E> current = buckets[index];
//        Node<E> previous = null;
//        while (current != null) {
//            if (Objects.equals(current.element, element)) {
//                if (previous == null) {
//                    buckets[index] = current.next;
//                } else {
//                    previous.next = current.next;
//                }
//                size--;
//                return true;
//            }
//            previous = current;
//            current = current.next;
//        }
//        return false;
//    }
//
//    int size() {
//        return size;
//    }
//
//
//    private void resize() {
//        Node<E>[] oldBuckets = buckets;
//        buckets = newBuckets(oldBuckets.length * 2);
//        for (Node<E> head : oldBuckets) {
//            Node<E> current = head;
//            while (current != null) {
//                Node<E> next = current.next;
//                int index = indexFor(current.element, buckets.length);
//                current.next = buckets[index];
//                buckets[index] = current;
//                current = next;
//            }
//        }
//    }
//
//    private static int indexFor(Object element, int capacity) {
//        int hash = (element == null) ? 0 : element.hashCode();
//        hash = hash ^ (hash >>> 16);
//        return hash & (capacity - 1);
//    }
//
//    @SuppressWarnings({"unchecked", "rawtypes"})
//    private static <E> Node<E>[] newBuckets(int capacity) {
//        return (Node<E>[]) new Node[capacity];
//    }
//}
//

public class Main {

    public static void main(String[] args) {

        MyHashSet<String> set = new MyHashSet<>();

        System.out.println(set.add("ali"));
        System.out.println(set.add("ali"));
        System.out.println(set.add(new String("ali")));
        System.out.println(set.add("Aa"));
        System.out.println(set.add("BB"));
        System.out.println(set.remove("Aa"));
        System.out.println(set.contains("BB"));
        System.out.println(set.remove("Aa"));
        System.out.println(set.add(null));
        System.out.println(set.contains(null));

        for (int i = 0; i < 1_000_000; i++) {
            set.add("key" + i);
        }

        System.out.println(set.size());
        System.out.println(set.contains("key999999"));
    }
}