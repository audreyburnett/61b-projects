package ngordnet.ngrams;

import java.util.*;

/** An object for mapping a year number (e.g. 1996) to numerical data. Provides
 *  utility methods useful for data analysis.
 *  @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    /** Constructs a new empty TimeSeries. */
    public TimeSeries() {
        super();
        clear();
    }

    /** Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     *  inclusive of both end points. */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        TimeSeries copy = new TimeSeries();
        for (int i = startYear; i < endYear + 1; i++) {
            if (ts.containsKey(i)) {
                copy.put(i, ts.get(i));
            }
        }
    }

    /** Returns all years for this TimeSeries (in any order). */
    public List<Integer> years() {
        List<Integer> years = new ArrayList<>();
        Set setView = keySet();
        years.addAll(setView);
        return years;
    }

    /** Returns all data for this TimeSeries (in any order).
     *  Must be in the same order as years(). */
    public List<Double> data() {
        List<Double> data = new ArrayList<>();
        int index = 0;
        for (Integer x : years()) {
            data.add(index, get(x));
            index += 1;
        }
        return data;
    }

    /** Returns the yearwise sum of this TimeSeries with the given TS. In other words, for
     *  each year, sum the data from this TimeSeries with the data from TS. Should return a
     *  new TimeSeries (does not modify this TimeSeries). */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries plus = new TimeSeries();
        for (Integer x : this.years()) {
            plus.put(x, this.get(x));
        }
        for (Integer y : ts.years()) {
            if (plus.containsKey(y)) {
                plus.put(y, plus.get(y) + ts.get(y));
            } else {
                plus.put(y, ts.get(y));
            }
        }
        return plus;
    }

     /** Returns the quotient of the value for each year this TimeSeries divided by the
      *  value for the same year in TS. If TS is missing a year that exists in this TimeSeries,
      *  throw an IllegalArgumentException. If TS has a year that is not in this TimeSeries, ignore it.
      *  Should return a new TimeSeries (does not modify this TimeSeries). */
     public TimeSeries dividedBy(TimeSeries ts) {
         TimeSeries quotient = new TimeSeries();
         for (Integer x : this.years()) {
             if (!(ts.containsKey(x))) {
                 throw new IllegalArgumentException();
             }
             quotient.put(x, this.get(x) / ts.get(x));
         }
         return quotient;
    }
}
