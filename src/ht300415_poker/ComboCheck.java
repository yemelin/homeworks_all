package ht300415_poker;

import java.util.Arrays;

class ComboCheck {
	ComboCheck(CardPile tp) {
		p = tp;
		sortRanks();
		obtainCombo();//set _combo and _hirank
	}
	
	private int _combo = -1;
	private int _hirank = -1; //highest card's rank in the combo
	
//	combinations constants
	final int SIZE = 5; //5 cards in the hand
	
	final static int[] combomult = {0, 1, 1, 2, 3, 4, 5, 7, 20, 50, 100};
	final static String[] combonames = {"", "King and Ace", "Pair", "Two pairs",
		"Three of a kind", "Straight", "Flush", "Full house", "Four of a kind",
		"Straight flush", "Royal flush",};
	final static int NONE = 0;
	final static int KINGACE = 1;
	final static int DEUCE = 2;
	final static int DOUBLE_DEUCE = 3;
	final static int TRINE = 4;
	final static int STRAIGHT = 5;
	final static int FLUSH = 6;
	final static int FULL_HOUSE = 7;
	final static int SQUARE = 8;
	final static int STRAIGHT_FLUSH = 9;
	final static int ROYAL_FLUSH = 10;
	
// just a convenience array: 1,2,3,4 times repeating the same rank
	final static int[] deuceTrineSquareCombos = {NONE, DEUCE, TRINE, SQUARE};
//	--------------------
	private CardPile p;
	private int ac[] = new int[2]; //auxiliary array to store temporary combo info
	
//it's much easier to look for a combo when card ranks are sorted
	private int[] sortedRanks = new int[SIZE];
	private void sortRanks() {
		Card card = p.top();
		for (int i=0; i<SIZE; i++) {
			sortedRanks[i] = card.getRank();
			card = card.link;
		}
		Arrays.sort(sortedRanks);
	}
//Counts the repeating cards, meanwhile determines the highest card in the combo
	private void loadSameRankCombos(){
		ac[0]=0;
		ac[1]=0;
		int k=0;
		for (int i=1; i<SIZE; i++) {
			if (sortedRanks[i]==sortedRanks[i-1])
				ac[k]++;
			else if (ac[k]>0) {
				_hirank = sortedRanks[i-1]; 
				k++;
			}
		}
		if (_hirank==-1)
			_hirank = sortedRanks[sortedRanks.length-1];
	}
	private void loadStraighOrFlushCombos(){
		int k=0;
		if (isStraight()) {
			ac[0]=STRAIGHT;
			k++;
		}
		if (isFlush())
			ac[k]=FLUSH;
	}
	private boolean isFlush(){
		for (Card card = p.top(); card.link!=null; card=card.link)
			if (card.getSuit()!=card.link.getSuit())
				return false;
		return true;
	}
	private boolean isStraight(){
		return sortedRanks[SIZE-1]-sortedRanks[0]==4;
	}
	private boolean isAceAndKing(){
		return (sortedRanks[sortedRanks.length-1]==12) &&
				(sortedRanks[sortedRanks.length-2]==11);
	}
//	check for combo with same ranks repeating, like Deuce or Full House
	private int sameRankCombo(){
		loadSameRankCombos();
		if (ac[1]>0) {
			if(ac[1]==ac[0])
				return DOUBLE_DEUCE;
			else return FULL_HOUSE;
		}
		else 
			return deuceTrineSquareCombos[ac[0]];
	}
//	straight, flush or straight flush
	private int straightOrFlushCombo(){
		int ret =0;
		loadStraighOrFlushCombos();
		if (ac[1]>0) {
			if (sortedRanks[sortedRanks.length-1]==12)
				ret = ROYAL_FLUSH;
			else
				ret = STRAIGHT_FLUSH;
		}
		else
			ret = ac[0];
		return ret;
	}

	private int obtainCombo() {
		if ((_combo = sameRankCombo())==NONE) {
			if ((_combo = straightOrFlushCombo())==NONE)
				if (isAceAndKing())
					_combo = KINGACE;
		}
		if (_combo==DEUCE && isFlush()) {
			_combo = FLUSH;
			_hirank = sortedRanks[sortedRanks.length-1];
		}
		return _combo;
	}

	int getCombo() {
		return _combo;
	}

	int getHighest() {
		return _hirank;
	}
	
	int compareTo(ComboCheck cc) {
		int ret = getCombo() - cc.getCombo();
		if (ret==0)
			ret = getHighest() - cc.getHighest();
		return ret;
	}
}
