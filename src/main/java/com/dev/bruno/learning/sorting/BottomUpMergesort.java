package com.dev.bruno.learning.sorting;

import java.util.Comparator;

public class BottomUpMergesort extends AbstractSort {

    @SuppressWarnings("unchecked")
    public static <T> void sort(final T[] array, Comparator<T> comparator) {
        final int N = array.length;
        final T[] aux = (T[]) new Object[array.length];
        for (int size = 1; size < N; size += size) {
            for (int low = 0; low < N - size; low += size + size) {
                merge(array, aux, low, low + size - 1, Math.min(low + size + size - 1, N - 1), comparator);
            }
        }
    }
}
