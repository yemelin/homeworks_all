package ht140515_romanNumbers;

import static org.junit.Assert.*;
import ht140515_romanNumbers.RomanNumbers;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith (Parameterized.class)
public class RomanNumbersTest {
	
	private int _arabic;
	private String _roman;

	public RomanNumbersTest (String roman, int arabic) {
		_roman = roman;
		_arabic=arabic;
	}
	
	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{"I",1},
				{"V",5},
				{"X",10},
				{"XV",15},
				{"CC",200},
				{"IV",4},
				{"XC",90},
				{"MMDCCCXCVI",2896},
				{"",0},
//				{"",-1},
//				{"",4000},
		});
	}


	@Test
	public void test() {
		assertEquals(_roman, RomanNumbers.toRoman(_arabic));
		assertEquals(_arabic, RomanNumbers.toArabic(_roman));
	}

}
