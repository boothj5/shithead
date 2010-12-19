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
		
		Random generator = new Random() ;
		
		Card card1 = deck.cards.get(generator.nextInt(52)) ;
		Card card2 = deck.cards.get(generator.nextInt(52)) ;

		String outcome ;
		if (card1.compareTo(card2) < 0) outcome = " loses to " ;
		else if (card1.compareTo(card2) > 0) outcome = " beats " ;
		else outcome = " draws with " ;
		
		System.out.println(card1 + outcome + card2) ;
		
	}

	

}
