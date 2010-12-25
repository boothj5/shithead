import java.util.* ;
import java.io.* ;

public class ShitheadGameEngine {
	
	ShitheadGame game ;
	Console c = System.console();

	public void setupGame() {
		String numPlayersString = c.readLine("How many players? ") ;
		int numPlayers = Integer.parseInt(numPlayersString) ;
		String numCardsString = c.readLine("How many cards each? ") ;
		int numCards = Integer.parseInt(numCardsString) ;

		List<String> playerNames = new ArrayList<String>() ;
		for (int i = 1 ; i <= numPlayers ; i++) {
			playerNames.add(c.readLine("Enter name for player " + i + ": ")) ;
		}

		game = new ShitheadGame(numPlayers, numCards, playerNames) ;
	}	
	
	
	public void swapCards() {
		Iterator<Player> playerIterator = game.players.iterator() ;
		while (playerIterator.hasNext()) {
			Player player = playerIterator.next() ;
			System.out.println() ;
			System.out.println(player.showHand()) ;
			System.out.println(player.showFaceUp()) ;
			System.out.println() ;

			String swap = c.readLine(player.name + 
								", do you want to swap cards (y/n) ? ") ;
			if ("y".equals(swap)) {
				swapCards(player) ;
			}
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
		Iterator<Player> playerIterator = game.players.iterator() ;
		while (playerIterator.hasNext()) {
			lowestCardsByPlayerIndex.add(
					Collections.min(playerIterator.next().hand, 
										new ShitheadCardComparator())) ;
		}
		
		// get the index of the player with the lowest card
		playerToLayIndex = lowestCardsByPlayerIndex.indexOf(
					Collections.min(lowestCardsByPlayerIndex, 
										new ShitheadCardComparator())) ; 
		
		cardsToPlay.add(lowestCardsByPlayerIndex.get(playerToLayIndex)) ;
	
		// iterate of the players cards for any of the same rank
		Iterator<Card> playersCardsIterator = 
			game.players.get(playerToLayIndex).hand.iterator() ;
		while (playersCardsIterator.hasNext()) {
			Card toCompare = playersCardsIterator.next() ;
			if ((cardsToPlay.get(0).compareTo(toCompare) == 0) && 
				(!cardsToPlay.get(0).equals(toCompare))) {
				cardsToPlay.add(toCompare) ;
			}
		}
		
		StringBuffer output = 
			new StringBuffer(game.players.get(playerToLayIndex).name + 
				" must play with : ") ;
		
		Iterator<Card> cardsToPlayIterator = cardsToPlay.iterator() ;
		while (cardsToPlayIterator.hasNext()) {
			Card toPlay = cardsToPlayIterator.next() ;
			output.append(toPlay + ", ") ;
		}
		
		game.playFromHand(playerToLayIndex, cardsToPlay) ;
		
		System.out.println(output.toString()) ;
	}
}
