package com.boothj5.shithead.game;
import java.util.* ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.player.Player;
public class LastMove {

	private Player player ;
	private List<Card> cards ; 
	private boolean burnt ;
	private boolean missAGo ;

	public LastMove(Player player, List<Card> cards) {
		this.player = player ;
		this.cards = cards ;
	}
	
	public Player getPlayer() {
		return player;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setBurnt(boolean burnt) {
		this.burnt = burnt;
	}
	
	public boolean getBurnt() {
		return burnt ;
	}

	public void setMissAGo(boolean missAGo) {
		this.missAGo = missAGo;
	}

	public boolean getMissAGo() {
		return missAGo;
	}

}
