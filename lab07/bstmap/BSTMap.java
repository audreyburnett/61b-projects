package bstmap;

import java.util.Set;

public class BSTMap implements Map61B<K,V>{
    private class Node{
        private K key;
        private V val;
        private Node left, right;
        private int size;

        public Node(K key, V val, int size){
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }


    public void clear(){
        for (int i = 0; i < size; i++){
            
        }
    }
    public boolean containsKey(K key){

    }
    public V get(K key){

    }
    public int size(){

    }
    public void put(K key, V value){

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
