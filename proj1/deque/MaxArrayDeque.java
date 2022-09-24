package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private ArrayDeque<T> items;
    private Comparator<T> given;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        items = new ArrayDeque();
        given = c;
    }

    public T max() {
        if (this.isEmpty()) {
            return null;
        } else {
            T max = this.get(0);
            for (int i = 0; i < this.size(); i++) {
                if (given.compare(max, this.get(i)) < 0) {
                    max = this.get(i);
                }
            }
            return max;
        }
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        } else {
            T max = this.get(0);
            for (int i = 0; i < this.size() - 1; i++) {
                if (c.compare(max, get(i)) < 0) {
                    max = get(i);
                }
            }
            return max;
        }
    }
}


