package test;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MergeSort {
	public static void main(String[] args) {
		int[] A, B;
		double inicio1, fim1, tempo1;
		double inicio2, fim2, tempo2;
		
		System.out.printf("%20s%30s%30s\n","n", "tempo1", "tempo2");
		System.out.println("--------------------------------------------------------------------------------------------");
		for (int n = 1; n <= 50; n++) {
			A = criaVetorAleatorio(n);
			B = A.clone();
			inicio1 = System.currentTimeMillis();
			mergeSort1(A, 0, n-1);
			fim1 = System.currentTimeMillis();
			tempo1 = fim1 - inicio1;
			inicio2 = System.currentTimeMillis();
			mergeSort2(B, 0, n-1);
			fim2 = System.currentTimeMillis();
			tempo2 = fim2 - inicio2;
			System.out.printf("%20d%30.0f%30.0f\n", n, tempo1, tempo2);
			if (n == 50) {
				System.out.println("-----------------------------------------------------------------------------");
				System.out.println("ordenação:");
				imprimeVetor(B);
			}
		}
	}
	
	static int[] criaVetorAleatorio (int n) {
		Random randomGenerator = new Random();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = randomGenerator.nextInt(100*n);
		}
		return A;
	}
	
	static void imprimeVetor (int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.println(A[i]);
		}
	}

	static void mergeSort1(int[] A, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort1(A, p, q);
			mergeSort1(A, q + 1, r);
			merge1(A, p, q, r);
		}
	}

	static void merge1(int[] A, int p, int q, int r) {
		int i, j;
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (i = 0; i < n1; i++) {
			L[i] = A[p + i];
		}
		for (j = 0; j < n2; j++) {
			R[j] = A[q + j + 1];
		}
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;
		i = 0;
		j = 0;
		for (int k = p; k <= r; k++) {
			try {
				TimeUnit.MILLISECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (L[i] <= R[j]) {
				A[k] = L[i++];
			} else {
				A[k] = R[j++];
			}
		}
	}

	static void mergeSort2(int[] A, int p, int r) {
		if (p < r) {  // n = 10  r = 9 p = 0
			int q1 = (2*p + r) / 3; //0 +9 /3 = 3
			int q2 = (p + 2*r) / 3; // 0 + 18/3 = 6

			mergeSort2(A, p, q1);
			mergeSort2(A, q1 + 1,q2);
			mergeSort2(A, q2 + 1, r);
			merge2(A, p, q1,q2, r);
		}
	}

	static void merge2(int[] A, int p, int q1, int q2, int r) {
		int i, j, z;    //p = 0 q1 = 3 q2 = 6 r = 9
		int n1 = q1 - p + 1; // 3-0 +1 = 4
		int n2 = q2 - q1; //3
		int n3 = r - q2; // 9-6 = 3
		int[] L = new int[n1 + 1];
		int[] M = new int[n2 + 1];
		int[] R = new int[n3 + 1];
		
		for (i = 0; i < n1; i++) {// 0 - 3
			L[i] = A[p + i]; 
		}
		for (j = 0; j < n2; j++) { // 4 - 6
			M[j] = A[q1 + j + 1]; 
		}
		
		for (z = 0; z < n3; z++) {  // 7 - 9
			R[z] = A[q2 + z + 1];
		}
		
		L[n1] = Integer.MAX_VALUE; // ponto de parar INFINITO
		M[n2] = Integer.MAX_VALUE;
		R[n3] = Integer.MAX_VALUE;
		i = 0;
		j = 0;
		z = 0;
		for (int k = p; k <= r; k++) { // k = 0 r = 9
			try {
				TimeUnit.MILLISECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if ((L[i] <= M[j]) && (L[i] <= R[z])) { 
				A[k] = L[i++]; 
			} else if((M[j] <= L[i]) && (M[j] <= R[z])){
				A[k] = M[j++];
			} else {
				A[k] = R[z++];
			}
		}
	}
// 9 8 7 6 | 5 4 3 | 2 1 0
}
