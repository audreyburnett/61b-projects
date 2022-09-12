package deque;

/**This is a linked list deque class with a constructor and methods.
 * @author audreyburnett*/
public class LinkedListDeque<T> {
    /**@author audreyburnett
     * This is a TNode class containing a TNode constructor
     * and private variables item, next, and prev.*/
    private class TNode {
        /**This private variable item tracks the value of a node.*/
        private T item;
        /**This private TNode next tracks a nodes next TNode in a deque.*/
        private TNode next;
        /**This private TNode prev tracks a nodes previous TNode in q deque.*/
        private TNode prev;

        /**@param i
         * @param n
         * @param p
         * This is a TNode constructor. It creates a TNode with value i.
         * It points to TNode n as its next node and TNode p as its prev.*/
        public TNode(TNode p, T i, TNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /**This private TNode points to the first and last TNodes of the deque.*/
    private TNode sentinel;
    /**This private integer size tracks the size of the deque.*/
    private int size;

    /**This is a linked list deque constructor.
     * It creates a sentinel node with item set to null,
     * and with prev and next pointing to itself.*/
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**@param item
     * This method adds an item of type T to the front of the deque.
     * Assume that item is never null.*/
    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new TNode(sentinel, item, sentinel);
            sentinel.prev = sentinel.next;
            size += 1;
        } else {
            sentinel.next = new TNode(sentinel, item, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
            size += 1;
        }
    }

    /**@param item
     * This method adds an item of type T to the back of the deque.
     * Assume item is never null.*/
    public void addLast(T item) {
        if (size == 0) {
            addFirst(item);
        } else {
            TNode last = new TNode(sentinel.prev, item, sentinel);
            sentinel.prev = last;
            last.prev.next = last;
            size += 1;
        }
    }

    /**This method returns true if the deque is empty, and false otherwise.*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;

    }

    /**This method returns the number of items in the deque.*/
    public int size() {
        return size;
    }


    /**This method prints the items in the deque from first to last,
     * separated by a space. Once all the items have been printed,
     * it prints out a new line.*/
    public void printDeque() {
        TNode current;
        current = new TNode(sentinel, sentinel.next.item, sentinel.next.next);
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println(" ");
    }

    /**This method removes and returns the item at the front of the deque.
     * If no such item exists, it returns null.*/
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T removed = sentinel.next.item;
            sentinel.next.prev = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev.next = sentinel.next.prev;
            sentinel.next.prev = sentinel;
            size -= 1;
            return removed;
        }
    }

    /**This method removes and returns the item at the back of the deque.
     * If no such item exists, it returns null.*/
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T removed = sentinel.prev.item;
            sentinel.prev.next = sentinel.prev;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next.prev = sentinel.prev.next;
            sentinel.prev.next = sentinel;
            size -= 1;
            return removed;
        }
    }

    /**@param index
     * This method returns the item at a given index using iteration.
     * If no such item exists, it returns null.*/
    public T get(int index) {
        if (size == 0) {
            return null;
        }
        if ((index + 1) > size) {
            return null;
        } else {
            TNode current = sentinel;
            for (int pointer = 0; pointer <= index; pointer += 1) {
                current = current.next;
            }
            return current.item;
        }
    }

    /**@param index
     * It returns the item at a given index using a recursive helper function.
     * If no such item exists, it returns null.*/
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }

    /**This is a helper method for getRecursive.
     * @param index
     * @param node
     * It returns the item at a given index using recursion.
     * If no such item exists, it returns null. */
    private T getRecursiveHelper(int index, TNode node) {
        if (size == 0) {
            return null;
        }
        if ((index + 1) > size) {
            return null;
        } else if (index == 0) {
            return node.item;
        } else {
            return getRecursiveHelper(index - 1, node.next);
        }
    }
}
