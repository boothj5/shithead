package com.boothj5.shithead.game;

import java.util.* ;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Deck;
import com.boothj5.shithead.game.card.Hand;
import com.boothj5.shithead.game.card.ShitheadCardComparator;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;

public final class ShitheadGame {
	final private List<Player> players = new ArrayList<Player>() ;
	final private Deck deck = new Deck() ;
    final private Stack<Card> pile = new Stack<Card>() ;
    final private List<Card> burnt = new ArrayList<Card>() ;
	private int numPlayers ;
	private int numCardsPerHand ;
	private int currentPlayer ;
	private LastMove lastMove ;

	public ShitheadGame(final int numPlayers, final List<String> playerNames, final List<String> playerTypes, 
						final int cardsPerHand) throws ShitheadException {
		this.numPlayers = numPlayers ;
		this.numCardsPerHand = cardsPerHand ;

		for (int i = 0 ; i < numPlayers ; i++) {
			final Player player = PlayerFactory.createPlayer(playerTypes.get(i), playerNames.get(i), numCardsPerHand) ;
			players.add(player) ;
		}
	}
	
	public ShitheadGameDetails getGameDetails() {
		final ShitheadGameDetails details = new ShitheadGameDetails(players, deck, numPlayers, 
				numCardsPerHand, currentPlayer, pile, burnt, lastMove) ;
		
		return details ;
	}
	
	public void deal() {
		final int totalCardsNeeded = (numCardsPerHand * numPlayers) * 3 ;
		final int div = totalCardsNeeded / 52 ;
		final int add = ((totalCardsNeeded % 52) > 0) ? 1 : 0 ; ;
        int decksRequired = 1 ;

		decksRequired = div + add ;
		
		for (int i = 1 ; i < decksRequired ; i++) 
			  deck.addAnotherDeck() ;
		
		deck.shuffle() ;
		final Iterator<Card> deckIterator = deck.getCards().iterator() ;
		for (int i = 0 ; i < numCardsPerHand ; i++) 
			for (int j = 0 ; j < numPlayers ; j++) 
				players.get(j).dealToFaceDown(deckIterator.next());
		for (int i = 0 ; i < numCardsPerHand ; i++) 
			for (int j = 0 ; j < numPlayers ; j++) 
				players.get(j).dealToFaceUp(deckIterator.next());
		for (int i = 0 ; i < numCardsPerHand ; i++) 
			for (int j = 0 ; j < numPlayers ; j++)
				players.get(j).dealToHand(deckIterator.next());

		final int totalToRemove = (numCardsPerHand * 3) * numPlayers ;
		for (int i = 0 ; i < totalToRemove ; i++) 
			deck.remove(0) ;
		
		currentPlayer = 0 ;
		lastMove = null ;
	}
	
	public void firstMove() {
		int playerToLayIndex ;
		final List<Card> lowestCardsByPlayerIndex = new ArrayList<Card>() ;
		final List<Card> cardsToPlay = new ArrayList<Card>() ;

		for (Player player : players) {
			final Hand playersHand = player.getHand() ;
			final Card playersLowestCard = playersHand.lowest() ;
			lowestCardsByPlayerIndex.add(playersLowestCard) ;
		}
		
		final Card lowestOfAllCards = Collections.min(lowestCardsByPlayerIndex, new ShitheadCardComparator()) ;
		playerToLayIndex = lowestCardsByPlayerIndex.indexOf(lowestOfAllCards) ;
		cardsToPlay.add(lowestCardsByPlayerIndex.get(playerToLayIndex)) ;

		for (Card toCompare : players.get(playerToLayIndex).getHand().cards())
			if ((cardsToPlay.get(0).compareTo(toCompare) == 0) && 
								(!cardsToPlay.get(0).equals(toCompare))) 
				cardsToPlay.add(toCompare) ;

		playFromHand(playerToLayIndex, cardsToPlay) ;
		currentPlayer = playerToLayIndex ;
		moveToNextPlayer() ;
	}
	
