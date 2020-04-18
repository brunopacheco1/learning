package com.dev.bruno.learning.sorting;

public class Mergesort implements Sort {

    @Override
    public void sort(Comparable[] array) {
        var aux = new Comparable[array.length];
        sort(array, aux, 0, array.length - 1);
    }

    private void sort(Comparable[] array, Comparable[] aux, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        sort(array, aux, low, mid);
        sort(array, aux, mid + 1, high);
        merge(array, aux, low, mid, high);
    }
}
