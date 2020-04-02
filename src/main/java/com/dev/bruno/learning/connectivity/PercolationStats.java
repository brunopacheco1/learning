package com.dev.bruno.learning.connectivity;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

  private static final double CONFIDENCE_95 = 1.96;
  private final int experimentsCount;
  private final double[] fractions;

  public PercolationStats(final int n, final int trials) {
    if (n <= 0 || trials <= 0)
      throw new IllegalArgumentException();

    experimentsCount = trials;
    fractions = new double[experimentsCount];
    for (int experiment = 0; experiment < experimentsCount; experiment++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        final int i = StdRandom.uniform(0, n);
        final int j = StdRandom.uniform(0, n);
        if (!percolation.isOpen(i, j)) {
          percolation.open(i, j);
        }
      }
      final double fraction = percolation.numberOfOpenSites() / (n * n);
      fractions[experiment] = fraction;
    }
  }

  public double mean() {
    return StdStats.mean(fractions);
  }

  public double stddev() {
    return StdStats.stddev(fractions);
  }

  public double confidenceLo() {
    return mean() - ((CONFIDENCE_95 * stddev()) / Math.sqrt(experimentsCount));
  }

  public double confidenceHi() {
    return mean() + ((CONFIDENCE_95 * stddev()) / Math.sqrt(experimentsCount));
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
