package deque;

/**
 * Do this by referring to 4.3 Subtype polymorphism
 * Lesson Learned: Comparable is for us to compare this object and another object. It is embedded within the object itself
 *                 Comparator provides us different ways of comparison (like compare according to size, or anything about
 *                 the object), it more like a third party machine that compares 2 objects to each other.
 */

import java.util.Comparator;
import java.util.Collection;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    /**
     * Constructor
     * public MaxArrayDeque(Comparator<T> c): creates a MaxArrayDeque with the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    /**
     * public T max(): returns the maximum element in the deque as governed by the previously given
     * Comparator. If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        if (this.isEmpty()) {
            return null;
        }
        /**
         * set first element to be the maximum element
         * then iterate through and compare to each element in array deque to
         * find the largest element according to previous comparator
         */
        T maximum = this.get(0);
        for (T element : this) {
            if (comparator.compare(element, maximum) > 0) { // if element is greater than current maximum
                maximum = element; // found new maximum
            }
        }
        return maximum;
    }

    /**
     * public T max(Comparator<T> c): returns the maximum element in the deque as governed by the
     * parameter Comparator c. If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        // short solution using max() implemented above
        Comparator<T> temp = comparator;
        comparator = c;
        T maximum = max(); // find max using this parameter c
        comparator = temp; // set comparator back to the previous comparator
        return maximum;
    }

    private static class IntComparator implements Comparator<Integer> {
        @Override
        /**
         * This method is implemented using inequality property, it returns positive number if a > b
         * returns 0 when a = b, returns negative when a < b
         */
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            /**
             * this method built in for String returns positive number is a is greater than b
             * returns 0 if they are equal, returns -1 if a < b.
             */
            return a.compareTo(b);
        }
    }

    // just a conventional/industrial way for using the comparator class
    public static Comparator<Integer> getIntComparator() {
        return new IntComparator();
    }

    public static Comparator<String> getStringComparator() {
        return new StringComparator();
    }

    public static void main(String args[]) {
        MaxArrayDeque<Integer> a1 = new MaxArrayDeque<>(getIntComparator());
        MaxArrayDeque<String> a2 = new MaxArrayDeque<>(getStringComparator());


        System.out.println(a2.max(getStringComparator()));

    }
}
