package com.dev.bruno.learning.sorting;

public class SortTest {

    public static void main(String[] args) {
        var notSorted = new Character[] { 'd', 'g', 'a', 'c', 'd', 'b', 'f', 'e' };

        new Selection().sort(notSorted);

        for (Character character : notSorted) {
            System.out.println(character);
        }
    }
}
