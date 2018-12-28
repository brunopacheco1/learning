package com.dev.bruno.learning.test.a5;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test1 {

    public static List<Integer> solution(int days, int[] states) {
        for (int day = 0; day < days; day++) {
            int[] newStates = new int[states.length];

            for (int neighbor = 0; neighbor < states.length; neighbor++) {
                int leftNeighbor = 0;
                if (neighbor > 0) {
                    leftNeighbor = states[neighbor - 1];
                }

                int rightNeighbor = 0;
                if (neighbor < states.length - 1) {
                    rightNeighbor = states[neighbor + 1];
                }

                if (leftNeighbor != rightNeighbor) {
                    newStates[neighbor] = 1;
                }
            }

            states = newStates;
        }

        return IntStream.of(states).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(solution(
                2, new int[]{0, 0, 0, 0, 1, 1, 0, 1, 1})
        );
    }
}