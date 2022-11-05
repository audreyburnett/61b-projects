package ngordnet.main;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.*;
import java.util.stream.Collectors;

public class WordNet {
    private HyponymsGraph graphWordID;
    private HyponymsGraph hyponymID;
    private HyponymsGraph wordToIDs;
    private NGramMap ngm;

    public WordNet(String synsets, String hyponyms, String wordsFilename, String countsFilename) {
        graphWordID = new HyponymsGraph();
        hyponymID = new HyponymsGraph();
        wordToIDs = new HyponymsGraph();
        ngm = new NGramMap(wordsFilename, countsFilename);
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

    private class Node implements Comparable<Node>{
        private Double key;
        private String val;

        private Node(Double key, String val) {
            this.key = key;
            this.val = val;
        }

        public int compareTo(Node node) {
            if (this.key < node.key) {
                return 1;
            } else if (this.key > node.key) {
                return -1;
            } else {
                return 0;
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
        ArrayList<ArrayList<String>> toReturn = new ArrayList<>();
        if (words.size() > 0 && words != null) {
            ArrayList<String> result = hyponymIDList(words.get(0));
            if (words.get(0) == "") {
                toReturn.add(result);
                return toReturn.toString();
            }
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

    public String kNotZero(List<String> words, int startYear, int endYear, int k) {
        MaxPQ<Node> pq = new MaxPQ<>();
        String hyponyms = listOfWords(words);
        if (k == 0) {
            return hyponyms;
        } else {
            hyponyms = hyponyms.replace("]", "");
            hyponyms = hyponyms.replace("[", "");
            String[] arr = hyponyms.split(", ");
            for (String word : arr) {
                if (word.length() > 0) {
                    TimeSeries wordcount = ngm.countHistory(word, startYear, endYear);
                    Double total = wordcount.data().stream().collect(Collectors.summingDouble(Double::doubleValue));
                    if (total == 0.0) {
                        continue;
                    }
                    Node data = new Node(total, word);
                    if (pq.size() < k) {
                        pq.insert(data);
                    } else {
                        Double peek = pq.max().key;
                        if (data.key < peek) {
                            continue;
                        } else if (data.key > peek) {
                            pq.delMax();
                            pq.insert(data);
                        }
                    }
                }
            }
            ArrayList<String> resultArray = new ArrayList<>();
            for (Node n : pq) {
                if (n != null) {
                    resultArray.add(n.val);
                }
            }
            Collections.sort(resultArray);
            String result = resultArray.toString();
            return result;
        }
    }
}

