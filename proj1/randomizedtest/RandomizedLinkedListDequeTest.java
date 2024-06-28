package randomizedtest;

import deque.LinkedListDeque;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class RandomizedLinkedListDequeTest {
    // YOUR TESTS HERE
    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();

        int N = 10000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                assertEquals(Integer.valueOf(randVal), L.getRecursive(L.size() - 1));
            } else if (operationNumber == 1) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                assertEquals(Integer.valueOf(randVal), L.getRecursive(0));
            } else if (operationNumber == 2) {
                // removeFirst
                if (!L.isEmpty()) {
                    Integer first = L.getRecursive(0);
                    Integer removedFirst = L.removeFirst();
                    assertEquals(first, removedFirst);
                }
            } else if (operationNumber == 3) {
                // removeLast
                if (!L.isEmpty()) {
                    Integer last = L.getRecursive(L.size() - 1);
                    Integer removedLast = L.removeLast();
                    assertEquals(last, removedLast);
                }
            }
        }
    }
}
