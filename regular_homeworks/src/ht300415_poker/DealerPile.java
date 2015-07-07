package ht300415_poker;

import java.awt.Graphics;

class DealerPile extends CardPile {
	public static int shiftX = 50;
	DealerPile(int x, int y, int c) {
		super(x, y);
		for (int i = 0; i < c; i++) {
			addCard(Poker.deckPile.pop());
		}
		top().flip();
	}
	void openCards() {
		for (Card card = top().link; card!=null; card = card.link)
			card.flip();
	}
	
	void display(final Graphics g) {
		stackDisplay(g, top());
	}

	void stackDisplay(final Graphics g, final Card aCard) {
		Card card = aCard;
		int localX=x;
		while (card!=null) {
			card.draw(g, localX, y);
			localX+=shiftX;
			card = card.link;
		}
	}
}