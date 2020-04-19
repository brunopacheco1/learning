package com.dev.bruno.learning.sorting;

import java.util.Comparator;

public class Selectionsort extends AbstractSort {

    public static <T> void sort(final T[] array, final Comparator<T> comparator) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(array[j], array[min], comparator)) {
                    min = j;
                }
            }
            exchange(array, i, min);
        }
    }
}
