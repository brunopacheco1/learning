package com.dev.bruno.learning.test.e1;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TextUtils {

	public static boolean hasDuplicatedChar(String text) {
		boolean hasDuplicated = false;

		Map<Character, Integer> counters = getDuplications(text);

		for (Integer counter : counters.values()) {
			if (counter > 1) {
				hasDuplicated = true;
				break;
			}

		}

		return hasDuplicated;
	}

	private static int countMaxDuplicatedChar(String text, Map<Character, Integer> counters) {
		Integer maxCounter = 0;

		for (Integer counter : counters.values()) {
			if (counter > maxCounter) {
				maxCounter = counter;
			}
		}

		return maxCounter;
	}

	public static int countMaxDuplicatedChar(String text) {
		Map<Character, Integer> counters = getDuplications(text);

		return countMaxDuplicatedChar(text, counters);
	}

	public static Character maxDuplicatedChar(String text) {

		Map<Character, Integer> counters = getDuplications(text);

		Integer maxCounter = countMaxDuplicatedChar(text, counters);

		Character character = null;

		for (Entry<Character, Integer> keyValue : counters.entrySet()) {
			if (keyValue.getValue() == maxCounter) {
				character = keyValue.getKey();
				break;
			}
		}

		return character;
	}

	private static Map<Character, Integer> getDuplications(String text) {
		char[] chars = text.toCharArray();

		Map<Character, Integer> counters = new HashMap<>();

		for (Character character : chars) {
			Integer counter = 0;

			if (counters.containsKey(character)) {
				counter = counters.get(character);
			}

			counter++;

			counters.put(character, counter);
		}

		return counters;
	}

	public static void main(String[] args) {
		String text = "testes test";

		Integer counter = TextUtils.countMaxDuplicatedChar(text);

		if (counter == 4) {
			System.out.println("It works!");
		} else {
			System.out.println("It does not works!");
		}

		char maxCharacter = TextUtils.maxDuplicatedChar(text);

		if (maxCharacter == 't') {
			System.out.println("It works!");
		} else {
			System.out.println("It does not works!");
		}

		if (TextUtils.hasDuplicatedChar(text)) {
			System.out.println("It works!");
		} else {
			System.out.println("It does not works!");
		}
	}
}