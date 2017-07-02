package com.dev.bruno.learning.test.a2;

public class MapColorsSolution {

	public static int solution(int[][] A) {
		int countries = 0;
		
		for(int n = 0; n < A.length; n++) {
			int [] row = A[n];
			
			for(int m = 0; m < row.length; m++) {
				int color = A[n][m];
				
				if(color != -9) {
					countries++;
				} else {
					continue;
				}
				
				A[n][m] = -9;
				
				if(n > 0 && n < A.length - 1 && m > 0 && m < row.length - 1) {
					int nextColor = A[n - 1][m];
					if(nextColor == color) {
						A[n - 1][m] = -9;
					}
					
					nextColor = A[n + 1][m];
					if(nextColor == color) {
						A[n + 1][m] = -9;
					}
					
					nextColor = A[n][m - 1];
					if(nextColor == color) {
						A[n][m - 1] = -9;
					}
					
					nextColor = A[n][m + 1];
					if(nextColor == color) {
						A[n][m + 1] = -9;
					}
				} else if((n == 0 || n == A.length - 1) && m > 0 && m < row.length - 1) {
					int nextColor = A[n][m - 1];
					if(nextColor == color) {
						A[n][m - 1] = -9;
					}
					
					nextColor = A[n][m + 1];
					if(nextColor == color) {
						A[n][m + 1] = -9;
					}
				} else if(n > 0 && n < A.length - 1 && (m == 0 || m == row.length - 1)) {
					int nextColor = A[n - 1][m];
					if(nextColor == color) {
						A[n - 1][m] = -9;
					}
					
					nextColor = A[n + 1][m];
					if(nextColor == color) {
						A[n + 1][m] = -9;
					}
				}
			}
		}
		
		return countries;
	}

	public static void main(String[] args) {
		System.out.println(solution(new int[][] { { 5, 4, 3, 2, 3, 1, 4 }, { 4, 3, 2, 2, 3, 4, 1 }, { 4, 4, 4, 2, 4, 4, 1 } }));
	}
}
