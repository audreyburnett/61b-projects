package hw2;

import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    StdStats stats;
    StdRandom random;
    double[] lst;
    public PercolationStats(int N, int T, PercolationFactory pf){
        if (T <= 0){
            throw new java.lang.IllegalArgumentException("Can't perform negative operations!");
        }
        if (N <= 0){
            throw new java.lang.IllegalArgumentException("Can't have negative area!");
        }
        lst = new double[T];

        for (int i = 0; i < T; i ++) {
            Percolation grid = pf.make(N);
            while (grid.percolates() != true) {
                int row = random.uniform(N);
                int col = random.uniform(N);
                grid.open(row, col);
            }
            lst[i] = ((double) grid.numberOfOpenSites() / (double) (N * N));
        }
    }
    public double mean(){
        return stats.mean(lst);
    }
    public double stddev(){
        return stats.stddev(lst);
    }
    public double confidenceLow(){
        return;
    }
    public double confidenceHigh(){
        return;
    }
}
