package com.dev.bruno.learning.arrays;

import java.math.BigInteger;
import java.util.Arrays;

public class IncrementNumber {
    /**
     * Let's assume that we need to operate on very big numbers and to represent
     * those numbers we use integers tables. Each table element can take value
     * in range 0-9. Sample representation of number in the notation is:
     *
     *                 12345 <===> [1, 2, 3, 4, 5]
     *
     * Method which need to be implemented must do an increment (add 1) to given
     * as an input attribute number, and return result
     *
     *
     * @param number
     *         number representation (sescription above), that need to be incremented.
     *
     *
     * @return integer array that contains incremened number.
     *
     */
    public int[] increment1(int[] number) {
        int index = number.length - 1;

        while (index > 0 && number[index] == 9) index--;

        number[index]++;

        if(number[0] > 9) {
            number = new int [number.length + 1];
            number[0] = 1;
            return number;
        }

        while (index < number.length - 1) {
            number[++index] = 0;
        }

        return number;
    }

    public int[] increment2(int[] number) {
        int index = number.length - 1;

        while (index > 0 && number[index] == 9) {
            index--;
        }

        if (index == 0 && number[index] == 9) {
            int[] newArray = new int[number.length + 1];
            System.arraycopy(number, 0, newArray, 1, number.length);
            number = newArray;
        }

        number[index]++;

        while (index < number.length - 1) {
            number[++index] = 0;
        }

        return number;
    }

    //-----------------------------------------------------------------
    // TEST methods, that checks if the result of function is correct
    //-----------------------------------------------------------------

    public static void main(String[] args) {
        int steps = 100000;
        BigInteger totalTime = BigInteger.ZERO;
        for(int step = 0; step < steps; step++) {
            IncrementNumber solutionVerifier = new IncrementNumber();
            int[] number = {8,9,9,9,9};
            int i = 0;

            long startTime = System.nanoTime();

            while (i < 10000) {
                number = solutionVerifier.increment1(number);
                i++;
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            totalTime = totalTime.add(BigInteger.valueOf(duration));
            //printArray(number);
            System.out.println("Step[" + step + "] - " + duration + "ns");
        }

        System.out.println("Average Time - " + (totalTime.divide(BigInteger.valueOf(steps))) + "ns");
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}