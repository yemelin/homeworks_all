package ht_1;

public class SevenGuesses {
	public GuessNumber gn;
	public static void main(String[] args) {
		SevenGuesses sg = new SevenGuesses();
		sg.gn = new GuessNumber(0,100,0,sg);
		System.gc();
	}
}