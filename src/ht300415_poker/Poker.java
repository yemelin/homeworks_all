package ht300415_poker;

/*
 * Simple Caribbean Poker Game
 * based on Classes from
 Simple Solitaire Card Game in Java
 Written by Tim Budd, Oregon State University, 1996
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;

public class Poker extends Applet {
	private static final long serialVersionUID = 1L;

	private final static int ANTE = 1;
	private int pmoney = 50;
//	private Panel panel = new Panel(new FlowLayout(FlowLayout.LEFT));
	private Button betButton = new Button("Bet");
	private Button foldButton = new Button("Fold");
	private Button dealButton = new Button("Deal");
	private Label money = new Label();
	private String status; //game info to be displayed 
	
	CardPile allPiles[];
	static DeckPile deckPile;
	static DealerPile tableau;
	TablePile hand;

// initializing the buttons	
	private Panel controlPanel() {
		Panel panel = new Panel(new FlowLayout(FlowLayout.LEFT));
		panel.setBounds(5, 300, 300, 50);
		panel.add(betButton);
		panel.add(foldButton);
		panel.add(dealButton);
		panel.add(money);
		betButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				compareHands();
				money.setText(Integer.toString(pmoney)+"$");
				getAppletContext().showStatus(status);
				fold();
			}
		});
		foldButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				fold();	
			}
		});
		dealButton.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				restart();
				repaint();
			}
		});
		return panel;
	}
//	reaction to "Bet"
	private void compareHands() {
		pmoney-=ANTE*2;		//player makes a bet
		tableau.openCards();//dealer opens his cards
		repaint();
		ComboCheck tc = new ComboCheck(tableau);
//	if the dealer has no combo, player gets his bets back plus one ANTE
		if (tc.getCombo()==ComboCheck.NONE) {
			status = "Casino hand doesn't qualify";
			pmoney+=Poker.ANTE*4;
		}
		else { //the dealer has a combo
			ComboCheck hc = new ComboCheck(hand);
			int cmp = hc.compareTo(tc);
			status = ComboCheck.combonames[hc.getCombo()]+" vs "+
				ComboCheck.combonames[tc.getCombo()];
			if (cmp==0) { //equal combos - get bets back
				pmoney+=Poker.ANTE*3;
				status+=", bets pushed";
			}
			else if (cmp>0) {
				pmoney+=ANTE*(1+2*(ComboCheck.combomult[hc.getCombo()+1]));
				status+=", you win";
			}
			else status+=", you lose";
//			System.out.println(ComboCheck.combonames[hc.getCombo()]+" vs "+
//				ComboCheck.combonames[tc.getCombo()]);
		}
	}
	
//	nothing to do, just invite for the next deal enabling Deal button
	private void fold() {
		betButton.setEnabled(false);
		foldButton.setEnabled(false);
		dealButton.setEnabled(true);
	}
	
	private void restart() {
		dealButton.setEnabled(false);
		if (pmoney<ANTE*3)
			getAppletContext().showStatus("Game over");
		else {
			allPiles[0] = deckPile = new DeckPile(335, 5);
			allPiles[1] = tableau = new DealerPile(5,80,5);
			allPiles[2] = hand = new TablePile(5,180,5);
			pmoney--;
			betButton.setEnabled(true);
			foldButton.setEnabled(true);
			money.setText(Integer.toString(pmoney)+"$");
			getAppletContext().showStatus("Bet 2$ or fold");
		}
	}

	public void init() {
		setSize(400,400);
		setLayout(null);
		allPiles = new CardPile[3];
		add(controlPanel());
		restart();
	}
	
//	unnecessarily redraws player's pile when the dealer opens cards, but
//	it doesn't seem critical. No need to draw DeckPile in this game
	public void paint(final Graphics g) {
		for (int i = 1; i < 3; i++) {
			allPiles[i].display(g);
		}
	}
}