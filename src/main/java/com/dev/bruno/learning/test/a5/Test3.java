package com.dev.bruno.learning.test.a5;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Test3 {

    public static List<List<Integer>> solution(int totalHouses, int [][] allHouses, int numHouses) {
        var housesByDistance = new TreeMap<Integer, List<List<Integer>>>();

        for(int[] house : allHouses) {
            var distance = (house[0] ^ 2) + (house[1] ^ 2);

            var pair = new ArrayList<Integer>();
            pair.add(house[0]);
            pair.add(house[1]);

            var list = housesByDistance.getOrDefault(distance, new ArrayList<>());

            list.add(pair);

            housesByDistance.put(distance, list);
        }

        var result = new ArrayList<List<Integer>>();

        first : for(var houses : housesByDistance.values()) {
            for (var house : houses) {
                result.add(house);
                if (result.size() == numHouses) break first;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(
                solution(
                        3,
                        new int[][] { { 1,2 }, { 3,4 }, { 1,-1 } },
                        2
                )
        );
    }
}
