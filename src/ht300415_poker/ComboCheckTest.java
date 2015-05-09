package ht300415_poker;

import static org.junit.Assert.*;
import static ht300415_poker.Card.*;

import java.util.Random;

import org.junit.Test;

/* JUnit tests for ComboCheck class which determines poker combinations
 * Test hands' sets are fixed, but the cards in the hand are shuffled 
 * randomly before the corresponding test 
 */
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
	private CardPile generateNone() {
		return generateHand(NONE_HAND);
	}
	private CardPile generateKingAce() {
		return generateHand(KINGACE_HAND);
	}
	private CardPile generateDeuce() {
		return generateHand(DEUCE_HAND);
	}
	private CardPile generateDoubleDeuce() {
		return generateHand(DOUBLE_DEUCE_HAND);
	}
	private CardPile generateTrine() {
		return generateHand(TRINE_HAND);
	}
	private CardPile generateStraight() {
		return generateHand(STRAIGHT_HAND);
	}
	private CardPile generateFlush() {
		return generateHand(FLUSH_HAND);
	}
	private CardPile generateFullHouse() {
		return generateHand(FULL_HOUSE_HAND);
	}
	private CardPile generateSquare() {
		return generateHand(SQUARE_HAND);
	}
	private CardPile generateStraightFlush() {
		return generateHand(STRAIGHT_FLUSH_HAND);
	}
	private CardPile generateRoyalFlush() {
		return generateHand(ROYAL_FLUSH_HAND);
	}

	@Test
	public void testNone() {
		CardPile t = generateNone();
		printHand(t);
		assertEquals (ComboCheck.NONE, new ComboCheck(t).getCombo());
	}
	@Test
	public void testKingAce() {
		CardPile t = generateKingAce();
		printHand(t);
		assertEquals (ComboCheck.KINGACE, new ComboCheck(t).getCombo());
	}
	@Test
	public void testDeuce() {
		CardPile t = generateDeuce();
		printHand(t);
		assertEquals (ComboCheck.DEUCE, new ComboCheck(t).getCombo());
	}
	@Test
	public void testDoubleDeuce() {
		CardPile t = generateDoubleDeuce();
		printHand(t);
		assertEquals (ComboCheck.DOUBLE_DEUCE, new ComboCheck(t).getCombo());
	}
	@Test
	public void testTrine() {
		CardPile t = generateTrine();
		printHand(t);
		assertEquals (ComboCheck.TRINE, new ComboCheck(t).getCombo());
	}
	@Test
	public void testStraight() {
		CardPile t = generateStraight();
		printHand(t);
		assertEquals (ComboCheck.STRAIGHT, new ComboCheck(t).getCombo());
	}
	@Test
	public void testFlush() {
		CardPile t = generateFlush();
		printHand(t);
		assertEquals (ComboCheck.FLUSH, new ComboCheck(t).getCombo());
	}
	@Test
	public void testFullHouse() {
		CardPile t = generateFullHouse();
		printHand(t);
		assertEquals (ComboCheck.FULL_HOUSE, new ComboCheck(t).getCombo());
	}
	@Test
	public void testSquare() {
		CardPile t = generateSquare();
		printHand(t);
		assertEquals (ComboCheck.SQUARE, new ComboCheck(t).getCombo());
	}
	@Test
	public void testStraightFlush() {
		CardPile t = generateStraightFlush();
		printHand(t);
		assertEquals (ComboCheck.STRAIGHT_FLUSH, new ComboCheck(t).getCombo());
	}
	@Test
	public void testRoyalFlush() {
		CardPile t = generateRoyalFlush();
		printHand(t);
		assertEquals (ComboCheck.ROYAL_FLUSH, new ComboCheck(t).getCombo());
	}

}
