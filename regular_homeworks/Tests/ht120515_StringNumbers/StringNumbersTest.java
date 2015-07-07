package ht120515_StringNumbers;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StringNumbersTest {
	
	private int _input;
	private String _output;

	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{0, "ноль"},
				{1, "один"},
				{2, "два"},
				{3, "три"},
				{21, "двадцать один"},
				{10, "десять"},
				{19, "девятнадцать"},
				{117,"сто семьнадцать"},
				{200, "двести"},
				{336, "триста тридцать шесть"},
		}
		);
	}
	
	public StringNumbersTest(final int input, final String output) {
		_input = input;
		_output = output;
	}

//	public boolean deepEquals (int[] a, int[] b) {
//		int i=0;
//		if (a.length == b.length)
//			for (; i<a.length && a[i]==b[i]; i++);
//		return (i==a.length);
//	}
	@Test
	public void test() {
		assertEquals(_output, StringNumbers.toString(_input));
	}
	
//	@Test 
//	public void testToDigits () {
//		int[] t = {1,2,3};
//		assertTrue(deepEquals (t, StringNumbers.toDigits(123)));
//	}
}
