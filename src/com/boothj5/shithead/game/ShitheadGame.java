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
	private final List<Player> players = new ArrayList<Player>() ;
    private final Stack<Card> pile = new Stack<Card>() ;
    private final List<Card> burnt = new ArrayList<Card>() ;
    private final Deck deck ;
	private final int numPlayers ;
	private final int numCardsPerHand ;
	private int currentPlayer ;
	private LastMove lastMove ;

	public ShitheadGame(List<String> playerNames, List<String> playerTypes, int cardsPerHand) 
	        throws ShitheadException {
		numPlayers = playerNames.size() ;
		numCardsPerHand = cardsPerHand ;
        deck = new Deck(numPlayers * numCardsPerHand * 3) ;
        currentPlayer = 0 ;
        lastMove = null ;

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
	}
	
	public void firstMove() {
	    // find player with lowest card
	    currentPlayer = playerWithLowest() ;
	    Player player = getCurrentPlayer() ;
	    List<Card> cardsToLay = new ArrayList<Card>() ;

	    // get other cards of same rank
	    Card first = player.getHand().get(0) ;
	    for(Card current : player.getHand().cards()) {
	        if (current.equalsRank(first)) {
	            cardsToLay.add(current) ;
	        }
	    }
	    
	    // play them
		playFromHand(cardsToLay) ;
		moveToNextPlayer() ;
	}
	
	public boolean currentPlayerCanMove() {
		if (pile.isEmpty())
		    return true ;

		Player player = getCurrentPlayer() ;
		if (player.hasCardsInHand())
		    return canMoveWithOneOf(player.getHand());
		else if (player.hasCardsInFaceUp()) 
			return canMoveWithOneOf(player.getFaceUp());
		else if (player.hasCardsInFaceDown()) 
			return true ;
		else 
		    return false ;
	}

	private boolean canLay(Card card, Stack<Card> cards) {
	    if (cards.isEmpty()) {
	        return true ;
	    }
	    else if (ShitheadRules.isSpecial(card)) {
	        return true ;
	    }
	    else if (ShitheadRules.isInvisible(cards.peek())) {
	        Stack<Card> newPile = new Stack<Card>() ;
	        newPile.addAll(cards) ;
	        newPile.pop() ;
	        return canLay(card, newPile) ;
	    }
	    else if ((card.compareTo(cards.peek())) < 0) {
	        return false ;
	    }
	    else {
	        return true ;
	    }	    
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
		Player player = getCurrentPlayer() ;
		
		if (player.playingFromHand()) 
			return player.getHand() ;
		else if (player.playingFromFaceUp())
			return player.getFaceUp() ;
		else
			return player.getFaceDown() ;
	}
	
	
	public void playerPickUpPile() {
		Player currentPlayer = getCurrentPlayer() ;
		currentPlayer.recieve(pile) ;
		pile.removeAllElements() ;
	}
	
	public void playerPickUpPileAndFaceDownCard(int cardFromFaceDown) {
		playerPickUpPile() ;
		Player currentPlayer = getCurrentPlayer() ;
		currentPlayer.getHand().add(currentPlayer.getFaceDown().get(cardFromFaceDown)) ;
		currentPlayer.getFaceDown().remove(cardFromFaceDown) ;
	}
	
	public void play(List<Integer> choice) {
		List<Card> cardsToPlay = getCards(choice) ;
		Player player = getCurrentPlayer() ;
		
		if (player.playingFromHand()) 
			playFromHand(cardsToPlay) ;
		else if (player.playingFromFaceUp())
			playFromFaceUp(cardsToPlay) ;
		else
			playFromFaceDown(cardsToPlay) ;
	}	

	public void moveToNextPlayer() {
		currentPlayer ++ ;
		if (currentPlayer >= players.size())
			currentPlayer = 0 ;
		while (!getCurrentPlayer().hasCards()) {
			currentPlayer++ ;
			if (currentPlayer >= players.size())
				currentPlayer = 0 ;
		}
	}
	
	public boolean canContinue() {
		int numPlayersWithCards = 0 ;
		for (Player player : players) 
			if (player.hasCards()) 
				numPlayersWithCards++ ;

		return (numPlayersWithCards > 1) ;
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
			if (currentPlayer < 0)
			    currentPlayer = numPlayers - 1 ;
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

	private void playFromHand(List<Card> toPlay) {
		Player player = getCurrentPlayer() ;
	    pile.addAll(toPlay) ;
	    player.getHand().removeAll(toPlay) ;
		pickupFromDeck(toPlay);
		player.sortHand() ;

		boolean didBurn = burnIfPossible() ;
		boolean missedAGo = missAGoIfRequied() ;
		lastMove = new LastMove(getCurrentPlayer(), toPlay, didBurn, missedAGo) ;
	}	

	private void playFromFaceUp(List<Card> toPlay) {
		Player player = getCurrentPlayer() ;
	    
	    pile.addAll(toPlay) ;
		player.getFaceUp().removeAll(toPlay) ;
		pickupFromDeck(toPlay);

		boolean didBurn = burnIfPossible() ;
		boolean missedAGo = missAGoIfRequied() ;
		lastMove = new LastMove(player, toPlay, didBurn, missedAGo) ;
	}

	private void playFromFaceDown(List<Card> toPlay) {
		Player player = getCurrentPlayer() ;
	    
	    pile.addAll(toPlay) ;
		player.getFaceDown().removeAll(toPlay) ;
		pickupFromDeck(toPlay);

		boolean didBurn = burnIfPossible() ;
		boolean missedAGo = missAGoIfRequied() ;
		lastMove = new LastMove(player, toPlay, didBurn, missedAGo) ;
	}	

	private void pickupFromDeck(List<Card> toPlay) {
		Player player = getCurrentPlayer() ;
	    for (int i = 0 ; i < toPlay.size() ; i++) {
			boolean deckIsEmpty = deck.isEmpty() ;
			boolean playersHandLessThanGameHandSize = 
			        player.getHandSize() < numCardsPerHand ;
			if (!deckIsEmpty && playersHandLessThanGameHandSize) {
					Card pickup = deck.takeCard() ;
					List<Card> pickupList = new ArrayList<Card>();
					pickupList.add(pickup) ;
					player.recieve(pickupList) ;
					player.sortHand() ;
			}
		}
	}	
	
    private boolean canMoveWithOneOf(Hand hand) {
        for (Card card : hand.cards())
            if (canLay(card, pile))
                return true ;
        return false ;
    }

    private int playerWithLowest() {
        // assumes players hands are not empty and are sorted
        int lowestPlayer = 0 ;
        ShitheadCardComparator comp = new ShitheadCardComparator() ;
        
        for (int i = 1 ; i < players.size() ; i++) {
            Card playersLowest = players.get(i).getHand().get(0) ;
            Card currentLowest = players.get(lowestPlayer).getHand().get(0);
            if (comp.compare(playersLowest, currentLowest) < 0)
                lowestPlayer = i ;
        }

        return lowestPlayer ;
    }

}
