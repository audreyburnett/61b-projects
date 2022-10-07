package bstmap;

import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    BSTNode root;
    int size;
    private class BSTNode{
        private K key;
        private V value;
        private BSTNode left, right;

        public BSTNode(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    public BSTMap() {
        clear();
    }

    public void clear(){
        root = null;
        size = 0;
    }
    public boolean containsKey(K key){
        return get(key) != null;
    }
    public V get(K key){
        return getHelper(root, key);
    }

    private V getHelper(BSTNode root, K key){
        if (root == null){
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp > 0){
            return getHelper(root.right, key);
        } else if (cmp < 0){
            return getHelper(root.left, key);
        }
        else{
            return root.value;
        }
    }
    public int size(){
        return size;
    }
    public void put(K key, V value){
       root = putHelper(root, key, value);
    }

    private BSTNode putHelper(BSTNode root, K key, V val) {
        if (root == null) {
            root = new BSTNode(key, val);
            size += 1;
            return root;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = putHelper(root.left, key, val);
        } else if (cmp > 0){
            root.right = putHelper(root.right, key, val);
        }
        else {
            root.value = val;
        }
        return root;
    }
    public void printInOrder(){
        return;
    }
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }
    public V remove(K key, V val) {
        throw new UnsupportedOperationException();
    }
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
