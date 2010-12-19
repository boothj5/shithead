import java.util.* ;

public class Card {

	public enum Rank {TWO, THREE, FOUR, FIvE, SIX, SEVEN, EIGHT, NINE,TEN,
		JACK, QUEEN, KING, ACE} ;
	
	public enum Suit {HEARTS, SPADES, DIAMONDS, CLUBS} ;
		
	private Rank rank  ;
	private Suit suit ;

	public Card(Rank rank, Suit suit) {
		this.rank = rank ;
		this.suit = suit ;
	}
	
	public String toString () {
		return (rank + " of " + suit) ;
	}
}
