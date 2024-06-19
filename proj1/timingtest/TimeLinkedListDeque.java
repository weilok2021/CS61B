package timingtest;
import deque.ArrayDeque;
import deque.TwoSentinelsLinkedListDeque;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeLinkedListDeque {
    private static void printTimingTable(ArrayDeque<Integer> Ns, ArrayDeque<Double> times, ArrayDeque<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int size = 1000;
        ArrayDeque sizeList = new ArrayDeque();
        ArrayDeque timeList = new ArrayDeque();
        ArrayDeque functionCall = new ArrayDeque();

        while (size <= 128000) {
            TwoSentinelsLinkedListDeque testCall = new TwoSentinelsLinkedListDeque();
            int numCalls = 10000;
            // add items to the list.
            for (int i = 0; i < size; i++) {
                testCall.addLast(i);
            }

            // start the timer
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < numCalls; j++) {
                testCall.getLast();
            }
            // measure the time of calling getLast on different sizes of LinkedListDeque
            double timeInSeconds = sw.elapsedTime();
            sizeList.addLast(size);
            timeList.addLast(timeInSeconds);
            functionCall.addLast(numCalls);
            size *= 2;
        }


        printTimingTable(sizeList, timeList, functionCall);
    }

}
