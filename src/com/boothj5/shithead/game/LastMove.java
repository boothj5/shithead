package com.boothj5.shithead.game;
import java.util.* ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.player.Player;
public class LastMove {

	private Player player ;
	private List<Card> cards ; 

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
	
}