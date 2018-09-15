package com.dev.bruno.learning.test.a1;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	
	public static int getMinimumTapeCount(final Batch batch) {
        int [] sorted = Arrays.stream(batch.getFileSizes()).sorted().toArray();
        int [] tapes = new int[sorted.length];
        int [] tapeFileCounter = new int[sorted.length];

        for(int i = sorted.length - 1; i >= 0; i--) {
            for(int tapeIndex = 0; tapeIndex < tapes.length; tapeIndex++) {
                int sum = tapes[tapeIndex] + sorted[i];

                if(sum <= batch.getTapeSize() && tapeFileCounter[tapeIndex] < 2) {
                    tapes[tapeIndex] = sum;
                    tapeFileCounter[tapeIndex] += 1;
                    break;
                }
            }
        }

        return IntStream.of(tapes).filter(tape -> tape > 0).toArray().length;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(Paths.get("inputs/input_delivery.txt"));

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