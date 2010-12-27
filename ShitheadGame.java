import java.util.* ;
import java.io.* ;

public class ShitheadGame {
	
	public boolean debug = false ;
	public List<Player> players = new ArrayList<Player>() ;
	
	private Deck deck = new Deck() ;
	public int numPlayers ;
	public int numCards ;
	
	public int currentPlayer ;
	
	public Stack<Card> pile = new Stack<Card>() ;
	public List<Card> burnt = new ArrayList<Card>() ;
	
	public static final EnumSet<Card.Rank> layOnAnythingRanks = 
		EnumSet.<Card.Rank>of(Card.Rank.TWO, Card.Rank.SEVEN, Card.Rank.TEN) ;
	public static final EnumSet<Card.Rank> normalRanks = 
		EnumSet.complementOf(layOnAnythingRanks) ;

	public ShitheadGame(int numPlayers, int numCards, 
							List<String> playerNames, boolean debug) {
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
	}
	
	public void swap(List<Card> hand1, List<Card> hand2, 
										int card1, int card2) {
		Card savedHand1 = hand1.get(card1-1) ;
		Card savedHand2 = hand2.get(card2-1) ;
		hand2.set((card2-1), savedHand1) ;
		hand1.set((card1-1), savedHand2) ;
	}
	
	
	public void playFromHand(int player, List<Card> toPlay) {
		currentPlayer = player ;
		
		pile.addAll(toPlay) ;
		players.get(player).hand.removeAll(toPlay) ;
		
		for (int i = 0 ; i < toPlay.size() ; i++) {
			if (!deck.cards.isEmpty()) {
					Card pickup = deck.cards.get(0) ;
					players.get(player).hand.add(pickup) ;
					deck.cards.remove(0) ;
			}
		}	
	}
	
	public void moveToNextPlayer() {
		currentPlayer ++ ;
		if (currentPlayer >= players.size())
			currentPlayer = 0 ;
	}

	public String showGame() {
		StringBuffer output = new StringBuffer("Hands:") ;
		output.append("\n------\n") ;
		
		for (Player player : players) 
			output.append(player.showHand()) ;
		
		return output.toString() ;
	}
	
	public boolean checkValidMove(Card cardToLay) {
		Card onPile = pile.peek() ;

	    if (layOnAnythingRanks.contains(cardToLay.rank)) 
	    	return true ;
	    else 
	    	return (onPile.compareTo(cardToLay) <= 0);		
	}
	
	
	public String showPile() {
		StringBuffer output = new StringBuffer() ;
		Iterator<Card> pileIterator = pile.iterator() ;
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
}
