package com.boothj5.shithead.card;
import java.util.* ;


public class Deck {

	public List<Card> cards = new ArrayList<Card>() ;
	
	public Deck () {
		for (Card.Suit suit : Card.Suit.values())
			for (Card.Rank rank : Card.Rank.values())
				cards.add(new Card(rank, suit)) ;
	}
	
	public void shuffle() {
		Collections.shuffle(cards) ;
	}
	
	public int getSize() {
		return cards.size() ;
	}
}
