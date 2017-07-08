package com.dev.bruno.learning.test.a3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DeliverySystemSolution {

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

	static int minimumNumberOfTrips(int tripMaxWeight, int[] packagesWeight) {
		packagesWeight = countingSort(packagesWeight);
	
		reverse(packagesWeight);
		
		// The maximum of bins is the length of items array
		int[] bins = new int[packagesWeight.length];
		int trips = 0;

		for (int weightIndex = 0; weightIndex < packagesWeight.length; weightIndex++) {
			int binCurrentIndex = 0;

			for (int binIndex = 0; binIndex <= binCurrentIndex; binIndex++) {
				int weight = bins[binIndex] + packagesWeight[weightIndex];

				if (weight <= tripMaxWeight) {
					bins[binIndex] = weight;

					break;
				} else {
					binCurrentIndex++;
				}
			}
		}

		for (int index = 0; index < bins.length; index++) {
			if (bins[index] > 0) {
				trips++;
			} else {
				break;
			}
		}

		return trips;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(Paths.get("inputs/input_delivery.txt"));

		int tripMaxWeight = in.nextInt();
		int numberOfPackages = in.nextInt();

		int[] packagesWeight = new int[numberOfPackages];

		for (int i = 0; i < numberOfPackages; i++) {
			packagesWeight[i] = in.nextInt();
		}

		int minimumNumberOfTrips = minimumNumberOfTrips(tripMaxWeight, packagesWeight);

		System.out.println(minimumNumberOfTrips);
		
		in.close();
	}
}
