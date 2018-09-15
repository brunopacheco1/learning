package com.dev.bruno.learning.test.a3;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class DeliverySystemSolution {

	static int minimumNumberOfTrips(int tripMaxWeight, int[] packagesWeight) {
		int [] sorted = Arrays.stream(packagesWeight).sorted().toArray();
		int [] trips = new int[sorted.length];
		int [] tripsPackagesCounter = new int[sorted.length];

		for(int i = sorted.length - 1; i >= 0; i--) {
			for(int tripIndex = 0; tripIndex < trips.length; tripIndex++) {
				int sum = trips[tripIndex] + sorted[i];

				if(sum <= tripMaxWeight && tripsPackagesCounter[tripIndex] < 2) {
					trips[tripIndex] = sum;
					tripsPackagesCounter[tripIndex] += 1;
					break;
				}
			}
		}

		return IntStream.of(trips).filter(trip -> trip > 0).toArray().length;
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
