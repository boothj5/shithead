package com.boothj5.shithead.engine;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.player.*;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class CliEngine extends ShitheadEngine {
    ShitheadCli cli = new ShitheadCli() ;

	@Override
	public void globalInit(String[] args) {
	    numGames = 1 ;
	}
	
	@Override
	public void init() throws ShitheadException {
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
	
	@Override
	public void deal() {
		game.deal() ;
		ShitheadGameDetails details = game.getGameDetails() ;
		
		cli.showGame(details, true);
		cli.showCardsDealtMessage() ;
		cli.waitOnUser() ;
	}

	@Override
	public void swap() {
		ShitheadGameDetails details = game.getGameDetails() ;

		for (Player player : details.getPlayers()) {
			if (player.isComputer()) 
		        computerPlayerSwap(player) ;
			else 
			    humanPlayerSwap(details, player, cli) ;
		}
		
		details = game.getGameDetails() ;
		cli.showGame(details, true) ;
	}

	@Override
	public void firstMove() {
		game.firstMove() ;
		ShitheadGameDetails details = game.getGameDetails() ;

		cli.showGame(details, true) ;
		cli.showLastMove(details) ;
		cli.line() ;
		cli.showNextMoveMessage() ;
		cli.waitOnUser() ;
	}
	
	@Override
	public void play() throws ShitheadException {
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
		    				throw new ShitheadException("Computer player chose invalid move") ;

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

	
	@Override
	public void end() throws ShitheadException {
		String shithead = game.getShithead() ;
		cli.showGameOver(shithead) ;
	}

    @Override
    public void globalEnd() {
    }
	
	
	@Override
    public void error(ShitheadException e) {
        ShitheadGameDetails details = game.getGameDetails() ;
        cli.bail(e, details) ;
    }

    private static void humanPlayerSwap(ShitheadGameDetails details, Player player, ShitheadCli cli) {
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
