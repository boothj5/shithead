import java.io.Console ;
import java.util.* ;

public class ShitheadGameEngine {

	ShitheadGame game ;
	Console c = System.console();
	
	public void playShithead() {
		init() ;
		deal() ;
		swap() ;
		firstMove() ;
		play() ;
		end() ;
	}
	
	private void init() {
		clearScreen() ;
		
		System.out.println("Welcome to JavaHead!") ;
		System.out.println() ;

		int numPlayers = Integer.parseInt(c.readLine("How many players? ")) ;
		int numCards = Integer.parseInt(c.readLine("How many cards each? ")) ;
		
		List<String> playerNames = new ArrayList<String>() ;
		for (int i = 1 ; i <= numPlayers ; i++) 
			playerNames.add(c.readLine("Enter name for player " + i + ": ")) ;

		game = new ShitheadGame(numPlayers, playerNames, numCards) ;		
	}
	
	private void deal() {
		game.deal() ;
		showGame();		
	}

	private void swap() {
		
	}
	
	private void firstMove() {
		
	}
	
	private void play() {
		
	}
	
	private void end() {
		
	}

	private void showGame() {
		ShitheadGameDetails details = game.getGameDetails() ;

		clearScreen() ;
		
		// show pile
	    int pileRemaining = details.getPile().size() ;
		System.out.println(pileRemaining + " on pile:") ;

		for (int i = pileRemaining - 1 ; i >= 0 ; i--) {
			Card card = details.getPile().get(i) ;
			if (card.equals(details.getPile().peek())) 
				System.out.println("\t(*)" + card.toString()) ;
			else
				System.out.println("\t" + card.toString()) ;
		}
		
		// deck left
		System.out.println(details.getDeck().getSize() + " remaing on deck") ;
		System.out.println() ;		
		
		// burnt
		System.out.println(details.getBurnt().size() + " burnt") ;
		System.out.println() ;		
		
		for (Player player : details.getPlayers()) {
			// player name
			System.out.println("-----------------------------------------") ;
			System.out.println("PLAYER:\t" + player.getName()) ;
			System.out.println("-----------------------------------------") ;
	
			// player hand
			if (details.isCurrentPlayer(player)) {
				System.out.print("HAND:    ") ;
				for (Card card : player.getHand()) { 
					System.out.print(card + ", ") ;
				}
				System.out.println() ;
			}
			else {
				System.out.print("HAND:    ") ;
				System.out.println(player.getHand().size() + " cards."); 
			}
				
			// player faceup
			System.out.print("FACE UP: ") ;
			for (Card card : player.getFaceUp()) {
				System.out.print(card + ", ") ;
			}			
			System.out.println() ;
			
			// player facedown
			System.out.print("FACE UP: ") ;
			for (Card card : player.getFaceDown()) {
				System.out.print("****, ") ;
			}			
			System.out.println() ;
			System.out.println() ;
		}
	}

	
	private void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
	}	
	
}
