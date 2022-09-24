package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int wizPos;

        public ArraySetIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int DEFAULT_SIZE = 8;

    public ArrayDeque() {
        items = (T[]) new Object[DEFAULT_SIZE];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    private void resizeAdd(int capacity) {
        T[] a = (T[]) new Object[capacity];
        T[] b = (T[]) new Object[size];
        System.arraycopy(items, (nextFirst + 1) % items.length, b, 0, items.length - nextLast);
        System.arraycopy(items, 0, b, items.length - nextLast, items.length - (items.length - nextLast));
        System.arraycopy(b, 0, a, 0, size);
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    private void resizeRemove(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int length1;
        int length2;
        if (nextFirst > nextLast) {
            length1 = items.length - (nextFirst + 1);
            length2 = size - length1;
        } else {
            length1 = nextLast - (nextFirst + 1);
            length2 = 0;
        }
        System.arraycopy(items, (nextFirst + 1) % items.length, a, 0, length1);
        System.arraycopy(items, 0, a, length1, length2);
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resizeAdd(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (items.length == size) {
            resizeAdd(size * 2);
        }
        items[nextLast] = item;
        if (nextLast + 1 == items.length) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println(" ");
    }

    @Override
    public T removeFirst() {
        int min = 4;
        if (size == 0) {
            return null;
        }
        if (items.length >= DEFAULT_SIZE * 2 && size < items.length / min) {
            resizeRemove(size * min);
        }
        int first;
        if (nextFirst == items.length - 1) {
            first = 0;
        } else {
            first = nextFirst + 1;
        }
        T item = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        int min = 4;
        if (size == 0) {
            return null;
        }
        if (items.length >= DEFAULT_SIZE * 2 && size < items.length / min) {
            resizeRemove(size * min);
        }
        int last;
        if (nextLast == 0) {
            last = items.length - 1;
        } else {
            last = nextLast - 1;
        }
        T item = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        int newIndex;
        if (size == 0) {
            return null;
        }
        if (index >= size) {
            return null;
        }
        newIndex = ((nextFirst + 1) + index) % items.length;
        return items[newIndex];
    }

    /**@param o
     * This method returns true if object o is equal
     * to the array deque instance.*/
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        } else {
            Deque<T> cast = (ArrayDeque<T>) o;
            for (int i = 0; i < size; i++) {
                if (get(i) != null) {
                    if (!(get(i).equals(cast.get(i)))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
