import java.util.* ;

public class ShitheadGame {
	
	public boolean debug = false ;
	public List<Player> players = new ArrayList<Player>() ;
	
	public Deck deck = new Deck() ;
	public int numPlayers ;
	public int numCards ;
	
	public int currentPlayer ;
	
	public Stack<Card> pile = new Stack<Card>() ;
	public List<Card> burnt = new ArrayList<Card>() ;

	public static final EnumSet<Card.Rank> layOnAnythingRanks = 
		EnumSet.<Card.Rank>of(Card.Rank.TWO, Card.Rank.SEVEN, Card.Rank.TEN) ;
	public static final EnumSet<Card.Rank> normalRanks = 
		EnumSet.complementOf(layOnAnythingRanks) ;

	public static final Card.Rank invisibleRank = Card.Rank.SEVEN ;
	public static final Card.Rank missTurnRank = Card.Rank.EIGHT ;
	public static final Card.Rank burnRank = Card.Rank.TEN ;	
	
	public ShitheadGame() {
		// do nothing
	}
	
	public ShitheadGame(int numPlayers, int numCards, 
							List<String> playerNames, boolean debug) {

		assert (totalCardsInGame() == 52) ;

		this.debug = debug ;
		this.numPlayers = numPlayers ;
		this.numCards = numCards ;
		deck.shuffle() ;

		// set player names
		for (int i = 0 ; i < numPlayers ; i++) {
			Player player = new Player(playerNames.get(i)) ;
			players.add(player) ;
		}

		// deal the cards
		Iterator<Card> deckIterator = deck.cards.iterator() ;

		for (int i = 0 ; i < numCards ; i++) 
			for (int j = 0 ; j < numPlayers ; j++) 
				players.get(j).faceDown.add(deckIterator.next()) ;
		for (int i = 0 ; i < numCards ; i++) 
			for (int j = 0 ; j < numPlayers ; j++) 
				players.get(j).faceUp.add(deckIterator.next()) ;
		for (int i = 0 ; i < numCards ; i++) 
			for (int j = 0 ; j < numPlayers ; j++)
				players.get(j).hand.add(deckIterator.next()) ;
		
		// remove the dealt cards from the pack
		int totalToRemove = (numCards * 3) * numPlayers ;
		for (int i = 0 ; i < totalToRemove ; i++) 
			deck.cards.remove(0) ;
		
		assert (totalCardsInGame() == 52) ;
	}
	
	public Player getShithead() {
		Player shithead = null ;
		
		for (Player player : players) {
			if (player.hasCards())
				shithead = player ;
		}
		
		return shithead ;
	}

	public void swap(List<Card> hand1, List<Card> hand2, 
										int card1, int card2) {
		assert (totalCardsInGame() == 52) ;

		Card savedHand1 = hand1.get(card1-1) ;
		Card savedHand2 = hand2.get(card2-1) ;
		hand2.set((card2-1), savedHand1) ;
		hand1.set((card1-1), savedHand2) ;

		assert (totalCardsInGame() == 52) ;
	}
	
	
	public void playFromHand(int player, List<Card> toPlay) {
		assert (totalCardsInGame() == 52) ;

		currentPlayer = player ;
		
		pile.addAll(toPlay) ;
		players.get(player).hand.removeAll(toPlay) ;
		
		for (int i = 0 ; i < toPlay.size() ; i++) {
			if (!deck.cards.isEmpty() && players.get(player).hand.size() < numCards) {
					Card pickup = deck.cards.get(0) ;
					players.get(player).hand.add(pickup) ;
					deck.cards.remove(0) ;
			}
		}
		
		// burn if required
		burnIfPossible() ;

		assert (totalCardsInGame() == 52) ;
	}
	
		
	public void playFromFaceUp(int player, List<Card> toPlay) {
		assert (totalCardsInGame() == 52) ;

		currentPlayer = player ;
		
		pile.addAll(toPlay) ;
		players.get(player).faceUp.removeAll(toPlay) ;
		
		for (int i = 0 ; i < toPlay.size() ; i++) {
			if (!deck.cards.isEmpty() && players.get(player).hand.size() < numCards) {
					Card pickup = deck.cards.get(0) ;
					players.get(player).hand.add(pickup) ;
					deck.cards.remove(0) ;
			}
		}
		
		// burn if required
		burnIfPossible() ;

		assert (totalCardsInGame() == 52) ;
	}

	public void playFromFaceDown(int player, List<Card> toPlay) {
		assert (totalCardsInGame() == 52) ;

		currentPlayer = player ;
		
		pile.addAll(toPlay) ;
		players.get(player).faceDown.removeAll(toPlay) ;
		
		for (int i = 0 ; i < toPlay.size() ; i++) {
			if (!deck.cards.isEmpty() && players.get(player).hand.size() < numCards) {
					Card pickup = deck.cards.get(0) ;
					players.get(player).hand.add(pickup) ;
					deck.cards.remove(0) ;
			}
		}
		
		// burn if required
		burnIfPossible() ;

		assert (totalCardsInGame() == 52) ;
	}


	
	public void burnIfPossible() {
		// burn card
		if ((!pile.empty()) && (pile.peek().rank.equals(burnRank))) {
			currentPlayer-- ;
			burnt.addAll(pile) ;
			pile.removeAllElements() ;
		}
		// four of a kind
		else if ((pile.size() >= 4) && 
				((pile.get(pile.size()-1).rank.equals(pile.get(pile.size()-2).rank)) && 
				  (pile.get(pile.size()-2).rank.equals(pile.get(pile.size()-3).rank)) &&
			  		(pile.get(pile.size()-3).rank.equals(pile.get(pile.size()-4).rank))) ) {
			currentPlayer-- ;
			burnt.addAll(pile) ;
			pile.removeAllElements() ;
		}
	}		

