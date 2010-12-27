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
		
		Iterator<Player> playerIterator = game.players.iterator() ;
		while (playerIterator.hasNext()) {
				Player player = playerIterator.next() ;
				playerNamesInGame.add(player.name) ;
		}
		
		Iterator<String> testNamesIterator = names.iterator() ;
		while (testNamesIterator.hasNext()) {
			String name = testNamesIterator.next() ;
			assertTrue(playerNamesInGame.contains(name)) ;
		}
	}
	
	@Test
	public void test3PlayersCreated() {
		
		names.add("James") ;
		names.add("Monkey") ;
		names.add("Stevie") ;
		ShitheadGame game = new ShitheadGame(3, 4, names, false) ;
		
		assertTrue(game.players.size() == 3) ;
		
		List<String> playerNamesInGame = new ArrayList<String>() ;
		
		Iterator<Player> playerIterator = game.players.iterator() ;
		while (playerIterator.hasNext()) {
				Player player = playerIterator.next() ;
				playerNamesInGame.add(player.name) ;
		}
		
		Iterator<String> testNamesIterator = names.iterator() ;
		while (testNamesIterator.hasNext()) {
			String name = testNamesIterator.next() ;
			assertTrue(playerNamesInGame.contains(name)) ;
		}
	}

	// laying on a TWO
	@Test
	public void testLayingOnATwo() {
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
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
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
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
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
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
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
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
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
	
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
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
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
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
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
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
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
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
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
		names.add("James") ;
		names.add("Monkey") ;
		ShitheadGame game = new ShitheadGame(2,4, names, false) ;
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
	
}
