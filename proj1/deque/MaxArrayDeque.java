package deque;

import java.sql.Array;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private ArrayDeque<T> items;
    private Comparator<T> given;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        items = new ArrayDeque();
        given = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        } else {
            T max = get(0);
            for (int i = 0; i < size() - 1; i++) {
                for (int j = 1; i < size(); i++) {
                    if (given.compare(get(i), get(j)) > 0 && given.compare(get(i), max) >= 0) {
                        max = get(i);
                    } else if (given.compare(get(j), get(i)) > 0 && given.compare(get(j), max) >= 0) {
                        max = get(j);
                    } else if (given.compare(get(j), get(i)) == 0 && given.compare(get(j), max) >= 0) {
                        max = get(j);
                    }
                }
            }
            return max;
        }
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        } else {
            T max = get(0);
            for (int i = 0; i < size() - 1; i++) {
                for (int j = 1; i < size(); i++) {
                    if (c.compare(get(i), get(j)) > 0 && c.compare(get(i), max) >= 0) {
                        max = get(i);
                    } else if (c.compare(get(j), get(i)) > 0 && c.compare(get(j), max) >= 0) {
                        max = get(j);
                    } else if (c.compare(get(j), get(i)) == 0 && c.compare(get(j), max) >= 0) {
                        max = get(j);
                    }
                }
            }
            return max;
        }
    }
}