	public void pickupPile(int playerIndex) {
		assert (totalCardsInGame() == 52) ;

		players.get(playerIndex).hand.addAll(pile) ;
		pile.removeAllElements() ;

		assert (totalCardsInGame() == 52) ;
	}
	
	public void moveToNextPlayer() {
		currentPlayer ++ ;
		if (currentPlayer >= players.size())
			currentPlayer = 0 ;
		while (!players.get(currentPlayer).hasCards()) {
			currentPlayer++ ;
			if (currentPlayer >= players.size())
				currentPlayer = 0 ;
		}
	}

	public String showGame() {
		StringBuffer output = new StringBuffer("Hands:") ;
		output.append("\n------\n") ;
		
		for (Player player : players) 
			output.append(player.showHand()) ;
		
		return output.toString() ;
	}
	
	public boolean checkValidMove(Card cardToLay) {
		if (pile.empty()) 
			return true ;
		else if (invisibleRank.equals(pile.peek().rank)) {
			//look for first non invisible and check that
			Card testCard = pile.peek() ;
			for (int i = pile.size() -1 ; (i >=0 && (testCard.rank.equals(invisibleRank))) ; i-- ) {
				testCard = pile.get(i) ;
			}
			if (testCard.rank.equals(invisibleRank))
				return true ;
			else
				return checkValidMove(testCard, cardToLay) ;
		}
		else 
			return checkValidMove(pile.peek(), cardToLay) ;	
	}
	
	public boolean checkValidMove(Card onPile, Card toLay) {
		if (layOnAnythingRanks.contains(toLay.rank)) 
			return true ;
		else
			return (onPile.compareTo(toLay) <= 0);
	}		
	
	public String showPile() {
		StringBuffer output = new StringBuffer() ;
	    int pileRemaining = pile.size() ;
		output.append(pileRemaining + " on pile:\n") ;
	    
		for (int i = pileRemaining - 1 ; i >= 0 ; i--) {
			Card card = pile.get(i) ;
			if (card.equals(pile.peek())) 
				output.append("\t(*)" + card.toString() + "\n") ;
			else
				output.append("\t" + card.toString() + "\n") ;
		}
		
		return output.toString() ;
	}
	
	public boolean canPlayFromHand(Player player) {
		boolean canPlay = false ;
		
		Iterator<Card> cardIterator = player.hand.iterator() ;
		while (!canPlay && cardIterator.hasNext()) {
				canPlay = checkValidMove(cardIterator.next()) ;
		}
		
		return canPlay ;
	}

	public boolean canPlayFromFaceUp(Player player) {
		boolean canPlay = false ;
		
		Iterator<Card> cardIterator = player.faceUp.iterator() ;
		while (!canPlay && cardIterator.hasNext()) {
				canPlay = checkValidMove(cardIterator.next()) ;
		}
		
		return canPlay ;
	}		

	public String toString() {

		StringBuffer output = new StringBuffer("---- GAME INFO ----\n") ;
		
		output.append("\nRules:\n") ;
		output.append("Lay on anything ranks = " + layOnAnythingRanks + "\n") ;
		output.append("Normal ranks = " + normalRanks + "\n") ;
		
		output.append("\nNumber of players : " + numPlayers + "\n") ;
		output.append("Number of cards each : " + numCards + "\n") ;
		
	    for (Player player : players) {
			output.append("Player : " + player.name + "\n") ;
			output.append("\t" + player.showHand()) ;
			output.append("\n") ;
			output.append("\t" + player.showFaceUp()) ;
			output.append("\n") ;
			output.append("\t" + player.showFaceDown(true)) ;
			output.append("\n") ;
			output.append("\tLowest card in hand = " + 
							Collections.min(player.hand, 
										new ShitheadCardComparator()))  ;
			output.append("\n\n") ;
		}
		
		output.append(showPile()) ;		
		output.append("\n") ;
		
	    int burntRemaining = burnt.size() ;
		output.append(burntRemaining + " burnt:\n") ;
	    
		for (Card card : burnt) 
			output.append("\t" + card.toString() + "\n") ;
		
		output.append("\n") ;
		
	    int remaining = deck.cards.size() ;
		output.append(remaining + " cards remaining:\n") ;

		for (Card card : deck.cards) 
			output.append("\t" + card.toString() + "\n") ;
		
		output.append("\n---- END GAME INFO ----\n") ;
		
		return output.toString() ;
	}
	
	public int totalCardsInGame() {
		int pileCards = pile.size() + deck.cards.size() + burnt.size() ;
		
		int playerCards = 0 ;
		for (Player player : players) {
			playerCards += player.hand.size() ;
			playerCards += player.faceUp.size() ;
			playerCards += player.faceDown.size() ;
		}
		
		return pileCards + playerCards ;
		
	}
}
