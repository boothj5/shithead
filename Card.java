import java.util.* ;

public class Card implements Comparable {

	public enum Rank {TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,TEN,
		JACK, QUEEN, KING, ACE} ;
	
	public enum Suit {HEARTS, SPADES, DIAMONDS, CLUBS} ;
		
	public Rank rank  ;
	public Suit suit ;

	public Card(Rank rank, Suit suit) {
		this.rank = rank ;
		this.suit = suit ;
	}
	
	public String toString () {
		return (rank + " of " + suit) ;
	}
//
	public int compareTo(Object o) {
		Card otherCard = (Card) o ;
		return this.rank.compareTo(otherCard.rank) ;
	}
}
