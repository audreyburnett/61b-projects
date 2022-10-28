package ngordnet.main;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collections;

public class WordNet {
    private HyponymsGraph graphWordID;
    private HyponymsGraph hyponymID;

    public WordNet(String synsets, String hyponyms) {
        graphWordID = new HyponymsGraph();
        hyponymID = new HyponymsGraph();
        In in = new In(synsets);
        In inTwo = new In(hyponyms);
        while (!(in.isEmpty())) {
            String string = in.readLine();
            String[] arr = string.split(",");
            int key = Integer.parseInt(arr[0]);
            String val = arr[1];
            String[] synonyms = val.split("\\s");
            for (int x = 0; x < synonyms.length; x++) {
                graphWordID.addEdge(key, synonyms[x]);
            }
        }
        while (!(inTwo.isEmpty())) {
            String string = in.readLine();
            String[] arr = string.split(",");
            int key = Integer.parseInt(arr[0]);
            int end = arr.length;
            for (int x = 1; x < end; x++) {
                hyponymID.addEdge(key, x);
            }
        }
    }

    public ArrayList<String> hyponymList(int wordID) {
        if (!(hyponymID.containsVertex(wordID))) {
            return null;
        } else {
            ArrayList<Integer> allIDs = hyponymID.getAdjList(wordID);
            ArrayList<String> hyponyms = new ArrayList<>();
            allIDs.add(wordID);
            for (Integer x : allIDs) {
                hyponyms.addAll(graphWordID.getAdjList(x));
            }
            Collections.sort(hyponyms);
            return hyponyms;
        }
    }
}
