import org.junit.Test ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.card.ShitheadCardComparator;

import static org.junit.Assert.* ;

//import Card ;
//import ShitheadCardComparator ;

public class ShitheadCardComparatorTest {

	Card card1, card2 ;
	boolean result ;
	
	@Test
	public void specialCardsEqual() {
		card1 = new Card(Card.Rank.TWO, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 0 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.TWO, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 0 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.SEVEN, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 0 ;
		assertTrue(result) ;
	}

	@Test
	public void sameRankNormalEqual() {
		card1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 0 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 0 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.KING,  Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 0 ;
		assertTrue(result) ;
	}

	@Test
	public void sameRankSpecialEqual() {
		card1 = new Card(Card.Rank.TWO, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 0 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.SEVEN, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 0 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.TEN,  Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 0 ;
		assertTrue(result) ;
	}

	
	@Test
	public void specialCard1NormalCard2SpecialWins() {
		card1 = new Card(Card.Rank.TWO, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.SEVEN, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 1 ;
		assertTrue(result) ;
	}

	@Test
	public void normalCard1SpecialCard2SpecialWins() {
		card1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == -1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.KING, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == -1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.EIGHT, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == -1 ;
		assertTrue(result) ;
	}
	
	@Test
	public void highestNormalFirstWins() {
		card1 = new Card(Card.Rank.FOUR, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.FIVE, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.NINE, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.QUEEN, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == 1 ;
		assertTrue(result) ;
	}
	
	@Test
	public void highestNormalSecondWins() {
		card1 = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == -1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.FOUR, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == -1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.EIGHT, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == -1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.JACK, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == -1 ;
		assertTrue(result) ;

		card1 = new Card(Card.Rank.KING, Card.Suit.HEARTS) ;
		card2 = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		result = (new ShitheadCardComparator().compare(card1, card2)) == -1 ;
		assertTrue(result) ;
	}	
}
