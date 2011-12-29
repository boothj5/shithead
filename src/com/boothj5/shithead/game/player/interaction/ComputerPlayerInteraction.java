package com.boothj5.shithead.game.player.interaction;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;

public final class ComputerPlayerInteraction extends PlayerInteraction {
    private final ShitheadGame game ;
    
    public ComputerPlayerInteraction(final Player player, final ShitheadGame game) {
        this.player = player ;
        this.game = game ;
    }
    
    public void swap() throws ShitheadException {
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
    
    public void move() throws ShitheadException {
        final Player currentPlayer = game.getCurrentPlayer() ;
        final PlayerHelper helper = game.getPlayerHelper() ;
        List<Integer> cardChoice = new ArrayList<Integer>() ;

        if (currentPlayer.playingFromFaceUp()) 
            cardChoice = currentPlayer.askCardChoiceFromFaceUp(helper) ;                        
        else // play from hand
            cardChoice = currentPlayer.askCardChoiceFromHand(helper) ;                      
            
        if (game.validMove(cardChoice)) 
            game.makeMove(cardChoice) ;
        else
            throw new ShitheadException("Computer player chose invalid move") ;

        game.moveToNextPlayer() ;
    }

    public void faceDownMove() {
        final List<Integer> cardChoice = new ArrayList<Integer>() ;
        cardChoice.add(0) ;

        if (game.validMove(cardChoice)) 
            game.makeMove(cardChoice) ;
        else 
            game.playerPickUpPileAndFaceDownCard(cardChoice.get(0)) ;
        
        game.moveToNextPlayer() ;
    }    
}
