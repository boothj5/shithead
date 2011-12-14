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

public class ShitheadGame {
	private List<Player> players = new ArrayList<Player>() ;
	private Deck deck ;
    private Stack<Card> pile = new Stack<Card>() ;
    private List<Card> burnt = new ArrayList<Card>() ;
	private int numPlayers ;
	private int numCardsPerHand ;
	private int currentPlayer ;
	private LastMove lastMove ;

	public ShitheadGame(List<String> playerNames, List<String> playerTypes, int cardsPerHand) 
	        throws ShitheadException {
		numPlayers = playerNames.size() ;
		numCardsPerHand = cardsPerHand ;
        currentPlayer = 0 ;
        deck = new Deck(numPlayers, numCardsPerHand) ;

		for (int i = 0 ; i < numPlayers ; i++) {
		    String playerType = playerTypes.get(i) ;
		    String playerName = playerNames.get(i) ;
			Player player = PlayerFactory.createPlayer(playerType, playerName, numCardsPerHand) ;
			players.add(player) ;
		}
	}

	public void deal() {
	    for (Player player : players) {
		    for (int i = 0 ; i < numCardsPerHand ; i++) {
		        player.dealToHand(deck.takeCard()) ;
                player.dealToFaceUp(deck.takeCard()) ;
                player.dealToFaceDown(deck.takeCard()) ;
		    }
		    player.sortHand() ;
		}
		currentPlayer = 0 ;
		lastMove = null ;
	}
	
	public void firstMove() {
	    currentPlayer = playerWithLowest() ;
	    Player player = getCurrentPlayer() ;
	    List<Card> cardsToLay = new ArrayList<Card>() ;
	    cardsToLay.add(player.getLowestHandCard()) ;
	    Card firstCardToLay = cardsToLay.get(0) ;
	    
	    for(Card handCard : player.getHand().cards()) {
	        if (handCard.sameRankDifferentSuit(firstCardToLay)) {
	            cardsToLay.add(handCard) ;
	        }
	    }
	    
		playFromHand(currentPlayer, cardsToLay) ;
		moveToNextPlayer() ;
	}
	
	public int playerWithLowest() {
	    int lowestPlayer = 0 ;
	    ShitheadCardComparator comp = new ShitheadCardComparator() ;
	    
	    for (int i = 2 ; i < players.size() ; i++) {
	        Card playersLowest = players.get(i).getLowestHandCard() ;
	        Card currentLowest = players.get(lowestPlayer).getLowestHandCard();
	        
	        if (comp.compare(playersLowest, currentLowest) < 0)
	            lowestPlayer = i ;
	    }
	    return lowestPlayer ;
	}
	
	public boolean currentPlayerCanPlay() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		
		if (currentPlayer.hasCardsInHand())
		    return canPlayWIthCards(currentPlayer.getHand());
		else if (currentPlayer.hasCardsInFaceUp()) 
			return canPlayWIthCards(currentPlayer.getFaceUp());
		else if (currentPlayer.hasCardsInFaceDown()) 
			return true ;
		else 
		    return false ;
	}

	public boolean checkValidMove(List<Integer> choice) {
		List<Card> cardsToLay = getCards(choice) ;
        Card firstCard = cardsToLay.get(0) ;
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
	
	private List<Card> getCards(List<Integer> cardChoice) {
		Hand handToPlayFrom = getCurrentPlayersActiveHand() ;
		List<Card> returnCards = new ArrayList<Card>() ;

		for (int cardIndex : cardChoice) {
			returnCards.add(handToPlayFrom.get(cardIndex)) ;
		}
		return returnCards ;
	}	
	
	private Hand getCurrentPlayersActiveHand() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		
		if (currentPlayer.playingFromHand()) 
			return currentPlayer.getHand() ;
		else if (currentPlayer.playingFromFaceUp())
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
		Player currentPlayer = players.get(this.currentPlayer) ;
		
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
		List<PlayerSummary> playerSummaries = new ArrayList<PlayerSummary>() ;

		for (Player player : players) {
			if (player.hasCards()) 
				numPlayersStillPlaying++ ;
			PlayerSummary playerSummary = new PlayerSummary(player.getName(), player.getHandSize(), 
			        Collections.unmodifiableList(player.getFaceUp().cards()), player.getFaceDownSize(), player.hasCards()) ;
			playerSummaries.add(playerSummary) ;
		}
		
		return new PlayerHelper(deck.size(), numPlayers, numPlayersStillPlaying, numCardsPerHand, currentPlayer, 
		        pile, Collections.unmodifiableList(burnt), playerSummaries) ;
	}

    public Player getCurrentPlayer() {
        return players.get(currentPlayer) ;
    }

    public ShitheadGameDetails getGameDetails() {
        ShitheadGameDetails details = new ShitheadGameDetails(players, deck, numPlayers, 
                numCardsPerHand, currentPlayer, pile, burnt, lastMove) ;
        return details ;
    }
	
	private boolean checkValidMove(Card cardToLay) {
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
		players.get(player).sortHand() ;

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
			boolean deckIsEmpty = deck.isEmpty() ;
			boolean playersHandLessThanGameHandSize = 
				players.get(player).getHandSize() < numCardsPerHand ;
			if (!deckIsEmpty && playersHandLessThanGameHandSize) {
					Card pickup = deck.takeCard() ;
					List<Card> pickupList = new ArrayList<Card>();
					pickupList.add(pickup) ;
					players.get(player).recieve(pickupList) ;
					players.get(player).sortHand() ;
			}
		}
	}	
	
    private boolean canPlayWIthCards(Hand hand) {
        Iterator<Card> cardIterator = hand.iterator() ;
        boolean canPlay = false ;
        while (!canPlay && cardIterator.hasNext()) {
                canPlay = checkValidMove(cardIterator.next()) ;
        }
        return canPlay ;
    }
}
