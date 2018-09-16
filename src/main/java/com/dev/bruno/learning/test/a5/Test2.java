package com.dev.bruno.learning.test.a5;

public class Test2 {

    public static int solution(int num, int [] arr) {

        int gcd = 1;

        doesntHaveRemainder : while(true) {
            int tempGCD = gcd + 1;
            for(int value : arr) if(value % tempGCD != 0) break doesntHaveRemainder;
            gcd = tempGCD;
        }

        return gcd;
    }

    public static void main(String[] args) {
        System.out.println(
                solution(
                        5, new int[] {2, 4, 6, 8, 10}
                )
        );
    }
}
