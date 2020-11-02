package hw2;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] percentages;
    private int N;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 1 || T < 1) {
            throw new IllegalArgumentException("Illegal Argument Exception");
        }
        percentages = new double[T];
        this.N = N;
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                open(p);
            }
            percentages[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        double totalMean = 0.0;
        for (int i = 0; i < this.T; i++) {
            totalMean += percentages[i];
        }
        return totalMean / T;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double totalStdDev = 0.0;
        for (int i = 0; i < this.T; i++) {
            totalStdDev += Math.pow(percentages[i] - mean(), 2);
        }
        return Math.sqrt(totalStdDev / (T - 1));
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }


    private void open(Percolation p) {
        int row = StdRandom.uniform(N);
        int col = StdRandom.uniform(N);
        while (p.isOpen(row, col)) {
            row = StdRandom.uniform(N);
            col = StdRandom.uniform(N);
        }
        p.open(row, col);
    }

}
