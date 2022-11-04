package ngordnet.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class HyponymsGraph<T> {
    private HashMap<T,LinkedList<T>> adjList;

    public HyponymsGraph() {
        adjList = new HashMap();
    }

    //add edge
    public void addEdge(T key, T val) {
        LinkedList<T> ll = new LinkedList<>();
        if (!(adjList.containsKey(key))) {
            ll.addLast(val);
            adjList.put(key, ll);
        } else {
            adjList.get(key).addLast(val);
        }
    }

    //does the graph contain this vertex
    public boolean containsVertex(T key) {
        return adjList.containsKey(key);
    }
    public boolean containsVal(T val) {
        return adjList.containsValue(val);
    }
//    public ArrayList<T> getKeyList(T word) {
//        ArrayList<T> keyList = new ArrayList<>();
//        for (T i : adjList.keySet()) {
////            System.out.println("us"+adjList.get(i));
//            if (adjList.get(i).contains(word)) {
//                keyList.add(i);
//            }
//        }
////        System.out.println(keyList);
//        return keyList;
//    }

    public LinkedList<T> getAdjList(T key) {
//        ArrayList<T> returnList = new ArrayList<T>();
        LinkedList<T> val = adjList.get(key);
//        if (!(containsVertex(key))) {
//            return null;
//        } else {
//        int i = 0;
//        while (val != null && i < val.size()) {
//            returnList.add(val.get(i));
//            i++;
//        }
//        return returnList;
        return val;
    }
//    }

    public ArrayList<T> traverse(T item) {
        ArrayList<T> result = new ArrayList<>();
        Stack<T> fringe = new Stack<>();
        fringe.add(item);
        while (!(fringe.isEmpty())) {
            T popped = fringe.pop();
            result.add(popped);
            LinkedList<T> nextItems = getAdjList(popped);
            if (nextItems != null) {
                fringe.addAll(nextItems);
            }
        }
        return result;
    }
}
