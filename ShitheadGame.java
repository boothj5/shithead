import java.util.* ;
import java.io.* ;

public class ShitheadGame {
	
	public List<Player> players = new ArrayList<Player>() ;
	private Deck deck = new Deck() ;
	private int numPlayers ;
	private int numCards ;
	
	private List<Card> pile = new ArrayList<Card>() ;
	private List<Card> burnt = new ArrayList<Card>() ;
	
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
			System.out.println("\n" + player.showHand()) ;
			System.out.println(player.showFaceUp() + "\n") ;
			String swap = c.readLine(player.name + 
									", do you want to swap cards (y/n) ? ") ;
			boolean swapMore = false ;
			if ("y".equals(swap)) {
				swapCards(player) ;
			}
		}
	}		
	
	public void swapCards(Player player) {
	    Console c = System.console();

		System.out.println("\n" + player.showHand()) ;
		System.out.println(player.showFaceUp() + "\n") ;

		int cardFromHand = Integer.parseInt(c.readLine("Which card from your" + 
								" hand do you want to swap (1-" + numCards + ") ? ")) ;
		
		int cardFromPile = Integer.parseInt(c.readLine("Which card from the " + 
								"pile do you want to swap (1-" + numCards + ") ? ")) ;
			
		Card savedFromHand = player.hand.get(cardFromHand-1) ;
		Card savedFromPile = player.faceUp.get(cardFromPile-1) ;
		
		player.faceUp.set((cardFromPile-1), savedFromHand) ;
		player.hand.set((cardFromHand-1), savedFromPile) ;

		System.out.println("\n" + player.showHand()) ;
		System.out.println(player.showFaceUp() + "\n") ;

	}
	
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
