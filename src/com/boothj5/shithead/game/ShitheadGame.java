package com.boothj5.shithead.game;

import java.util.* ;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Deck;
import com.boothj5.shithead.game.card.ShitheadCardComparator;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;
import com.boothj5.shithead.game.player.computer.ComputerPlayer;

public class ShitheadGame {
	private List<Player> players = new ArrayList<Player>() ;

	private Deck deck = new Deck() ;
	private int numPlayers ;
	private int numCardsPerHand ;

	private int currentPlayer ;

	private Stack<Card> pile = new Stack<Card>() ;
	private List<Card> burnt = new ArrayList<Card>() ;
	
	private LastMove lastMove ;

	public ShitheadGame(int numPlayers, List<String> playerNames, List<String> playerTypes, 
						int cardsPerHand) throws Exception {
		this.numPlayers = numPlayers ;
		this.numCardsPerHand = cardsPerHand ;

		for (int i = 0 ; i < numPlayers ; i++) {
			Player player = PlayerFactory.createPlayer(playerTypes.get(i), playerNames.get(i), numCardsPerHand) ;
			players.add(player) ;
		}
	}
	
	public ShitheadGameDetails getGameDetails() {
		ShitheadGameDetails details = new ShitheadGameDetails(players, deck, numPlayers, 
				numCardsPerHand, currentPlayer, pile, burnt, lastMove) ;
		
		return details ;
	}
	
	public void deal() {
		int decksRequired = 1 ;
		
		int totalCardsNeeded = (numCardsPerHand * numPlayers) * 3 ;

		int div = totalCardsNeeded / 52 ;
		int add = ((totalCardsNeeded % 52) > 0) ? 1 : 0 ; ;

		decksRequired = div + add ;
		
		deck = new Deck() ;
		for (int i = 1 ; i < decksRequired ; i++) 
			  deck.addAll(new Deck().getCards()) ;
		
		deck.shuffle() ;

		Iterator<Card> deckIterator = deck.getCards().iterator() ;

		for (int i = 0 ; i < numCardsPerHand ; i++) 
			for (int j = 0 ; j < numPlayers ; j++) 
				players.get(j).dealToFaceDown(deckIterator.next());
		for (int i = 0 ; i < numCardsPerHand ; i++) 
			for (int j = 0 ; j < numPlayers ; j++) 
				players.get(j).dealToFaceUp(deckIterator.next());
		for (int i = 0 ; i < numCardsPerHand ; i++) 
			for (int j = 0 ; j < numPlayers ; j++)
				players.get(j).dealToHand(deckIterator.next());

		// remove the dealt cards from the pack
		int totalToRemove = (numCardsPerHand * 3) * numPlayers ;
		for (int i = 0 ; i < totalToRemove ; i++) 
			deck.remove(0) ;
		
		currentPlayer = 0 ;
		lastMove = null ;
	}
	
	public void firstMove() {
		int playerToLayIndex ;
		List<Card> lowestCardsByPlayerIndex = new ArrayList<Card>() ;
		List<Card> cardsToPlay = new ArrayList<Card>() ;

		// add lowest card of each player to a list
		for (Player player : players) {
			List<Card> playersHand = player.getHand() ;
			Card playersLowestCard = Collections.min(playersHand, new ShitheadCardComparator()) ;

			lowestCardsByPlayerIndex.add(playersLowestCard) ;
		}
		
		Card lowestOfAllCards = Collections.min(lowestCardsByPlayerIndex, new ShitheadCardComparator()) ;

		// get the index of the player with the lowest card
		playerToLayIndex = lowestCardsByPlayerIndex.indexOf(lowestOfAllCards) ;

		// add to the list to be played
		cardsToPlay.add(lowestCardsByPlayerIndex.get(playerToLayIndex)) ;

		// iterate over the players cards for any of the same rank and add them 
		for (Card toCompare : players.get(playerToLayIndex).getHand())
			if ((cardsToPlay.get(0).compareTo(toCompare) == 0) && 
								(!cardsToPlay.get(0).equals(toCompare))) 
				cardsToPlay.add(toCompare) ;

		playFromHand(playerToLayIndex, cardsToPlay) ;
		
		currentPlayer = playerToLayIndex ;
		moveToNextPlayer() ;
	}
	
