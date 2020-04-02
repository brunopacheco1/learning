package com.dev.bruno.learning.connectivity;

public class Percolation {

  private static final int VIRTUAL_SITES = 2;
  private final int gridSize;
  private final int[] sites;
  private final int[] sizes;
  private final boolean[] openSites;
  private int openSitesCounter;

  public Percolation(int n) {
    if (n < 1)
      throw new IllegalArgumentException();
    gridSize = n;
    int arraySize = (n * n) + VIRTUAL_SITES;
    sites = new int[arraySize];
    sizes = new int[arraySize];
    openSites = new boolean[arraySize];
    for (int i = 0; i < arraySize; i++)
      sizes[i] = 1;
    for (int i = gridSize + 1; i < arraySize - gridSize - 1; i++)
      sites[i] = i;
    for (int i = arraySize - gridSize - 1; i < arraySize; i++)
      sites[i] = arraySize - 1;
  }

  public void open(int row, int col) {
    int position = arrayPosition(row, col);
    if (!openSites[position]) {
      union(position, position - gridSize);
      union(position, position - 1);
      union(position, position + 1);
      union(position, position + gridSize);
      openSites[position] = true;
      openSitesCounter++;
    }
  }

  private void union(int a, int b) {
    boolean outOfBounds = b < 1 || b > sites.length - 2;
    boolean aIsInADiferentRow = (a % gridSize == 1 && b % gridSize == 0);
    boolean bIsInADiferentRow = (b % gridSize == 1 && a % gridSize == 0);
    if (outOfBounds || aIsInADiferentRow || bIsInADiferentRow || !openSites[b]) {
      return;
    }
    int rootA = root(a);
    int rootB = root(b);
    if (rootA == rootB)
      return;
    if (sizes[rootA] < sizes[rootB]) {
      sites[rootA] = rootB;
      sizes[rootA] += sizes[rootB];
    } else {
      sites[rootB] = rootA;
      sizes[rootB] += sizes[rootA];
    }
  }

  public boolean isOpen(int row, int col) {
    int position = arrayPosition(row, col);
    return openSites[position] == 1;
  }

  public boolean isFull(int row, int col) {
    int position = arrayPosition(row, col);
    return isConnected(0, position);
  }

  private int arrayPosition(int row, int col) {
    int position = col + (row * gridSize) + 1;
    if (position < 1 || position > sites.length - 2) {
      throw new IllegalArgumentException();
    }
    return position;
  }

  public int numberOfOpenSites() {
    return openSitesCounter;
  }

  public boolean percolates() {
    return isConnected(0, sites.length - 1);
  }

  private boolean isConnected(int a, int b) {
    return root(a) == root(b);
  }

  private int root(int i) {
    if (sites[i] == i)
      return i;
    sites[i] = sites[sites[i]];
    return root(sites[i]);
  }
}
