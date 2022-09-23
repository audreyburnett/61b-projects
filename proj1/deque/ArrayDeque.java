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

    public void resizeAdd(int capacity){
        T[] a = (T[]) new Object[capacity];
        T[] b = (T[]) new Object[size];
        System.arraycopy(items, nextLast, b, 0, size-nextLast);
        System.arraycopy(items, 0, b, size-nextLast, size- (size-nextLast));
        System.arraycopy(b, 0, a, 0, size);
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void resizeRemove(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int length1;
        int length2;
        if (nextFirst > nextLast){
            length1 = items.length - (nextFirst + 1);
            length2 = size - length1;
        } else{
            length1 = nextLast - (nextFirst+1);
            length2 = 0;
        }
        System.arraycopy(items, (nextFirst + 1) % items.length, a, 0, length1);
        System.arraycopy(items, 0, a, length1, length2);
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resizeAdd(size*2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else{
            nextFirst -= 1;
        }
        size += 1;
    }

    public void addLast(T item){
        if (items.length == size){
            resizeAdd(size*2);
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
            System.out.print(get(i) + " ");
        }
        System.out.println(" ");
    }

    public T removeFirst(){
        if(size == 0){
            return null;
        }
        if(items.length >= 16 && size < items.length/4){
            resizeRemove(size*4);
        }
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
        if(items.length >= 16 && size < items.length/4) {
            resizeRemove(size * 4);
        }
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
        if (index >= size){
            return null;
        }
        if (((nextFirst + 1) + index) >= size()){
            newIndex = (-1)*(size - ((nextFirst + 1) + index));
        } else{
            newIndex = ((nextFirst + 1) + index);
        }
//        System.out.println(newIndex);
        return items[newIndex];
    }

    public boolean equals(Object o){
        if (!(o instanceof ArrayDeque)){
            return false;
        } else{
            ArrayDeque <T> cast = (ArrayDeque<T>) o;
            for (int i = 0; i < size; i++){
                if (!(get(i).equals((ArrayDeque) cast.get(i)))){
                    return false;
                }
            }
        }
        return true;
    }
}