	public boolean currentPlayerCanPlay() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		boolean canPlay = false ;
		
		// check can play from hand
		if (currentPlayer.getHand().size() > 0) {
			Iterator<Card> cardIterator = currentPlayer.getHand().iterator() ;
			while (!canPlay && cardIterator.hasNext()) {
					canPlay = checkValidMove(cardIterator.next()) ;
			}
		}
		// check can play from face up
		else if (currentPlayer.getFaceUp().size() > 0) {
			Iterator<Card> cardIterator = currentPlayer.getFaceUp().iterator() ;
			while (!canPlay && cardIterator.hasNext()) {
					canPlay = checkValidMove(cardIterator.next()) ;
			}
		}
		// else can play from face down
		else if (currentPlayer.getFaceDown().size() > 0) {
			canPlay = true ;
		}
		
		return canPlay ;
	}

	public boolean isCurrentPlayerComputerPlayer() {
		return players.get(currentPlayer) instanceof ComputerPlayer ;
	}
	
	public int getHandSize() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		if (currentPlayer.getHand().size() > 0) 
			return currentPlayer.getHand().size() ;
		else if (currentPlayer.getFaceUp().size() > 0) 
			return currentPlayer.getFaceUp().size() ;
		else 
			return currentPlayer.getFaceDown().size() ;
	}
	
	public boolean playingFromHand() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		if (currentPlayer.getHand().size() > 0) 
			return true ;
		else 
			return false ;
	}

	public boolean playingFromFaceUp() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		if (playingFromHand()) 
			return false ;
		else if (currentPlayer.getFaceUp().size() > 0) 
			return true ;
		else 
			return false;
	}
	
	public boolean playingFromFaceDown() {
		if (playingFromHand() || playingFromFaceUp()) 
			return false ;
		else 
			return true ;
	}

	public boolean checkValidMove(List<Integer> choice) {
		List<Card> cardsToLay = getCards(choice) ;
		boolean validRank = true ;
		
		
		// check the first one is valid
		Card firstCard = cardsToLay.get(0) ;
		validRank = checkValidMove(firstCard) ;
		
		if (!validRank) 
			return false ;
		else { // check whether the rest are the same rank
			for (Card otherCard : cardsToLay) {
				if (firstCard.compareTo(otherCard)!=0)
					return false ;
			}
			return true ;
		}
	}	
	
	private List<Card> getCards(List<Integer> cardChoice) {
		
		List<Card> handToPlayFrom = getCurrentPlayersActiveHand() ;
		List<Card> returnCards = new ArrayList<Card>() ;

		for (int cardIndex : cardChoice) {
			returnCards.add(handToPlayFrom.get(cardIndex)) ;
		}
		return returnCards ;
	}	
	
	private List<Card> getCurrentPlayersActiveHand() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		
		if (playingFromHand()) 
			return currentPlayer.getHand() ;
		else if (playingFromFaceUp())
			return currentPlayer.getFaceUp() ;
		else
			return currentPlayer.getFaceDown() ;
	}
	
	
	public void playerPickUpPile() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		
		currentPlayer.recieve(pile) ;
		pile.removeAllElements() ;
	}
	
	public void playerPickUpPileAndFaceDownCard(int cardFromFaceDown) {
		playerPickUpPile() ;
		Player currentPlayer = players.get(this.currentPlayer) ;
		currentPlayer.getHand().add(currentPlayer.getFaceDown().get(cardFromFaceDown)) ;
		currentPlayer.getFaceDown().remove(cardFromFaceDown) ;
	}
	
	public void play(List<Integer> choice) {
		List<Card> cardsToPlay = getCards(choice) ;
		
		if (playingFromHand()) 
			playFromHand(this.currentPlayer, cardsToPlay) ;
		else if (playingFromFaceUp())
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
		boolean continueGame = true ;

		// if more than one player with cards, continue
		int numPlayersWithCards = 0 ;
		for (Player player : players) 
			if (player.hasCards()) 
				numPlayersWithCards++ ;

		if (numPlayersWithCards >= 2)
			continueGame = true ;
		else 
			continueGame = false ;

		return continueGame ;		
	}
	
	public String getShithead() throws Exception {
		for (Player player : players) 
			if (player.hasCards()) 
				return player.getName() ;
		throw new Exception("Game finished but no Shithead found!") ;
	}
	
	public PlayerHelper getPlayerHelper() {
		int numPlayersStillPlaying = 0 ;
		List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;

		for (Player player : players) {
			if (player.hasCards()) 
				numPlayersStillPlaying++ ;
			PlayerSummary playerSummary = new PlayerSummary(player.getName(), 
															player.getHand().size(), 
															Collections.unmodifiableList(player.getFaceUp()), 
															player.getFaceDown().size(), player.hasCards()) ;
			playerSummaries.add(playerSummary) ;
		}
		
		return new PlayerHelper(deck.getSize(), 
						numPlayers, 
						numPlayersStillPlaying, 
						numCardsPerHand, 
						currentPlayer, 
						pile, 
						Collections.unmodifiableList(burnt),
						playerSummaries) ;
	}
		
	private boolean checkValidMove(Card cardToLay) {
		if (pile.empty()) 
			return true ;
		else if (Card.Rank.SEVEN.equals(pile.peek().getRank())) {
			//look for first non invisible and check that
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
	
	private boolean checkValidMove(Card onPile, Card toLay) {
		if (ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(toLay.getRank())) 
			return true ;
		else {
			return (onPile.compareTo(toLay) <= 0);
		}
	}	
	

	private boolean burnIfPossible() {
		boolean didBurn = false ;

		boolean burnCardOnPile = (!pile.empty()) && (pile.peek().getRank().equals(ShitheadRules.BURN)) ;
		boolean fourOfAKindOnPile = (pile.size() >= 4) && 
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

		boolean missAGoCardOnPile = (!pile.empty()) && (pile.peek().getRank().equals(ShitheadRules.MISS_A_TURN)) ;
		if (missAGoCardOnPile) {
			moveToNextPlayer();
			missAGo = true ;
		}
		return missAGo ;
	}

	private void playFromHand(int player, List<Card> toPlay) {
		pile.addAll(toPlay) ;
		players.get(player).getHand().removeAll(toPlay) ;
		pickupFromDeck(player, toPlay);

		boolean didBurn = burnIfPossible() ;
		boolean missedAGo = missAGoIfRequied() ;
		lastMove = new LastMove(players.get(player), toPlay, didBurn, missedAGo) ;
	}	

	private void playFromFaceUp(int player, List<Card> toPlay) {
		pile.addAll(toPlay) ;
		players.get(player).getFaceUp().removeAll(toPlay) ;
		pickupFromDeck(player, toPlay);

		boolean didBurn = burnIfPossible() ;
		boolean missedAGo = missAGoIfRequied() ;
		lastMove = new LastMove(players.get(player), toPlay, didBurn, missedAGo) ;
	}

	private void playFromFaceDown(int player, List<Card> toPlay) {
		pile.addAll(toPlay) ;
		players.get(player).getFaceDown().removeAll(toPlay) ;
		pickupFromDeck(player, toPlay);

		boolean didBurn = burnIfPossible() ;
		boolean missedAGo = missAGoIfRequied() ;
		lastMove = new LastMove(players.get(player), toPlay, didBurn, missedAGo) ;
	}	

	private void pickupFromDeck(int player, List<Card> toPlay) {
		for (int i = 0 ; i < toPlay.size() ; i++) {
			boolean deckIsEmpty = deck.getCards().isEmpty() ;
			boolean playersHandLessThanGameHandSize = 
				players.get(player).getHand().size() < numCardsPerHand ;
			if (!deckIsEmpty && playersHandLessThanGameHandSize) {
					Card pickup = deck.getCards().get(0) ;
					List<Card> pickupList = new ArrayList<Card>();
					pickupList.add(pickup) ;
					players.get(player).recieve(pickupList) ;
					deck.remove(0) ;
			}
		}
	}	

}
