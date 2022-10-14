package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int size;
    private double loadfactor;
    private static final int DEFAULT_INITSIZE = 16;
    private static final double DEFAULT_LOADFACTOR = 0.75;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(DEFAULT_INITSIZE);
        size = 0;
        loadfactor = DEFAULT_LOADFACTOR;
    }

    public MyHashMap(int initialSize) {
        buckets = createTable(initialSize);
        size = 0;
        loadfactor = DEFAULT_LOADFACTOR;

    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        size = 0;
        loadfactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    private int findBucket(K key) {
        return findBucket(key, buckets.length);
    }

    private int findBucket(K key, int bucketSize) {
        return Math.floorMod(key.hashCode(), bucketSize);
    }
    private Node getNode(K key) {
        int node = findBucket(key);
        if (buckets[node] != null) {
            for (Node n : buckets[node]) {
                if (n.key.equals(key)) {
                    return n;
                }
            }
        }
        return null;
    }

    private void resize(int target) {
        Collection<Node>[] newBucket = createTable(target);
        for (Collection<Node> c : buckets) {
            if (c == null) {
                continue;
            }
            for (Node n : c) {
                K key = n.key;
                int index = findBucket(key, newBucket.length);
                if (newBucket[index] == null) {
                    newBucket[index] = createBucket();
                }
                newBucket[index].add(getNode(key));
            }
        }
        buckets = newBucket;
    }

    public void clear() {
        buckets = createTable(DEFAULT_INITSIZE);
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (containsKey(key)) {
            return getNode(key).value;
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (getNode(key) != null) {
            getNode(key).value = value;
        } else {
            if (((double) size) / buckets.length >= loadfactor) {
                resize(buckets.length * 2);
            }
            size = size + 1;
            int index = findBucket(key);
            if (buckets[index] == null) {
                buckets[index] = createBucket();
            }
            buckets[index].add(createNode(key, value));
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
