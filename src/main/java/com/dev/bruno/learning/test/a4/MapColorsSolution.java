package com.dev.bruno.learning.test.a4;

public class MapColorsSolution {

	private static void clearNext(int [][] A, int color, int n, int m) {
        boolean doesntRespectHeight = n < 0 || n >= A.length;
        boolean doesntRespectWidth = m < 0 || m >= A[0].length;
        boolean differentColor = A[n][m] == -9 || A[n][m] != color;

		if(doesntRespectHeight || doesntRespectWidth || differentColor) {
			return;
		}
		
		A[n][m] = -9;
		
		clearNext(A, color, n, m - 1);
		clearNext(A, color, n, m + 1);
		clearNext(A, color, n - 1, m);
		clearNext(A, color, n + 1, m);
	}

	public static int solution(int[][] A) {
		int countries = 0;
		
		for(int n = 0; n < A.length; n++) {
			for(int m = 0; m < A[0].length; m++) {
				int color = A[n][m];
				
				if(color != -9) {
					countries++;
					clearNext(A, color, n, m);
				}
			}
		}
		
		return countries;
	}

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {
			{ 5, 4, 3, 2, 3, 1, 4 },
			{ 4, 3, 2, 2, 3, 4, 1 },
			{ 4, 4, 4, 2, 4, 4, 1 }
		}));
	}
}