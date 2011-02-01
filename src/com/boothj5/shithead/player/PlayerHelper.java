package com.boothj5.shithead.player;

import java.util.List;
import java.util.Stack;

import com.boothj5.shithead.card.Card;

public class PlayerHelper {
//	private final List<Player> players ;
//	private final int currentPlayer ;
//	private final LastMove lastmove ;

	private final int numberOnDeck ;
	private final int numPlayersInGame ;
	private final int numPlayersStillPlaying ;
	private final int numCardsPerHand ;
	private final int currentPlayer ;
	private final Stack<Card> pile  ;
	private final List<Card> burnt ;
	private final List<PlayerSummary> players ;

	public PlayerHelper(final int numberOnDeck, 
						final int numPlayersInGame, 
						final int numPlayersStillPlaying, 
						final int numCardsPerHand,
						final int currenPlayer, 
						final Stack<Card> pile, 
						final List<Card> burnt,
						final List<PlayerSummary> players) {
		this.numberOnDeck = numberOnDeck ;
		this.numPlayersInGame = numPlayersInGame ;
		this.numPlayersStillPlaying = numPlayersStillPlaying ;
		this.numCardsPerHand = numCardsPerHand ;
		this.currentPlayer = currenPlayer ;
		this.pile = pile ;
		this.burnt = burnt ;
		this.players = players ;
	}

	public int getNumberOnDeck() {
		return numberOnDeck;
	}

	public int getNumPlayersInGame() {
		return numPlayersInGame;
	}

	public int getNumPlayersStillPlaying() {
		return numPlayersStillPlaying;
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

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public List<PlayerSummary> getPlayers() {
		return players;
	}

}
