package com.dev.bruno.learning.heap;

import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class FindRunningMedianSolution {

	private static class Heap {
		private Queue<Integer> low = new PriorityQueue<>(Comparator.reverseOrder());

		private Queue<Integer> high = new PriorityQueue<>();

		public void add(int number) {
			Queue<Integer> target = low.size() <= high.size() ? low : high;
			target.add(number);
			balance();
		}

		private void balance() {
			while (!low.isEmpty() && !high.isEmpty() && low.peek() > high.peek()) {
				Integer lowHead = low.poll();
				Integer highHead = high.poll();
				low.add(highHead);
				high.add(lowHead);
			}
		}

		public double median() {
			if (low.isEmpty() && high.isEmpty()) {
				throw new IllegalStateException("Heap is empty");
			} else {
				return low.size() == high.size() ? (low.peek() + high.peek()) / 2.0 : low.peek();
			}
		}
	}

	private static Heap heap = new Heap();

	public static void findMedian(Integer integer) {
		heap.add(integer);

		System.out.printf(Locale.ENGLISH, "%.1f\n", heap.median());
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(Paths.get("input/input_median.txt"));

		int size = in.nextInt();

		for (int op = 0; op < size; op++) {
			int integer = in.nextInt();

			findMedian(integer);
		}

		in.close();
	}
}
