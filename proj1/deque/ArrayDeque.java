package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
        nextFirst = 0;
        nextLast = size;
    }
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * items.length);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else{
            nextFirst -= 1;
        }
        size += 1;
    }
//        if(size == 0){
//            items[nextFirst] = item;
//            nextFirst
//        }
//        if (items.length == size){
//            resize(size*4);
//            nextLast = size;
//            nextFirst = size;
//        }
//        items[nextFirst] = item;
//        if (nextFirst - 1 < 0){
//            nextFirst = items.length;
//        } else{
//            nextFirst -= 1;
//        }
//        size += 1;

    public void addLast(T item){
        if (items.length == size){
            resize(size*4);
        }
        items[nextLast] = item;
        if (nextLast + 1 == items.length){
            nextLast = 0;
        } else{
            nextLast += 1;
        }
        size += 1;
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
        for(int i = 0; i < size; i++){
            System.out.print(items[i] + " ");
        }
        System.out.println(" ");
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        //resize
        int first;
        if(nextFirst == items.length - 1){
            first = 0;
        } else{
            first = nextFirst + 1;
        }
        T item = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        return item;
    }

    public T removeLast(){
        if(size == 0){
            return null;
        }
        //resize
        int last;
        if(nextLast == 0){
            last = items.length -1;
        } else{
            last = nextLast -1;
        }
        T item = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;
        return item;
    }

    public T get(int index){
        int newIndex;
        if (size == 0){
            return null;
        }
        if ((index+1) > size){
            return null;
        }
        if (nextFirst == items.length-1){
            newIndex = index;
        } else{
            newIndex = (nextFirst + 1) + index;
        }
        return items[newIndex];
    }


}
