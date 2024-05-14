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

        for (int i = 0; i < 3; i++) {
            int x = a1.removeLast();
            int y = a2.removeLast();
            assertEquals(a1.size(), a2.size());
            assertEquals(x, y);
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
        }
    }
}
