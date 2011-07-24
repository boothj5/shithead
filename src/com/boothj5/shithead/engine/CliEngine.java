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
	
	private void showGame(boolean wait) {
		ShitheadGameDetails details = game.getGameDetails() ;
		cli.showGame(details, true) ;
		cli.showLastMove(details) ;
		if (wait) 
			cli.waitPressEnter() ;
		else
			cli.line() ;
	}
	
	@Override
	public void play() throws ShitheadException {
		
		// while no loser
		while (game.canContinueGame()) {
			showGame(false) ;

		    Player currentPlayer = game.getCurrentPlayer() ;
		    
		    if (game.currentPlayerCanPlay()) {
		    	if (currentPlayer.isComputer()) {
		    		if (game.playingFromFaceDown())
		    		    computerPlayerFaceDownMove();
		    		else 
				    	computerPlayerMove(currentPlayer);

		    		showGame(true) ;
		    	}
		    	
		    	else { // human player
		    		String playerName = game.getCurrentPlayer().getName() ;
		    		
		    		int handSize = game.getHandSize() ;
		    		
		    		// if playing from face down
		    		if (game.playingFromFaceDown()) {
		    		    List<Integer> cardChoice = new ArrayList<Integer>() ;
		    			int cardChoiceFromFaceDown = cli.requestFromFaceDown(playerName, handSize) ;
		    			cardChoice.add(cardChoiceFromFaceDown) ;
		    			
		    			// play if valid card
		    			if (game.checkValidMove(cardChoice)) {
				    		cli.showHandDownOk(playerName, game.getCurrentPlayer().getFaceDown().get(cardChoiceFromFaceDown)) ;
		    				game.play(cardChoice) ;
		    			}
		    			// pick up if not
		    			else {
				    		cli.showHandDownNotOk(playerName, game.getCurrentPlayer().getFaceDown().get(cardChoiceFromFaceDown)) ;
		    				game.playerPickUpPileAndFaceDownCard(cardChoiceFromFaceDown) ;
		    			}
				    	// move game on
		    			game.moveToNextPlayer() ;
		    		}
		    		// if from hand or face up
		    		else {
		    		    List<Integer> cardChoice = new ArrayList<Integer>() ;
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
	    		String playerName = game.getCurrentPlayer().getName() ;
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
