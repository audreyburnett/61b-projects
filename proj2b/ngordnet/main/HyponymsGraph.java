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

    public boolean containsVertex(T key) {
        return adjList.containsKey(key);
    }
    public boolean containsVal(T val) {
        return adjList.containsValue(val);
    }

    public LinkedList<T> getAdjList(T key) {
        LinkedList<T> val = adjList.get(key);
        return val;
    }

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
