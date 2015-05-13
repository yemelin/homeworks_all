package ht300415_poker;

import static org.junit.Assert.*;
import static ht300415_poker.Card.*;
import static ht300415_poker.ComboCheck.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/* JUnit tests for ComboCheck class which determines poker combinations
 * Test hands' sets are fixed, but the cards in the hand are shuffled 
 * randomly before the corresponding test 
 */
@RunWith(Parameterized.class)
public class ComboCheckTest {
//	note that 0 is 2(two), 1 is 3..., 8 is 10, 9 is Jack etc
	public final static int[][] NONE_HAND = {{10,heart},{4,spade},{2,diamond},
		{3,diamond},{12,club}};
	public final static int[][] KINGACE_HAND = {{11,heart},{4,spade},{2,diamond},
		{3,diamond},{12,club}};
	public final static int[][] DEUCE_HAND = {{10,heart},{10,spade},{9,diamond},
		{8,diamond},{7,club}};
	public final static int[][] DOUBLE_DEUCE_HAND = {{10,heart},{10,spade},{9,diamond},
		{8,diamond},{8,club}};
	public final static int[][] TRINE_HAND = {{10,heart},{10,spade},{10,diamond},
		{8,diamond},{7,club}};
	public final static int[][] STRAIGHT_HAND = {{7,heart},{8,spade},{J,diamond},
		{K,diamond},{D,club}};
	public final static int[][] FLUSH_HAND = {{7,spade},{8,spade},{2,spade},
		{D,spade},{J,spade}};
	public final static int[][] FULL_HOUSE_HAND = {{10,heart},{10,spade},{9,diamond},
		{9,spade},{9,club}};
	public final static int[][] SQUARE_HAND = {{10,heart},{10,spade},{10,diamond},
		{8,diamond},{10,club}};
	public final static int[][] STRAIGHT_FLUSH_HAND = {{7,spade},{8,spade},{K,spade},
		{D,spade},{J,spade}};
	public final static int[][] ROYAL_FLUSH_HAND = {{8,spade},{J,spade},{D,spade},
		{K,spade},{A,spade}};


	private void printHand (CardPile pile) {
		for (Card card = pile.top(); card!=null;card=card.link)
			System.out.print(names[card.getRank()]+","+card.getSuit()+" ");
		System.out.println();		
	}

	// Implementing Fisher–Yates shuffle
	private void shuffleArray(Object[] ar)
	  {
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      Object a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	
	private CardPile generateHand(int[][]a) {
		int[][]copyA = new int[5][2];
		System.arraycopy(a, 0, copyA, 0, a.length);
		shuffleArray(copyA);
		CardPile pile = new CardPile(0,0);
		for (int[] card: copyA) {
			pile.addCard(new Card(card[1], card[0]));
		}
		return pile;
	}
	
	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{NONE, NONE_HAND},
				{KINGACE,KINGACE_HAND},
				{DEUCE, DEUCE_HAND},
				{DOUBLE_DEUCE, DOUBLE_DEUCE_HAND},
				{TRINE, TRINE_HAND},
				{STRAIGHT, STRAIGHT_HAND},
				{FLUSH, FLUSH_HAND},
				{FULL_HOUSE, FULL_HOUSE_HAND},
				{SQUARE, SQUARE_HAND},
				{STRAIGHT_FLUSH, STRAIGHT_FLUSH_HAND},
				{ROYAL_FLUSH, ROYAL_FLUSH_HAND},
		}
		);
	}
	private int _combo;
	private int[][] _hand;
	public ComboCheckTest(int combo, int[][] hand) {
		_combo = combo;
		_hand = hand;
	}
	@Test
	public void testCombo() {
		CardPile t = generateHand(_hand);
		printHand(t);
		assertEquals (_combo, new ComboCheck(t).getCombo());
	}
}