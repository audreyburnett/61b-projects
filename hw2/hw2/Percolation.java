package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] perc;
    private int len;
    private int numberOfOpenSites;
    private WeightedQuickUnionUF group;


    public Percolation(int N){
        if (N <= 0){
            throw new java.lang.IllegalArgumentException("Cannot have negative area!");
        }
        len = N;
        numberOfOpenSites = 0;
        perc = new int[N][N];
        group = new WeightedQuickUnionUF(N * N);
    }
    private int modulo(int row, int col) {
        return (((row - 1) * len) + col);
    }
    public void open(int row, int col){
        if (row >= len || col >= len){
            throw new java.lang.IndexOutOfBoundsException("Index out of bounds!");
        }
        perc[row][col] = 1;
        if ((row + 1) < len && isOpen(row + 1, col)) {
            group.union(modulo(row, col), modulo(row + 1, col));
        }
        if ((row - 1) >= 0 && isOpen(row - 1, col)) {
            group.union(modulo(row, col), modulo(row - 1, col));
        }
        if ((col + 1) < len && isOpen(row + 1, col)) {
            group.union(modulo(row, col), modulo(row, col + 1));
        }
        if ((col - 1) >= 0 && isOpen(row + 1, col)) {
            group.union(modulo(row, col), modulo(row, col - 1));
        }
        if (row == 0){
            group.union(modulo(row, col), modulo(-1, -1));
        }
        if (row == len - 1){
            group.union(modulo(row, col), modulo(-2, -2));
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
        return group.connected(modulo(row, col), modulo(-1, -1));
    }
    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }
    public boolean percolates(){
        return group.connected(modulo(-1, -1), modulo(-2, -2));
    }
}
