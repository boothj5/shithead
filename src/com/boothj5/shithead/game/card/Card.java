package com.boothj5.shithead.game.card;
public class Card {

	public static enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,TEN,
		JACK, QUEEN, KING, ACE} ;
	
	public static enum Suit {HEARTS, SPADES, DIAMONDS, CLUBS} ;
		
	private final Rank rank  ;
	private final Suit suit ;

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}	

	public Card(Rank rank, Suit suit) {
		this.rank = rank ;
		this.suit = suit ;
	}
	
	public String toString () {
		return (rank + " of " + suit) ;
	}

	public int compareTo(Object o) {
		Card otherCard = (Card) o ;
		return this.rank.compareTo(otherCard.rank) ;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Card)) {
				return false ;
		}
		else {
			Card other = (Card) o ;
			return (this.rank == other.rank && this.suit == other.suit) ;
		}
	}
	
	public String getImage() {
	    return "/" + rank.toString() + suit.toString() + ".png" ;
	}
	
	public boolean sameRankDifferentSuit(Card card) {
        return ((this.rank == card.rank) && (this.suit != card.suit)) ; 
	}
}
