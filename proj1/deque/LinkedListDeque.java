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
public class LinkedListDeque<Type> {
    private Node sentinelF;
    private Node sentinelB;
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
        sentinelF = new Node(null,null, null);
        sentinelB = new Node(null, null, null);
        sentinelF.next = sentinelB;
        sentinelB.prev = sentinelF;
        size = 0;
    }
    public LinkedListDeque(Type i) {
        sentinelF = new Node(null, null, null);
        sentinelB = new Node(null, null, null);
        Node firstNode = new Node(sentinelF, i, sentinelB);

        sentinelF.next = firstNode;
        sentinelB.prev = firstNode;

        // chatgpt solution, shorter and cleaner
//        this();
//        addFirst(i);
        size = 1;
    }

    public void addFirst(Type i) {
        Node firstNode = new Node(sentinelF, i, sentinelF.next);
        sentinelF.next.prev = firstNode; // sentinelF.next.prev will either be sentinelB or original firstNode
        sentinelF.next = firstNode; // insert new node into between sentinelF and original first node
        size++;
    }

    public void addLast(Type i) {
        Node lastNode = new Node(sentinelB.prev, i, sentinelB);
        sentinelB.prev.next = lastNode;
        sentinelB.prev = lastNode;
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
        Type first = sentinelF.next.item;
        sentinelF.next = sentinelF.next.next;
        size--;
        return first;
    }

    public Type removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        Type last = sentinelB.prev.item;
        sentinelB.prev = sentinelB.prev.prev;
        size--;
        return last;
    }

    public Type getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return sentinelF.next.item;
    }

    public Type getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return sentinelB.prev.item;
    }
    public Type get(int i) {
//        Node p = sentinelF.next; //start from first item
//        while (i != 0) {
//            // System.out.println(p.item);
//            p = p.next;
//            i--;
//        }
//        return p.item;

        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node p = sentinelF.next; // Start from the first item
        while (i != 0) {
            p = p.next;
            i--;
        }
        return p.item;
    }

    public static void main(String[] args) {
        //LinkedListDeque L1 = new LinkedListDeque();
        LinkedListDeque<Integer> L2 = new LinkedListDeque<>(100);
        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
        for (int i = 1; i <= 10; i++) {
            L2.addFirst(i);
        }
        L2.printDeque();
        System.out.println(L1.getFirst());
        System.out.println(L1.getLast());

    }
}
