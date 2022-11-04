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
        In inThree = new In(synsets);
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
//        while (!(inThree.isEmpty())) {
//            String string3 = inThree.readLine();
//            String[] arr = string3.split(",");
//            int val = Integer.parseInt(arr[0]);
//            String[] words = arr[1].split("\\s");
//            for (String word : words) {
//                wordToIDs.addEdge(word, val);
//            }
//        }
    }

    public ArrayList<String> hyponymIDList(String word) {
        ArrayList<Integer> keyList = new ArrayList<>();
        keyList = wordToIDs.getAdjList(word);

        ArrayList<Integer> allIDs = new ArrayList<>();
        HashSet<String> allWords = new HashSet<>();
        for (Integer i : keyList) {
            System.out.println(keyList);
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

