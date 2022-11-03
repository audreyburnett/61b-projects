package ngordnet.main;
import edu.princeton.cs.algs4.In;

import java.util.*;

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
            String string = inTwo.readLine();
            String[] arr = string.split(",");
            int key = Integer.parseInt(arr[0]);
            int end = arr.length;
            for (int x = 1; x < end; x++) {
                hyponymID.addEdge(key, Integer.parseInt(arr[x]));
            }
        }
    }

    public ArrayList<String> hyponymIDList(String word) {
        ArrayList<Integer> wordID = new ArrayList<>();
        wordID = graphWordID.getKeyList(word);

        ArrayList<Integer> allIDs = new ArrayList<>();
        HashSet<String> allWords = new HashSet<>();
        for (Integer i : wordID) {
            if ((hyponymID.containsVertex(i))) {
                allIDs = hyponymID.traverse(i);
            }
        }
        for (Integer x : allIDs) {
            allWords.addAll(graphWordID.getAdjList(x));
        }
        ArrayList<String> allWordsList = new ArrayList<String>(allWords);
        Collections.sort(allWordsList);
        return allWordsList;
    }

    public ArrayList<String> listOfWords(List<String> words) {
        ArrayList<String> result = hyponymIDList(words.get(0));
        for (int i = 0; i < words.size(); i ++) {
            result.retainAll(hyponymIDList(words.get(i)));
        }
        return result;
    }
}
