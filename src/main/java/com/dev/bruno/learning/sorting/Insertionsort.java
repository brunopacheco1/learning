package com.dev.bruno.learning.sorting;

import java.util.Comparator;

public class Insertionsort extends AbstractSort {

    public static <T> void sort(final T[] array, final Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(array[j], array[j - 1], comparator)) {
                    exchange(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
