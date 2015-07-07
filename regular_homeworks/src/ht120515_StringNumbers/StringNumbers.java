package ht120515_StringNumbers;

import java.util.ArrayList;

public class StringNumbers {
	
	public static final String[] digits = {"один","два","три","четыре","пять",
		"шесть","семь","восемь","девять","десять",
		"одиннадцать","двенадцать","тринадцать","четырнадцать","пятнадцать",
		"шестнадцать","семьнадцать","восемьнадцать","девятнадцать"};
	public static final String[] tenToTwenty = {"одиннадцать","двенадцать","тринадцать","четырнадцать","пятнадцать","шестнадцать","семьнадцать","восемьнадцать","девятнадцать"};
	public static final String[] tens = {"десять","двадцать","тридцать","сорок","пятьдесят","шестьдесят","семьдесят","восемьдесят","девяносто"};
	public static final String[] hundreds = {"сто","двести", "триста"};
	public static final String[][] numClasses = {digits, tens, hundreds};
	
	public static String toString(int number) {
		if (number == 0)
			return "ноль";
		ArrayList <String> output = new ArrayList<>();
		int dig = 0;
		int rank = 0;
		int next_digit;
		String[] numClass = null;
		while (number>0) {
			next_digit = number%10;
			if (rank==1 && next_digit==1 && dig!=0) {
				numClass = tenToTwenty;
				output.remove(output.size()-1);
			}
			else {
				dig = next_digit;
				numClass = numClasses[rank];
			}
			if (dig>0)
				output.add(numClass[dig-1]);
			rank++;
			number/=10;
		}
		String ret = output.get(output.size()-1);
		for (int i=output.size()-2; i>=0; i--) {
			ret+=(" " +output.get(i));
		}
		System.out.println(ret);
		return ret;
	}
	
	static int[] toDigits (int number) {
		int tmp[] = new int[3];
		int dig;
		int rank = 0;
		while (number>0) {
			dig = number%10;
			if (dig>0)
				tmp[rank] = 
			rank++;
			number/=10;
		}
		
		return null;
	}
	
	

}
