package ht300415_poker;

import java.awt.Color;
import java.awt.Graphics;

class CardPile {

	private Card firstCard;

	// coordinates of the card pile
	protected int x;
	protected int y;

	CardPile(final int xl, final int yl) {
		x = xl;
		y = yl;
		firstCard = null;
	}

	void addCard(final Card aCard) {
		aCard.link = firstCard;
		firstCard = aCard;
	}


	void display(final Graphics g) {
		g.setColor(Color.black);
		if (firstCard == null) {
			g.drawRect(x, y, Card.width, Card.height);
		} else {
			firstCard.draw(g, x, y);
		}
	}

	boolean empty() {
		return firstCard == null;
	}


	Card pop() {
		Card result = null;
		if (firstCard != null) {
			result = firstCard;
			firstCard = firstCard.link;
		}
		return result;
	}


	Card top() {
		return firstCard;
	}

/* Unneeded because the only interaction with a player is Bet or Fold
 * Kept for historical reasons. Could be used for manual deal from DeckPile 
 * or a poker variation where a player can replace some cards	
 */
//	 boolean canTake(final Card aCard) {
//		return false;
//	}
//	 boolean includes(final int tx, final int ty) {
//		return x <= tx && tx <= x + Card.width && y <= ty
//				&& ty <= y + Card.height;
//	}
//	 void select(final int tx, final int ty) {
//		// do nothing
//	}
}