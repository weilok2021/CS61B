package randomizedtest;

import deque.ArrayDeque;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class RandomizedArrayDequeTest {
    // YOUR TESTS HERE
    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> L = new ArrayDeque<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                assertEquals(Integer.valueOf(randVal), L.get(L.size() - 1));
            } else if (operationNumber == 1) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                assertEquals(Integer.valueOf(randVal), L.getFirst());
                // System.out.println("addFirst(" + randVal + ")");
            } else if (operationNumber == 2) {
                // size
                int size = L.size();
                // System.out.println("size(): " + size);
            } else if (operationNumber == 3) {
                // isEmpty
                boolean isEmpty = L.isEmpty();
                // System.out.println("isEmpty(): " + isEmpty);
            } else if (operationNumber == 4) {
                // removeFirst
                if (!L.isEmpty()) {
                    Integer first = L.getFirst();
                    Integer removedFirst = L.removeFirst();
                    assertEquals(Integer.valueOf(removedFirst), first);
                }
            } else if (operationNumber == 5) {
                // removeLast
                if (!L.isEmpty()) {
                    Integer last = L.getLast();
                    Integer removedLast = L.removeLast();
                    assertEquals(Integer.valueOf(removedLast), last);
                }
            }
        }
    }
}
