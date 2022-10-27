package ngordnet.main;
import edu.princeton.cs.algs4.In;

public class WordNet {
    private HyponymsGraph graph;
    public WordNet(String synsets, String hyponyms) {
        graph = new HyponymsGraph();
        In in = new In(synsets);
        In inTwo = new In(hyponyms);
        while (!(in.isEmpty())) {
            String string = in.readLine();
            String[] arr = string.split(",");
            int key = Integer.parseInt(arr[0]);
            String val = arr[1];
            graph.addEdge(key, val);
        }
        while (!(inTwo.isEmpty())) {
            String string = in.readLine();
            String[] arr = string.split(",");
            int key = Integer.parseInt(arr[0]);
            int end = arr.length;
            for (int x = 1; x < end; x ++) {
                graph.addEdge(key, x);
            }
        }

    }
    //wrapper fro a graph

}
