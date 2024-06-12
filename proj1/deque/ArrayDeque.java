package deque;

/**
 * nextFirst + 1 is the current First
 * nextLast - 1 is the current Last
 */
public class ArrayDeque<Type> {
    private Type[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        size = 0;
        items = (Type[]) new Object[30];
        nextFirst = 28;
        nextLast = 29;
    }

    public void addFirst(Type v) {
        // when the array full, resize
//        if (items.length == size) {
//
//        }
        items[nextFirst] = v;
        // I use nextFirst + items.length here to avoid negative index
        nextFirst = (nextFirst + items.length - 1) % 30;
        size++;
    }

    public void addLast(Type v) {
        items[nextLast] = v;
        nextLast = (nextLast + 1) % 30;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        // O(n)
        int t = 0;
        while (t < items.length) {
            System.out.print(items[(nextFirst + 1) % 30] + " ");
            nextFirst = (nextFirst + 1) % 30;
            t++;
        }
        System.out.println("");
    }

    public Type removeFirst() {
        if (!isEmpty()) {
            Type firstItem = items[(nextFirst + 1) % 30];
            items[(nextFirst + 1) % 30] = null;
            nextFirst = (nextFirst + 1) % 30;
            size--;
            return firstItem;
        }
        return null;
    }

    public Type removeLast() {
        if (!isEmpty()) {
            Type lastItem = items[(nextLast - 1) % 30];
            items[(nextLast - 1) % 30] = null;
            nextLast = (nextLast - 1) % 30;
            size--;
            return lastItem;
        }
        return null;
    }

    public Type get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        return items[(nextFirst + 1 + i) % 30];
    }

    public static void main(String[] args) {
//        ArrayDeque L1 = new ArrayDeque();
        // L1.addFirst(4);
//        System.out.println(L1.isEmpty());
//        L1.addLast(1);
//        L1.addLast(2);
//        L1.addLast(3);
//        L1.addFirst(10);
//        System.out.println(L1.removeFirst());
//        System.out.println(L1.size());
//
//        L1.printDeque();

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

    }
}
