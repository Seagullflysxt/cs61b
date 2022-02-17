// DOWN: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//DOWN:: Make sure to make this class and all of its methods public
//DOWN:: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // DOWN:: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        rb = (T[]) new Object[this.capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }



    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    private int addOne(int pos) {
        int nextPos = pos + 1;
        if (nextPos == capacity) {
            nextPos = 0;
        }
        return nextPos;
    }
    public void enqueue(T x) {
        // DOWN:: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow,enqueue isfull");
        }

        rb[last] = x;
        fillCount += 1;
        //!!!!!!!!!!!
        last = addOne(last);
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // DOWN:: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow,dequeue empty");
        }
        fillCount -= 1;
        T returnValue = rb[first];
        rb[first] = null;
        //!!!!!!!!
        first = addOne(first);

        return returnValue;

    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // DOWN:: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow,peek empty");
        }
        return rb[first];
    }

    // DOWN:: When you get to part 5, implement the needed code to support iteration.
    public Iterator<T> iterator() {
        return new BQIterartor();
    }
    private class BQIterartor implements Iterator<T> {
        private int wizPos;

        BQIterartor() {
            wizPos = 0;

        }
        public boolean hasNext() {
           /* if (wizPos < size){
                return true;
            }
            return false;*/
            return wizPos < fillCount();
        }
        public T next() {
            T returnItem = rb[wizPos];
            wizPos += 1;
            return returnItem;
        }
    }
}
