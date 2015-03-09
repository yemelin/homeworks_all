package ht_1;

public class Format_IO {
	int n;
	public static int simpleFib(int n){
		if (n<=0)
			return 0;
		else if (n<3)
			return 1;
		else return (simpleFib(n-1)+simpleFib(n-2));
	}
	
	public static void main(String[] args) {
		
		for (int i=2; i<=20; i++)
			System.out.printf("%dth Fibonacci number is: %d%n", i, simpleFib(i));
	}
}