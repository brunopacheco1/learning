package com.dev.bruno.learning.trees;

import java.util.Scanner;

public class HeightBinaryTreeSolution {

	public static Node insert(Node root, int data) {
        if(root == null){
            return new Node(data);
        }
        else {
            Node cur;
            if(data <= root.data){
                cur = insert(root.left, data);
                root.left = cur;
            }
            else{
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }
	
	public static int height(Node root) {
	    // Write your code here.
		if(root == null) {
			return -1;
		}
		
		int leftHeight = 1 + height(root.left);
		
		int rightHeight = 1 + height(root.right);
		
		return leftHeight > rightHeight ? leftHeight : rightHeight;
    }
	
	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0){
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }
}