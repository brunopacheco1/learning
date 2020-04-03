package com.dev.bruno.learning.connectivity;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final int gridSize;
  private final int top;
  private final int bottom;
  private final WeightedQuickUnionUF quickUnionUF;
  private final byte[] sites;
  private int openSitesCounter;

  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }
    gridSize = n;
    sites = new byte[n * n + 2];
    quickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    top = 0;
    bottom = n * n + 1;
  }

  public void open(int row, int col) {
    if (isOpen(row, col)) {
      return;
    }
    
    int position = arrayPosition(row, col);
    sites[position] = 1;
    if (row == 1) {
      quickUnionUF.union(position, top);
      quickUnionUF.union(position, position + gridSize);
      sites[position] = 2;
    } else if (row == gridSize) {
      quickUnionUF.union(position, bottom);
      quickUnionUF.union(position, position - gridSize);
    } else {
      quickUnionUF.union(position, position + gridSize);
      quickUnionUF.union(position, position - gridSize);
    }

    if (col == 1) {
      quickUnionUF.union(position, position + 1);
    } else if (col == gridSize) {
      quickUnionUF.union(position, position - 1);
    } else {
      quickUnionUF.union(position, position - 1);
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
    return sites[position] > 0;
  }

  public boolean isFull(int row, int col) {
    int position = arrayPosition(row, col);
    return sites[position] == 2;
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
}