	public boolean currentPlayerCanPlay() {
		final Player currentPlayer = players.get(this.currentPlayer) ;
		
		if (currentPlayer.hasCardsInHand())
		    return canPlayWIthCards(currentPlayer.getHand());
		else if (currentPlayer.hasCardsInFaceUp()) 
			return canPlayWIthCards(currentPlayer.getFaceUp());
		else if (currentPlayer.hasCardsInFaceDown()) 
			return true ;
		else 
		    return false ;
	}

	public boolean checkValidMove(final List<Integer> choice) {
		final List<Card> cardsToLay = getCards(choice) ;
        final Card firstCard = cardsToLay.get(0) ;
		boolean validRank = true ;
		validRank = checkValidMove(firstCard) ;
		
		if (!validRank) 
			return false ;
		else {
			for (Card otherCard : cardsToLay) {
				if (firstCard.compareTo(otherCard)!=0)
					return false ;
			}
			return true ;
		}
	}	
	
	private List<Card> getCards(final List<Integer> cardChoice) {
		final Hand handToPlayFrom = getCurrentPlayersActiveHand() ;
		final List<Card> returnCards = new ArrayList<Card>() ;

		for (int cardIndex : cardChoice) {
			returnCards.add(handToPlayFrom.get(cardIndex)) ;
		}
		return returnCards ;
	}	
	
	private Hand getCurrentPlayersActiveHand() {
		final Player currentPlayer = players.get(this.currentPlayer) ;
		
		if (currentPlayer.playingFromHand()) 
			return currentPlayer.getHand() ;
		else if (currentPlayer.playingFromFaceUp())
			return currentPlayer.getFaceUp() ;
		else
			return currentPlayer.getFaceDown() ;
	}
	
	
	public void playerPickUpPile() {
		final Player currentPlayer = players.get(this.currentPlayer) ;
		currentPlayer.recieve(pile) ;
		pile.removeAllElements() ;
	}
	
	public void playerPickUpPileAndFaceDownCard(final int cardFromFaceDown) {
		playerPickUpPile() ;
		final Player currentPlayer = players.get(this.currentPlayer) ;
		currentPlayer.getHand().add(currentPlayer.getFaceDown().get(cardFromFaceDown)) ;
		currentPlayer.getFaceDown().remove(cardFromFaceDown) ;
	}
	
