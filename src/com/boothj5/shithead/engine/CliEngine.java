package com.boothj5.shithead.engine;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.player.*;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public final class CliEngine extends ShitheadEngine {
    final ShitheadCli cli ;

    public CliEngine(final ShitheadCli cli) {
        this.cli = cli ;
    }
    
    @Override
	public void globalInit(final String[] args) {
	    numGames = 1 ;
	}
	
	@Override
	public void init() throws ShitheadException {
		int numPlayers ;
		int numCards ;
		final List<String> playerNames = new ArrayList<String>() ;
		final List<String> playerTypes = new ArrayList<String>() ;

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
		cli.showGame(game.getGameDetails(), true);
		cli.showCardsDealtMessage() ;
		cli.waitOnUser() ;
	}

	@Override
	public void swap() throws ShitheadException {
		ShitheadGameDetails details = game.getGameDetails() ;

		for (Player player : details.getPlayers()) {
			if (player.isComputer()) 
		        computerPlayerSwap(player) ;
			else 
			    humanPlayerSwap(details, player, cli) ;
		}
		
		cli.showGame(game.getGameDetails(), true) ;
	}

	@Override
	public void firstMove() {
		game.firstMove() ;
		final ShitheadGameDetails details = game.getGameDetails() ;
		cli.showGame(details, true) ;
		cli.showLastMove(details) ;
		cli.line() ;
		cli.showNextMoveMessage() ;
		cli.waitOnUser() ;
	}
	
	@Override
	public void play() throws ShitheadException {
		while (game.canContinueGame()) {
		    cli.showGameWithWait(game.getGameDetails(), false) ;

		    final Player currentPlayer = game.getCurrentPlayer() ;
		    
		    if (game.currentPlayerCanPlay()) {
		    	if (currentPlayer.isComputer()) {
		    		if (game.playingFromFaceDown())
		    		    computerPlayerFaceDownMove();
		    		else 
				    	computerPlayerMove();

		    		cli.showGameWithWait(game.getGameDetails(), true) ;
		    	}
		    	else // human player
		    		if (game.playingFromFaceDown())
		    		    humanPlayerFaceDownMove();
		    		else 
		    		    humanPlayerMove();
		    }
		    else {
	    		showPickupAndWait();
		    	game.playerPickUpPile() ;
		    	game.moveToNextPlayer() ;
		    }
		}
	}

	@Override
	public void end() throws ShitheadException {
		cli.showGameOver(game.getShithead()) ;
	}

    @Override
    public void globalEnd() {
    }
	
	
	@Override
    public void error(final ShitheadException e) {
        cli.bail(e, game.getGameDetails()) ;
    }

    private static void humanPlayerSwap(final ShitheadGameDetails details, final Player player, final ShitheadCli cli) {
        cli.showPlayerSwap(details, player) ;

        boolean wantsToSwap = cli.requestIfWantsToSwapCards(player.getName()) ;
        while (wantsToSwap) {
            final int cardFromHand = cli.requestCardFromHandToSwap(details.getNumCardsPerHand()) ;
            final int cardFromPile = cli.requestCardFromPileToSwap(details.getNumCardsPerHand()) ;
            final SwapResponse response = new SwapResponse(cardFromHand, cardFromPile) ;
            player.swapCards(response) ;
            cli.showPlayerSwap(details, player) ;
            wantsToSwap = cli.requestIfWantsToSwapCards(player.getName()) ;
        }
    }

    private void showPickupAndWait() {
        final String playerName = game.getCurrentPlayer().getName() ;
        cli.showPlayerPickupMessage(playerName) ;
        cli.waitOnUser() ;
    }

    private void humanPlayerMove() {
        final int handSize = game.getHandSize() ;
        final String playerName = game.getCurrentPlayer().getName() ;
        List<Integer> cardChoice = cli.requestMove(playerName, handSize, false) ;
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

    private void humanPlayerFaceDownMove() {
        final int handSize = game.getHandSize() ;
        final String playerName = game.getCurrentPlayer().getName() ;
        List<Integer> cardChoice = new ArrayList<Integer>() ;

        final int cardChoiceFromFaceDown = cli.requestFromFaceDown(playerName, handSize) ;
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
}
