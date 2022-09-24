package gh2;

import deque.ArrayDeque;
import deque.Deque;
import java.lang.Math;

public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    private Deque<Double> buffer;

    public GuitarString(double frequency) {
        int capacity = (int) (round(SR / frequency));
        buffer = new ArrayDeque<>();
        for (int i= 0; i < capacity; i++) {
            buffer.addFirst(0);
        }

    }

    public void pluck() {
        for (int i = 0; i < capacity; i++){
            buffer.removeFirst();
        }
        for (int i = 0; i < capacity; i++) {
            double r = Math.random() - 0.5;
            buffer.addFirst(r);
        }
    }


    public void tic() {
        T first = buffer.get(0);
        buffer.removeFirst();
        T newDouble = ((first + buffer.get(0))/2) * 0.996;
        buffer.addLast(newDouble);
    }

    public double sample() {
        return buffer.get(0);
    }
}