	public void play(final List<Integer> choice) {
		final List<Card> cardsToPlay = getCards(choice) ;
		final Player currentPlayer = players.get(this.currentPlayer) ;
		
		if (currentPlayer.playingFromHand()) 
			playFromHand(this.currentPlayer, cardsToPlay) ;
		else if (currentPlayer.playingFromFaceUp())
			playFromFaceUp(this.currentPlayer, cardsToPlay) ;
		else
			playFromFaceDown(this.currentPlayer, cardsToPlay) ;
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
	
	public boolean canContinueGame() {
		int numPlayersWithCards = 0 ;
		for (Player player : players) 
			if (player.hasCards()) 
				numPlayersWithCards++ ;

		return (numPlayersWithCards >= 2) ;
	}
	
	public String getShithead() throws ShitheadException {
		for (Player player : players) 
			if (player.hasCards()) 
				return player.getName() ;
		throw new ShitheadException("Game finished but no Shithead found!") ;
	}
	
	public PlayerHelper getPlayerHelper() {
		int numPlayersStillPlaying = 0 ;
		final List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;

		for (Player player : players) {
			if (player.hasCards()) 
				numPlayersStillPlaying++ ;
			final PlayerSummary playerSummary = new PlayerSummary(player.getName(), player.getHandSize(), 
			        Collections.unmodifiableList(player.getFaceUp().cards()), player.getFaceDownSize(), player.hasCards()) ;
			playerSummaries.add(playerSummary) ;
		}
		
		return new PlayerHelper(deck.getSize(), numPlayers, numPlayersStillPlaying, numCardsPerHand, currentPlayer, 
		        pile, Collections.unmodifiableList(burnt), playerSummaries) ;
	}
		
	private boolean checkValidMove(final Card cardToLay) {
		if (pile.empty()) 
			return true ;
		else if (Card.Rank.SEVEN.equals(pile.peek().getRank())) {
			Card testCard = pile.peek() ;
			for (int i = pile.size() -1 ; (i >=0 && (testCard.getRank().equals(Card.Rank.SEVEN))) ; i-- ) {
				testCard = pile.get(i) ;
			}
			if (testCard.getRank().equals(Card.Rank.SEVEN))
				return true ;
			else
				return checkValidMove(testCard, cardToLay) ;
		}
		else 
			return checkValidMove(pile.peek(), cardToLay) ;	
	}
	
	private boolean checkValidMove(final Card onPile, final Card toLay) {
		if (ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(toLay.getRank())) 
			return true ;
		else {
			return (onPile.compareTo(toLay) <= 0);
		}
	}	

	private boolean burnIfPossible() {
		boolean didBurn = false ;

		final boolean burnCardOnPile = (!pile.empty()) && (pile.peek().getRank().equals(ShitheadRules.BURN)) ;
		final boolean fourOfAKindOnPile = (pile.size() >= 4) && 
		        ((pile.get(pile.size()-1).getRank().equals(pile.get(pile.size()-2).getRank())) && 
				  (pile.get(pile.size()-2).getRank().equals(pile.get(pile.size()-3).getRank())) &&
			  		(pile.get(pile.size()-3).getRank().equals(pile.get(pile.size()-4).getRank()))) ;
		
		if (burnCardOnPile || fourOfAKindOnPile) {
			currentPlayer-- ;
			burnt.addAll(pile) ;
			pile.removeAllElements() ;
			didBurn = true ;
		}

		return didBurn ;
	}
	
	private boolean missAGoIfRequied() {
		boolean missAGo = false ;

		final boolean missAGoCardOnPile = (!pile.empty()) && (pile.peek().getRank().equals(ShitheadRules.MISS_A_TURN)) ;
		if (missAGoCardOnPile) {
			moveToNextPlayer();
			missAGo = true ;
		}
		return missAGo ;
	}

	private void playFromHand(final int player, final List<Card> toPlay) {
		pile.addAll(toPlay) ;
		players.get(player).getHand().removeAll(toPlay) ;
		pickupFromDeck(player, toPlay);

		final boolean didBurn = burnIfPossible() ;
		final boolean missedAGo = missAGoIfRequied() ;
		lastMove = new LastMove(players.get(player), toPlay, didBurn, missedAGo) ;
	}	

	private void playFromFaceUp(final int player, final List<Card> toPlay) {
		pile.addAll(toPlay) ;
		players.get(player).getFaceUp().removeAll(toPlay) ;
		pickupFromDeck(player, toPlay);

		final boolean didBurn = burnIfPossible() ;
		final boolean missedAGo = missAGoIfRequied() ;
		lastMove = new LastMove(players.get(player), toPlay, didBurn, missedAGo) ;
	}

	private void playFromFaceDown(final int player, final List<Card> toPlay) {
		pile.addAll(toPlay) ;
		players.get(player).getFaceDown().removeAll(toPlay) ;
		pickupFromDeck(player, toPlay);

		final boolean didBurn = burnIfPossible() ;
		final boolean missedAGo = missAGoIfRequied() ;
		lastMove = new LastMove(players.get(player), toPlay, didBurn, missedAGo) ;
	}	

	private void pickupFromDeck(int player, List<Card> toPlay) {
		for (int i = 0 ; i < toPlay.size() ; i++) {
			final boolean deckIsEmpty = deck.getCards().isEmpty() ;
			final boolean playersHandLessThanGameHandSize = 
				players.get(player).getHandSize() < numCardsPerHand ;
			if (!deckIsEmpty && playersHandLessThanGameHandSize) {
					final Card pickup = deck.getCards().get(0) ;
					final List<Card> pickupList = new ArrayList<Card>();
					pickupList.add(pickup) ;
					players.get(player).recieve(pickupList) ;
					deck.remove(0) ;
			}
		}
	}	
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayer) ;
	}

    private boolean canPlayWIthCards(final Hand hand) {
        final Iterator<Card> cardIterator = hand.iterator() ;
        boolean canPlay = false ;
        while (!canPlay && cardIterator.hasNext()) {
                canPlay = checkValidMove(cardIterator.next()) ;
        }
        return canPlay ;
    }
}
