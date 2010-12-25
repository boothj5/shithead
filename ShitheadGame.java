import java.util.* ;
import java.io.* ;

public class ShitheadGame {
	
	public final boolean debug = true ;
	public List<Player> players = new ArrayList<Player>() ;
	
	private Deck deck = new Deck() ;
	public int numPlayers ;
	public int numCards ;
	
	private Stack<Card> pile = new Stack<Card>() ;
	private List<Card> burnt = new ArrayList<Card>() ;
	
	public static final EnumSet<Card.Rank> layOnAnythingRanks = 
		EnumSet.<Card.Rank>of(Card.Rank.TWO, Card.Rank.SEVEN, Card.Rank.TEN) ;
	public static final EnumSet<Card.Rank> normalRanks = 
		EnumSet.complementOf(layOnAnythingRanks) ;

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
	
	public void swap(List<Card> hand1, List<Card> hand2, 
										int card1, int card2) {
		Card savedHand1 = hand1.get(card1-1) ;
		Card savedHand2 = hand2.get(card2-1) ;
		hand2.set((card2-1), savedHand1) ;
		hand1.set((card1-1), savedHand2) ;
	}
	
	
	public void playFromHand(int player, List<Card> toPlay) {
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
	
	public String toString() {

		StringBuffer output = new StringBuffer("---- GAME INFO ----\n") ;
		
		output.append("\nRules:\n") ;
		output.append("Lay on anything ranks = " + layOnAnythingRanks + "\n") ;
		output.append("Normal ranks = " + normalRanks + "\n") ;
		
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
			output.append("\n") ;
			output.append("\tLowest card in hand = " + 
							Collections.min(player.hand, 
										new ShitheadCardComparator()))  ;
			output.append("\n\n") ;
		}
		
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
