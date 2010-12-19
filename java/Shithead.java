import java.util.* ;

public class Shithead {
	
	public static void main(String[] args) {

		Deck deck = new Deck() ;
		System.out.println("Size = " + deck.cards.size() ) ;
		System.out.println() ;
		
		Iterator<Card> deckIterator = deck.cards.iterator();
	
		while ( deckIterator.hasNext() ){
			System.out.println( deckIterator.next() );
		}	
		
		System.out.println() ;
		deck.shuffle() ;

		deckIterator = deck.cards.iterator() ;
		while ( deckIterator.hasNext() ){
			System.out.println( deckIterator.next() );
		}

		System.out.println() ;
	}
}
