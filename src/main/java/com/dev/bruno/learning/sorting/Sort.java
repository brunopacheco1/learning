package com.dev.bruno.learning.sorting;

public interface Sort {

    void sort(Comparable[] array);

    default boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    default void exchange(Comparable[] array, int i, int j) {
        Comparable swap = array[i];
        array[i] = array[j];
        array[j] = swap;
    }

    default void merge(Comparable[] array, Comparable[] aux, int low, int mid, int high) {
        assert isSorted(array, low, mid);
        assert isSorted(array, mid + 1, high);

        for (int i = 0; i < array.length; i++) {
            aux[i] = array[i];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > high) {
                array[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                array[k] = aux[i++];
            } else {
                array[k] = aux[j++];
            }
        }

        assert isSorted(array, low, high);
    }

    default boolean isSorted(Comparable[] array, int start, int end) {
        for (int i = start; i < end; i++) {
            if (!less(array[i + 1], array[i])) {
                return false;
            }
        }
        return true;
    }
}