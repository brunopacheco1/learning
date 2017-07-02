package com.dev.bruno.learning.test.a2;

public class MapColorsSolution {
	
	//Try to find out a better solution
	private static void clearNext(int [][] A, int currentColor, int currentN, int currentM) {
		int matrixN = A.length;
		int matrixM = A[0].length;
		
		if(currentN < 0 || currentN >= matrixN || currentM < 0 || currentM >= matrixM) {
			return;
		}
		
		int color = A[currentN][currentM];
		
		if(color == -9 || color != currentColor) {
			return;
		}
		
		A[currentN][currentM] = -9;
		
		clearNext(A, currentColor, currentN, currentM - 1);
		clearNext(A, currentColor, currentN, currentM + 1);
		clearNext(A, currentColor, currentN - 1, currentM);
		clearNext(A, currentColor, currentN + 1, currentM);
	}

	public static int solution(int[][] A) {
		int countries = 0;
		
		for(int n = 0; n < A.length; n++) {
			int [] row = A[n];
			
			for(int m = 0; m < row.length; m++) {
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
		System.out.println(solution(new int[][] { { 5, 4, 3, 2, 3, 1, 4 }, { 4, 3, 2, 2, 3, 4, 1 }, { 4, 4, 4, 2, 4, 4, 1 } }));
	}
}
