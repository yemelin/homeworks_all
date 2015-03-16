package ht120315;

import java.util.Arrays;
import utils.ArrayInt;

public class LSDsort {
	public static void main(String[] args) {
		long start, stop;
		int max;
		if (args.length>0)
			max = Integer.parseInt(args[0]);
		else
			max = 1000000;
		int data[] = new int[max];
		int datacopy[] = new int[max];

		ArrayInt.fillRandom(data,max);
		
//TODO: shorten the code below by passing corresponding sort function as a 
//		parameter, class factory should be used.
//TODO: create classes, to use data.sort() instead ArrayInt.sort(data)
		System.arraycopy(data, 0, datacopy, 0, max);		
		System.out.println(max+" elements Radix sort C-style, radix "+0x100);
		start = System.currentTimeMillis();
		ArrayInt.radixSort2(datacopy);
		stop = System.currentTimeMillis();
//		System.out.println(Arrays.toString(datacopy));
		System.out.println("Sorted OK:"+ArrayInt.isSortedUp(datacopy));
		System.out.println("Elapsed = " + (stop - start)+"ms");
/*
		System.arraycopy(data, 0, datacopy, 0, max);
		System.out.println(max+" Wiki java example radix sort");
		start = System.currentTimeMillis();
//wiki example uses the maximum key length as a known parameter, while the 
//other two methods calculate it, but it's anyway slow
		ArrayInt.sortLSD(datacopy,6);
		stop = System.currentTimeMillis();
		System.out.println("Sorted OK:"+ArrayInt.isSortedUp(datacopy));
		System.out.println("Elapsed = " + (stop - start)+"ms");
*/		
		System.arraycopy(data, 0, datacopy, 0, max);
		System.out.println(max+" Built-in sort");
		start = System.currentTimeMillis();
		Arrays.sort(datacopy);
		stop = System.currentTimeMillis();
		System.out.println("Elapsed = " + (stop - start)+"ms");
	}
}
