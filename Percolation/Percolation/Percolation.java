package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] sites;
    private int openSites;
    private WeightedQuickUnionUF wqUF;
    private WeightedQuickUnionUF backWash;
    private int N;
    private int top;
    private int bottom;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N < 1) {
            throw new IllegalArgumentException();
        }
        // blocked = 0, open = 1
        sites = new int[N][N];
        openSites = 0;
        wqUF = new WeightedQuickUnionUF(N * N + 2);
        backWash = new WeightedQuickUnionUF(N * N + 1);
        this.N = N;
        top = N * N;
        bottom = N * N + 1;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException("Index Out of Bounds Exception");
        }
        if (isOpen(row, col)) {
            return;
        }
        sites[row][col] = 1;
        openSites++;
        connect(row, col);
    }

    private void connect(int row, int col) {
        int oneDim = row * N + col;
        if (row + 1 < N && isOpen(row + 1, col)) {
            wqUF.union(oneDim, (row + 1) * N + col);
            backWash.union(oneDim, (row + 1) * N + col);
        }
        if (row - 1 > -1 && isOpen(row - 1, col)) {
            wqUF.union(oneDim, (row - 1) * N + col);
            backWash.union(oneDim, (row - 1) * N + col);
        }
        if (col + 1 < N && isOpen(row, col + 1)) {
            wqUF.union(oneDim, row * N + (col + 1));
            backWash.union(oneDim, row * N + (col + 1));
        }
        if (col - 1 > -1 && isOpen(row, col - 1)) {
            wqUF.union(oneDim, row * N + (col - 1));
            backWash.union(oneDim, row * N + (col - 1));
        }
        if (row == 0) {
            wqUF.union(top, oneDim);
            backWash.union(top, oneDim);
        }
        if (row == N - 1) {
            wqUF.union(oneDim, bottom);
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= this.N || col < 0 || col >= this.N) {
            throw new IndexOutOfBoundsException();
        }
        return (sites[row][col] == 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= this.N || col < 0 || col >= this.N) {
            throw new IndexOutOfBoundsException();
        }
        int oneDim = row * N + col;
        return wqUF.connected(top, oneDim) && backWash.connected(top, oneDim);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqUF.connected(top, bottom);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }
}
