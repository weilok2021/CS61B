package deque;
import java.util.Iterator;


/**  Invariance
 * sentinel.next is always equals to either first node or sentinel itself
 * sentinel.prev is always equals to either last node or sentinel itself
 * this creates a circular structure
 * the size variable is always equals to the number of items in list.
 * */

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>{
    private Node sentinel;
    // private Node sentinelB;
    private int size;

    private class Node {
        private Node prev;
        private T item;
        private Node next;
        private Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T i) {
        sentinel = new Node(null, null, null);
        Node firstNode = new Node(sentinel, i, sentinel);
        sentinel.next = firstNode;
        sentinel.prev = firstNode;
        size = 1;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int currentPos;

        public LinkedListDequeIterator() {
            currentPos = 0;
        }

        public boolean hasNext() {
            return currentPos < size;
        }

        /** I try to avoid using get method in linked list because it takes O(n) time
         * every time we called L.get(i).
         * Cons: easier to get bug if I am traversing nodes through pointers.
         * */
        public T next() {
            T returnItem = get(currentPos); // very slow when size is large
//             T returnItem = p.item;
//             p = p.next;
             currentPos++;
             return returnItem;
        }
    }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            LinkedListDeque<?> otherObject = (LinkedListDeque<?>) o;
            if (this.size() != otherObject.size()) {
                return false;
            }
            for (int i = 0; i < this.size(); i++) {
                if (!this.get(i).equals(otherObject.get(i))) {  // Use equals for proper object comparison
                    return false;
                }
            }
            // otherwise, the 2 linkedlistdeque are equal.
            return true;
        }

    @Override
    public void addFirst(T i) {
        /**
         * let newFirst.prev points at sentinel, newFirst.next points to oldFirst(if exist)
         * let oldFirst.prev now points at new firstItem, which mean it comes after newFirst.
         * Lastly, set sentinel.next points at the newFirst
         */

        Node oldFirst = sentinel.next;
        Node newFirst = new Node(sentinel, i, oldFirst);
        oldFirst.prev = newFirst;
        sentinel.next = newFirst;
        size += 1;

//        Node firstNode = new Node(sentinel, i, sentinel.next);
//        sentinel.next.prev = firstNode; // sentinelF.next.prev will either be sentinel or original firstNode
//        sentinel.next = firstNode; // insert new node into between sentinel and original first node
//        size++;
    }

    @Override
    public void addLast(T i) {
        /**
         * newLast.prev points at oldLast (if no last node exist, it will point to sentinel),
         * newLast.next points to sentinel.
         * oldLast.next now points at newLast, which mean it comes before newLast.
         * Lastly, sentinel.prev points at the newLast.
         */
        Node oldLast = sentinel.prev;
        Node newLast = new Node(oldLast, i, sentinel);
        oldLast.next = newLast;
        sentinel.prev = newLast;
        size += 1;

//        Node lastNode = new Node(sentinel.prev, i, sentinel);
//        sentinel.prev.next = lastNode; // sentinel.prev.next is either sentinel or original last node
//        sentinel.prev = lastNode;
//        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("()");
            return;
        }
        System.out.print("(");
        for (int i = 0; i < size() - 1; i++) {
            System.out.printf("%s --> ", get(i));
        }
        System.out.print(get(size - 1));
        System.out.println(")");
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next; // sentinel points to new firstnode
        sentinel.next.prev = sentinel; // new firstnode point to sentinel
        size--;
        return first;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev; // sentinel point to new lastnode
        sentinel.prev.next = sentinel; // new lastnode point to sentinel
        size--;
        return last;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return sentinel.next.item;
    }

    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return sentinel.prev.item;
    }

    @Override
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
    private T getRecursive(int i, Node p) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        // the condition p.item != null is to prevent accessing null sentinel.item
        // this is possible when the list is empty.
        if (i == 0 && p.item != null) {
            return p.item;
        }
        return getRecursive(i - 1, p.next);
    }

    public T getRecursive(int i) {
        return getRecursive(i, sentinel.next);
    }
}
