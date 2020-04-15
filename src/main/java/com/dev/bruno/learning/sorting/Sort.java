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
}