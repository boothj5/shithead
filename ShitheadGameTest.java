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

}
