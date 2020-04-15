package com.dev.bruno.learning.sorting;

import edu.princeton.cs.algs4.StdRandom;

public class Shuffle implements Sort {

    @Override
    public void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int r = StdRandom.uniform(i + 1);
            exchange(array, i, r);
        }
    }
}
