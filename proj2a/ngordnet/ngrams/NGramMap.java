package ngordnet.ngrams;

import java.util.Collection;
import java.util.HashMap;
import edu.princeton.cs.algs4.In;

/** An object that provides utility methods for making queries on the
 *  Google NGrams dataset (or a subset thereof).
 *
 *  An NGramMap stores pertinent data from a "words file" and a "counts
 *  file". It is not a map in the strict sense, but it does provide additional
 *  functionality.
 *
 *  @author Josh Hug
 */
public class NGramMap {
    private HashMap<String, TimeSeries> words;
    private TimeSeries counts;
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    public NGramMap(String wordsFilename, String countsFilename) {
        words = new HashMap();
        counts = new TimeSeries();
        In in = new In(wordsFilename);
        while (!(in.isEmpty())) {
            String string = in.readLine();
            String[] arr = string.split("\\t");
            String key = arr[0];
            if (words != null && words.containsKey(key)) {
                int a = Integer.parseInt(arr[1]);
                Double b = Double.parseDouble(arr[2]);
                words.get(key).put(a, b);
            } else {
                TimeSeries value = new TimeSeries();
                int a = Integer.parseInt(arr[1]);
                Double b = Double.parseDouble(arr[2]);
                value.put(a, b);
                words.put(key, value);
            }
        }
        In two = new In(countsFilename);
        while (!(two.isEmpty())) {
            String nextString = two.readString();
            String[] arrOfStr = nextString.split(",");
            int key = Integer.parseInt(arrOfStr[0]);
            Double value = Double.parseDouble(arrOfStr[1]);
            counts.put(key, value);
        }
    }

    /** Provides the history of WORD. The returned TimeSeries should be a copy,
     *  not a link to this NGramMap's TimeSeries. In other words, changes made
     *  to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word) {
        TimeSeries copy = new TimeSeries();
        copy.putAll(words.get(word));
        return copy;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     *  returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other words,
     *  changes made to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries copy = new TimeSeries();
        for (Integer x : words.get(word).years()) {
            if (x >= startYear && x <= endYear) {
                copy.put(x, words.get(word).get(x));
            }
        }
        return copy;
    }

    /** Returns a defensive copy of the total number of words recorded per year in all volumes. */
    public TimeSeries totalCountHistory() {
        TimeSeries copy = new TimeSeries();
        copy.putAll(counts);
        return copy;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD compared to
     *  all words recorded in that year. */
    public TimeSeries weightHistory(String word) {
        TimeSeries wordHistory = countHistory(word);
        TimeSeries weight = wordHistory.dividedBy(totalCountHistory());
        return weight;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     *  and ENDYEAR, inclusive of both ends. */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries wordHistory = countHistory(word, startYear, endYear);
        TimeSeries weight = wordHistory.dividedBy(totalCountHistory());
        return weight;
    }

    /** Returns the summed relative frequency per year of all words in WORDS. */
    public TimeSeries summedWeightHistory(Collection<String> collectionOfWords) {
        TimeSeries result = new TimeSeries();
        for (String x : collectionOfWords) {
            result = result.plus(weightHistory(x));
        }
        return result;
    }

    /** Provides the summed relative frequency per year of all words in WORDS
     *  between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     *  this time frame, ignore it rather than throwing an exception. */
    public TimeSeries summedWeightHistory(Collection<String> collectionOfWords, int startYear, int endYear) {
        TimeSeries result = new TimeSeries();
        for (String x : collectionOfWords) {
            result = result.plus(weightHistory(x, startYear, endYear));
        }
        return result;
    }


}
