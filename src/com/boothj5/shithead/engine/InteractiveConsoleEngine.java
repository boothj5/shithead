package com.boothj5.shithead.engine;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.*;

public class InteractiveConsoleEngine implements ShitheadEngine {

	ShitheadGame game ;
	ShitheadConsole console = new ShitheadConsole() ;
	
	public void runEngine(String[] args) {
		try {
			init() ;
			deal() ;
			swap() ;
			firstMove() ;
			play() ;
			end() ; 
		} catch (Exception e) {
			ShitheadGameDetails details = game.getGameDetails() ;
			console.bail(e, details) ;
		}
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
		
		console.showGame(details, true);
		console.showCardsDealtMessage() ;
		console.waitOnUser() ;
	}

	private void swap() {
		ShitheadGameDetails details = game.getGameDetails() ;
		
		for (Player player : details.getPlayers()) {
			
			if (player.isComputer()) {
				boolean wantsToSwap = player.askSwapMore() ;
				if (wantsToSwap) {
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
			else { // human player
				console.clearScreen() ;
				console.showPlayerName(details, player, false);
				console.line() ;
				console.showHand(details, player, false) ;
				console.showFaceUp(player) ;
				console.line() ;
				
				boolean wantsToSwap = console.requestIfWantsToSwapCards(player.getName()) ;
				
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
		}
		
		details = game.getGameDetails() ;
		console.showGame(details, true) ;
	}
	
	private void firstMove() {
		game.firstMove() ;
		ShitheadGameDetails details = game.getGameDetails() ;

		console.showGame(details, true) ;
		console.showLastMove(details) ;
		console.line() ;
		console.showNextMoveMessage() ;
		console.waitOnUser() ;
	}
	
	private void play() throws Exception {
		ShitheadGameDetails details ;
		
		// while no loser
		while (game.canContinueGame()) {
			details = game.getGameDetails() ;
			console.showGame(details, true) ;
			console.showLastMove(details) ;
			console.line() ;

		    Player currentPlayer = details.getCurrentPlayer() ;
		    List<Integer> cardChoice = new ArrayList<Integer>() ;
		    
		    // if player can possibly lay any cards
		    if (game.currentPlayerCanPlay()) {

		    	// if computer player
		    	if (game.isCurrentPlayerComputerPlayer()) {
		    		
		    		// if face down, pick for computer, as we don't want any cheating!!
		    		if (game.playingFromFaceDown()) {
		    			cardChoice.add(0) ;

		    			// play if valid card
		    			if (game.checkValidMove(cardChoice)) 
				    		game.play(cardChoice) ;
		    			// pick up if not
		    			else 
		    				game.playerPickUpPileAndFaceDownCard(cardChoice.get(0)) ;
				    	
		    			// move game on
		    			game.moveToNextPlayer() ;
		    		}
		    		// otherwise ask it to choose a card
		    		else {
				    	PlayerHelper helper = game.getPlayerHelper() ;
		    			details = game.getGameDetails() ;

		    			if (game.playingFromFaceUp()) 
			    			cardChoice = currentPlayer.askCardChoiceFromFaceUp(helper) ;				    	
			    		else // play from hand
			    			cardChoice = currentPlayer.askCardChoiceFromHand(helper) ;				    	
			    			
		    			// if its a valid move play
		    			if (game.checkValidMove(cardChoice)) 
		    				game.play(cardChoice) ;

		    			// otherwise, computers mustn't try invalid moves when we ask them
		    			// we could get stuck asking them forever
		    			else
		    				throw new Exception("Computer player chose invalid move") ;

		    			// move game on
		    			game.moveToNextPlayer() ;
		    		}
		    		details = game.getGameDetails() ;
		    		console.showGame(details, true) ;
		    		console.showLastMove(details) ; 
		    		console.waitPressEnter() ;
		    	}
		    	// else if human player
		    	else {
		    		String playerName = details.getCurrentPlayer().getName() ;
		    		
		    		int handSize = game.getHandSize() ;
		    		
		    		// if playing from face down
		    		if (game.playingFromFaceDown()) {
		    			int cardChoiceFromFaceDown = console.requestFromFaceDown(playerName, handSize) ;
		    			cardChoice.add(cardChoiceFromFaceDown) ;
		    			
		    			// play if valid card
		    			if (game.checkValidMove(cardChoice)) {
				    		console.showHandDownOk(playerName, details.getCurrentPlayer().getFaceDown().get(cardChoiceFromFaceDown)) ;
		    				game.play(cardChoice) ;
		    			}
		    			// pick up if not
		    			else {
				    		console.showHandDownNotOk(playerName, details.getCurrentPlayer().getFaceDown().get(cardChoiceFromFaceDown)) ;
		    				game.playerPickUpPileAndFaceDownCard(cardChoiceFromFaceDown) ;
		    			}
				    	// move game on
		    			game.moveToNextPlayer() ;
		    		}
		    		// if from hand or face up
		    		else {
		    			cardChoice = console.requestMove(playerName, handSize, false) ;
			    		boolean validMove = game.checkValidMove(cardChoice) ;
			    		
			    		// we know there is a valid move, since we've checked, so loop until they pick it
			    		while (!validMove) {
			    			cardChoice = console.requestMove(playerName, handSize, true) ;
			    			validMove = game.checkValidMove(cardChoice) ;
			    		}
	
			    		// once they've picked, play and move game on
			    		game.play(cardChoice) ;
				    	game.moveToNextPlayer() ;
		    		}
		    	}
		    }
		    // current player cannot actually play
		    else {
	    		String playerName = details.getCurrentPlayer().getName() ;
	    		console.showPlayerPickupMessage(playerName) ;
		    	console.waitOnUser() ;
		    	
	    		// make them pick up and move game on
		    	game.playerPickUpPile() ;
		    	game.moveToNextPlayer() ;
		    }
		}
	}

	
	private void end() throws Exception{
		
		String shithead = game.getShithead() ;
		
		console.showGameOver(shithead) ;
		
	}
}
