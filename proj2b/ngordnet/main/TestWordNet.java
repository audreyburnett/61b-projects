package ngordnet.main;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TestWordNet {
    @Test
    public void testHyponymsSimple() {
        WordNet wn = new WordNet("./ngordnetData2022/data/wordnet/synsets11.txt", "./ngordnetData2022/data/wordnet/hyponyms11.txt");
        assertEquals(Set.of("antihistamine", "actifed"), wn.hyponymIDList("antihistamine"));
    }
}
