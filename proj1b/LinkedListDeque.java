public class LinkedListDeque<T> implements Deque<T>{
    //nested class
    private class TNode {
        TNode prev;
        T item;
        TNode next;
        //constructor of TNode
        TNode(TNode p, T i, TNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private TNode sentinel;
    private int size;

    //constructor of LinkedListDeque
    /*public LinkedListDeque(T x) {
        sentinel = new TNode(null, (T) "A", null);
        sentinel.next = new TNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }*/
    public LinkedListDeque() {
        sentinel = new TNode(sentinel, (T) "A", sentinel);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    /**Adds an item of type T to the front of the deque.*/
    @Override
    public void addFirst(T item) {
        sentinel.next = new TNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        //sentinel.prev.prev = sentinel.next;
        size += 1;
    }
    /**Adds an item of type T to the back of the deque.*/
    @Override
    public void addLast(T item) {
        sentinel.prev = new TNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }
    /**Returns true if deque is empty, false otherwise.*/
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    /**Returns the number of items in the deque.*/
    @Override
    public int size() {
        return size;
    }
    /**Prints the items in the deque from first to last,
     * separated by a space.*/
    @Override
    public void printDeque() {
        if (size == 0) {
            System.out.println("null deque");
            return;
        }
        TNode ptr = this.sentinel.next;
        for (int i = 0; i < (size - 1); i++) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println(ptr.item);
    }
    /**Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;

        return temp;
    }
    /**Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;

        return temp;
    }
    /**Gets the item at the given index,
     * where 0 is the front, 1 is the next item,and so forth.
     * If no such item exists, returns null.
     * Use iteration,
     * Must not alter the deque!*/
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        TNode ptr = sentinel.next;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }

        return ptr.item;
    }
    /**Same as get, but uses recursion.
     * Needs a helper method!!!*/
    private T getRecursive(int index, TNode current) {
        if (index == 0) {
            return current.item;
        }
        return getRecursive(index - 1, current.next);
    }
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursive(index, this.sentinel.next);
    }

   /**public static void main(String[] args) {
        LinkedListDeque<Integer> l1 = new LinkedListDeque<>();
        l1.addFirst(1);
        l1.addFirst(2);
        l1.addFirst(4);
        l1.addFirst(5);
        l1.addFirst(6);
        //l1.printDeque();
        int i = l1.removeLast();
        l1.addFirst(8);
        l1.addFirst(9);
        l1.addFirst(10);
        l1.removeLast();

    }*/
}
