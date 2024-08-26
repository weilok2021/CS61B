package deque;
import java.util.Iterator;


/**  Invariance
 * sentinel.next is always equals to either first node or sentinel itself
 * sentinel.prev is always equals to either last node or sentinel itself
 * this creates a circular structure
 * the size variable is always equals to the number of items in list.
 * */

public class ReviseLinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        Node prev;
        T item;
        Node next;
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    // empty linked list constructor
    public ReviseLinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public ReviseLinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        Node firstNode = new Node(sentinel, item, sentinel);
        sentinel.next = firstNode;
        sentinel.prev = firstNode;
        size = 1;
    }

    public void addFirst(T item) {
        /**
         * newFirst.prev points at sentinel, newFirst.next points to oldFirst(if exist)
         * oldFirst.prev now points at new firstItem, which mean it comes after newFirst.
         * Lastly, sentinel.next points at the newFirst
         */

        Node oldFirst = sentinel.next;
        Node newFirst = new Node(sentinel, item, oldFirst);
        oldFirst.prev = newFirst;
        sentinel.next = newFirst;
        size += 1;
    }

    public void addLast(T item) {
        /**
         * newLast.prev points at oldLast (if no last node exist, it will point to sentinel),
         * newLast.next points to sentinel.
         * oldLast.next now points at newLast, which mean it comes before newLast.
         * Lastly, sentinel.prev points at the newLast.
         */
        Node oldLast = sentinel.prev;
        Node newLast = new Node(oldLast, item, sentinel);
        oldLast.next = newLast;
        sentinel.prev = newLast;
        size += 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public T get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node p = sentinel.next; // Start from the first item

        for (int j = 0; j < i; j++) {
            p = p.next;
        }
        return p.item;
    }

    // getRecursive helper method
    private T getRecursive(Node p, int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        // the condition p.item != null is to avoid accessing null sentinel.item
        // this is possible when the list is empty.
        if (i == 0 && p.item != null) {
            return p.item;
        }
        return getRecursive(p.next, i-1);
    }

    public T getRecursive(int i) {
        return getRecursive(sentinel.next, i); //start from first item
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node firstNode = sentinel.next;
        T firstItem = firstNode.item;
        firstNode.next.prev = sentinel;
        sentinel.next = firstNode.next;
        size--;
        // throw away all the references of removed firstNode, not necessary but good practices
        firstNode.prev = null;
        firstNode.next = null;
        return firstItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node lastNode = sentinel.prev;
        T lastItem = lastNode.item;
        lastNode.prev.next = sentinel;
        sentinel.prev = lastNode.prev;
        size--;
        lastNode.prev = null;
        lastNode.next = null;
        return lastItem;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("()");
            return;
        }
        System.out.print("(");
        for (int i = 0; i < size - 1; i++) {
            System.out.printf("%s --> ", get(i));
        }
        System.out.print(get(size - 1));
        System.out.println(")");
    }
    /**
     * part one done, now left with iterator and equal.
     */
}
