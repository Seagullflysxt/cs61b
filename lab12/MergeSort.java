import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> toReturn = new Queue<>();
        for (Item i : items) {
            Queue<Item> temp = new Queue<>();
            temp.enqueue(i);
            toReturn.enqueue(temp);
        }
        return toReturn;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> toReturn = new Queue<>();
        int size = q1.size() + q2.size();
        for (int i = 0; i < size; i += 1) {
            Item item = getMin(q1, q2);
            toReturn.enqueue(item);
        }
        return toReturn;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() <= 1) {
            return items;
        }
        Queue<Item> left = new Queue<>();
        Queue<Item> right = new Queue<>();
        //split into 2 parts
        int middle = items.size() / 2;
        for (Item i : items) {
            if (middle > 0) {
                left.enqueue(i);
            } else {
                right.enqueue(i);
            }
            middle -= 1;
        }
        left = mergeSort(left);
        right = mergeSort(right);
        Queue<Item> toReturn = mergeSortedQueues(left, right);
        return toReturn;
    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Zthan");
        students.enqueue("Vthan");

        Queue<String> sorted = mergeSort(students);
        System.out.println("Original: " + students);
        System.out.println("Sorted: " + sorted);

        Queue<Integer> numbers = new Queue<>();
        numbers.enqueue(6);
        numbers.enqueue(5);
        numbers.enqueue(0);
        numbers.enqueue(8);
        numbers.enqueue(3);
        Queue<Integer> sortedN = mergeSort(numbers);
        System.out.println("Original: " + numbers);
        System.out.println("Sorted: " + sortedN);

    }
}
