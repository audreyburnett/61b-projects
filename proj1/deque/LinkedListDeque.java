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
        TNode current = sentinel.next;
        while (current != sentinel){
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println(" ");
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        } else{
            T removed = sentinel.next.item;
            sentinel.next.prev = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev.next = sentinel.next.prev;
            sentinel.next.prev = sentinel;
            size -= 1;
            return removed;
        }
    }

    public T removeLast(){
        if (size == 0){
            return null;
        } else {
             T removed = sentinel.prev.item;
             sentinel.prev.next = sentinel.prev;
             sentinel.prev = sentinel.prev.prev;
             sentinel.prev.next.prev = sentinel.prev.next;
             sentinel.prev.prev = sentinel;
             size -= 1;
             return removed;
        }
    }

    public T get(int index){
        if (size == 0){
            return null;
        }
        if ((index+1)>size){
            return null;
        } else{
            TNode current = sentinel;
            for(int pointer = 0; pointer <= index; pointer+=1){
                current = current.next;
            }
            return current.item;
        }
    }

    public T getRecursive(int index){
        return getRecursiveHelper(index, sentinel.next);
    }

    public T getRecursiveHelper(int index, TNode node){
        if (size == 0){
            return null;
        }
        if ((index+1)>size){
            return null;
        } else if (index == 0){
            return node.item;
        }
        else{
            return getRecursiveHelper(index -1, node.next);
        }
    }


}
