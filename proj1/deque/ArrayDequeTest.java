package deque;

import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayDequeTest{

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
        for(int i = 0; i < 100; i ++) {
            test2.addLast(i);
//            test2.printDeque();
//            System.out.println(test2);
        }
        assertTrue(test2.size() == 100);
        test2.printDeque();

    }

    @Test public void removeBigTest() {
        ArrayDeque<Integer> test3 = new ArrayDeque();
        for (int i = 0; i < 100; i++) {
            test3.addLast(i);
        }

        assertTrue(test3.size() == 100);
        for (int i = 0; i < 100; i++) {
            assertEquals(i, (int) test3.removeFirst());
        }

        assertTrue(test3.size() == 0);

        assertTrue(test3.removeLast() == null);

    }

    @Test public void equalsTest(){
        ArrayDeque<Integer> test4 = new ArrayDeque();
        ArrayDeque<Integer> test5 = new ArrayDeque();
        ArrayDeque<Integer> test6 = new ArrayDeque();

        for (int i = 0; i < 100; i++){
            test4.addLast(i);
            test5.addLast(i);
        }
        assertTrue(test4.size() == 100);
        assertTrue(test5.size() == 100);
        assertEquals(test4, test5);
        assertNotEquals(test4, test6);

    }

    @Test public void resizeTest(){
        ArrayDeque<Integer> test7 = new ArrayDeque();
        ArrayDeque<Integer> test8 = new ArrayDeque();
        for (int i = 0; i < 9; i++){
            test7.addLast(i);
//            assertTrue(test7.get(i) == i);
        }
        assertTrue(test7.get(8) == 8);

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

