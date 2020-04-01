package com.dev.bruno.learning.connectivity;

public class QuickFindUF {

  private int [] ids;

    public QuickFindUF(int n) {
    ids = new int[n];
    for(int i = 0; i < n; i++) {
      ids[i] = i;
    }
  }

  public void union(int a, int b) {
    int pointerA = ids[a];
    int pointerB = ids[b];
    for(int i = 0; i < ids.length; i++) {
      if(ids[i] == pointerB) {
        ids[i] = pointerA;
      }
    }
  }

  public boolean isConnected(int a, int b) {
    return ids[a] == ids[b];
  }

  public static void main(String [] args) {
  }
}
