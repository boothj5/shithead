import java.util.* ;
import java.io.* ;

public class Shithead {
	
	public static void main(String[] args) {

		boolean debug = false ;
		Console c = System.console();
		
		String numPlayersString = c.readLine("How many players? ") ;
		int numPlayers = Integer.parseInt(numPlayersString) ;
		String numCardsString = c.readLine("How many cards each? ") ;
		int numCards = Integer.parseInt(numCardsString) ;

		List<String> playerNames = new ArrayList<String>() ;
		for (int i = 1 ; i <= numPlayers ; i++) {
			playerNames.add(c.readLine("Enter name for player " + i + ": ")) ;
		}

		ShitheadGame game = new ShitheadGame(numPlayers, numCards, 
												playerNames) ;
		
		if (debug) System.out.println(game.toString()) ;

		game.swapCards() ;
		
		game.firstMove() ;
		
		if (debug) System.out.println(game.toString()) ;
		
	}
}
