package ht120315;

public class ArrayBuiltinSort extends ArrayIntRand{
	public ArrayBuiltinSort(int n) {
		super(n);
		info = n + " elements Built-in sort";
	} 
	public ArrayBuiltinSort(int[] src) {
		super(src);
		info = src.length + " elements Built-in sort";
	}
	@Override
	public void sort(){
		java.util.Arrays.sort(a);
	}
}
