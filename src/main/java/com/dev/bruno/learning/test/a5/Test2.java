package com.dev.bruno.learning.test.a5;

public class Test2 {

    public static int solution(int num, int[] arr) {

        int minValue = Integer.MAX_VALUE;
        for (int value : arr) if (value < minValue) minValue = value;

        while (minValue > 1) {
            boolean hasRemainder = false;

            for (int value : arr) hasRemainder |= value % minValue != 0;

            if (!hasRemainder) break;

            minValue--;
        }

        return minValue;
    }

    public static void main(String[] args) {
        System.out.println(
                solution(
                        5, new int[]{2, 4, 6, 8, 10}
                )
        );
    }
}
