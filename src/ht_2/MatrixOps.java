package ht_2;

public class MatrixOps {
	public static void fillRandomDouble(double a[][], int n){
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				a[i][j] = (Math.random() * n);
			}
		}
	}
	
	public static void deepSimpleCopy (double a[][], int apos, double b[][],
	int bpos) {
		for (int i = apos; i < a.length; i++) {
			b[i] = new double[a[i].length];
			for (int j = 0; j < a[i].length; j++) {
				System.arraycopy(a[i], 0, b[i], 0, a[i].length);
			}
		}
	}

	private static void printMatrix(double[][] b) {
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				System.out.printf("%10.2f", b[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	private static boolean validateMultMatrices(double[][] a, double[][] b){
		boolean ret = true;
		if (a.length>0 && b.length>0) {
//a is rectangular and the length of its rows is equal to b's number of rows
			for (int i = 0; i < a.length; i++) {
				if (a[i].length!=b.length) {
					ret = false;
					break;
				}
			}
			if (ret) {
//b is rectangular
				for (int i = 1; i < b.length; i++) {
					if (b[i].length!=b[0].length) {
						ret = false;
						break;
					}
				}
			}
		}
		else
			ret = false;
		return ret;
	}
	public static double[][] multMatrices(double[][] a, double[][] b) {
		if (!validateMultMatrices(a,b))
			return null;
		int bcols = b[0].length;
		double[][] ret = new double[a.length][];
		for (int i = 0; i < a.length; i++) {//for each row of a
			ret[i] = new double[bcols];
			for (int j = 0; j < bcols; j++) {//for each column of b
				for (int k = 0; k < b.length; k++) {
					ret[i][j] += a[i][k]*b[k][j];
				}
//				System.out.printf("%.1f\r", (float)(100*(i*bcols+j))/(a.length*bcols));
			}
			System.out.printf("%.1f\r", (100f*i)/a.length);
		}
		return ret;
	}
	public static void main(String[] args) {
		double a[][] = new double[1000][2000];
		double c[][] = new double[2000][500];
		double d[][] = new double[4][2];
		fillRandomDouble(a, 10);
		fillRandomDouble(c, 10);
		double[][] b = new double[a.length][];
		deepSimpleCopy(a, 0, b, 0);
/*		
		printMatrix(b);
		printMatrix(c);
*/
		
		double res[][] = multMatrices(a,c);
//		double res = multMatrices(a,d);
		if (res!=null)
			System.out.println("Multiplied successfully");
//			printMatrix(res);
		else System.out.println("Matrices can't be multiplied");
		
	}
}
