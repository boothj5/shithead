package com.boothj5.shithead.game;
import java.util.List;
import java.util.Stack;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.card.Deck;
import com.boothj5.shithead.player.Player;


public class ShitheadGameDetails {
	private final List<Player> players ;

	private final Deck deck ;
	private final int numPlayers ;
	private final int numCardsPerHand ;

	private final int currentPlayer ;

	private final Stack<Card> pile ;
	private final List<Card> burnt ;

	public ShitheadGameDetails(List<Player> players, Deck deck, int numPlayers, 
				int numCardsPerHand, int currentPlayer, Stack<Card> pile, List<Card> burnt) {
		this.players = players ;
		this.deck = deck ;
		this.numPlayers = numPlayers ;
		this.numCardsPerHand = numCardsPerHand ;
		this.currentPlayer = currentPlayer ;
		this.pile = pile ;
		this.burnt = burnt ;
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

	public int getCurrentPlayer() {
		return currentPlayer;
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
	
}
