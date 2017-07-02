package com.dev.bruno.learning.test.a2;

import java.util.HashMap;
import java.util.Map;

public class PairedValuesSolution {

	public static int solution(int[] A) {
		Map<Integer, Integer> counters = new HashMap<>();

		int counter = 0;

		for (Integer i : A) {
			if (counters.containsKey(i)) {
				int localCounter = 1 + counters.get(i);

				counter += localCounter;

				counters.put(i, localCounter);
			} else {
				counters.put(i, 0);
			}
		}
		
		//I don't need to do the FOR again to sum the repetitions, I can do it at the first for.
//		int counter = 0;
//		for(Integer value : counters.values()) {
//			if(value % 2 == 0) {
//				counter += (value / 2) * (1 + value);
//			} else {
//				counter += (((value - 1) / 2) * (2 + value)) + 1;
//			}
//		}

		return counter;
	}

	public static void main(String[] args) {
		System.out.println(solution(new int[] { 3, 5, 6, 3, 3, 5, 3, 3, 3, 3 }));
	}
}
