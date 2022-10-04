package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] perc;
    private int len;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF group;
    private WeightedQuickUnionUF group2;


    public Percolation(int N){
        if (N <= 0){
            throw new java.lang.IllegalArgumentException("Cannot have negative area!");
        }
        len = N;
        numberOfOpenSites = 0;
        perc = new int[N][N];
        group = new WeightedQuickUnionUF(N * N + 2);
        group2 = new WeightedQuickUnionUF(N * N + 1);
    }
    private int modulo(int row, int col) {
        return (((row) * len) + col);
    }
    public void open(int row, int col){
        if (row >= len || col >= len){
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds!");
        }
        perc[row][col] = 1;
        if ((row + 1) < len && isOpen(row + 1, col)) {
            group.union(modulo(row, col), modulo(row + 1, col));
            group2.union(modulo(row, col), modulo(row + 1, col));
        }
        if ((row - 1) >= 0 && isOpen(row - 1, col)) {
            group.union(modulo(row, col), modulo(row - 1, col));
            group2.union(modulo(row, col), modulo(row - 1, col));
        }
        if ((col + 1) < len && isOpen(row + 1, col)) {
            group.union(modulo(row, col), modulo(row + 1, col));
            group2.union(modulo(row, col), modulo(row + 1, col));
        }
        if ((col - 1) >= 0 && isOpen(row + 1, col)) {
            group.union(modulo(row, col), modulo(row, col - 1));
            group2.union(modulo(row, col), modulo(row, col - 1));
        }
        if (row == 0){
            group.union(modulo(row, col), modulo(len, len));
            group2.union(modulo(row, col), modulo(len, len));
        }
        if (row == len - 1){
            group.union(modulo(row, col), modulo(len + 1, len + 1));
        }
        numberOfOpenSites += 1;
    }
    public boolean isOpen(int row, int col){
        if (row >= len || col >= len){
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds!");
        }
        return perc[row][col] == 1;
    }
    public boolean isFull(int row, int col){
        if (row >= len || col >= len || row < 0 || col < 0){
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds!");
        }
        return group2.connected(modulo(row, col), modulo(len, len));
    }
    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }
    public boolean percolates(){
        return group.connected(modulo(len, len), modulo(len + 1, len + 1));
    }
}
