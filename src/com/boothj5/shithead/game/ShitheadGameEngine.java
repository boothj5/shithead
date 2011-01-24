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
		
		// while no loser
		while (continueGame) {
			details = game.getGameDetails() ;
			console.showGame(details) ;
			console.showLastMove(details) ;
			console.line() ;

		    Player currentPlayer = details.getCurrentPlayer() ;
		    List<Integer> cardChoice = null ;
		    List<Card> cardsToPlay = null ;
		    
		    // if player can possibly lay any cards
		    if (game.currentPlayerCanPlay()) {
		    	//Player.Hand handToPlayFrom = game.getHandToPlayFrom() ;
		    	
		    	// if computer player
		    	if (currentPlayer instanceof ComputerPlayer) {
		    		
		    		// if face down, pick for computer, as we don't want any cheating!!
		    		if (game.playingFromFaceDown()) {
		    			cardChoice = new ArrayList<Integer>() ;
		    			cardChoice.add(0) ;

			    		cardsToPlay = convertChoicesToCards(cardChoice, 
								details.getCurrentPlayer().getFaceDown()) ;
		    			
			    		// play if valid card
		    			if (game.checkValidMove(cardsToPlay)) 
				    		game.play(cardsToPlay) ;
		    			// pick up if not
		    			else 
		    				game.playerPickUpPileAndFaceDownCard(cardsToPlay.get(0)) ;
				    	
		    			// move game on
		    			game.moveToNextPlayer() ;
				    	continueGame = game.canContinueGame() ;			    		
		    		}
		    		// otherwise ask it to pick
		    		else {
		    			if (game.playingFromFaceUp()) {
			    			cardChoice = currentPlayer.askCardChoiceFromFaceUp(details) ;				    	
			    			cardsToPlay = convertChoicesToCards(cardChoice, 
			    									details.getCurrentPlayer().getFaceUp()) ; 
			    		}
			    		else { // play from hand
			    			cardChoice = currentPlayer.askCardChoiceFromHand(details) ;				    	
		    				cardsToPlay = convertChoicesToCards(cardChoice, 
		    									details.getCurrentPlayer().getHand()) ; 
			    		}
			    			
		    			// if its a valid move play
		    			if (game.checkValidMove(cardsToPlay)) 
		    				game.play(cardsToPlay) ;
		    			// otherwise, computers mustn't try invalid moves when we ask them
		    			// we could get stuck asking them forever
		    			else
		    				throw new Exception("Computer player chose invalid move") ;

		    			// move game on
		    			game.moveToNextPlayer() ;
		    			continueGame = game.canContinueGame() ;
		    		}
		    	}
		    	// else if human player
		    	else {
		    		String playerName = details.getCurrentPlayer().getName() ;
		    		
		    		int handSize = game.getHandSize() ;
		    		
		    		// if playing from face down
		    		if (game.playingFromFaceDown()) {
		    			int cardChoiceFromFaceDown = console.requestFromFaceDown(playerName, handSize) ;
		    			
		    			cardsToPlay = new ArrayList<Card>() ;
		    			cardsToPlay.add(details.getCurrentPlayer().getFaceDown().get(cardChoiceFromFaceDown)) ;
		    			
		    			// play if valid card
		    			if (game.checkValidMove(cardsToPlay)) {
				    		console.showHandDownOk(playerName, cardsToPlay.get(0)) ;
		    				game.play(cardsToPlay) ;
		    			}
		    			// pick up if not
		    			else {
				    		console.showHandDownNotOk(playerName, cardsToPlay.get(0)) ;
		    				game.playerPickUpPileAndFaceDownCard(cardsToPlay.get(0)) ;
		    			}
				    	// move game on
		    			game.moveToNextPlayer() ;
				    	continueGame = game.canContinueGame() ;
		    		}
		    		// if from hand or face up
		    		else {
		    			cardChoice = console.requestMove(playerName, handSize, false) ;
		    			if (game.playingFromFaceUp()) 
		    				cardsToPlay = convertChoicesToCards(cardChoice, 
		    						details.getCurrentPlayer().getFaceUp()) ;
		    			
		    			else 
		    				cardsToPlay = convertChoicesToCards(cardChoice, 
		    						details.getCurrentPlayer().getHand()) ;
		    				
			    		boolean validMove = game.checkValidMove(cardsToPlay) ;
			    		
			    		// we know there is a valid move, since we've checked, so loop until they pick it
			    		while (!validMove) {
			    			cardChoice = console.requestMove(playerName, handSize, true) ;
			    			if (game.playingFromFaceUp()) 
			    				cardsToPlay = convertChoicesToCards(cardChoice, 
			    						details.getCurrentPlayer().getFaceUp()) ;
			    			
			    			else 
			    				cardsToPlay = convertChoicesToCards(cardChoice, 
			    						details.getCurrentPlayer().getHand()) ;

			    			validMove = game.checkValidMove(cardsToPlay) ;
			    		}
	
			    		// once they've picked, play and move game on
			    		game.play(cardsToPlay) ;
				    	game.moveToNextPlayer() ;
				    	continueGame = game.canContinueGame() ;
		    		}
		    	}
		    }
		    // current player cannot actually play
		    else {
	    		String playerName = details.getCurrentPlayer().getName() ;
		    	console.waitOnUser("UH OH, " + playerName + " has to pick up, press enter!") ;
		    	
	    		// make them pick up and move game on
		    	game.playerPickUpPile() ;
		    	game.moveToNextPlayer() ;
		    	continueGame = game.canContinueGame() ;
		    }
		}
	}
	
	private List<Card> convertChoicesToCards(List<Integer> cardChoice, List<Card> cardsInHand) {
		List<Card> returnCards = new ArrayList<Card>() ;
		for (int cardIndex : cardChoice) {
			returnCards.add(cardsInHand.get(cardIndex)) ;
		}
		return returnCards ;
	}
	
	private void end() throws Exception{
		
		String shithead = game.getShithead() ;
		
		console.showGameOver(shithead) ;
		
	}
}
