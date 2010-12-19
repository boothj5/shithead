import java.util.* ;


public class ShitheadGame {
	
	public List<Player> players = new ArrayList<Player>() ;
	private Deck deck = new Deck() ;
	private int numPlayers ;
	private int numCards ;
	
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
	
	public String toString() {

		StringBuffer output = new StringBuffer("---- GAME INFO ----\n") ;
		
		output.append("\nNumber of players : " + numPlayers + "\n") ;
		output.append("Number of cards each : " + numCards + "\n") ;
		
	    Iterator<Player> playerIterator = players.iterator() ;
		while (playerIterator.hasNext()) {
			Player player = playerIterator.next() ;
			output.append("Player : " + player.name + "\n") ;
			output.append("\tFace down = ") ;
			for (int i = 0 ; i < numCards ; i++ ) {
				output.append(player.faceDown.get(i) + ", ") ;
			}
			output.append("\n") ;
			output.append("\tFace up = ") ;
			for (int i = 0 ; i < numCards ; i++ ) {
				output.append(player.faceUp.get(i) + ", ") ;
			}
			output.append("\n") ;
			output.append("\tHand = ") ;
			for (int i = 0 ; i < numCards ; i++ ) {
				output.append(player.hand.get(i) + ", ") ;
			}
			output.append("\n\n") ;
		}
		
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
