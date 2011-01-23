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
		lastMove = null ;
	}
	
	public void firstMove() {
		int playerToLayIndex ;
		List<Card> lowestCardsByPlayerIndex = new ArrayList<Card>() ;
		List<Card> cardsToPlay = new ArrayList<Card>() ;

		// add lowest card of each player to a list
		for (Player player : players) 
			lowestCardsByPlayerIndex.add(Collections.min(player.getHand(Player.Hand.HAND), new ShitheadCardComparator())) ;

		// get the index of the player with the lowest card
		playerToLayIndex = lowestCardsByPlayerIndex.indexOf(
					Collections.min(lowestCardsByPlayerIndex, 
										new ShitheadCardComparator())) ; 

		cardsToPlay.add(lowestCardsByPlayerIndex.get(playerToLayIndex)) ;

		// iterate over the players cards for any of the same rank
		for (Card toCompare : players.get(playerToLayIndex).getHand(Player.Hand.HAND))
			if ((cardsToPlay.get(0).compareTo(toCompare) == 0) && 
								(!cardsToPlay.get(0).equals(toCompare))) 
				cardsToPlay.add(toCompare) ;

		play(playerToLayIndex, Player.Hand.HAND, cardsToPlay) ;
		
		currentPlayer = playerToLayIndex ;
		moveToNextPlayer() ;
	}
	
	private void moveToNextPlayer() {
		currentPlayer ++ ;
		if (currentPlayer >= players.size())
			currentPlayer = 0 ;
		while (!players.get(currentPlayer).hasCards()) {
			currentPlayer++ ;
			if (currentPlayer >= players.size())
				currentPlayer = 0 ;
		}
	}
	
	private void play(int player, Player.Hand hand, List<Card> toPlay) {

		pile.addAll(toPlay) ;
		players.get(player).getHand(hand).removeAll(toPlay) ;

		for (int i = 0 ; i < toPlay.size() ; i++) {
			if (!deck.cards.isEmpty() && players.get(player).getHand(Player.Hand.HAND).size() < numCardsPerHand) {
					Card pickup = deck.cards.get(0) ;
					List<Card> pickupList = new ArrayList<Card>();
					pickupList.add(pickup) ;
					players.get(player).recieve(pickupList) ;
					deck.cards.remove(0) ;
			}
		}

		lastMove = new LastMove(players.get(player), toPlay) ;
		// burn if required
		lastMove.setBurnt(burnIfPossible()) ;
	}	

	private boolean burnIfPossible() {
		boolean didBurn = false ;
		// burn card
		if ((!pile.empty()) && (pile.peek().rank.equals(Card.Rank.TEN))) {
			currentPlayer-- ;
			burnt.addAll(pile) ;
			pile.removeAllElements() ;
			didBurn = true ;
		}
		else if ((pile.size() >= 4) && 
				((pile.get(pile.size()-1).rank.equals(pile.get(pile.size()-2).rank)) && 
				  (pile.get(pile.size()-2).rank.equals(pile.get(pile.size()-3).rank)) &&
			  		(pile.get(pile.size()-3).rank.equals(pile.get(pile.size()-4).rank))) ) {
			currentPlayer-- ;
			burnt.addAll(pile) ;
			pile.removeAllElements() ;
			didBurn = true ;
		}
		return didBurn ;
	}
	
	public ShitheadGameDetails getGameDetails() {
		ShitheadGameDetails details = new ShitheadGameDetails(players, deck, numPlayers, 
				numCardsPerHand, currentPlayer, pile, burnt, lastMove) ;
		
		return details ;
	}
}
