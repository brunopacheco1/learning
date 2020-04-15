package com.dev.bruno.learning.sorting;

public class SortTest {

    public static void main(String[] args) {
        var notSorted = new Character[] { 'd', 'g', 'a', 'c', 'd', 'b', 'f', 'e' };

        new Shellsort().sort(notSorted);

        for (Character character : notSorted) {
            System.out.println(character);
        }

        new Shuffle().sort(notSorted);

        for (Character character : notSorted) {
            System.out.println(character);
        }
    }
}
