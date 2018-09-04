package com.dev.bruno.learning.test.a1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/*
Amazon wants to implement a new backup system, in which files are stored into data tapes.
 
This new system must follow the following 2 rules:
	1. Never place more than two files on the same tape.
	2. Files cannot be split across multiple tapes.
	
It is guaranteed that all tapes have the same size and that they will always be able to store the largest file.
Every time this process is executed, we already know the size of each file, and the capacity of the tapes. 
Having that in mind, we want to design a system that is able to count how many tapes will be required to store the backup in the most efficient way.
The parameter of your function will be a structure that will contain the file sizes and the capacity of the tapes. 
You must return the minimum amount of tapes required to store the files.

You can write any methods or classes you would like. However, all new classes must be written in this 
file and you cannot change the BackupSystem class name, the Batch interface contract or the getMinimumTapeCount() method signature.

Example:
--------
	Input: Tape Size = 100; Files: 70, 10, 20
	Output: 2
	
*/
public class BackupSystem {

	private static class Batch {
	    int[] fileSizes;
	    int tapeSize;
	    
	    public Batch(int [] fileSizes, int tapeSize) {
	    	this.fileSizes = fileSizes;
	    	this.tapeSize = tapeSize;
	    }
	    
	    public int[] getFileSizes() {
			return fileSizes;
		}
	    
	    public int getTapeSize() {
			return tapeSize;
		}
	}
	
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

	public static int getMinimumTapeCount(final Batch batch) {
		int [] fileSizes = batch.getFileSizes();
		
		fileSizes = countingSort(fileSizes);
		
		reverse(fileSizes);
		
		// The maximum of bins is the length of items array
		int[] tapes = new int[fileSizes.length];
		int trips = 0;

		for (int weightIndex = 0; weightIndex < fileSizes.length; weightIndex++) {
			int sizeCurrentIndex = 0;

			for (int sizeIndex = 0; sizeIndex <= sizeCurrentIndex; sizeIndex++) {
				int weight = tapes[sizeIndex] + fileSizes[weightIndex];

				if (weight <= batch.getTapeSize()) {
					tapes[sizeIndex] = weight;

					break;
				} else {
					sizeCurrentIndex++;
				}
			}
		}

		for (int index = 0; index < tapes.length; index++) {
			if (tapes[index] > 0) {
				trips++;
			} else {
				break;
			}
		}

		return trips;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(Paths.get("./inputs/input_delivery.txt"));

		int tapeSize = in.nextInt();
		int numberOfPackages = in.nextInt();

		int[] fileSizes = new int[numberOfPackages];

		for (int i = 0; i < numberOfPackages; i++) {
			fileSizes[i] = in.nextInt();
		}
		
		Batch batch = new Batch(fileSizes, tapeSize);

		int minimumTapeCount = getMinimumTapeCount(batch);

		System.out.println(minimumTapeCount);
		
		in.close();
	}
}