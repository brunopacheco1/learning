package com.dev.bruno.learning.trees;

public class TopViewBinaryTreeSolution {

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
	
	public static void printRightTopView(Node root) {
		if(root == null) {
			return;
		}
		
		System.out.print(" ");
		System.out.print(root.data);
		
		if(root.right != null) {
			printRightTopView(root.right);
		}
    }
	
	public static void printLeftTopView(Node root) {
		if(root == null) {
			return;
		}
		
		if(root.left != null) {
			printLeftTopView(root.left);
		}
		
		System.out.print(root.data);
		System.out.print(" ");
    }
	
	public static void topView(Node root) {
		if(root == null) {
			return;
		}
		
	    // Write your code here.
		printLeftTopView(root.left);
		System.out.print(root.data);
		printRightTopView(root.right);
    }
	
	public static void main(String[] args) {
		int [] ints = {1,2,5,3,6,4};
        Node root = null;

        for(int data : ints){
            root = insert(root, data);
        }
        
        topView(root);
    }
}