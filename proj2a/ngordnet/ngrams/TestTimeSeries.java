package ngordnet.ngrams;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** Unit Tests for the TimeSeries class.
 *  @author Josh Hug
 */
public class TestTimeSeries {
    @Test
    public void testFromSpec() {
        TimeSeries catPopulation = new TimeSeries();
        catPopulation.put(1991, 0.0);
        catPopulation.put(1992, 100.0);
        catPopulation.put(1994, 200.0);

        TimeSeries dogPopulation = new TimeSeries();
        dogPopulation.put(1994, 400.0);
        dogPopulation.put(1995, 500.0);

        TimeSeries totalPopulation = catPopulation.plus(dogPopulation);
        // expected: 1991: 0,
        //           1992: 100
        //           1994: 600
        //           1995: 500

        List<Integer> expectedYears = new ArrayList<>
                (Arrays.asList(1991, 1992, 1994, 1995));

        assertEquals(expectedYears, totalPopulation.years());

        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(0.0, 100.0, 600.0, 500.0));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertEquals(expectedTotal.get(i), totalPopulation.data().get(i), 1E-10);
        }
    }
    @Test
    public void myTest() {
        TimeSeries a = new TimeSeries();
        Double k = 1000.0;
        for (int i = 1900; i < 2000; i = i + 20) {
            a.put(i, k);
            k = k /2;
        }

        TimeSeries b = new TimeSeries();
        for (int j = 1940; j < 2000; j = j + 20) {
            b.put(j, 5.0);
        }

        TimeSeries c = b.dividedBy(a);
        List<Double> expectedTotal = new ArrayList<>
                (Arrays.asList(5.0/250, 5.0/125, 5.0/62.5));

        for (int i = 0; i < expectedTotal.size(); i += 1) {
            assertEquals(expectedTotal.get(i), c.data().get(i), 1E-10);
        }
    }
} 