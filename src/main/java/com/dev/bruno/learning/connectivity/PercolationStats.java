package com.dev.bruno.learning.connectivity;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

  private static final double CONFIDENCE_95 = 1.96;
  private final int trials;
  private final double[] treshold;

  public PercolationStats(final int n, final int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException();
    }
    this.trials = trials;
    treshold = new double[trials];
    for (int i = 0; i < treshold.length; i++) {
      treshold[i] = calculateTreshold(n);
    }
  }

  private double calculateTreshold(int n) {
    double counter = 0;
    int i, j;
    Percolation percolation = new Percolation(n);
    while (!percolation.percolates()) {
      i = StdRandom.uniform(n);
      j = StdRandom.uniform(n);
      if (!percolation.isOpen(i, j)) {
        counter++;
        percolation.open(i, j);
      }
    }
    return counter / (n * n);
  }

  public double mean() {
    return StdStats.mean(treshold);
  }

  public double stddev() {
    return StdStats.stddev(treshold);
  }

  public double confidenceLo() {
    return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
  }

  public double confidenceHi() {
    return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(trials));
  }

  public static void main(final String[] args) {
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    final PercolationStats stats = new PercolationStats(n, trials);

    final String confidence = stats.confidenceLo() + ", " + stats.confidenceHi();
    StdOut.println("mean                    = " + stats.mean());
    StdOut.println("stddev                  = " + stats.stddev());
    StdOut.println("95% confidence interval = " + confidence);
  }
}
