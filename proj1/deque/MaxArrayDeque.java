package deque;

import java.sql.Array;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    public MaxArrayDeque(Comparator<T> c){

    }
    public T max(){
        if (isEmpty()){
            return null;
        }
        return;
    }
    public T max(Comparator<T> c){
        if (isEmpty()){
            return null;
        }
    }
}
