package deque;
import static org.junit.Assert.assertEquals;

/**  Invariance
 * SentinelF.next always equals to the first node of the linked list
 * SentinelB.next always equals to the last node of the linked list.
 * the size variable is always equals to the number of items in list.
 * Edge cases (Empty List):
 *      SentinelF.next is equals to Sentinel B.
 *      SentinelB.prev is equals to Sentinel F.
 * */
public class CircularSentinelList<Type> {
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

    public CircularSentinelList() {
        sentinel = new Node(sentinel,null, sentinel);
        // sentinelB = new Node(null, null, null);
        size = 0;
    }
    public CircularSentinelList(Type i) {
        sentinel = new Node(null, null, null);
        Node firstNode = new Node(sentinel, i, sentinel);
        sentinel.next = firstNode;
        sentinel.prev = firstNode;

        // chatgpt solution, shorter and cleaner
//        this();
//        addFirst(i);
        size = 1;
    }

    public void addFirst(Type i) {
        Node firstNode = new Node(sentinel, i, sentinel.next);
        sentinel.next.prev = firstNode; // sentinelF.next.prev will either be sentinel or original firstNode
        sentinel.next = firstNode; // insert new node into between sentinel and original first node
        size++;
    }

    public void addLast(Type i) {
        Node lastNode = new Node(sentinel.prev, i, sentinel);
        sentinel.prev.next = lastNode; // sentinel.prev.next is either sentinel or original last node
        sentinel.prev = lastNode;
        size++;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size() - 1; i++) {
            System.out.printf("%s --> ", get(i));
        }
        System.out.println(get(size - 1));
        // System.out.println();
    }

    public Type removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        Type first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size--;
        return first;
    }

    public Type removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Type last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
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
    public Type get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node p = sentinel.next; // Start from the first item
        while (i != 0) {
            p = p.next;
            i--;
        }
        return p.item;
    }

    public static void main(String[] args) {
        //LinkedListDeque L1 = new LinkedListDeque();
        CircularSentinelList<Integer> L2 = new CircularSentinelList<>(100);
        CircularSentinelList<Integer> L1 = new CircularSentinelList<>();
        for (int i = 1; i <= 10; i++) {
            L2.addFirst(i);
        }
        L2.printDeque();
//        System.out.println(L1.getFirst());
//        System.out.println(L1.getLast());

    }
}
