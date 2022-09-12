package deque;

public class LinkedListDeque<T> {
    private class TNode{
        public T item;
        public TNode next;
        public TNode prev;
        public TNode(TNode p, T i, TNode n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev =  sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        if (size == 0){
            sentinel.next = new TNode(sentinel, item, sentinel);
            sentinel.prev = sentinel.next;
            size += 1;
        } else {
            sentinel.next = new TNode(sentinel, item, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
            size += 1;
        }
    }

    public void addLast(T item){
        if (size == 0){
            addFirst(item);
        } else {
            TNode last = new TNode(sentinel.prev, item, sentinel);
            sentinel.prev = last;
            last.prev.next = last;
            size += 1;
        }
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        } else{
            return false;
        }
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        String items = "";
        TNode destructive = sentinel;
        int tempsize = size;
        while (tempsize > 0){
            System.out.println(destructive.next.item);
            
        }
    }


}
