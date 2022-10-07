package bstmap;

import java.util.Set;

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
        if (key == null){
            throw new IllegalArgumentException();
        }
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
        if (root == null) {

        }
        if (key == null){
            throw new IllegalArgumentException();
        }
        if (value == null) {

        }
        size += 1;
    }

    private void put(BSTNode root, K key, V val){
        if (key == root.key) {
            root.value = val;
            return;
        }
        if (root == null) {
            root = new BSTNode(key, val);
            size += 1;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            if (root.left != null) {
                put(root.left, key, val);
            }
            else {
                root.left = new BSTNode(key, val);
            }
        else {
            if (root.right != null) {
                put(root, key, val);
            }
            else {
                root.right = new BSTNode(key, val);
            }
        }
    }
    public void printInOrder(){

    }
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }
    public V remove(K key){
        throw new UnsupportedOperationException();
    }
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
    public Iterator<K> iterator(){
        throw new UnsupportedOperationException();
    }
}
