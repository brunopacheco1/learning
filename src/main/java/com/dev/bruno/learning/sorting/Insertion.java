package com.dev.bruno.learning.sorting;

public class Insertion implements Sort {

    @Override
    public void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(array[j], array[j - 1])) {
                    exchange(array, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
