import java.util.* ;
import java.io.* ;

public class Shithead {
	
	public static void main(String[] args) {

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
		
		
		System.out.println(game.toString()) ;
/*		
		Iterator<Player> playerIterator = game.players.iterator() ;
		while (playerIterator.hasNext()) {
			Player player = playerIterator.next() ;
			StringBuffer faceDown = new StringBuffer(player.name + 
														" face down = ") ;
			for (int i = 0 ; i < numCards ; i++ ) {
				faceDown.append(player.faceDown.get(i) + ", ") ;
			}
			
			StringBuffer faceUp = new StringBuffer(player.name + 
														" face up = ") ;
			for (int i = 0 ; i < numCards ; i++ ) {
				faceUp.append(player.faceUp.get(i) + ", ") ;
			}

			StringBuffer hand = new StringBuffer(player.name + 
														" hand = ") ;
			for (int i = 0 ; i < numCards ; i++ ) {
				hand.append(player.hand.get(i) + ", ") ;
			}

			System.out.println() ;
			System.out.println(faceDown.toString()) ;
			System.out.println(faceUp.toString()) ;
			System.out.println(hand.toString()) ;
		}
		
*/		
		
	}
}
