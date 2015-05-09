package ht300415_poker;

class TablePile extends DealerPile {
// Player's pile. The only difference to the parent is that Dealer in the beginning
//	opens only one card, while the player naturally opens all of them
	TablePile(final int x, final int y, final int c) {
		// initialize the parent class
		super(x, y, c);
		openCards();
	}
}