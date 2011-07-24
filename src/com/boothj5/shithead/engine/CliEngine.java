package com.boothj5.shithead.engine;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.player.*;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class CliEngine implements ShitheadEngine {

	ShitheadGame game ;
	int numGames = 1 ;
	ShitheadCli cli = new ShitheadCli() ;
	
	public void runEngine(String[] args) {

       try {
            globalInit(args) ;
            for (int i = 0 ; i < numGames ; i++) {
                init() ;
                deal() ;
                swap() ;
                firstMove() ;
                play() ;
                end() ;
            }
            globalEnd() ;
        } catch (Exception e) {
            ShitheadGameDetails details = game.getGameDetails() ;
            cli.bail(e, details) ;
        }
	}
	
	private void globalInit(String[] args) {
	    
	}
	
	private void globalEnd() {
	    
	}
	
	
	private void init() throws Exception {
		int numPlayers ;
		int numCards ;
		List<String> playerNames = new ArrayList<String>() ;
		List<String> playerTypes = new ArrayList<String>() ;

		cli.clearScreen() ;
		cli.welcome() ;

		numPlayers = cli.requestNumPlayers() ;
		numCards = cli.requestNumCardsPerHand() ;
		
		String name, type ;
		for (int i = 1 ; i <= numPlayers ; i++) { 
			name = cli.requestPlayerName(i) ;
			playerNames.add(name) ;
				
			type = cli.requestPlayerType(name) ;
			playerTypes.add(type) ;
		}
		
		game = new ShitheadGame(numPlayers, playerNames, playerTypes, numCards) ;		
	}
	
	private void deal() {
		game.deal() ;
		ShitheadGameDetails details = game.getGameDetails() ;
		
		cli.showGame(details, true);
		cli.showCardsDealtMessage() ;
		cli.waitOnUser() ;
	}

	private void swap() {
		ShitheadGameDetails details = game.getGameDetails() ;
		
		for (Player player : details.getPlayers()) {
			
			if (player.isComputer()) {
				boolean wantsToSwap = player.askSwapMore() ;
				if (wantsToSwap) {
					SwapResponse response = player.askSwapChoice() ;
					player.swapCards(response) ;
					wantsToSwap = player.askSwapMore() ;
					while (wantsToSwap) {
						response = player.askSwapChoice() ;
						player.swapCards(response) ;
						wantsToSwap = player.askSwapMore() ;
					}
				}
			}
			else { 
				cli.showPlayerSwap(details, player) ;

				boolean wantsToSwap = cli.requestIfWantsToSwapCards(player.getName()) ;
				while (wantsToSwap) {
					int cardFromHand = cli.requestCardFromHandToSwap(details.getNumCardsPerHand()) ;
					int cardFromPile = cli.requestCardFromPileToSwap(details.getNumCardsPerHand()) ;
					SwapResponse response = new SwapResponse(cardFromHand, cardFromPile) ;
					player.swapCards(response) ;
					cli.showPlayerSwap(details, player) ;
					wantsToSwap = cli.requestIfWantsToSwapCards(player.getName()) ;
				}
			}
		}
		
		details = game.getGameDetails() ;
		cli.showGame(details, true) ;
	}
	
	private void firstMove() {
		game.firstMove() ;
		ShitheadGameDetails details = game.getGameDetails() ;

		cli.showGame(details, true) ;
		cli.showLastMove(details) ;
		cli.line() ;
		cli.showNextMoveMessage() ;
		cli.waitOnUser() ;
	}
	
	private void play() throws Exception {
		ShitheadGameDetails details ;
		
		// while no loser
		while (game.canContinueGame()) {
			details = game.getGameDetails() ;
			cli.showGame(details, true) ;
			cli.showLastMove(details) ;
			cli.line() ;

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
		    		cli.showGame(details, true) ;
		    		cli.showLastMove(details) ; 
		    		cli.waitPressEnter() ;
		    	}
		    	// else if human player
		    	else {
		    		String playerName = details.getCurrentPlayer().getName() ;
		    		
		    		int handSize = game.getHandSize() ;
		    		
		    		// if playing from face down
		    		if (game.playingFromFaceDown()) {
		    			int cardChoiceFromFaceDown = cli.requestFromFaceDown(playerName, handSize) ;
		    			cardChoice.add(cardChoiceFromFaceDown) ;
		    			
		    			// play if valid card
		    			if (game.checkValidMove(cardChoice)) {
				    		cli.showHandDownOk(playerName, details.getCurrentPlayer().getFaceDown().get(cardChoiceFromFaceDown)) ;
		    				game.play(cardChoice) ;
		    			}
		    			// pick up if not
		    			else {
				    		cli.showHandDownNotOk(playerName, details.getCurrentPlayer().getFaceDown().get(cardChoiceFromFaceDown)) ;
		    				game.playerPickUpPileAndFaceDownCard(cardChoiceFromFaceDown) ;
		    			}
				    	// move game on
		    			game.moveToNextPlayer() ;
		    		}
		    		// if from hand or face up
		    		else {
		    			cardChoice = cli.requestMove(playerName, handSize, false) ;
			    		boolean validMove = game.checkValidMove(cardChoice) ;
			    		
			    		// we know there is a valid move, since we've checked, so loop until they pick it
			    		while (!validMove) {
			    			cardChoice = cli.requestMove(playerName, handSize, true) ;
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
	    		cli.showPlayerPickupMessage(playerName) ;
		    	cli.waitOnUser() ;
		    	
	    		// make them pick up and move game on
		    	game.playerPickUpPile() ;
		    	game.moveToNextPlayer() ;
		    }
		}
	}

	
	private void end() throws Exception{
		String shithead = game.getShithead() ;
		cli.showGameOver(shithead) ;
	}
}
