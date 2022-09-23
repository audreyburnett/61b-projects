package deque;

import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayDequeTest {

    @Test
    public void addFirstTest() {
        ArrayDeque<Integer> test1 = new ArrayDeque<Integer>();
        assertTrue("arraydeque test1 is empty", test1.isEmpty());
        test1.addFirst(0);
        assertTrue(test1.size() == 1);

        assertFalse("test1 should now contain 1 item", test1.isEmpty());

        test1.addLast(1);
        assertEquals(2, test1.size());

        test1.addLast(2);
        assertEquals(3, test1.size());
    }

    @Test
    public void addFirstBigTest() {
        ArrayDeque<Integer> test2 = new ArrayDeque();
        test2.printDeque();
        for(int i = 0; i <= 16; i ++) {
            test2.addFirst(i);
//            test2.printDeque();
//            System.out.println(test2);
        }
        assertTrue(test2.size() == 17);
        test2.printDeque();

    }
//        for (int i = 0; i < 8; i++) {
//            test1.addFirst(i);
//            System.out.println((int) test1.get(0));
//        }
//        System.out.println(test1);
//        for (int i = 0; i < 8; i++){
//            assertEquals(i, (int) test1.removeFirst());
//        }
    }

