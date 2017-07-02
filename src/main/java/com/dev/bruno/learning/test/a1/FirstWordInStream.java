package com.dev.bruno.learning.test.a1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private Map<String, Long> words = new HashMap<>();
	private Map<Long, List<String>> repeating = new HashMap<>();

	public interface Stream {
		char next();

		boolean hasNext();
	}

	/*Author: Bruno Pacheco Lopes da Silva*/
	public String firstWord(final Stream input) {

		StringBuilder currentWord = new StringBuilder();
		while (input.hasNext()) {
			Character character = input.next();

			boolean stopWord = false;
			if (character.toString().matches("[a-z|A-Z]")) {
				currentWord.append(character);
			} else {
				stopWord = true;
			}

			if (stopWord || !input.hasNext()) {
				addWord(currentWord.toString());

				currentWord = new StringBuilder();
			}
		}

		String result = null;

		if (repeating.containsKey(1l)) {
			result = repeating.get(1l).get(0);
		}

		return result;
	}

	private void addWord(String word) {
		if(word == null || word.trim().length() == 0) {
			return;
		}
		
		word = word.trim().toLowerCase();
		
		Long count = 1l;

		if (words.containsKey(word)) {
			count += words.get(word);
		}

		words.put(word, count);

		List<String> repeatedWords = new ArrayList<>();
		repeatedWords.add(word);

		if (repeating.containsKey(count)) {
			repeatedWords = repeating.get(count);
			
			repeatedWords.add(word);
		}

		repeating.put(count, repeatedWords);
		
		for(long i = 1l; i < count; i++) {
			repeating.get(i).remove(word);
		}
	}
}