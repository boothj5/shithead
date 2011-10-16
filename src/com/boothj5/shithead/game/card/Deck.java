package com.boothj5.shithead.game.card;
import java.util.* ;

public final class Deck {

	private final List<Card> cards ;
	
	public Deck () {
	    cards = new ArrayList<Card>() ;
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
	
	public Card remove(int index) {
		return cards.remove(index) ;
	}
	
	public void removeAll(List<Card> cards) {
		this.cards.removeAll(cards) ;
	}
	
	public void addAnotherDeck() {
	    final Deck extraDeck = new Deck() ;
	    cards.addAll(extraDeck.getCards()) ;
	}
	
	public int size() {
	    return cards.size() ;
	}

}
