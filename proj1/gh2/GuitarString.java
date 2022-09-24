package gh2;

import deque.ArrayDeque;
import deque.Deque;

public class GuitarString {
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    private Deque<Double> buffer;

    public GuitarString(double frequency) {
        int capacity = (int) (Math.round(SR / frequency));
        buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addFirst(0.0);
        }

    }

    public void pluck() {
        int sub = buffer.size();
        for (int i = 0; i < sub; i++) {
            buffer.removeFirst();
        }
        for (int i = 0; i < sub; i++) {
            double r = Math.random() - 0.5;
            buffer.addFirst(r);
        }
    }


    public void tic() {
        Double first = buffer.get(0);
        buffer.removeFirst();
        Double newDouble = ((first + buffer.get(0)) / 2) * DECAY;
        buffer.addLast(newDouble);
    }

    public double sample() {
        return buffer.get(0);
    }
}

