package bstmap;

import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    private BSTNode root;
    private int size;
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
    @Override
    public void clear(){
        root = null;
        size = 0;
    }
    @Override
    public boolean containsKey(K key){
        if (get(key) == null){
            return false;
        }
        return true;
    }
    @Override
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
    @Override
    public int size(){
        return size;
    }

    @Override
    public void put(K key, V value){
       root = putHelper(root, key, value);
    }

    private BSTNode putHelper(BSTNode root, K key, V val) {
        if (root == null) {
            size += 1;
            return new BSTNode(key, val);
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
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key, V val) {
        throw new UnsupportedOperationException();
    }
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
