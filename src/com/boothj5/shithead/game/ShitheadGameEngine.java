package com.boothj5.shithead.game;

import java.io.Console ;
import java.util.* ;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.player.*;

public class ShitheadGameEngine {

	ShitheadGame game ;
	ShitheadConsole console = new ShitheadConsole() ;
	
	public void playShithead() throws Exception {
		init() ;
		deal() ;
		swap() ;
		firstMove() ;
		play() ;
		end() ;
	}
	
	private void init() throws Exception {
		int numPlayers ;
		int numCards ;
		List<String> playerNames = new ArrayList<String>() ;
		List<String> playerTypes = new ArrayList<String>() ;

		console.clearScreen() ;
		console.welcome() ;

		numPlayers = console.requestNumPlayers() ;
		numCards = console.requestNumCardsPerHand() ;
		
		String name, type ;
		for (int i = 1 ; i <= numPlayers ; i++) { 
			name = console.requestPlayerName(i) ;
			playerNames.add(name) ;
				
			type = console.requestPlayerType(name) ;
			playerTypes.add(type) ;
		}
		
		game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCards) ;		
	}
	
	private void deal() {
		game.deal() ;
		
		ShitheadGameDetails details = game.getGameDetails() ;
		console.showGame(details);
		
		console.waitOnUser("Cards dealt, press enter:") ;
	}

	private void swap() {
		ShitheadGameDetails details = game.getGameDetails() ;
		
		for (Player player : details.getPlayers()) {
			
			// ask player if they want to swap cards
			Boolean wantsToSwap = player.askSwapMore() ;
			
			// if we got null, its a human and we need to interact
			if (null == wantsToSwap) {
				console.clearScreen() ;
				console.showPlayerName(details, player, false);
				console.line() ;
				console.showHand(details, player, false) ;
				console.showFaceUp(player) ;
				console.line() ;
				
				// auto boxing
				wantsToSwap = console.requestIfWantsToSwapCards(player.getName()) ;
				
				while (wantsToSwap) {
					int cardFromHand = console.requestCardFromHandToSwap(details.getNumCardsPerHand()) ;
					int cardFromPile = console.requestCardFromPileToSwap(details.getNumCardsPerHand()) ;

					SwapResponse response = new SwapResponse(cardFromHand, cardFromPile) ;
					player.swapCards(response) ;

					console.clearScreen() ;
					console.line() ;
					console.showHand(details, player, false) ;
					console.showFaceUp(player) ;
					console.line() ;
					
					wantsToSwap = console.requestIfWantsToSwapCards(player.getName()) ;
				}
			}

			// if we get a true of false, its a computer player
			else if (wantsToSwap) {
				SwapResponse response = player.askSwapChoice() ;
				player.swapCards(response) ;
				
				// again and keep swapping until 'n'
				wantsToSwap = player.askSwapMore() ;
				while (wantsToSwap) {
					response = player.askSwapChoice() ;
					player.swapCards(response) ;
					wantsToSwap = player.askSwapMore() ;
				}
			}
		}
		
		details = game.getGameDetails() ;
		console.showGame(details) ;
	}
	
	private void firstMove() {
		game.firstMove() ;
		ShitheadGameDetails details = game.getGameDetails() ;

		console.showGame(details) ;
		console.showLastMove(details) ;
		console.line() ;
		console.waitOnUser("Press enter key for next players move:") ;
	}
	
	private void play() throws Exception {
		ShitheadGameDetails details ;

		boolean continueGame = true ;
		
		while (continueGame) {
			details = game.getGameDetails() ;
			console.showGame(details) ;
			console.showLastMove(details) ;
			console.line() ;

			details = game.getGameDetails() ;
		    Player currentPlayer = details.getCurrentPlayer() ;
			
		    if (game.currentPlayerCanPlay()) {
		    	Player.Hand handToPlayFrom = game.getHandToPlayFrom() ;
		    	
		    	if (currentPlayer instanceof ComputerPlayer) {
		    		List<Integer> choices = null;
		    		
		    		// if face down, pick for computer, as we don't want any cheating!!
		    		if (handToPlayFrom.equals(Player.Hand.FACEDOWN)) {
		    			choices = new ArrayList<Integer>() ;
		    			choices.add(0) ;
		    		}
		    		// otherwise ask it
		    		else {
			    		choices = currentPlayer.askCardChoiceFromHand(details, handToPlayFrom) ;
		    		}				    	
		    		// for the moment just get the first one
		    		Card cardChoice = currentPlayer.getHand(handToPlayFrom).get(choices.get(0)) ;
		    		List<Card> cardsToPlay = new ArrayList<Card>() ;
		    		cardsToPlay.add(cardChoice) ;
		    		
		    		if (game.checkValidMove(cardsToPlay)) 
		    			game.play(cardsToPlay) ;
		    		else
		    			throw new Exception("Computer player chose invalid move, exiting rahter than looping forever") ;
			    	game.moveToNextPlayer() ;
			    	continueGame = game.canContinueGame() ;
		    	}
		    	else { // HumanPlayer
		    		List<Integer> choices = null;

		    		String playerName = details.getCurrentPlayer().getName() ;
		    		int handSize = details.getCurrentPlayer().getHand(handToPlayFrom).size() ;
		    		
		    		choices = console.requestMove(playerName, handToPlayFrom, handSize, false) ;
		    		// for the moment just get the first one
		    		Card cardChoice = currentPlayer.getHand(handToPlayFrom).get(choices.get(0)) ;
		    		List<Card> cardsToPlay = new ArrayList<Card>() ;
		    		cardsToPlay.add(cardChoice) ;
		    		
		    		boolean validMove = game.checkValidMove(cardsToPlay) ;
		    		
		    		while (!validMove) {
		    			choices = console.requestMove(playerName, handToPlayFrom, handSize, true) ;
			    		// for the moment just get the first one
			    		cardChoice = currentPlayer.getHand(handToPlayFrom).get(choices.get(0)) ;
			    		cardsToPlay = new ArrayList<Card>() ;
			    		cardsToPlay.add(cardChoice) ;
			    		validMove = game.checkValidMove(cardsToPlay) ;
		    		}
		    		game.play(cardsToPlay) ;
			    	game.moveToNextPlayer() ;
			    	continueGame = game.canContinueGame() ;
		    	}
		    }
		    else { // current player cannot play
	    		String playerName = details.getCurrentPlayer().getName() ;
		    	console.waitOnUser("UH OH, " + playerName + " has to pick up, press enter!") ;
		    	
		    	game.playerPickUpPile() ;
		    	
		    	game.moveToNextPlayer() ;
		    	continueGame = game.canContinueGame() ;
		    }
		}
	}
	
	private void end() {
		
	}
}
