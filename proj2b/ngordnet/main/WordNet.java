package ngordnet.main;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    public ArrayList<String> hyponymIDList(String word) {
        int wordID;
        if (graphWordID.containsVal(word)) {
            wordID = graphWordID.getKey(word);
        } else {
            return null;
        }
        ArrayList<Integer> allIDs = new ArrayList<>();
        HashSet<String> allWords = new HashSet<>();
        if (!(hyponymID.containsVertex(wordID))) {
            return null;
        } else {
            allIDs = hyponymID.traverse(wordID);
        }
        for (Integer x : allIDs) {
            allWords.addAll(graphWordID.getAdjList(x));
        }
        ArrayList<String> allWordsList = new ArrayList<String>(allWords);
        Collections.sort(allWordsList);
        return allWordsList;
    }
}
