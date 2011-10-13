package com.boothj5.shithead.engine;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;

public abstract class ShitheadEngine {
    protected ShitheadGame game ;
    protected int numGames ;
    
    protected static final void computerPlayerSwap(final Player player) throws ShitheadException {
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
    
	protected final void computerPlayerMove() throws ShitheadException {
		final Player currentPlayer = game.getCurrentPlayer() ;
		final PlayerHelper helper = game.getPlayerHelper() ;
		List<Integer> cardChoice = new ArrayList<Integer>() ;

		if (game.playingFromFaceUp()) 
			cardChoice = currentPlayer.askCardChoiceFromFaceUp(helper) ;				    	
		else // play from hand
			cardChoice = currentPlayer.askCardChoiceFromHand(helper) ;				    	
			
		if (game.checkValidMove(cardChoice)) 
			game.play(cardChoice) ;
		else
			throw new ShitheadException("Computer player chose invalid move") ;

		game.moveToNextPlayer() ;
	}

	protected final void computerPlayerFaceDownMove() {
		final List<Integer> cardChoice = new ArrayList<Integer>() ;
		cardChoice.add(0) ;

		if (game.checkValidMove(cardChoice)) 
			game.play(cardChoice) ;
		else 
			game.playerPickUpPileAndFaceDownCard(cardChoice.get(0)) ;
		
		game.moveToNextPlayer() ;
	}    
    
    public final int getNumGames() {
        return numGames ;
    }

    public abstract void globalInit(final String[] args) throws ShitheadException;
    public abstract void init() throws ShitheadException;
    public abstract void deal() ;
    public abstract void swap() throws ShitheadException ;
    public abstract void firstMove() ;
    public abstract void play() throws ShitheadException;
    public abstract void end() throws ShitheadException;
    public abstract void globalEnd() ;
    public abstract void error(final ShitheadException e) ;
}	
