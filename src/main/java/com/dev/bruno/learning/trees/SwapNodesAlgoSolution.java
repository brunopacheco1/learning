package com.dev.bruno.learning.trees;

import java.util.Scanner;

public class SwapNodesAlgoSolution {

	public static final int ROOT_NODE = 1;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int N = in.nextInt();
		int[][] tree = new int[N + ROOT_NODE][2];

		for (int i = ROOT_NODE; i < N + ROOT_NODE; i++) {
			for (int j = 0; j < 2; j++) {
				tree[i][j] = in.nextInt();
			}
		}

		int numDepths = in.nextInt();
		for (int i = 0; i < numDepths; i++) {
			swap(tree, ROOT_NODE, in.nextInt(), 1);
			System.out.println();
		}
		
		in.close();
	}

	public static void swap(int[][] tree, int node, int targetDepth, int depth) {
		if (node == -1)
			return;

		if (depth % targetDepth == 0) {
			int temp = tree[node][0];
			tree[node][0] = tree[node][1];
			tree[node][1] = temp;
		}
		swap(tree, tree[node][0], targetDepth, depth + 1);
		System.out.print(Integer.toString(node) + " ");
		swap(tree, tree[node][1], targetDepth, depth + 1);
	}
}