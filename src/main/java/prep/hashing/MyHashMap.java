package prep.hashing;

import java.util.Objects;

/**
 *
 * @author Yousef ELHAID
 */
public class MyHashMap<K, V> {

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        private Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry<K, V>[] buckets = newBuckets(INITIAL_CAPACITY);
    private int size;

    public void put(K key, V value) { // T O(1) moyen, amorti M O(1) amorti
        Entry<K, V> existing = findEntry(key);
        if (existing != null) {
            existing.value = value;
            return;
        }
        int index = indexFor(key, buckets.length);
        buckets[index] = new Entry<>(key, value, buckets[index]);
        size++;
        if (size > buckets.length * LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) { // T O(1) moyen M O(1)
        Entry<K, V> entry = findEntry(key);
        return entry == null ? null : entry.value;
    }

    public boolean containsKey(K key) { // T O(1) moyen M O(1)
        return findEntry(key) != null;
    }

    public V remove(K key) { // T O(1) moyen M O(1)
        int index = indexFor(key, buckets.length);
        Entry<K, V> previous = null;
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public int size() { // T O(1) M O(1)
        return size;
    }

    public boolean isEmpty() { // T O(1) M O(1)
        return size == 0;
    }

    private Entry<K, V> findEntry(K key) {
        Entry<K, V> current = buckets[indexFor(key, buckets.length)];
        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        buckets = newBuckets(oldBuckets.length * 2);
        for (Entry<K, V> bucket : oldBuckets) {
            Entry<K, V> current = bucket;
            while (current != null) {
                Entry<K, V> nextEntry = current.next;
                int index = indexFor(current.key, buckets.length);
                current.next = buckets[index];
                buckets[index] = current;
                current = nextEntry;
            }
        }
    }

    private static int indexFor(Object key, int capacity) {
        int hash = Objects.hashCode(key);
        hash = hash ^ (hash >>> 16);
        return hash & (capacity - 1);
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Entry<K, V>[] newBuckets(int capacity) {
        return (Entry<K, V>[]) new Entry[capacity];
    }
}