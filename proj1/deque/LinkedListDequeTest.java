package deque;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {
    @Test
    public void sizeTest() {
        TwoSentinelsLinkedListDeque<Integer> L1 = new TwoSentinelsLinkedListDeque<>();
        TwoSentinelsLinkedListDeque<Integer> L2 = new TwoSentinelsLinkedListDeque<>(2);
        TwoSentinelsLinkedListDeque<Integer> L3 = new TwoSentinelsLinkedListDeque<>();

        for (int i = 0; i < 10; i++) {
            L2.addFirst(i);
        }
        for (int i = 0; i < 10; i++) {
            L1.addLast(i);
        }

        assertEquals(11, L2.size());
        assertEquals(10, L1.size());
        assertEquals(0, L3.size());
    }

    @Test
    public void getTest() {
        TwoSentinelsLinkedListDeque L1 = new TwoSentinelsLinkedListDeque();
        TwoSentinelsLinkedListDeque L2 = new TwoSentinelsLinkedListDeque(3);
        for (int i = 0; i < 10; i++) {
            L1.addLast(i);
            assertEquals(L1.get(i), i);
        }

        for (int j = 12; j >= 0; j--) {
            L2.addFirst(j);
        }

        for (int k = 0; k <= 12; k++) {
            assertEquals(L2.get(k), k);
        }
        assertEquals(L2.get(13), 3);
    }
    @Test
    public void getFirstTest() {
        TwoSentinelsLinkedListDeque L1 = new TwoSentinelsLinkedListDeque();
        TwoSentinelsLinkedListDeque L2 = new TwoSentinelsLinkedListDeque(11);
        assertEquals(L2.getFirst(), 11);
        for (int i = 10; i >= 0; i--) {
            L2.addFirst(i);
        }
        for (int i = 10; i >= 0; i--) {
            L1.addFirst(i);
        }
        // assertEquals(L1.get(0), -100000);
        // assertEquals(10, L2.get(0));
        for (int j = 0; j <= 11; j++) {
            assertEquals(L2.get(j), j);
            //i++;
        }
        for (int j = 0; j <= 10; j++) {
            assertEquals(L1.get(j), j);
            //i++;
        }
    }

    @Test
    public void getLastTest() {
        TwoSentinelsLinkedListDeque L1 = new TwoSentinelsLinkedListDeque();
        TwoSentinelsLinkedListDeque L2 = new TwoSentinelsLinkedListDeque(0);
        assertEquals(L2.getLast(), 0);
        for (int i = 1; i <= 10; i++) {
            L2.addLast(i);
            assertEquals(i, L2.getLast());
        }
        // assertEquals(L2.get(2), 100);
    }

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        TwoSentinelsLinkedListDeque<String> lld1 = new TwoSentinelsLinkedListDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        TwoSentinelsLinkedListDeque<Integer> lld1 = new TwoSentinelsLinkedListDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addLast(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        TwoSentinelsLinkedListDeque<Integer> lld1 = new TwoSentinelsLinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        TwoSentinelsLinkedListDeque<String> lld1 = new TwoSentinelsLinkedListDeque<String>();
        TwoSentinelsLinkedListDeque<Double> lld2 = new TwoSentinelsLinkedListDeque<Double>();
        TwoSentinelsLinkedListDeque<Boolean> lld3 = new TwoSentinelsLinkedListDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        TwoSentinelsLinkedListDeque<Integer> lld1 = new TwoSentinelsLinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        TwoSentinelsLinkedListDeque<Integer> lld1 = new TwoSentinelsLinkedListDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }
}


