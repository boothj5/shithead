package com.boothj5.shithead.game;

import java.util.* ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.card.Deck;
import com.boothj5.shithead.card.ShitheadCardComparator;
import com.boothj5.shithead.player.Player;
import com.boothj5.shithead.player.PlayerFactory;

public class ShitheadGame {
	private List<Player> players = new ArrayList<Player>() ;

	private Deck deck = new Deck() ;
	private int numPlayers ;
	private int numCardsPerHand ;

	private int currentPlayer ;

	private Stack<Card> pile = new Stack<Card>() ;
	private List<Card> burnt = new ArrayList<Card>() ;
	
	private LastMove lastMove ;

	public static final EnumSet<Card.Rank> LAY_ON_ANYTHING_RANKS = 
		EnumSet.<Card.Rank>of(Card.Rank.TWO, Card.Rank.SEVEN, Card.Rank.TEN) ;
	public static final EnumSet<Card.Rank> NORMAL_RANKS = 
		EnumSet.complementOf(LAY_ON_ANYTHING_RANKS) ;

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
	
	public ShitheadGameDetails getGameDetails() {
		ShitheadGameDetails details = new ShitheadGameDetails(players, deck, numPlayers, 
				numCardsPerHand, currentPlayer, pile, burnt, lastMove) ;
		
		return details ;
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
		lastMove = null ;
	}
	
	public void firstMove() {
		int playerToLayIndex ;
		List<Card> lowestCardsByPlayerIndex = new ArrayList<Card>() ;
		List<Card> cardsToPlay = new ArrayList<Card>() ;

		// add lowest card of each player to a list
		for (Player player : players) {
			List<Card> playersHand = player.getHand(Player.Hand.HAND) ;
			Card playersLowestCard = Collections.min(playersHand, new ShitheadCardComparator()) ;

			lowestCardsByPlayerIndex.add(playersLowestCard) ;
		}
		
		Card lowestOfAllCards = Collections.min(lowestCardsByPlayerIndex, new ShitheadCardComparator()) ;

		// get the index of the player with the lowest card
		playerToLayIndex = lowestCardsByPlayerIndex.indexOf(lowestOfAllCards) ;

		// add to the list to be played
		cardsToPlay.add(lowestCardsByPlayerIndex.get(playerToLayIndex)) ;

		// iterate over the players cards for any of the same rank and add them 
		for (Card toCompare : players.get(playerToLayIndex).getHand(Player.Hand.HAND))
			if ((cardsToPlay.get(0).compareTo(toCompare) == 0) && 
								(!cardsToPlay.get(0).equals(toCompare))) 
				cardsToPlay.add(toCompare) ;

		play(playerToLayIndex, Player.Hand.HAND, cardsToPlay) ;
		
		currentPlayer = playerToLayIndex ;
		moveToNextPlayer() ;
	}
	
	public boolean currentPlayerCanPlay() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		boolean canPlay = false ;
		
		// check can play from hand
		if (currentPlayer.getHand(Player.Hand.HAND).size() > 0) {
			Iterator<Card> cardIterator = currentPlayer.getHand(Player.Hand.HAND).iterator() ;
			while (!canPlay && cardIterator.hasNext()) {
					canPlay = checkValidMove(cardIterator.next()) ;
			}
		}
		// check can play from face up
		else if (currentPlayer.getHand(Player.Hand.FACEUP).size() > 0) {
			Iterator<Card> cardIterator = currentPlayer.getHand(Player.Hand.FACEUP).iterator() ;
			while (!canPlay && cardIterator.hasNext()) {
					canPlay = checkValidMove(cardIterator.next()) ;
			}
		}
		// else can play from face down
		else if (currentPlayer.getHand(Player.Hand.FACEDOWN).size() > 0) {
			canPlay = true ;
		}
		
