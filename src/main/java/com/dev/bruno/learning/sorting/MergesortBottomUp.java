package com.dev.bruno.learning.sorting;

public class MergesortBottomUp implements Sort {

    @Override
    public void sort(Comparable[] array) {
        final int N = array.length;
        var aux = new Comparable[N];
        for (int size = 1; size < N; size += size) {
            for (int low = 0; low < N - size; low += size + size) {
                merge(array, aux, low, low + size - 1, Math.min(low + size + size - 1, N - 1));
            }
        }
    }
}
