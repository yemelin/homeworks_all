package ht_1;

public class GuessNumber {
	public GuessNumber(int l, int h, int count, SevenGuesses parent){		
		lo = l;
		hi = h;
		g = (lo+hi)/2;
		counter = count;
		System.out.printf("Next object with %d %d %d %d%n", lo, hi, g, counter);
		if ((hi>lo) && !((s=getAnswer()).equals("y"))) {
			nextGuess(s.charAt(0));
			parent.gn = new GuessNumber(lo,hi,counter+1, parent);
		}
		else
			System.out.printf("%d guesses made, Your number is %d%n",
					counter, g);

	}
	protected void finalize() {
		System.out.printf("Guess %d closed %n", counter);
	}
	private int lo=0, hi=100, g = (lo+hi)/2;
	private int counter=0;
	static String s;
	
	public void nextGuess (char s){
		if (s=='l')
			hi = g-1;
		else if (s=='m')
			lo = g+1;		
		g = (lo+hi)/2;		
	}
	
	public String getAnswer() {
		System.out.println("Is it "+g +"(y|m|l)?");
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		while (!validate(s = scanner.nextLine()))
			System.out.println("y,l or m, please");
//		scanner.close();  //complains resource leakage. How can I avoid it?
//		if I close scanner, input stops working for all the objects!
		return s;
	}
	private boolean validate(String s) {
		return (s.equals("y")||s.equals("l")||s.equals("m"));
	}
}