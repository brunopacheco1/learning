package com.dev.bruno.learning.sorting;

public class SortTest {

    public static void main(String[] args) {
        var notSorted = new Character[] { 'd', 'g', 'a', 'c', 'd', 'b', 'f', 'e' };

        BottomUpMergesort.sort(notSorted, (a, b) -> a.compareTo(b));

        for (Character character : notSorted) {
            System.out.println(character);
        }
    }
}
