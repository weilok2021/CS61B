package deque;
import static org.junit.Assert.assertEquals;

/**  Invariance
 * sentinel.next is always equals to either first node or sentinel itself
 * sentinel.prev is always equals to either last node or sentinel itself
 * this creates a circular structure
 * the size variable is always equals to the number of items in list.
 * */

public class LinkedListDeque<Type> implements Deque<Type>{
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
        for (int i = 0; i < size() - 1; i++) {
            System.out.printf("%s --> ", get(i));
        }
        System.out.println(get(size - 1));
        // System.out.println();
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
