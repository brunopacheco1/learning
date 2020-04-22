package com.dev.bruno.learning.sorting;

import java.util.Comparator;

public class Quicksort extends AbstractSort {

    public static <T> void sort(final T[] array, final Comparator<T> comparator) {
        Shuffle.sort(array);
        sort(array, 0, array.length - 1, comparator);
    }

    private static <T> void sort(final T[] array, final int low, final int high, final Comparator<T> comparator) {
        if (high <= low) {
            return;
        }

        final int j = partition(array, low, high, comparator);

        sort(array, low, j - 1, comparator);
        sort(array, j + 1, high, comparator);
    }
}