package com.boothj5.shithead.game;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Deck;
import com.boothj5.shithead.game.player.Player;


public class ShitheadGameDetails {
	private final List<Player> players ;

	private final Deck deck ;
	private final int numPlayers ;
	private final int numCardsPerHand ;

	private final int currentPlayer ;

	private final Stack<Card> pile ;
	private final List<Card> burnt ;
	private final LastMove lastmove ;

	public ShitheadGameDetails(final List<Player> players, final Deck deck, final int numPlayers, final int numCardsPerHand, 
	        final int currentPlayer, final Stack<Card> pile, final List<Card> burnt, final LastMove lastMove) {
		this.players = Collections.unmodifiableList(players) ;
		this.deck = deck ;
		this.numPlayers = numPlayers ;
		this.numCardsPerHand = numCardsPerHand ;
		this.currentPlayer = currentPlayer ;
		this.pile = pile ;
		this.burnt = Collections.unmodifiableList(burnt) ;
		this.lastmove = lastMove ;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Deck getDeck() {
		return deck;
	}

	public int getNumPlayers() {
		return numPlayers;
	}

	public int getNumCardsPerHand() {
		return numCardsPerHand;
	}

	public Stack<Card> getPile() {
		return pile;
	}

	public List<Card> getBurnt() {
		return burnt;
	}
	
	public boolean isCurrentPlayer(Player player) {
		return (players.indexOf(player) == currentPlayer) ;
	}

	public LastMove getLastmove() {
		return lastmove;
	}
	
	public Player getCurrentPlayer() {
		return players.get(currentPlayer) ;
	}
	
	public int getCurrentIndex() {
		return currentPlayer ;
	}
}