		return canPlay ;
	}
	
	public Player.Hand getHandToPlayFrom() {
		Player currentPlayer = players.get(this.currentPlayer) ;

		if (currentPlayer.getHand(Player.Hand.HAND).size() > 0) 
			return Player.Hand.HAND ;
		else if (currentPlayer.getHand(Player.Hand.FACEUP).size() > 0) 
			return Player.Hand.FACEUP ;
		else 
			return Player.Hand.FACEDOWN ;
	}

	public boolean checkValidMove(List<Card> cardsToLay) {
		boolean validRank = true ;
		Card firstCard = cardsToLay.get(0) ;
		
		// check the first one is valid
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

	public void playerPickUpPile() {
		Player currentPlayer = players.get(this.currentPlayer) ;
		
		currentPlayer.recieve(pile) ;
		pile.removeAllElements() ;
	}
	
	public void playerPickUpPileAndFaceDownCard(Card cardFromFaceDown) {
		playerPickUpPile() ;
		players.get(currentPlayer).getHand(Player.Hand.HAND).add(cardFromFaceDown) ;
		players.get(currentPlayer).getHand(Player.Hand.FACEDOWN).remove(cardFromFaceDown) ;
	}
	
	
	public void play(List<Card> toPlay) {
		Player currentPlayer = players.get(this.currentPlayer) ;
		Player.Hand hand = null ;
		
		if (currentPlayer.getHand(Player.Hand.HAND).size() > 0) 
			hand = Player.Hand.HAND ;
		else if (currentPlayer.getHand(Player.Hand.FACEUP).size() > 0)
			hand = Player.Hand.FACEUP ;
		else
			hand = Player.Hand.FACEDOWN ;
		play(this.currentPlayer, hand, toPlay) ;
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
		
	private boolean checkValidMove(Card cardToLay) {
		if (pile.empty()) 
			return true ;
		else if (Card.Rank.SEVEN.equals(pile.peek().rank)) {
			//look for first non invisible and check that
			Card testCard = pile.peek() ;
			for (int i = pile.size() -1 ; (i >=0 && (testCard.rank.equals(Card.Rank.SEVEN))) ; i-- ) {
				testCard = pile.get(i) ;
			}
			if (testCard.rank.equals(Card.Rank.SEVEN))
				return true ;
			else
				return checkValidMove(testCard, cardToLay) ;
		}
		else 
			return checkValidMove(pile.peek(), cardToLay) ;	
	}
	
	private boolean checkValidMove(Card onPile, Card toLay) {
		if (LAY_ON_ANYTHING_RANKS.contains(toLay.rank)) 
			return true ;
		else {
			return (onPile.compareTo(toLay) <= 0);
		}
	}	
	

	private boolean burnIfPossible() {
		boolean didBurn = false ;

		boolean burnCardOnPile = (!pile.empty()) && (pile.peek().rank.equals(burnRank)) ;
		boolean fourOfAKindOnPile = (pile.size() >= 4) && 
		((pile.get(pile.size()-1).rank.equals(pile.get(pile.size()-2).rank)) && 
				  (pile.get(pile.size()-2).rank.equals(pile.get(pile.size()-3).rank)) &&
			  		(pile.get(pile.size()-3).rank.equals(pile.get(pile.size()-4).rank))) ;
		
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

		boolean missAGoCardOnPile = (!pile.empty()) && (pile.peek().rank.equals(missTurnRank)) ;
		if (missAGoCardOnPile) {
			moveToNextPlayer();
			missAGo = true ;
		}
		return missAGo ;
	}

	
	
	private void play(int player, Player.Hand hand, List<Card> toPlay) {

		// add cards to pile
		pile.addAll(toPlay) ;
		
		// remove them from players hand
		players.get(player).getHand(hand).removeAll(toPlay) ;

		// pick up new cards from deck
		for (int i = 0 ; i < toPlay.size() ; i++) {
			boolean deckIsEmpty = deck.cards.isEmpty() ;
			boolean playersHandLessThanGameHandSize = 
				players.get(player).getHand(Player.Hand.HAND).size() < numCardsPerHand ;
			if (!deckIsEmpty && playersHandLessThanGameHandSize) {
					Card pickup = deck.cards.get(0) ;
					List<Card> pickupList = new ArrayList<Card>();
					pickupList.add(pickup) ;
					players.get(player).recieve(pickupList) ;
					deck.cards.remove(0) ;
			}
		}

		lastMove = new LastMove(players.get(player), toPlay) ;
		
		// burn if required
		lastMove.setMissAGo(missAGoIfRequied()) ;
		lastMove.setBurnt(burnIfPossible()) ;
	}	

}
