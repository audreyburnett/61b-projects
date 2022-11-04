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
                allIDs.addAll(hyponymID.traverse(i));
            }
            else {
                allIDs.add(i);
            }
        }
        for (Integer x : allIDs) {
            allWords.addAll(graphWordID.getAdjList(x));
        }
        ArrayList<String> allWordsList = new ArrayList<String>(allWords);
        Collections.sort(allWordsList);
        return allWordsList;
    }

    //    public ArrayList<String> listOfWords(String words) {
//        String[] arr = words.split(", ");
//        ArrayList<String> result = hyponymIDList(arr[0]);
//        int i = 1;
//        while (i < arr.length && arr[i] != null) {
//            result.retainAll(hyponymIDList(arr[i]));
//            i += 1;
//        }
//        return result;
//    }
    public String listOfWords(List<String> words) {
        if (words != null) {
            ArrayList<String> result = hyponymIDList(words.get(0));
//            ArrayList<String> result2 = hyponymIDList(words.get(2));
//            System.out.println(result);
            int i = 1;
//            result.retainAll(hyponymIDList(words.get(2)));
            while (i < words.size() && words.get(i) != null) {
                String string = words.get(i);
//                System.out.println(hyponymIDList(words.get(i)));
                result.retainAll(hyponymIDList(string));
                i += 1;
            }
            return result.toString();
        }
        return "";
    }
}

