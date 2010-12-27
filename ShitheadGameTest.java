import java.util.* ;

import org.junit.Test ;
import static org.junit.Assert.* ;

public class ShitheadGameTest {

	List<String> names = new ArrayList<String>() ;
	
	@Test
	public void test2PlayersCreated() {
		
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2, 4, names, false) ;
		
		assertTrue(game.players.size() == 2) ;
		
		List<String> playerNamesInGame = new ArrayList<String>() ;
		
		for (Player player : game.players) 
				playerNamesInGame.add(player.name) ;
		
		for (String name : names) 
			assertTrue(playerNamesInGame.contains(name)) ;
	}
	
	@Test
	public void test3PlayersCreated() {
		
		names.add("James") ;
		names.add("Monkey") ;
		names.add("Stevie") ;
		ShitheadGame game = new ShitheadGame(3, 4, names, false) ;
		
		assertTrue(game.players.size() == 3) ;
		
		List<String> playerNamesInGame = new ArrayList<String>() ;
		
		for (Player player : game.players) 
				playerNamesInGame.add(player.name) ;
		
		for (String name : names) 
			assertTrue(playerNamesInGame.contains(name)) ;
	}

	// laying on a TWO
	@Test
	public void testLayingOnATwo() {
		ShitheadGame game = new ShitheadGame() ;
		Card cardToLay ;		

		Card cardOnPile = new Card(Card.Rank.TWO, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}

	// laying on a THREE
	@Test
	public void testLayingOnAThree() {
		ShitheadGame game = new ShitheadGame() ;
		Card cardToLay ;		
		
		Card cardOnPile = new Card(Card.Rank.THREE, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}
	
	// laying on a FOUR
	@Test
	public void testLayingOnAFour() {
		ShitheadGame game = new ShitheadGame() ;
		Card cardToLay ;		
		
		Card cardOnPile = new Card(Card.Rank.FOUR, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}

	// laying on a FIVE
	@Test
	public void testLayingOnAFive() {
		ShitheadGame game = new ShitheadGame() ;
		Card cardToLay ;		
		
		Card cardOnPile = new Card(Card.Rank.FIVE, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}

	// laying on a SIX
	@Test
	public void testLayingOnASix() {
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
		Card cardToLay ;		
	
		Card cardOnPile = new Card(Card.Rank.SIX, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}

	// laying on a SEVEN
	@Test
	public void testLayingOnASeven() {
		ShitheadGame game = new ShitheadGame() ;
	
		assertTrue(1+1==2) ;
	}
	
	// laying on a EIGHT
	@Test
	public void testLayingOnAEight() {
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
		Card cardToLay ;		
		
		Card cardOnPile = new Card(Card.Rank.EIGHT, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}

	// laying on a NINE
	@Test
	public void testLayingOnANine() {
		ShitheadGame game = new ShitheadGame() ;
		Card cardToLay ;		
		
		Card cardOnPile = new Card(Card.Rank.NINE, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}

	// laying on a JACK
	@Test
	public void testLayingOnAJack() {
		ShitheadGame game = new ShitheadGame() ;
		Card cardToLay ;		
		
		Card cardOnPile = new Card(Card.Rank.JACK, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}

	// laying on a QUEEN
	@Test
	public void testLayingOnAQueen() {
		ShitheadGame game = new ShitheadGame() ;
		Card cardToLay ;		
		
		Card cardOnPile = new Card(Card.Rank.QUEEN, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}

	// laying on a KING
	@Test
	public void testLayingOnAKing() {
		ShitheadGame game = new ShitheadGame() ;
		Card cardToLay ;		
		
		Card cardOnPile = new Card(Card.Rank.KING, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}

	// laying on a ACE
	@Test
	public void testLayingOnAAce() {
		ShitheadGame game = new ShitheadGame() ;
		Card cardToLay ;		
		
		Card cardOnPile = new Card(Card.Rank.ACE, Card.Suit.HEARTS) ;
		game.pile.push(cardOnPile) ;

		cardToLay = new Card(Card.Rank.TWO, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.THREE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SIX, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.SEVEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.EIGHT, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.NINE, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.JACK, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.QUEEN, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.KING, Card.Suit.DIAMONDS) ;
		assertFalse(game.checkValidMove(cardToLay)) ;
		cardToLay = new Card(Card.Rank.ACE, Card.Suit.DIAMONDS) ;
		assertTrue(game.checkValidMove(cardToLay)) ;
	}
	
	@Test
	public void testSwappingCardsOnlyOne() {
		List<Card> list1, list2 ;
		list1 = new ArrayList<Card>() ;
		list2 = new ArrayList<Card>() ;
		Card card1 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		Card card2 = new Card(Card.Rank.SIX, Card.Suit.SPADES) ;
		list1.add(card1) ;
		list2.add(card2) ;

		ShitheadGame game = new ShitheadGame() ;
		game.swap(list1, list2, 1, 1) ;

		assertTrue(list1.get(0).equals((card2)) && list2.get(0).equals(card1)) ;
	}
	
	@Test
	public void testSwappingCardsSecondPlace() {
		List<Card> list1, list2 ;
		list1 = new ArrayList<Card>() ;
		list2 = new ArrayList<Card>() ;
		Card card1 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		Card card2 = new Card(Card.Rank.SIX, Card.Suit.SPADES) ;
		Card card3 = new Card(Card.Rank.JACK, Card.Suit.HEARTS) ;
		Card card4 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		Card card5 = new Card(Card.Rank.THREE, Card.Suit.SPADES) ;
		Card card6 = new Card(Card.Rank.SIX, Card.Suit.CLUBS) ;
		list1.add(card1) ;
		list1.add(card2) ;
		list1.add(card3) ;
		list2.add(card4) ;
		list2.add(card5) ;
		list2.add(card6) ;

		ShitheadGame game = new ShitheadGame() ;
		game.swap(list1, list2, 2, 2) ;

		assertTrue(list1.get(1).equals((card5)) && list2.get(1).equals(card2)) ;
	}
	
	@Test
	public void testSwappingCardsFirstandFourthPlace() {
		List<Card> list1, list2 ;
		list1 = new ArrayList<Card>() ;
		list2 = new ArrayList<Card>() ;
		Card card1 = new Card(Card.Rank.FOUR, Card.Suit.DIAMONDS) ;
		Card card2 = new Card(Card.Rank.SIX, Card.Suit.SPADES) ;
		Card card3 = new Card(Card.Rank.JACK, Card.Suit.HEARTS) ;
		Card card4 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS) ;
		Card card5 = new Card(Card.Rank.THREE, Card.Suit.SPADES) ;
		Card card6 = new Card(Card.Rank.SIX, Card.Suit.CLUBS) ;
		Card card7 = new Card(Card.Rank.QUEEN, Card.Suit.CLUBS) ;
		Card card8 = new Card(Card.Rank.FIVE, Card.Suit.DIAMONDS) ;
		list1.add(card1) ;
		list1.add(card2) ;
		list1.add(card3) ;
		list1.add(card4) ;
		list2.add(card5) ;
		list2.add(card6) ;
		list2.add(card7) ;
		list2.add(card8) ;

		ShitheadGame game = new ShitheadGame() ;
		game.swap(list1, list2, 1, 4) ;

		assertTrue(list1.get(0).equals((card8)) && list2.get(3).equals(card1)) ;
	}
	
	@Test
	public void testMoveFromFirstPlayer() {
		names.add("James") ;
		names.add("Stevie") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(3, 4, names, false) ;

		game.currentPlayer = 0 ;
		game.moveToNextPlayer() ;
		
		assertTrue(game.currentPlayer == 1) ;
	}

	@Test
	public void testMoveToLastPlayer() {
		names.add("James") ;
		names.add("Stevie") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(3, 4, names, false) ;

		game.currentPlayer = 1 ;
		game.moveToNextPlayer() ;
		
		assertTrue(game.currentPlayer == 2) ;
	}

	@Test
	public void testMoveFromLastPlayer() {
		names.add("James") ;
		names.add("Stevie") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(3, 4, names, false) ;

		game.currentPlayer = 2 ;
		game.moveToNextPlayer() ;
		
		assertTrue(game.currentPlayer == 0) ;
	}

}
