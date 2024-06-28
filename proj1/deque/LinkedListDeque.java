package deque;
import java.util.Iterator;


/**  Invariance
 * sentinel.next is always equals to either first node or sentinel itself
 * sentinel.prev is always equals to either last node or sentinel itself
 * this creates a circular structure
 * the size variable is always equals to the number of items in list.
 * */

public class LinkedListDeque<Type> implements Deque<Type>, Iterable<Type>{
    private Node sentinel;
    // private Node sentinelB;
    private int size;

    private class Node {
        private Node prev;
        private Type item;
        private Node next;
        private Node(Node p, Type i, Node n) {
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

    public LinkedListDeque(Type i) {
        sentinel = new Node(null, null, null);
        Node firstNode = new Node(sentinel, i, sentinel);
        sentinel.next = firstNode;
        sentinel.prev = firstNode;
        // chatgpt solution, shorter and cleaner
//        this();
//        addFirst(i);
        size = 1;
    }

    public Iterator<Type> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<Type> {
        private int currentPos;
        // private Node p = sentinel.next; // the first item in linkedlist

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
        public Type next() {
            Type returnItem = get(currentPos); // very slow when size is large
//             Type returnItem = p.item;
//             p = p.next;
             currentPos++;
             return returnItem;
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        // o is the identical object as this, return true immediately
//        if (this == o) {
//            return true;
//        }
//
//        if (o instanceof LinkedListDeque otherObject) {
//            // different size indicates different list
//            if (this.size() != otherObject.size()) {
//                return false;
//            }
//
//            // verify the equality of corresponding elements in both list.
//            for (int i = 0; i < this.size(); i++) {
//                if (this.get(i) != otherObject.get(i)) {
//                    return false;
//                }
//            }
//            return true;
//        }
//        return false;
//    }

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
    public void addFirst(Type i) {
        Node firstNode = new Node(sentinel, i, sentinel.next);
        sentinel.next.prev = firstNode; // sentinelF.next.prev will either be sentinel or original firstNode
        sentinel.next = firstNode; // insert new node into between sentinel and original first node
        size++;
    }

    @Override
    public void addLast(Type i) {
        Node lastNode = new Node(sentinel.prev, i, sentinel);
        sentinel.prev.next = lastNode; // sentinel.prev.next is either sentinel or original last node
        sentinel.prev = lastNode;
        size++;
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
    public Type removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Type first = sentinel.next.item;
        sentinel.next = sentinel.next.next; // sentinel points to new firstnode
        sentinel.next.prev = sentinel; // new firstnode point to sentinel
        size--;
        return first;
    }

    @Override
    public Type removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Type last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev; // sentinel point to new lastnode
        sentinel.prev.next = sentinel; // new lastnode point to sentinel
        size--;
        return last;
    }

    public Type getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return sentinel.next.item;
    }

    public Type getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return sentinel.prev.item;
    }

    @Override
    public Type get(int i) {
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
    private Type getRecursive(int i, Node p) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (i == 0 && p != null) {
            return p.item;
        }
        return getRecursive(i - 1, p.next);
    }

    public Type getRecursive(int i) {
        return getRecursive(i, sentinel.next);
    }
}
