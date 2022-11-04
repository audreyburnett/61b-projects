package ngordnet.main;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {
    private HyponymsGraph graphWordID;
    private HyponymsGraph hyponymID;
    private HyponymsGraph wordToIDs;

    public WordNet(String synsets, String hyponyms) {
        graphWordID = new HyponymsGraph();
        hyponymID = new HyponymsGraph();
        wordToIDs = new HyponymsGraph();
        In in = new In(synsets);
        In inTwo = new In(hyponyms);
        while (!(in.isEmpty())) {
            String string1 = in.readLine();
            String[] arr = string1.split(",");
            int key = Integer.parseInt(arr[0]);
            String val = arr[1];
            String[] synonyms = val.split("\\s");
            for (String x : synonyms) {
                graphWordID.addEdge(key, x);
                wordToIDs.addEdge(x, key);
            }
        }
        while (!(inTwo.isEmpty())) {
            String string2 = inTwo.readLine();
            String[] arr = string2.split(",");
            int key = Integer.parseInt(arr[0]);
            int end = arr.length;
            for (int x = 1; x < end; x++) {
                hyponymID.addEdge(key, Integer.parseInt(arr[x]));
            }
        }
    }

    public ArrayList<String> hyponymIDList(String word) {
        LinkedList<Integer> keyList = new LinkedList<>();
        keyList = wordToIDs.getAdjList(word);

        ArrayList<Integer> allIDs = new ArrayList<>();
        HashSet<String> allWords = new HashSet<>();
        if (keyList != null) {
            for (Integer i : keyList) {
                System.out.println(keyList);
                if ((hyponymID.containsVertex(i))) {
                    allIDs.addAll(hyponymID.traverse(i));
                } else {
                    allIDs.add(i);
                }
            }
        }
        for (Integer x : allIDs) {
            allWords.addAll(graphWordID.getAdjList(x));
        }
        ArrayList<String> allWordsList = new ArrayList<String>(allWords);
        Collections.sort(allWordsList);
        return allWordsList;
    }

    public String listOfWords(List<String> words) {
        ArrayList<String> toReturn = new ArrayList<>();
        if (words != null) {
            ArrayList<String> result = hyponymIDList(words.get(0));
            int i = 1;
            while (i < words.size() && words.get(i) != null) {
                String string = words.get(i);
                result.retainAll(hyponymIDList(string));
                i += 1;
            }
            return result.toString();
        }
        return "";
    }
}

