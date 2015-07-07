package ht300415_poker;

import java.awt.Color;
import java.awt.Graphics;

class Card {
	final static int black = 1;
	final static int red = 0;

	final static int heart = 0;
	final static int spade = 1;
	final static int diamond = 2;
	final static int club = 3;

	final static int J = 9;
	final static int D = 10;
	final static int K = 11;
	final static int A = 12;
	
	final static int width = 50;
	final static int height = 70;

	// data fields for colors and suits
//	order changed in comparison to the original, because unlike Solitaire
//	for this game we need Ace to be the highest rank, and not 0
	static String names[] = {"2", "3", "4", "5", "6",
	 "7", "8", "9", "10", "J", "Q", "K", "A"};
	
	// data fields
	private boolean faceup;
	private int rank;
	private int suit;

	Card link;

	// constructor
	Card(final int suitValue, final int rankValue) {
		suit = suitValue;
		rank = rankValue;
		faceup = false;
	}

	int color() {
		if (getSuit() == heart || getSuit() == diamond) {
			return red;
		}
		return black;
	}

	void draw(final Graphics g, final int x, final int y) {
//		String names[] = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
//				"J", "Q", "K", "A" };
		// clear rectangle, draw border
		g.clearRect(x, y, width, height);
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		// draw body of card
		if (isFaceUp()) {
			if (color() == red) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.blue);
			}
			g.drawString(names[getRank()], x + 3, y + 15);
			if (getSuit() == heart) {
				g.drawLine(x + 25, y + 30, x + 35, y + 20);
				g.drawLine(x + 35, y + 20, x + 45, y + 30);
				g.drawLine(x + 45, y + 30, x + 25, y + 60);
				g.drawLine(x + 25, y + 60, x + 5, y + 30);
				g.drawLine(x + 5, y + 30, x + 15, y + 20);
				g.drawLine(x + 15, y + 20, x + 25, y + 30);
			} else if (getSuit() == spade) {
				g.drawLine(x + 25, y + 20, x + 40, y + 50);
				g.drawLine(x + 40, y + 50, x + 10, y + 50);
				g.drawLine(x + 10, y + 50, x + 25, y + 20);
				g.drawLine(x + 23, y + 45, x + 20, y + 60);
				g.drawLine(x + 20, y + 60, x + 30, y + 60);
				g.drawLine(x + 30, y + 60, x + 27, y + 45);
			} else if (getSuit() == diamond) {
				g.drawLine(x + 25, y + 20, x + 40, y + 40);
				g.drawLine(x + 40, y + 40, x + 25, y + 60);
				g.drawLine(x + 25, y + 60, x + 10, y + 40);
				g.drawLine(x + 10, y + 40, x + 25, y + 20);
			} else if (getSuit() == club) {
				g.drawOval(x + 20, y + 25, 10, 10);
				g.drawOval(x + 25, y + 35, 10, 10);
				g.drawOval(x + 15, y + 35, 10, 10);
				g.drawLine(x + 23, y + 45, x + 20, y + 55);
				g.drawLine(x + 20, y + 55, x + 30, y + 55);
				g.drawLine(x + 30, y + 55, x + 27, y + 45);
			}
		} else { // face down
			g.setColor(Color.yellow);
			g.drawLine(x + 15, y + 5, x + 15, y + 65);
			g.drawLine(x + 35, y + 5, x + 35, y + 65);
			g.drawLine(x + 5, y + 20, x + 45, y + 20);
			g.drawLine(x + 5, y + 35, x + 45, y + 35);
			g.drawLine(x + 5, y + 50, x + 45, y + 50);
		}
	}

	boolean isFaceUp() {
		return faceup;
	}

	void flip() {
		faceup = !faceup;
	}

	// access attributes of card
	int getRank() {
		return rank;
	}

	int getSuit() {
		return suit;
	}
}