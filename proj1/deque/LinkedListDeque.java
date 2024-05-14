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
public class LinkedListDeque {
    private Node sentinelF;
    private Node sentinelB;
    private int size;

    private class Node {
        private Node prev;
        private int item;
        private Node next;
        private Node(Node p, int i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinelF = new Node(null,69, null);
        sentinelB = new Node(null, 96, null);
        sentinelF.next = sentinelB;
        sentinelB.prev = sentinelF;
        size = 0;
    }
    public LinkedListDeque(int i) {
//        sentinelF = new Node(null, 69, null);
//        sentinelB = new Node(null, 96, null);
//
//
//        sentinelF.next = new Node(sentinelF, i, null);
//        sentinelB.prev = sentinelF.next;  // sentinelB.prev is the last item of list
//        sentinelB.next = null;
        this();
        addFirst(i);
        size = 1;
    }

    public int size() {
        return size;
    }

    public void addFirst(int i) {
        Node firstNode = new Node(sentinelF, i, sentinelF.next);
        sentinelF.next.prev = firstNode; // sentinelF.next.prev will either be sentinelB or original firstNode
        sentinelF.next = firstNode; // insert new node into between sentinelF and original first node
        size++;
    }

    public void addLast(int i) {
        Node lastNode = new Node(sentinelB.prev, i, sentinelB);
        sentinelB.prev.next = lastNode;
        sentinelB.prev = lastNode;
        size++;
    }

    public int getFirst() {
        return sentinelF.next.item;
    }
    public int getLast() {
        return sentinelB.prev.item;
    }

    public int get(int i) {
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
        LinkedListDeque L2 = new LinkedListDeque(100);
        LinkedListDeque L1 = new LinkedListDeque();
        for (int i = 1; i <= 10; i++) {
            L2.addLast(i);
        }
        System.out.println(L2.get(0));
    }
}
