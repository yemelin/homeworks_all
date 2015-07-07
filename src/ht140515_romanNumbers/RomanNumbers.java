package ht140515_romanNumbers;

public class RomanNumbers {

	public static final String[] romanParts = {"I","IV","V","IX","X","XL","L",
		"XC","C","CD","D","CM","M"};
	public static final int[] arabicParts = {1,4,5,9,10,40,50,90,100,400,500,900,1000};

// returns empty string on illegal argument instead of throwing exception
// just to avoid creating of an additional test case
	public static String toRoman(int _arabic) {
		if (_arabic<1 || _arabic>3999)
			return "";
		int m=romanParts.length;
		String ret = "";
		while (--m>=0) {
			while (_arabic>=arabicParts[m]) {
				_arabic-=arabicParts[m];
				ret+=romanParts[m];
			}
		}
		return ret;
	}

// no illegal argument check is performed. The check would complicate the training example
// The conversion is based on the fact that the lower-numbered part of Roman number from
//	romanParts array cannot appear before the higher, which means that for example
//	single X cannot appear earlier than XC 
	public static int toArabic(String _roman) {
		int m=romanParts.length-1;
		int ret = 0;
		int pos = 0;
		while (pos<_roman.length() && m>=0) {
			if (_roman.charAt(pos) == romanParts[m].charAt(0) &&
				  (!isDoubleLettered(m) || 
				  (pos<_roman.length()-1 && _roman.charAt(pos+1)==romanParts[m].charAt(1)))) {
				ret+= arabicParts[m];
				pos+=romanParts[m].length();
			}
			else
				m--;
		}
		return ret;
	}

	private static final boolean isDoubleLettered(int m) {
		return (m&1)>0; //m is odd
	}
}
