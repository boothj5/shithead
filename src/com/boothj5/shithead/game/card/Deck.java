package com.boothj5.shithead.game.card;
import java.util.* ;

public class Deck {

	private List<Card> cards = new ArrayList<Card>() ;
	
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
	
	public List<Card> getCards() {
		return Collections.unmodifiableList(cards) ;
	}
	
	public void remove(int index) {
		cards.remove(index) ;
	}
	
	public void removeAll(List<Card> cards) {
		this.cards.removeAll(cards) ;
	}
//
//	public void addAll(List<Card> cards) {
//		this.cards.addAll(cards) ;
//	}
	
	public void addDeck(Deck deck) {
	    this.cards.addAll(deck.getCards()) ;
	}

}
