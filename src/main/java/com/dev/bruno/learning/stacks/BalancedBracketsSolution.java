package com.dev.bruno.learning.stacks;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class BalancedBracketsSolution {

	public static boolean balancedBrackets(String sample) {
		Stack<Character> bracketsStack = new Stack<Character>();
		
		for(Character c : sample.toCharArray()) {
			if(c == '[' || c == '(' || c == '{') {
				bracketsStack.push(c);
			} else if(c == ']' && (bracketsStack.isEmpty() || bracketsStack.pop() != '[')) {
               	return false;
            } else if(c == ')' && (bracketsStack.isEmpty() || bracketsStack.pop() != '(')) {
            	return false;
            } else if(c == '}' && (bracketsStack.isEmpty() || bracketsStack.pop() != '{')) {
            	return false;
            }
		}

		return bracketsStack.isEmpty();
	}
	
	public static void main(String[] args) throws Exception {
		Files.lines(Paths.get("/home/casa/dev/testes_amazon/input09.txt")).forEach(line -> {
			boolean balanced = balancedBrackets(line);
			
			if(balanced) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		});
	}
}
