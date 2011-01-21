import org.junit.Test ;
import static org.junit.Assert.* ;

public class HumanPlayerTest {
	
	@Test
	public void testPlayerName() {
		HumanPlayer james = new HumanPlayer("James") ;
		assertTrue("james".equals(james.getName())) ;
	}
	
	@Test
	public void testNewPlayerDoesNotHaveCards() {
		HumanPlayer james = new HumanPlayer("James") ;
		assertFalse(james.hasCards()) ;
	}

	@Test
	public void testPlayerHasCardsWhenOnlyInHand() {
		HumanPlayer james = new HumanPlayer("James") ;
		james.deal(Player.Hand.HAND, new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		assertTrue(james.hasCards()) ;
	}

	@Test
	public void testPlayerHasCardsWhenOnlyInFaceUp() {
		HumanPlayer james = new HumanPlayer("James") ;
		james.deal(Player.Hand.FACEUP, new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		assertTrue(james.hasCards()) ;
	}

	@Test
	public void testPlayerHasCardsWhenOnlyInFaceDown() {
		HumanPlayer james = new HumanPlayer("James") ;
		james.deal(Player.Hand.FACEDOWN, new Card(Card.Rank.THREE, Card.Suit.HEARTS)) ;
		assertTrue(james.hasCards()) ;
	}
}
