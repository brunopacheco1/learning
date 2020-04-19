package com.dev.bruno.learning.sorting;

import java.util.Comparator;

public class Mergesort extends AbstractSort {

    @SuppressWarnings("unchecked")
    public static <T> void sort(final T[] array, final Comparator<T> comparator) {
        final T[] aux = (T[]) new Object[array.length];
        sort(array, aux, 0, array.length - 1, comparator);
    }

    private static <T> void sort(final T[] array, final T[] aux, final int low, final int high,
            final Comparator<T> comparator) {
        if (low >= high) {
            return;
        }
        final int mid = low + (high - low) / 2;
        sort(array, aux, low, mid, comparator);
        sort(array, aux, mid + 1, high, comparator);
        merge(array, aux, low, mid, high, comparator);
    }
}
