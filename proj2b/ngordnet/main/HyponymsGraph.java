package ngordnet.main;

import java.util.HashMap;
import java.util.LinkedList;

public class HyponymsGraph<T> {
    private HashMap<T,LinkedList<T>> adjList;
    public HyponymsGraph() {
        adjList = new HashMap();
    }

    public void addEdge(T key, T val) {
        LinkedList<T> ll = new LinkedList<>();
        if (!(adjList.containsKey(key))) {
            ll.addLast(val);
            adjList.put(key, ll);
        } else {
            adjList.get(key).addLast(val);
        }
    }

    public void depthFirstTraversal() {

    }
}
