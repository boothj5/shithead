import java.util.* ;
import java.io.* ;

public class ShitheadGame {
	
	public List<Player> players = new ArrayList<Player>() ;
	
	private Deck deck = new Deck() ;
	private int numPlayers ;
	private int numCards ;
	
	private List<Card> pile = new ArrayList<Card>() ;
	private List<Card> burnt = new ArrayList<Card>() ;
	
	private enum bestRanks {TWO, SEVEN, TEN, ACE} ;
	private enum normalRanks {THREE, FOUR, FIVE, SIX, EIGHT, NINE, JACK, QUEEN,
		KING} ;

	public ShitheadGame(int numPlayers, int numCards, 
							List<String> playerNames) {
		
		this.numPlayers = numPlayers ;
		this.numCards = numCards ;
		deck.shuffle() ;

		for (int i = 0 ; i < numPlayers ; i++) {
			Player player = new Player(playerNames.get(i)) ;
			players.add(player) ;
		}
		
		Iterator<Card> deckIterator = deck.cards.iterator() ;
		for (int i = 0 ; i < numCards ; i++) {
			for (int j = 0 ; j < numPlayers ; j++) {
				players.get(j).faceDown.add(deckIterator.next()) ;
			}
		}
		for (int i = 0 ; i < numCards ; i++) {
			for (int j = 0 ; j < numPlayers ; j++) {
				players.get(j).faceUp.add(deckIterator.next()) ;
			}
		}
		for (int i = 0 ; i < numCards ; i++) {
			for (int j = 0 ; j < numPlayers ; j++) {
				players.get(j).hand.add(deckIterator.next()) ;
			}
		}
		
		// remove the dealt cards from the pack
		int totalToRemove = (numCards * 3) * numPlayers ;
		for (int i = 0 ; i < totalToRemove ; i++) {
			deck.cards.remove(0) ;
		}
	}
	
	public void swapCards() {
	    Console c = System.console();
		
		Iterator<Player> playerIterator = players.iterator() ;
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
	    Console c = System.console();

	    boolean keepSwapping = true ;
	    while (keepSwapping) {
	    	int cardFromHand = Integer.parseInt(
	    		c.readLine("Which card from your" + 
				" hand do you want to swap (1-" + numCards + ") ? ")) ;
		
			int cardFromPile = Integer.parseInt(
				c.readLine("Which card from the " + 
					"pile do you want to swap (1-" + numCards + ") ? ")) ;
		
			swap(player.hand, player.faceUp, cardFromHand, cardFromPile) ;

			System.out.println() ;
			System.out.println(player.showHand()) ;
			System.out.println(player.showFaceUp()) ;
			System.out.println() ;

			String swap = c.readLine(player.name + 
								", do you want to swap more cards (y/n) ? ") ;
		
			keepSwapping = ("y".equals(swap)) ;
		}
		
	}
	
	private void swap(List<Card> hand1, List<Card> hand2, 
										int card1, int card2) {
		Card savedHand1 = hand1.get(card1-1) ;
		Card savedHand2 = hand2.get(card2-1) ;
		hand2.set((card2-1), savedHand1) ;
		hand1.set((card1-1), savedHand2) ;
	}
	
	public void firstMove() {
		Iterator<Player> playerIterator = players.iterator() ;
		
		while (playerIterator.hasNext()) {
			getPlayersLowest
			
			Player player = playerIterator.next() ;
			List<Card> handCopy = player.hand.clone() ;
			handCopy.sort()
			
		
	
	
	public String toString() {

		StringBuffer output = new StringBuffer("---- GAME INFO ----\n") ;
		
		output.append("\nNumber of players : " + numPlayers + "\n") ;
		output.append("Number of cards each : " + numCards + "\n") ;
		
	    Iterator<Player> playerIterator = players.iterator() ;
		while (playerIterator.hasNext()) {
			Player player = playerIterator.next() ;
			output.append("Player : " + player.name + "\n") ;
			output.append("\t" + player.showHand()) ;
			output.append("\n") ;
			output.append("\t" + player.showFaceUp()) ;
			output.append("\n") ;
			output.append("\t" + player.showFaceDown()) ;
			output.append("\n\n") ;
		}
		
	    Iterator<Card> pileIterator = pile.iterator() ;
	    int pileRemaining = pile.size() ;
		output.append(pileRemaining + " on pile:\n") ;
	    while (pileIterator.hasNext()) {
			Card card = pileIterator.next() ;
			output.append("\t" + card.toString() + "\n") ;
		}
		output.append("\n") ;
		
	    Iterator<Card> burntIterator = burnt.iterator() ;
	    int burntRemaining = burnt.size() ;
		output.append(burntRemaining + " burnt:\n") ;
	    while (burntIterator.hasNext()) {
			Card card = burntIterator.next() ;
			output.append("\t" + card.toString() + "\n") ;
		}
		output.append("\n") ;
		
	    Iterator<Card> deckIterator = deck.cards.iterator() ;
	    int remaining = deck.cards.size() ;
		output.append(remaining + " cards remaining:\n") ;
	    while (deckIterator.hasNext()) {
			Card card = deckIterator.next() ;
			output.append("\t" + card.toString() + "\n") ;
		}
		
		output.append("\n---- END GAME INFO ----\n") ;
		
		return output.toString() ;
	}
}
