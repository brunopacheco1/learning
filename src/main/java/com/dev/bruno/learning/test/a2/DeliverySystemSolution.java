package com.dev.bruno.learning.test.a2;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DeliverySystemSolution {

	static int minimumNumberOfTrips(int tripMaxWeight, int[] packagesWeight) {
		int trips = 1;
		int n = packagesWeight.length;

		int[] remainingBins = new int[n];

		for (int i = 0; i < n; i++) {

			int min = tripMaxWeight + 1, bi = 0;

			for (int j = 0; j < trips; j++) {
				if (remainingBins[j] >= packagesWeight[i] && remainingBins[j] - packagesWeight[i] < min) {
					bi = j;
					min = remainingBins[j] - packagesWeight[i];
				}
			}

			if (min == tripMaxWeight + 1) {
				remainingBins[trips] = tripMaxWeight - packagesWeight[i];
				trips++;
			} else {
				remainingBins[bi] -= packagesWeight[i];
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
	}
}
