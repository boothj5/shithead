import java.io.Console ;
import java.util.* ;

public class ShitheadGameEngine {

	ShitheadGame game ;
	Console c = System.console();
	
	public void playShithead() throws Exception {
		init() ;
		deal() ;
		swap() ;
		firstMove() ;
		play() ;
		end() ;
	}
	
	private void init() throws Exception {
		clearScreen() ;
		
		System.out.println("Welcome to JavaHead!") ;
		System.out.println() ;

		int numPlayers = Integer.parseInt(c.readLine("How many players? ")) ;
		int numCards = Integer.parseInt(c.readLine("How many cards each? ")) ;
		
		List<String> playerNames = new ArrayList<String>() ;
		List<String> playerTypes = new ArrayList<String>() ;
		String currentName ;
		for (int i = 1 ; i <= numPlayers ; i++) { 
			currentName = c.readLine("Enter name for player " + i + ": ") ;
			playerNames.add(currentName) ;
			showPlayerTypes() ;
			playerTypes.add(c.readLine("Player type for  " + currentName + ": ")) ;
		}
		game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCards) ;		
	}
	
	private void deal() {
		game.deal() ;
		showGame();
		
		c.readLine("Cards dealt, press enter to continue:") ;
	}

	private void swap() {
		ShitheadGameDetails details = game.getGameDetails() ;
		
		for (Player player : details.getPlayers()) {
			
			// ask player if they want to swap cards
			String swap = player.askSwapMore() ;
			
			// if we get a y or n, its a computer player
			if ("y".equals(swap)) {
				SwapResponse response = player.askSwapChoice() ;
				player.swapCards(response) ;
				
				// again and keep swapping until 'n'
				swap = player.askSwapMore() ;
				while ("y".equals(swap)) {
					response = player.askSwapChoice() ;
					player.swapCards(response) ;
					swap = player.askSwapMore() ;
				}
			}
			
			// if we got null, its a human and we need to interact
			else if (null == swap) {
				clearScreen() ;
				showPlayerName(details, player, false);

				System.out.println() ;

				showHand(details, player, false) ;
				showFaceUp(player) ;

				System.out.println() ;
				swap = c.readLine(player.getName() + 
								", do you want to swap cards (y/n) ? ") ;
				
				if ("y".equals(swap)) {
					boolean keepSwapping = true ;
		    
					while (keepSwapping) {
						int cardFromHand = Integer.parseInt(
								c.readLine("Which card from your" + 
										" hand do you want to swap (1-" + details.getNumCardsPerHand() + ") ? ")) ;

						int cardFromPile = Integer.parseInt(
								c.readLine("Which card from the " + 
										"pile do you want to swap (1-" + details.getNumCardsPerHand() + ") ? ")) ;

						SwapResponse response = new SwapResponse(cardFromHand-1, cardFromPile-1) ;
						player.swapCards(response) ;

						clearScreen() ;
						System.out.println() ;
						details = game.getGameDetails() ;
						showHand(details, player, false) ;
						showFaceUp(player) ;
						System.out.println() ;

						String swapAgain = c.readLine(player.getName() + 
									", do you want to swap more cards (y/n) ? ") ;

						keepSwapping = ("y".equals(swapAgain)) ;
					}
				}
			}
		}
		showGame() ;
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
			showPlayerName(details, player, true);
			showHand(details, player, true);
			showFaceUp(player);
			showFaceDown(player);

			System.out.println() ;
		}
	}

	private void showFaceDown(Player player) {
		// player face down
		System.out.print("FACE UP: ") ;
		for (Card card : player.getFaceDown()) {
			System.out.print("****, ") ;
		}			
		System.out.println() ;
	}

	private void showFaceUp(Player player) {
		// player face up
		System.out.print("FACE UP: ") ;
		for (Card card : player.getFaceUp()) {
			System.out.print(card + ", ") ;
		}			
		System.out.println() ;
	}

	private void showHand(ShitheadGameDetails details, Player player, boolean hideWhenNotCurrent) {
		if (hideWhenNotCurrent && !details.isCurrentPlayer(player)) {
			System.out.print("HAND:    ") ;
			System.out.println(player.getHand().size() + " cards."); 
		}
		else {
				System.out.print("HAND:    ") ;
				for (Card card : player.getHand()) { 
					System.out.print(card + ", ") ;
				}
				System.out.println() ;
			}
	}

	private void showPlayerName(ShitheadGameDetails details, Player player, boolean indicateCurrent) {
		String currentPlayer = "" ;
		if (indicateCurrent && (details.isCurrentPlayer(player))) 
			currentPlayer = "(*)" ;
		// player name
		System.out.println("-----------------------------------------") ;
		System.out.println("PLAYER" + currentPlayer + ":" + player.getName()) ;
		System.out.println("-----------------------------------------") ;
	}

	
	private void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
	}
	
	public void showPlayerTypes() {
		System.out.println("(h)uman  - Human player") ;
		System.out.println("(s)imple - A very simple computer player") ;
	}
	
}
