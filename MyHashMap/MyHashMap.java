// import statements
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map61B<K, V> {
    // private instance variables
    private HashSet<K> keys;
    private ArrayList<Node>[] buckets;
    private int size;
    private double loadFactor;
    private static final int DEFAULT_INIT_SIZE = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    // private helper class
    private class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }

    }

    // constructor given initial size and max load
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = (ArrayList<Node>[]) new ArrayList[initialSize];
        size = 0;
        loadFactor = maxLoad;
        keys = new HashSet<K>();
    }

    // constructor given initial size, uses default load factor
    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    // constructor with all default settings
    public MyHashMap() {
        this(DEFAULT_INIT_SIZE, DEFAULT_LOAD_FACTOR);
    }

    // clears buckets
    @Override
    public void clear() {
        buckets = (ArrayList<Node>[]) new ArrayList[DEFAULT_INIT_SIZE];
        size = 0;
        keys = new HashSet<>();
    }

    // returns true if the key is in keys
    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    @Override
    public V get(K key) {
        int bucket = selectBucket(key, buckets.length);
        ArrayList<Node> lst = buckets[bucket];
        if (lst != null) {
            for(Node i : lst) {
                if(i.key.equals(key) && i != null) {
                    return i.value;
                }
            }
        }
        return null;
    }

    private int selectBucket(K key, int size) {
        return Math.floorMod(key.hashCode(), size);
    }

    // returns size
    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        Node find = getNode(key);
        if (find != null) {
            find.value = value;
        } else {
            if ((double) size() / buckets.length > loadFactor) {
                rebucket(buckets.length + 1);
            }
            size++;
            int index = selectBucket(key, buckets.length);
            keys.add(key);
            ArrayList<Node> thisLst = buckets[index];
            if (thisLst == null) {
                thisLst = new ArrayList<>();
                buckets[index] = thisLst;
            }
            thisLst.add(new Node(key, value));
        }
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    // creates an iterator for keys
    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    // finds a bucket based on key and numBuckets
    private int findBucket(K key, int numBuckets) {
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    // finds a bucket based on key
    private int findBucket(K key) {
        return findBucket(key, buckets.length);
    }

    private void rebucket(int targetSize) {
        ArrayList<Node>[] newBuckets = (ArrayList<Node>[]) new ArrayList[targetSize];
        for (K key : keys) {
            int idx = findBucket(key, newBuckets.length);
            if (newBuckets[idx] == null) {
                newBuckets[idx] = new ArrayList<>();
            }
            newBuckets[idx].add(getNode(key));
        }
        buckets = newBuckets;
    }

    private Node getNode(K key) {
        int idx = findBucket(key);
        ArrayList<Node> bucketList = buckets[idx];
        if (bucketList != null) {
            for (Node node : bucketList) {
                if (node.key.equals(key)) {
                    return node;
                }
            }
        }
        return null;
    }
}