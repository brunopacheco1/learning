package com.dev.bruno.learning.test.a1;

import java.util.*;

/*
Find the first word in a stream which is not repeated in the rest of the stream.
Please note that you are being provided a stream as a source for the characters.
The stream is guaranteed to eventually terminate (i.e. return false from a call to the hasNext() method), though it could be very long.
You will access this stream through the provided interface methods.
A call to hasNext() will return whether the stream contains any more characters to process.
A call to next() will return the next character to be processed in the stream.
It is not possible to restart the stream. 

You can write any methods or classes you would like. However, all new classes must be written in this 
file and you cannot change the FirstWordInStream class name, the Stream interface contract or the firstWord() method signature.

Example:
--------
	Input: "The angry dog was red. And the cat was also angry."
	Output: "dog"
In this example, the word "dog" is the first word in the stream which is not repeated in the rest of the stream. 

	
*/
public class FirstWordInStream {

	public static class Stream {
	    int index = 0;
	    String input = "The angry dog was red. And the cat was also angry.";

		char next() {
		    char c = input.charAt(index);
		    index++;
		    return c;
        }

		boolean hasNext() {
		    return input.length() != index;
        }
	}

	public static void main(String [] args) {
	    System.out.println(firstWord(new Stream()));
    }

    private static void checkIfItIsARepeatedWord(StringBuilder wordBuilder, Set<String> words) {
        if(wordBuilder.length() > 0) {
            String word = wordBuilder.toString();

            if(!words.remove(word)) {
                words.add(word);
            }
        }
    }

	/*Author: Bruno Pacheco Lopes da Silva*/
	private static String firstWord(final Stream input) {
	    StringBuilder wordBuilder = new StringBuilder();
	    Set<String> words = new LinkedHashSet<>();

	    while(input.hasNext()) {
	        String c = String.valueOf(input.next()).toLowerCase();
	        if(c.matches("[a-zA-Z]")){
	            wordBuilder.append(c);
	            continue;
            }

            checkIfItIsARepeatedWord(wordBuilder, words);
            wordBuilder = new StringBuilder();
        }

        checkIfItIsARepeatedWord(wordBuilder, words);

        return words.stream().findFirst().orElse(null);
	}
}