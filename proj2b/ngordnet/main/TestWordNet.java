//package ngordnet.main;
//
//import edu.princeton.cs.algs4.StdOut;
//import org.junit.Test;
//
//import java.util.List;
//import java.util.Set;
//
//import static org.junit.Assert.assertEquals;
//
//public class TestWordNet {
//    @Test
//    public void testHyponymsSimple() {
//        WordNet wn = new WordNet("./ngordnetData2022/data/wordnet/synsets16.txt", "./ngordnetData2022/data/wordnet/hyponyms16.txt");
//        assertEquals(List.of("actifed", "antihistamine"), wn.hyponymIDList("antihistamine"));
//    }
//    @Test
//    public void testHyponymsHuge() {
//        WordNet wn = new WordNet("./data/wordnet/synsets1000-subgraph.txt", "./data/wordnet/hyponyms1000-subgraph.txt");
//        assertEquals(List.of("albumen", "albumin", "lactalbumin", "ricin", "ricin_toxin", "serum_albumin"), wn.hyponymIDList("albumin"));
//    }
//    @Test
//    public void testHyponymsList() {
//        WordNet wn = new WordNet("./ngordnetData2022/data/wordnet/synsets16.txt", "./ngordnetData2022/data/wordnet/hyponyms16.txt");
////        System.out.print();
////        System.out.print();
//        //assertEquals(List.of("change", "demotion", "variation"), wn.listOfWords(List.of("action", "change")));
//    }
//
//    @Test
//    public void testNull() {
//        WordNet wn = new WordNet("./data/wordnet/synsets16.txt", "./data/wordnet/hyponyms16.txt");
//        assertEquals(List.of("alteration", "change", "increase", "jump", "leap", "modification", "saltation", "transition"), wn.listOfWords(List.of("change", "occurrence")));
//    }
//
//}
