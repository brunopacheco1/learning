package com.dev.bruno.learning.connectivity;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

  private int experimentsCount;
  private Percolation percolation;
  private double[] fractions;

  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
    
    experimentsCount = trials;
    fractions = new double[experimentsCount];
    for (int experiment = 0; experiment < experimentsCount; experiment++) {
      percolation = new Percolation(n);
      int openedSites = 0;
      while (!percolation.percolates()) {
        int i = StdRandom.uniform(1, n + 1);
        int j = StdRandom.uniform(1, n + 1);
        if (!percolation.isOpen(i, j)) {
          percolation.open(i, j);
        }
      }
      double fraction = percolation.numberOfOpenSites() / (n^2);
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
    return mean() - ((1.96 * stddev()) / Math.sqrt(experimentsCount));
  }

  public double confidenceHi() {
    return mean() + ((1.96 * stddev()) / Math.sqrt(experimentsCount));
  }

  public static void main(String[] args) {
    //int n = Integer.parseInt(args[0]);
    //int trials = Integer.parseInt(args[1]);
    int n = 10;
    int trials = 100;
    PercolationStats stats = new PercolationStats(n, trials);

    String confidence = stats.confidenceLo() + ", " + stats.confidenceHi();
    StdOut.println("mean                    = " + stats.mean());
    StdOut.println("stddev                  = " + stats.stddev());
    StdOut.println("95% confidence interval = " + confidence);
  }
}

