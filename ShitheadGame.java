import java.util.* ;

public class ShitheadGame {
	private List<Player> players = new ArrayList<Player>() ;

	private Deck deck = new Deck() ;
	private int numPlayers ;
	private int numCardsPerHand ;

	private int currentPlayer ;

	private Stack<Card> pile = new Stack<Card>() ;
	private List<Card> burnt = new ArrayList<Card>() ;

	public static final EnumSet<Card.Rank> layOnAnythingRanks = 
		EnumSet.<Card.Rank>of(Card.Rank.TWO, Card.Rank.SEVEN, Card.Rank.TEN) ;
	public static final EnumSet<Card.Rank> normalRanks = 
		EnumSet.complementOf(layOnAnythingRanks) ;

	public  static final Card.Rank invisibleRank = Card.Rank.SEVEN ;
	public  static final Card.Rank missTurnRank = Card.Rank.EIGHT ;
	public  static final Card.Rank burnRank = Card.Rank.TEN ;	
	
	public ShitheadGame(int numPlayers, List<String> playerNames, List<String> playerTypes, 
						int cardsPerHand) throws Exception {
		this.numPlayers = numPlayers ;
		this.numCardsPerHand = cardsPerHand ;

		for (int i = 0 ; i < numPlayers ; i++) {
			Player player = PlayerFactory.createPlayer(playerTypes.get(i), playerNames.get(i), numCardsPerHand) ;
			players.add(player) ;
		}
	}
	
	public void deal() {
		deck.shuffle() ;

		Iterator<Card> deckIterator = deck.cards.iterator() ;

		for (int i = 0 ; i < numCardsPerHand ; i++) 
			for (int j = 0 ; j < numPlayers ; j++) 
				players.get(j).deal(Player.Hand.FACEDOWN, deckIterator.next());
		for (int i = 0 ; i < numCardsPerHand ; i++) 
			for (int j = 0 ; j < numPlayers ; j++) 
				players.get(j).deal(Player.Hand.FACEUP, deckIterator.next());
		for (int i = 0 ; i < numCardsPerHand ; i++) 
			for (int j = 0 ; j < numPlayers ; j++)
				players.get(j).deal(Player.Hand.HAND, deckIterator.next());

		// remove the dealt cards from the pack
		int totalToRemove = (numCardsPerHand * 3) * numPlayers ;
		for (int i = 0 ; i < totalToRemove ; i++) 
			deck.cards.remove(0) ;
		
		currentPlayer = 0 ;
	}
	
	public ShitheadGameDetails getGameDetails() {
		ShitheadGameDetails details = new ShitheadGameDetails(players, deck, numPlayers, 
				numCardsPerHand, currentPlayer, pile, burnt) ;
		
		return details ;
	}
}
