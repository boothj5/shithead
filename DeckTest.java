import java.util.* ;
import org.junit.Test ;
import static org.junit.Assert.* ;

public class DeckTest {

	@Test
	public void constructor() {
		Deck deck = new Deck() ;
		
		assertTrue(deck.cards.size() == 52) ;
		
		for (Card.Suit suit : Card.Suit.values())
			for (Card.Rank rank : Card.Rank.values())
				assertTrue(deck.cards.contains(new Card(rank, suit))) ;
	}
}
