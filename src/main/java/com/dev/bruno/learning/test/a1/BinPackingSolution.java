package com.dev.bruno.learning.test.a1;

public class BinPackingSolution {

	public static void reverse(int[] data) {
		for (int left = 0, right = data.length - 1; left < right; left++, right--) {
			int temp = data[left];
			data[left] = data[right];
			data[right] = temp;
		}
	}

	public static int[] countingSort(int[] numbers) {
		int max = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > max)
				max = numbers[i];
		}

		int[] sortedNumbers = new int[max + 1];

		for (int i = 0; i < numbers.length; i++) {
			sortedNumbers[numbers[i]]++;
		}

		int insertPosition = 0;

		for (int i = 0; i <= max; i++) {
			for (int j = 0; j < sortedNumbers[i]; j++) {
				numbers[insertPosition] = i;
				insertPosition++;
			}
		}
		
		return numbers;
	}

	public static int minimumBins(int binCapacity, int[] weights, boolean sorted, boolean reversed) {
		
		if(sorted) {
			weights = countingSort(weights);
		}
		
		if(reversed) {
			reverse(weights);
		}
		
		// The maximum of bins is the length of items array
		int[] bins = new int[weights.length];
		int result = 0;

		for (int weightIndex = 0; weightIndex < weights.length; weightIndex++) {
			int binCurrentIndex = 0;

			for (int binIndex = 0; binIndex <= binCurrentIndex; binIndex++) {
				int weight = bins[binIndex] + weights[weightIndex];

				if (weight <= binCapacity) {
					bins[binIndex] = weight;

					break;
				} else {
					binCurrentIndex++;
				}
			}
		}

		for (int index = 0; index < bins.length; index++) {
			if (bins[index] > 0) {
				result++;
			} else {
				break;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		int binCapacity = 10;

		//First fit
		System.out.println("Sorted: false - Reverse: false - Result: " + minimumBins(binCapacity, new int[] { 4, 8, 1, 4, 2, 1 }, false, false));

		//First fit
		System.out.println("Sorted: false - Reverse: false - Result: " + minimumBins(binCapacity, new int[] { 9, 8, 2, 2, 5, 4 }, false, false));

		//First fit
		System.out.println("Sorted: false - Reverse: false - Result: " + minimumBins(binCapacity, new int[] { 2, 5, 4, 7, 1, 3, 8 }, false, false));
		
		//First fit increasing
		System.out.println("Sorted: true - Reverse: false - Result: " + minimumBins(binCapacity, new int[] { 2, 5, 4, 7, 1, 3, 8 }, true, false));
		
		//First fit decreasing
		System.out.println("Sorted: true - Reverse: true - Result: " + minimumBins(binCapacity, new int[] { 2, 5, 4, 7, 1, 3, 8 }, true, true));
	}
}
