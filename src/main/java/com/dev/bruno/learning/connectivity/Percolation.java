package com.dev.bruno.learning.connectivity;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final int gridSize;
  private final int top;
  private final int bottom;
  private final WeightedQuickUnionUF quickUnionUF;
  private final boolean[] openSites;
  private int openSitesCounter;

  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }
    gridSize = n;
    openSites = new boolean[n * n + 2];
    quickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    top = 0;
    bottom = n * n + 1;
  }

  public void open(int row, int col) {
    if (isOpen(row, col)) {
      return;
    }

    int position = arrayPosition(row, col);
    openSites[position] = true;
    if (row == 1) {
      quickUnionUF.union(position, top);
      if (openSites[position + gridSize])
        quickUnionUF.union(position, position + gridSize);
    } else if (row == gridSize) {
      quickUnionUF.union(position, bottom);
      if (openSites[position - gridSize])
        quickUnionUF.union(position, position - gridSize);
    } else {
      if (openSites[position + gridSize])
        quickUnionUF.union(position, position + gridSize);
      if (openSites[position - gridSize])
        quickUnionUF.union(position, position - gridSize);
    }

    if (col == 1) {
      if (openSites[position + 1])
        quickUnionUF.union(position, position + 1);
    } else if (col == gridSize) {
      if (openSites[position - 1])
        quickUnionUF.union(position, position - 1);
    } else {
      if (openSites[position - 1])
        quickUnionUF.union(position, position - 1);
      if (openSites[position + 1])
        quickUnionUF.union(position, position + 1);
    }

    openSitesCounter++;
  }

  private int arrayPosition(int row, int col) {
    isBounds(row, col);
    return (col - 1) + ((row - 1) * gridSize) + 1;
  }

  public boolean isOpen(int row, int col) {
    int position = arrayPosition(row, col);
    return openSites[position];
  }

  public boolean isFull(int row, int col) {
    int position = arrayPosition(row, col);
    return isOpen(row, col) && quickUnionUF.find(position) == quickUnionUF.find(top);
  }

  private void isBounds(int row, int col) {
    if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
      throw new IllegalArgumentException();
    }
  }

  public int numberOfOpenSites() {
    return openSitesCounter;
  }

  public boolean percolates() {
    return isConnected(top, bottom);
  }

  private boolean isConnected(int a, int b) {
    return quickUnionUF.find(a) == quickUnionUF.find(b);
  }

  public static void main(String[] args) {
    In in = new In(args[0]); // input file
    int n = in.readInt(); // n-by-n percolation system

    // repeatedly read in sites to open and draw resulting system
    Percolation perc = new Percolation(n);
    while (!in.isEmpty()) {
      int i = in.readInt();
      int j = in.readInt();
      perc.open(i, j);
    }
  }
}
