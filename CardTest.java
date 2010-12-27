import org.junit.Test ;
import static org.junit.Assert.* ;

public class CardTest {

	Card card1, card2 ;
	
	@Test
	public void constructor() {
		Card.Rank rank = Card.Rank.THREE ;
		Card.Suit suit = Card.Suit.HEARTS ;

		Card newCard = new Card(rank, suit) ;
		
		assertTrue(rank.equals(newCard.rank) && suit.equals(newCard.suit)) ;
	}

	@Test
	public void testHigherRankFirstGreaterThan() {
		card1 = new Card (Card.Rank.THREE, Card.Suit.HEARTS) ;
		card2 = new Card (Card.Rank.TWO, Card.Suit.CLUBS) ;
		assertTrue(card1.compareTo(card2) > 0) ;

		card1 = new Card (Card.Rank.SEVEN, Card.Suit.HEARTS) ;
		card2 = new Card (Card.Rank.TWO, Card.Suit.CLUBS) ;
		assertTrue(card1.compareTo(card2) > 0) ;

		card1 = new Card (Card.Rank.ACE, Card.Suit.HEARTS) ;
		card2 = new Card (Card.Rank.TEN, Card.Suit.CLUBS) ;
		assertTrue(card1.compareTo(card2) > 0) ;
	}
	
	@Test
	public void testHigherRankSecondLessThan() {
		card1 = new Card (Card.Rank.TWO, Card.Suit.HEARTS) ;
		card2 = new Card (Card.Rank.THREE, Card.Suit.CLUBS) ;
		assertTrue(card1.compareTo(card2) < 0) ;

		card1 = new Card (Card.Rank.TWO, Card.Suit.HEARTS) ;
		card2 = new Card (Card.Rank.SEVEN, Card.Suit.CLUBS) ;
		assertTrue(card1.compareTo(card2) < 0) ;

		card1 = new Card (Card.Rank.TEN, Card.Suit.HEARTS) ;
		card2 = new Card (Card.Rank.ACE, Card.Suit.CLUBS) ;
		assertTrue(card1.compareTo(card2) < 0) ;
	}

	@Test
	public void testEqualRank() {
		card1 = new Card (Card.Rank.TWO, Card.Suit.HEARTS) ;
		card2 = new Card (Card.Rank.TWO, Card.Suit.CLUBS) ;
		assertTrue(card1.compareTo(card2) == 0) ;

		card1 = new Card (Card.Rank.SEVEN, Card.Suit.HEARTS) ;
		card2 = new Card (Card.Rank.SEVEN, Card.Suit.CLUBS) ;
		assertTrue(card1.compareTo(card2) == 0) ;

		card1 = new Card (Card.Rank.ACE, Card.Suit.HEARTS) ;
		card2 = new Card (Card.Rank.ACE, Card.Suit.CLUBS) ;
		assertTrue(card1.compareTo(card2) == 0) ;
	}	
}
