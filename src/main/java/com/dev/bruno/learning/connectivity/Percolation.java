package com.dev.bruno.learning.connectivity;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final int gridSize;
  private int top;
  private int bottom;
  private final WeightedQuickUnionUF quickUnionUF;
  private final byte[][] sites;
  private int openSitesCounter;

  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }
    gridSize = n;
    sites = new byte[n][n];
    quickUnionUF = new WeightedQuickUnionUF(n * n + 2);
    top = 0;
    bottom = n * n + 1;
  }

  public void open(int row, int col) {
    if (isOpen(row, col)) {
      return;
    }
    
    sites[row][col] = 1;
    int position = arrayPosition(row, col);
    
    if(row == 0) {
      quickUnionUF.union(position, top);
      quickUnionUF.union(position, position + gridSize);
      sites[row][col] = 2;
    } else if(row == gridSize - 1) {
      quickUnionUF.union(position, bottom);
      quickUnionUF.union(position, position - gridSize);
    } else {
      quickUnionUF.union(position, position + gridSize);
      quickUnionUF.union(position, position - gridSize);
    }

    if(col == 0) {
      quickUnionUF.union(position, position + 1);
    } else if(col == gridSize - 1) {
      quickUnionUF.union(position, position - 1);
    } else {
      quickUnionUF.union(position, position - 1);
      quickUnionUF.union(position, position + 1);
    }

    openSitesCounter++;
  }

  private int arrayPosition(int row, int col) {
    return col + (row * gridSize) + 1;
  }

  public boolean isOpen(int row, int col) {
    isBounds(row, col);
    return sites[row][col] > 0;
  }

  public boolean isFull(int row, int col) {
    isBounds(row, col);
    return sites[row][col] == 2;
  }

  private void isBounds(int row, int col) {
    if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
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
