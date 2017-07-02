package com.dev.bruno.learning.trees;

public class LevelOrderTraversalBinaryTreeSolution {

	public static Node insert(Node root, int data) {
        if(root == null){
            return new Node(data);
        } else {
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
	
	public static void levelOrder(Node root) {
		// Write your code here.
		
		if(root == null) {
			return;
		}
		
		int height = height(root);
		
		for(int level = 0; level <= height; level++) {
			levelOrderByLevel(root, level, "");
		}
    }
	
	public static void levelOrderByLevel(Node root, int level, String separator) {
		if(root == null) {
			return;
		}
		
		if(level == 0) {
			System.out.print(separator);
			System.out.print(root.data);
		} else if (level > 0) {
			levelOrderByLevel(root.left, level - 1, " ");
			levelOrderByLevel(root.right, level - 1, " ");
		}
	}
	
	public static void main(String[] args) {
		int [] ints = {1,2,5,3,6,4};
        Node root = null;

        for(int data : ints){
            root = insert(root, data);
        }
        
        levelOrder(root);
    }
}