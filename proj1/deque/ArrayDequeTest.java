package deque;

import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayDequeTest {

    @Test
    public void addFirstTest() {
        ArrayDeque<Integer> test1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 8; i++) {
            test1.addFirst(i);
        }
        assertEquals(test1, [0,1,2,3,4,5,6,7]);
    }
}
