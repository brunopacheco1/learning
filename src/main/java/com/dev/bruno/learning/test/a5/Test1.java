package com.dev.bruno.learning.test.a5;

import java.util.Arrays;

public class Test1 {

    public static int[] solution(int days, int [] states) {
        for(int day = 0; day < days; day++) {
            var changeStates = new boolean[states.length];

            for(int cell = 0; cell < states.length; cell++) {
                int left = 0, right = 0;

                if(cell != 0) left = states[cell - 1];
                if(cell != states.length - 1) right = states[cell + 1];
                if(left == right) changeStates[cell] = true;
            }

            for(int cell = 0; cell < states.length; cell++)
                if(changeStates[cell]) states[cell] = 0; else states[cell] = 1;
        }

        return states;
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(solution(
                        2, new int[]{0, 0, 0, 0, 1, 1, 0, 1, 1}
                ))
        );
    }
}
