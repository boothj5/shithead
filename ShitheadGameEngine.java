import java.util.* ;
import java.io.* ;

public class ShitheadGameEngine {
	
	ShitheadGame game ;
	Console c = System.console();

	private void clearScreen() {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n") ;
	}
	
	public void setupGame(boolean debug) {
		
		String numPlayersString = c.readLine("How many players? ") ;
		int numPlayers = Integer.parseInt(numPlayersString) ;
		String numCardsString = c.readLine("How many cards each? ") ;
		int numCards = Integer.parseInt(numCardsString) ;

		List<String> playerNames = new ArrayList<String>() ;
		for (int i = 1 ; i <= numPlayers ; i++) 
			playerNames.add(c.readLine("Enter name for player " + i + ": ")) ;

		game = new ShitheadGame(numPlayers, numCards, playerNames, debug) ;
	}	
	
	
	public void swapCards() {
		for (Player player : game.players) {
			clearScreen() ;
			System.out.println() ;
			System.out.println(player.showHand()) ;
			System.out.println(player.showFaceUp()) ;
			System.out.println() ;

			String swap = c.readLine(player.name + 
								", do you want to swap cards (y/n) ? ") ;
			if ("y".equals(swap)) 
				swapCards(player) ;
		}
	}		
	
	private void swapCards(Player player) {
	    boolean keepSwapping = true ;
	    while (keepSwapping) {
	    	int cardFromHand = Integer.parseInt(
	    		c.readLine("Which card from your" + 
				" hand do you want to swap (1-" + game.numCards + ") ? ")) ;
		
			int cardFromPile = Integer.parseInt(
				c.readLine("Which card from the " + 
					"pile do you want to swap (1-" + game.numCards + ") ? ")) ;
		
			game.swap(player.hand, player.faceUp, cardFromHand, cardFromPile) ;

			clearScreen() ;
			System.out.println() ;
			System.out.println(player.showHand()) ;
			System.out.println(player.showFaceUp()) ;
			System.out.println() ;

			String swap = c.readLine(player.name + 
								", do you want to swap more cards (y/n) ? ") ;
		
			keepSwapping = ("y".equals(swap)) ;
		}
		
	}
	
	public void firstMove() {
		int playerToLayIndex ;
		List<Card> lowestCardsByPlayerIndex = new ArrayList<Card>() ;
		List<Card> cardsToPlay = new ArrayList<Card>() ;
		
		System.out.println() ;
		System.out.println("Looking for first player") ;
		System.out.println() ;
		
		// add lowest card of each player to a list
		for (Player player : game.players) 
			lowestCardsByPlayerIndex.add(Collections.min(player.hand, new ShitheadCardComparator())) ;
		
		// get the index of the player with the lowest card
		playerToLayIndex = lowestCardsByPlayerIndex.indexOf(
					Collections.min(lowestCardsByPlayerIndex, 
										new ShitheadCardComparator())) ; 
		
		cardsToPlay.add(lowestCardsByPlayerIndex.get(playerToLayIndex)) ;
	
		// iterate of the players cards for any of the same rank
		for (Card toCompare : game.players.get(playerToLayIndex).hand)
			if ((cardsToPlay.get(0).compareTo(toCompare) == 0) && 
								(!cardsToPlay.get(0).equals(toCompare))) 
				cardsToPlay.add(toCompare) ;
		
		StringBuffer output = 
			new StringBuffer(game.players.get(playerToLayIndex).name + 
				" must play with : ") ;
		
		for (Card toPlay : cardsToPlay) 
			output.append(toPlay + ", ") ;
		
		output.append("\n") ;
		
		game.playFromHand(playerToLayIndex, cardsToPlay) ;
		
		System.out.println(output.toString()) ;
	}
	
	public boolean nextMove() {
		game.moveToNextPlayer() ;
		
		clearScreen() ;
		System.out.println("-----------------------------------------") ;
		System.out.println("PLAYER:\t" + game.players.get(game.currentPlayer).name) ;
		System.out.println("-----------------------------------------") ;

		System.out.println(game.showPile()) ;
		System.out.println(game.deck.cards.size() + " remaing on deck") ;
		System.out.println() ;		
		System.out.println(game.burnt.size() + " burnt") ;
		System.out.println() ;		

		System.out.println(game.players.get(game.currentPlayer).showHand()) ;
		System.out.println(game.players.get(game.currentPlayer).showFaceUp()) ;
		System.out.println(game.players.get(game.currentPlayer).showFaceDown(false)) ;

	    System.out.println() ;
	    
		boolean canPlay = game.canPlay(game.players.get(game.currentPlayer)) ;
		
		if (canPlay) {
		
			int cardToPlay = 
				(Integer.parseInt(c.readLine("Which card do you want to play? (1 - " + 
					game.players.get(game.currentPlayer).hand.size() + ") :"))) -1 ;
				
			boolean validMove = game.checkValidMove(game.players.get(game.currentPlayer).hand.get(cardToPlay)) ;
			
			while (!validMove) {
				System.out.println("YOU CAN'T DO THAT!!") ;
				cardToPlay = 
					(Integer.parseInt(c.readLine("Which card do you want to play? (1 - " + 
						game.players.get(game.currentPlayer).hand.size() + ") :"))) -1 ;			
				validMove = game.checkValidMove(game.players.get(game.currentPlayer).hand.get(cardToPlay)) ;
			}
		
			List<Card> cardsToPlay = new ArrayList<Card>() ;
			cardsToPlay.add(game.players.get(game.currentPlayer).hand.get(cardToPlay)) ;

			// iterate of the players cards for any of the same rank
			for (Card toCompare : game.players.get(game.currentPlayer).hand)
				if ((cardsToPlay.get(0).compareTo(toCompare) == 0) && 
									(!cardsToPlay.get(0).equals(toCompare)))  {
					String add = c.readLine("Do you want to add the " + toCompare + "?") ;
					if ("y".equals(add)) 			
						cardsToPlay.add(toCompare) ;
				}
			
			game.playFromHand(game.currentPlayer, cardsToPlay) ;
		}
		else { // cannot play
			//FAIL!
			c.readLine("!!!! UH OH, YOU GOTTA PICK UP !!!! (Press enter):") ;
			game.pickupPile(game.currentPlayer) ;
		}
		return true ;
	}
}
