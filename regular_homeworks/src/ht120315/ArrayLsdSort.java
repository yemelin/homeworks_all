package ht120315;

public class ArrayLsdSort extends ArrayIntRand {
	public ArrayLsdSort(int n) {
		super(n);
		info = n +" elements Radix sort C-style, radix "+0x100;
	}
	public ArrayLsdSort(int[] data) {
		super(data);
		info = data.length +" elements Radix sort C-style, radix "+0x100;
	}
	@Override
	public void sort(){
		utils.ArrayInt.radixSort2(a);
	}
}
