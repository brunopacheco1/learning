package com.dev.bruno.learning.sorting;

import java.util.Comparator;

public class ThreeWayQuicksort extends AbstractSort {

    public static <T> void sort(final T[] array, final Comparator<T> comparator) {
        Shuffle.sort(array);
        sort(array, 0, array.length - 1, comparator);
    }

    private static <T> void sort(final T[] array, final int low, final int high, final Comparator<T> comparator) {
        if (high <= low) {
            return;
        }

        int lower = low;
        int higher = high;

        T value = array[low];

        int i = low;
        while(i <= higher) {
            int cmp = comparator.compare(array[i], value);
            if(cmp < 0) {
                exchange(array, lower++, i++);
            } else if(cmp > 0) {
                exchange(array, i, higher--);
            } else {
                i++;
            }
        }

        sort(array, low, lower - 1, comparator);
        sort(array, higher + 1, high, comparator);
    }
}