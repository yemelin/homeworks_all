package ht120315;
import utils.ArrayInt;
public class ArrayIntRand implements utils.Testable{
	public int a[];
	public String info;
	public ArrayIntRand(int n){
		a = new int[n];
		ArrayInt.fillRandom(a, n);
	}
	public ArrayIntRand(int src[]){
		a = new int[src.length];
		System.arraycopy(src, 0, a, 0, src.length);
	}
	public void sort() {}
	@Override
	public void routine() {
		sort();
	}
	@Override
	public String getInfo() {
		return info;
	}
}
