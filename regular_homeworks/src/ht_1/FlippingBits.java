package ht_1;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FlippingBits {
	Scanner scanner = new Scanner(System.in);
	public long readLong(){
		long ret = -1;
		if (scanner.hasNextLine()) {
			try {
				ret = scanner.nextLong();
				if (ret<0)
					throw new InputMismatchException ("Unsigned only!");
			}
			catch (InputMismatchException e){
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
		return ret;
	}
	public long a[];
	public void readArray() {
		long sz = readLong(); //not quite correct. Long input results in error
		int i=0;
		if (sz>0) {
			a = new long[(int) sz];
			while ((i<sz) &&((a[i++]=readLong())!=-1));//fill array
		}
	}
	
/*after flip set the upper 32-bits (incl.sign!) to zero, because we have a
 32-bits integer by the conditions of the task */
	public long flipUInt(long n){		
		return (~n)&0x00000000FFFFFFFFL;		
	}
	
	public static void main(String[] args) {
		FlippingBits fb  = new FlippingBits();
		fb.readArray();
		for (long x : fb.a) {
			System.out.println(fb.flipUInt(x));
		}
	}
}