import org.junit.Test ;
import static org.junit.Assert.* ;
import junit.framework.JUnit4TestAdapter ;

public class PlayerTest {
	
	@Test
	public void testPlayerDoesNotHaveCards() {
		Player james = new Player("James") ;
		assertFalse(james.hasCards()) ;
	}

	@Test
	public void testPlayerHasCardsWhenOnlyInHand() {
		Player james = new Player("James") ;
		james.hand.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		assertTrue(james.hasCards()) ;
	}

	@Test
	public void testPlayerHasCardsWhenOnlyInFaceUp() {
		Player james = new Player("James") ;
		james.faceUp.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		assertTrue(james.hasCards()) ;
	}

	@Test
	public void testPlayerHasCardsWhenOnlyInFaceDown() {
		Player james = new Player("James") ;
		james.faceDown.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		assertTrue(james.hasCards()) ;
	}

	@Test
	public void testPlayerHasCardsWhenAllHandsHaveCards() {
		Player james = new Player("James") ;
		james.hand.add(new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		james.faceUp.add(new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS)) ;
		james.faceDown.add(new Card(Card.Rank.FIVE, Card.Suit.CLUBS)) ;
		assertTrue(james.hasCards()) ;
	}

}
