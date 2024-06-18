package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> a1 = new AListNoResizing<>();
        BuggyAList<Integer> a2 = new BuggyAList<>();

        for (int i = 4; i <= 6; i++) {
            a1.addLast(i);
            a2.addLast(i);
        }
        assertEquals(a1.size(), a2.size());

        assertEquals(a1.removeLast(), a2.removeLast());
        assertEquals(a1.removeLast(), a2.removeLast());
        assertEquals(a1.removeLast(), a2.removeLast());
        assertEquals(a1.size(), a2.size());

    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(L.size(), L2.size());
            } else if (operationNumber == 2) {
                if (L.size() > 0 && L2.size() > 0) {
                    assertEquals(L.getLast(), L2.getLast());
                    assertEquals(L.removeLast(), L2.removeLast());
                }
            }
        }
    }
}