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

    public ArrayList<T> getAdjList(T key) {
        ArrayList<T> returnList = new ArrayList<T>();
        LinkedList<T> val = adjList.get(key);
        if (!(containsVertex(key))) {
            return null;
        } else {
            int i = 0;
            while (i < val.size()) {
                returnList.add(val.get(i));
            }
            return returnList;
        }
    }

    public ArrayList<T> traverse(T item) {
        ArrayList<T> result = new ArrayList<>();
        Stack<T> fringe = new Stack<>();
        fringe.add(item);
        while (!(fringe.isEmpty())) {
            T popped = fringe.pop();
            result.add(popped);
            fringe.addAll(getAdjList(item));
        }
        return result;
    }
}
