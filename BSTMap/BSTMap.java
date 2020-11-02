import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;
        public Node(K key, V value, int size){
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    /** Removes all of the mappings from this map. */
    public void clear(){
        clear(root);
    }
    private void clear(Node x){
        if (x == null) {
            return;
        } else {
            x.key = null;
            x.size = 0;
            x.value = null;
            clear(x.left);
            clear(x.right);
        }



    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        return containsKey(key, root);
    }
    private boolean containsKey(K key, Node x){
        if (x == null || x.key == null) {
            return false;
        }
        if (key.equals(x.key)) {
            return true;
        } else if (key.compareTo(x.key) < 0) {
            return containsKey(key, x.left);
        } else {
            return containsKey(key, x.right);
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return get(key, root);
    }
    private V get(K key, Node x){
        if (x == null || x.key == null) {
            return null;
        }
        if (key.compareTo(x.key) == 0) {
            return x.value;
        } else if (key.compareTo(x.key) < 0) {
            return get(key, x.left);
        } else {
            return get(key, x.right);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size(root);
    }

    private int size(Node x){
        if (x == null){
            return 0;
        } else {
            return x.size;
        }
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (value == null) {
            return;
        }
        root = put(key, value, root);
    }
    private Node put(K key, V value, Node x){
        if (x == null) {
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) == 0) {
            x.value = value;
        } else if (key.compareTo(x.key) < 0) {
            x.left = put(key, value, x.left);
        } else {
            x.right = put(key, value, x.right);
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
/**
    public void printInOrder(){
        printInOrder(root);
    }
    private void printInOrder(Node x){
        if (x == null){
            return;
        }
        printInOrder(x.left);
        System.out.println(x.value);
    }
 **/
















    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
