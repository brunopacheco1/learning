package com.dev.bruno.learning.test.a5;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Test4 {

    public static List<List<Integer>> solution(int maxTravelDistance, int [][] forwardList, int [][] returnList) {
        var pairsByDistance = new TreeMap<Integer, List<List<Integer>>>();

        for(int[] forward : forwardList) {
            for(int[] _return : returnList) {
                var pair = new ArrayList<Integer>();
                pair.add(forward[0]);
                pair.add(_return[0]);

                var travelDistance = -(forward[1] + _return[1]);

                var list = pairsByDistance.getOrDefault(travelDistance, new ArrayList<>());

                list.add(pair);

                pairsByDistance.put(travelDistance, list);
            }
        }

        for(int distance : pairsByDistance.keySet())
            if(Math.abs(distance) <= maxTravelDistance) return pairsByDistance.get(distance);

        return new ArrayList<>();
    }

    public static void main(String[] args) {
        System.out.println(
                solution(
                        10000,
                        new int[][] { { 1, 3000 }, { 2, 5000 }, { 3, 7000 }, { 4, 10000 } },
                        new int[][] { { 1, 2000 }, { 2, 3000 }, { 3, 4000 }, { 4, 5000 } }
                )
        );
    }
}
