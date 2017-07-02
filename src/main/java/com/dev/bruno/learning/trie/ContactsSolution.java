package com.dev.bruno.learning.trie;

import java.nio.file.Paths;
import java.util.Scanner;

public class ContactsSolution {

	private static class TrieNode {
		TrieNode[] arr;
		private int counter = 1;

		public TrieNode() {
			this.arr = new TrieNode[26];
		}
	}

	public static TrieNode insert(TrieNode root, String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);
			int index = character - 'a';
			if (node.arr[index] == null) {
				TrieNode temp = new TrieNode();
				node.arr[index] = temp;
				node = temp;
			} else {
				node = node.arr[index];
				node.counter++;
			}
		}

		return root;
	}
	
	public static TrieNode searchNode(TrieNode root, String s){
        TrieNode p = root;
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            if(p.arr[index]!=null){
                p = p.arr[index];
            }else{
                return null;
            }
        }
 
        if(p==root)
            return null;
 
        return p;
    }

	public static void find(TrieNode root, String partial) {
		TrieNode node = searchNode(root, partial);
		Integer counter = node == null ? 0 : node.counter;
		System.out.println(counter);
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(Paths.get("inputs/input_contacts.txt"));
		
		int size = Integer.parseInt(in.nextLine());

		TrieNode root = new TrieNode();
		for (int op = 0; op < size; op++) {
			String line = in.nextLine();

			String[] actionValue = line.split("\\s");

			switch (actionValue[0]) {
			case "add":
				root = insert(root, actionValue[1]);
				break;
			case "find":
				find(root, actionValue[1]);
				break;
			}
		}

		in.close();
	}
}
