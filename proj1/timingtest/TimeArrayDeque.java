package timingtest;
import deque.ArrayDeque;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeArrayDeque {
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
        timeArrayDequeConstruction();
    }

    public static void timeArrayDequeConstruction() {
        // TODO: YOUR CODE HERE
        // Stopwatch sw = new Stopwatch();
        int n = 1000;
        ArrayDeque numFunctionCalls = new <Integer>ArrayDeque();
        ArrayDeque timePerCall = new <Double>ArrayDeque();

        while (n <= 128000) {
            ArrayDeque testCall = new ArrayDeque();
            Stopwatch sw = new Stopwatch();
            // double timeInSeconds = 0;
            for (int i = 0; i < n; i++) {
                testCall.addLast(n);
                //timeInSeconds += sw.elapsedTime();
            }
            double timeInSeconds = sw.elapsedTime();
            numFunctionCalls.addLast(n);
            timePerCall.addLast(timeInSeconds);
            n *= 2;
        }
        printTimingTable(numFunctionCalls, timePerCall, numFunctionCalls);

    }
}
