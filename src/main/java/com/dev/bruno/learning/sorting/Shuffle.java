package com.dev.bruno.learning.sorting;

import edu.princeton.cs.algs4.StdRandom;

public class Shuffle extends AbstractSort {

    public static void sort(final Object[] array) {
        for (int i = 0; i < array.length; i++) {
            exchange(array, i, StdRandom.uniform(i + 1));
        }
    }
}
