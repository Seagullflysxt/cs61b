/**Invariants:
 * The first item in the deque is alwasy at the position one after nextFirst.
 * The last item ....at the position one before nextLast.
 * Size is always the number of items in the deque.
 *
 * notice:
 * Correctly resizing your deque
 * About usage ratio: For arrays of length 16 or more,
 * your usage factor should always be at least 25%.
 * For smaller arrays, your usage factor can be arbitrarily low.*/
public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private double r;

    /**Creates an empty array deque.*/
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
        r = 0;
    }

    /**Consider a helper function to do little tasks like compute array indices.
     *  For example, in my implementation of ArrayDeque,
     *  I wrote a function called int minusOne(int index)
     *  that computed the index immediately “before” a given index.*/

    private int minusOne(int f) {
        if (f == 0) {
            f = items.length - 1;
        } else {
            f = f - 1;
        }
        return f;
    }
    private int addOne(int l) {
        if (l == items.length - 1) {
            l = 0;
        } else {
            l = l + 1;
        }
        return l;
    }
    private void checkR() {
        r = (double) size / (double) items.length;
        if (items.length > 16 && r < 0.25) {
            resize(items.length / 2);
        }
    }
    private void resize(int capacity) {
        T[] a, b = (T[]) new Object[capacity];
        a = orderdDeque();
        System.arraycopy(a, 0, b, 0, size);
        items = b;
        nextFirst = items.length - 1;
        nextLast = size;
    }
    /**Adds an item of type T to the front of the deque*/
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }
    /** Adds an item of type T to the back of the deque.*/
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = addOne(nextLast);
        size += 1;
    }
    /** Returns true if deque is empty, false otherwise.*/
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    /** Returns the number of items in the deque.*/
    @Override
    public int size() {
        return size;
    }
    /**prints the items in the deque from first to last, separated by a space.*/
    private T[] orderdDeque() {
        T[] orderd = (T[]) new Object[size];
        int index = addOne(nextFirst);
        for (int i = 0; i < size; i++) {
            orderd[i] = items[index];
            index = addOne(index);
        }
        return orderd;
    }
    @Override
    public void printDeque() {
        T[] printing = (T[]) new Object[size];
        printing = orderdDeque();
        for (int i = 0; i < size - 1; i++) {
            System.out.print(printing[i] + " ");
        }
        System.out.println(printing[size - 1]);
    }
    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.*/
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int index = addOne(nextFirst);
        T removed = items[index];
        items[index] = null;
        nextFirst = index;
        size -= 1;
        checkR();
        return removed;
    }
    /**Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.*/
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int index = minusOne(nextLast);
        T removed = items[index];
        nextLast = index;
        items[index] = null;
        size -= 1;
        checkR();
        return removed;
    }
    /**Gets the item at the given index,
     *  where 0 is the front,
     *  1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!*/
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int ptr = addOne(nextFirst);
        for (int i = 0; i < index; i++) {
            ptr = addOne(ptr);
        }
        return items[ptr];
    }

/*    public static void main(String[] args) {
        ArrayDeque<Integer> ad = new ArrayDeque<>();

        for(int i = 0; i < 17; i++){
            ad.addFirst(i);
        }
        for(int i = 0; i < 16; i++){
            ad.removeFirst();
        }
        for(int i = 0; i < 16; i++){
            ad.addFirst(i+1);
        }
    }*/
}
