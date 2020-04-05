package com.dev.bruno.learning.test.a2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class BalancedBracketsSolution {

	private final Stack<Character> bracketsStack = new Stack<>();

	public boolean balancedBrackets(String sample) {
		for (char c : sample.toCharArray()) {
			switch (c) {
				case '[':
				case '(':
				case '{':
					bracketsStack.push(c);
					continue;
				case ']':
					if (isNotBalanced('['))
						return false;
				case ')':
					if (isNotBalanced('('))
						return false;
				case '}':
					if (isNotBalanced('{'))
						return false;
			}
		}

		return bracketsStack.isEmpty();
	}

	private boolean isNotBalanced(char open) {
		return bracketsStack.isEmpty() || bracketsStack.pop() != open;
	}

	public static void main(String[] args) throws Exception {
		BalancedBracketsSolution balancedBrackets = new BalancedBracketsSolution();

		Files.lines(Paths.get("inputs/input09.txt")).forEach(line -> {
			if (balancedBrackets.balancedBrackets(line)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		});
	}
}
