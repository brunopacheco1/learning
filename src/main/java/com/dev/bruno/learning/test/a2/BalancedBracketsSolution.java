package com.dev.bruno.learning.test.a2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class BalancedBracketsSolution {

	private static boolean balancedBrackets(String sample) {
		Stack<Character> bracketsStack = new Stack<>();
		
		for(Character c : sample.toCharArray()) {
			switch (c) {
				case '[':
				case '(':
				case '{': bracketsStack.push(c); break;
				case ']': if(bracketsStack.isEmpty() || bracketsStack.pop() != '[') return false;
				case ')': if(bracketsStack.isEmpty() || bracketsStack.pop() != '(') return false;
				case '}': if(bracketsStack.isEmpty() || bracketsStack.pop() != '{') return false;
			}
		}

		return bracketsStack.isEmpty();
	}
	
	public static void main(String[] args) throws Exception {
		Files.lines(Paths.get("./inputs/input09.txt")).forEach(line -> {
			boolean balanced = balancedBrackets(line);
			
			if(balanced) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		});
	}
}
