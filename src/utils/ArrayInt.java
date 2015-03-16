package utils;
import java.util.Arrays;
import java.util.Random;
import java.util.*;
public class ArrayInt {
	
	public static boolean isSortedUp(int a[]) {
		for (int i = 1; i < a.length; i++)
			if (a[i]<a[i-1])
				return false;
		return true;
	}
	public static boolean isSortedDown(int a[]) {
		for (int i = 1; i < a.length; i++)
			if (a[i]>a[i-1])
				return false;
		return true;
	}
	public static void fillRandom(int a[], int max){
		Random random = new Random();
		for (int i = 0; i < a.length; i++) {
			a[i] = random.nextInt(max);
		}
	}
	public static void swap (int a[], int i, int j) {
		int x = a[i];
		a[i] = a[j];
		a[j] = x;
	}
	public static void bubbleSort(int a[]) {
		for (int i = a.length-1; i>0 ; i--)
			for (int j = 1; j <= i; j++)
				if (a[j-1]>a[j])
					swap(a, j-1, j);
	}
	
//	----------------------------------------------
	static int base = 10;
	private static int getMax(int a[]){
		int mi = 0;
		for (int i = 1; i < a.length; i++)
			if (a[i]>a[mi])
				mi = i;
		return mi;
	}

//	"C-style"
	public static void radixSort(int a[]){
//buckets will be stored as one big bucket with partitions |--|----|--...
		int b[] = new int[a.length];
		int bi[] = new int[base]; //partitions' (buckets') borders in b
		int exp = 1;//biggest number with current number of digits
		int m = a[getMax(a)];
		while (m/exp>0){
			Arrays.fill(bi, 0); //memset with zeros
//first, count the number of keys for each of the buckets
			for (int i=0; i<a.length; i++)
				bi[(a[i]/exp)%base]++;
//get the borders. ith Border is the sum of all the bucket sizes till i (incl.)
			for (int i=0; i<base-1; i++)
				bi[i+1]+=bi[i];
//get the corresponding bucket for each key and store the key in it
//bucket borders are used to loop over buckets (partitions in b)
			for (int i=a.length-1; i>=0;i--) 
				b[--bi[(a[i]/exp)%base]] = a[i];
			System.arraycopy(b, 0, a, 0, a.length);
			exp*=base;
		}
	}
	
//	-----------------------------------------
//	from Wikipedia
    @SuppressWarnings("unchecked")
    private static LinkedList<Integer>[] counter = new LinkedList[] {
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>()
    };
 
    public static void sortLSD(int[] array, int maxDigitSymbols) {
        int mod = 10;
        int dev = 1;
        for (int i = 0; i < maxDigitSymbols; i++, dev *= 10, mod *= 10) {
            for(int j = 0; j < array.length; j++) {
                int bucket = (array[j] % mod) / dev;
                counter[bucket].add(array[j]);
            }
            int pos = 0;
            for(int j = 0; j < counter.length; j++) {
                Integer value = null;
                while ((value = counter[j].poll()) != null) {
                    array[pos++] = value;
                }
            }
        }
 
    }


}
