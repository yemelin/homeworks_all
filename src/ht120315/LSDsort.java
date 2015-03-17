package ht120315;

import utils.ArrayInt;
import utils.Testable;

public class LSDsort {
	public static void test(Testable t) {		
		System.out.println(t.getInfo());
		long start = System.currentTimeMillis();
		t.routine();
		long stop = System.currentTimeMillis();
//		System.out.println("Sorted OK:"+ArrayInt.isSortedUp(datacopy));
		System.out.println("Elapsed = " + (stop - start)+"ms");	
	}
	
	public static void main(String[] args) {
//		long start, stop;
		int max;
		if (args.length>0)
			max = Integer.parseInt(args[0]);
		else
			max = 1000000;
		int data[] = new int[max];
		ArrayInt.fillRandom(data,max);
		
		ArrayIntRand air = new ArrayBuiltinSort(data);
		test(air);

		air = new ArrayLsdSort(data);
		test(air);
	}
}
