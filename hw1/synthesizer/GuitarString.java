// DOWN: Make sure to make this class a part of the synthesizer package
package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // DOWN: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.

        buffer = new ArrayRingBuffer<>((int) Math.round(SR / frequency));

    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // DOWN: Dequeue everything in the buffer, and replace it with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each other.
        while (!buffer.isEmpty()) {
            buffer.dequeue();
        }
        double fillIn = Math.random() - 0.5;
        while (!buffer.isFull()) {
            buffer.enqueue(fillIn);
            double fillIn2 = Math.random() - 0.5;
            while (fillIn2 == fillIn) {
                fillIn2 = Math.random() - 0.5;
            }
            fillIn = fillIn2;
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // DOWN: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        if (buffer.isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        double front = buffer.dequeue();
        double second = buffer.peek();
        double toEnque = DECAY * 0.5 * (front + second);
        buffer.enqueue(toEnque);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // DOWN: Return the correct thing.
        if (buffer.isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return buffer.peek();
    }
}
