import static org.hamcrest.CoreMatchers.*;
import static org.junit.matchers.JUnitMatchers.* ;
import org.junit.Test ;
import static org.junit.Assert.* ;
import java.util.* ;

public class ShitheadGameTest {

	@Test
	public void newGameContainsSpecifiedPlayers() {
		int numPlayers = 3 ;
		int numCardsPerHand = 4 ;
		
		List<String> playerNames = new ArrayList<String>() ;
		playerNames.add("James") ;
		playerNames.add("Bob") ;
		playerNames.add("Monkey") ;
		
		ShitheadGame game = new ShitheadGame(numPlayers, playerNames, numCardsPerHand) ;
		ShitheadGameDetails details = game.getGameDetails() ;

		assertThat("Correct number of players", details.getPlayers().size(), is(numPlayers)) ;
		assertThat("Correct cards per hand", details.getNumCardsPerHand(), is(numCardsPerHand)) ;
		
	}
	
	@Test
	public void dealResultsIn52CardsInGame() {
		int numPlayers = 3 ;
		int numCardsPerHand = 4 ;
		
		List<String> playerNames = new ArrayList<String>() ;
		playerNames.add("James") ;
		playerNames.add("Bob") ;
		playerNames.add("Monkey") ;
		
		ShitheadGame game = new ShitheadGame(numPlayers, playerNames, numCardsPerHand) ;
		game.deal();
		ShitheadGameDetails details = game.getGameDetails() ;
		
		int totalCardsInGame = details.getDeck().getSize() + 
								details.getPile().size() + 
								details.getBurnt().size() ;
		
		int playerCards = 0 ;
		for (Player player : details.getPlayers()) {
			playerCards += player.getHand().size() ;
			playerCards += player.getFaceUp().size() ;
			playerCards += player.getFaceDown().size() ;
		}
		
		
		totalCardsInGame += playerCards ;
		
		assertThat(totalCardsInGame, is(52)) ;
	}

	@Test
	public void dealLeavesCorrectAmountInDeck() {
		int numPlayers = 3 ;
		int numCardsPerHand = 4 ;
		
		List<String> playerNames = new ArrayList<String>() ;
		playerNames.add("James") ;
		playerNames.add("Bob") ;
		playerNames.add("Monkey") ;
		
		ShitheadGame game = new ShitheadGame(numPlayers, playerNames, numCardsPerHand) ;
		game.deal();
		ShitheadGameDetails details = game.getGameDetails() ;
		int numberOfCardsThatShouldBeLeft = 52 - ((numPlayers * numCardsPerHand) * 3) ;
		
		assertThat(details.getDeck().getSize(), is(numberOfCardsThatShouldBeLeft)) ;
	}
}
