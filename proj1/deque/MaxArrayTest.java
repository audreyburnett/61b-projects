package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

public class MaxArrayTest {
    private static class IntComparator implements Comparator<Integer>{
        public int compare(int a, int b){
            return a - b;
        }
    }

    public static Comparator<T> getNameComparator() {
        return new NameComparator();
    }
    @Test
    public void compareIntTest() {
        Comparator<Integer> a = .getIntComparator<>();
        MaxArrayDeque<Integer> test1 = new MaxArrayDeque<>(a);




        a.compare()
    }
}


