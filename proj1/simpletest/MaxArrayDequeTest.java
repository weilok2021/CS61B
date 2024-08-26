package simpletest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import deque.MaxArrayDeque;

public class MaxArrayDequeTest {

    @Test
    public void testMaxOnEmptyDeque() {
        MaxArrayDeque<Integer> emptyDeque = new MaxArrayDeque<>(MaxArrayDeque.getIntComparator());
        assertNull("Max of empty deque should be null", emptyDeque.max());
    }

    @Test
    public void testMaxSingleElement() {
        MaxArrayDeque<Integer> singleElementDeque = new MaxArrayDeque<>(MaxArrayDeque.getIntComparator());
        singleElementDeque.addLast(42);
        assertEquals("Max of single element deque should be the element itself", Integer.valueOf(42), singleElementDeque.max());
    }

    @Test
    public void testMaxMultipleElements() {
        MaxArrayDeque<Integer> intDeque = new MaxArrayDeque<>(MaxArrayDeque.getIntComparator());
        intDeque.addLast(3);
        intDeque.addLast(9);
        intDeque.addLast(5);
        intDeque.addLast(7);
        assertEquals("Max of multiple element deque should be 9", Integer.valueOf(9), intDeque.max());

        MaxArrayDeque<String> stringDeque = new MaxArrayDeque<>(MaxArrayDeque.getStringComparator());
        stringDeque.addLast("apple");
        stringDeque.addLast("banana");
        stringDeque.addLast("cherry");
        assertEquals("Max of string deque should be 'cherry'", "cherry", stringDeque.max());
    }

    @Test
    public void testMaxWithDifferentComparator() {
        MaxArrayDeque<Integer> intDeque = new MaxArrayDeque<>(MaxArrayDeque.getIntComparator());
        intDeque.addLast(3);
        intDeque.addLast(9);
        intDeque.addLast(5);

        // Use a comparator that orders integers in reverse
        java.util.Comparator<Integer> reverseComparator = (a, b) -> b - a;
        assertEquals("Max with reverse comparator should be 3", Integer.valueOf(3), intDeque.max(reverseComparator));
    }
}
