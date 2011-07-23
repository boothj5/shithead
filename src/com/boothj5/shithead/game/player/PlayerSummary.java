package com.boothj5.shithead.game.player;

import java.util.List;

import com.boothj5.shithead.game.card.Card;

public class PlayerSummary {

	private final String name ;
	private final int handSize ;
	private final List<Card> faceUp ;
	private final int handDownSize ;
	private final boolean hasCards ;
	
	public PlayerSummary(final String name, final int handSize, 
							final List<Card> faceUp, final int handDownSize,
								final boolean hasCards) {
		this.name = name ;
		this.handSize = handSize ;
		this.faceUp = faceUp ;
		this.handDownSize = handDownSize ;
		this.hasCards = hasCards ;
	}

	public String getName() {
		return name;
	}

	public int getHandSize() {
		return handSize;
	}

	public List<Card> getFaceUp() {
		return faceUp;
	}

	public int getHandDownSize() {
		return handDownSize;
	}

	public boolean hasCards() {
		return hasCards;
	}


}
