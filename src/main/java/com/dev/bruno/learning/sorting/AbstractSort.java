package com.dev.bruno.learning.sorting;

import java.util.Comparator;

public abstract class AbstractSort {

    static <T> boolean less(final T a, final T b, final Comparator<T> comparator) {
        return comparator.compare(a, b) < 0;
    }

    static <T> void exchange(final T[] array, final int i, final int j) {
        final T swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

    static <T> void merge(final T[] array, final T[] aux, final int low, final int mid, final int high,
            final Comparator<T> comparator) {
        assert isSorted(array, low, mid, comparator);
        assert isSorted(array, mid + 1, high, comparator);

        for (int i = 0; i < array.length; i++) {
            aux[i] = array[i];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > high) {
                array[k] = aux[i++];
            } else if (less(aux[i], aux[j], comparator)) {
                array[k] = aux[i++];
            } else {
                array[k] = aux[j++];
            }
        }

        assert isSorted(array, low, high, comparator);
    }

    static <T> boolean isSorted(final T[] array, final int start, final int end, final Comparator<T> comparator) {
        for (int i = start; i < end; i++) {
            if (!less(array[i + 1], array[i], comparator)) {
                return false;
            }
        }
        return true;
    }
}
