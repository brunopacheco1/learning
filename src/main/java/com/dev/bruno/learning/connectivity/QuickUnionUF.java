package com.dev.bruno.learning.connectivity;

public class QuickUnionUF {

  private int [] ids;

  public QuickUnionUF(int n) {
    ids = new int[n];
    for(int i = 0; i < n; i++) {
      ids[i] = i;
    }
  }

  private int root(int i) {
    int pointer = ids[i];
    if(pointer == i) return pointer;
    return root(pointer);
  }

  public void union(int a, int b) {
    int rootA = root(a);
    int rootB = root(b);
    ids[rootA] = rootB;
  }

  public boolean isConnected(int a, int b) {
    return root(a) == root(b);
  }
}

