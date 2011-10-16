package com.boothj5.shithead.game.player.interaction;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;

public final class ComputerPlayerInteraction {
    public static final void swap(final Player player) throws ShitheadException {
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
    
    public static final void move(ShitheadGame game) throws ShitheadException {
        final Player currentPlayer = game.getCurrentPlayer() ;
        final PlayerHelper helper = game.getPlayerHelper() ;
        List<Integer> cardChoice = new ArrayList<Integer>() ;

        if (currentPlayer.playingFromFaceUp()) 
            cardChoice = currentPlayer.askCardChoiceFromFaceUp(helper) ;                        
        else // play from hand
            cardChoice = currentPlayer.askCardChoiceFromHand(helper) ;                      
            
        if (game.checkValidMove(cardChoice)) 
            game.play(cardChoice) ;
        else
            throw new ShitheadException("Computer player chose invalid move") ;

        game.moveToNextPlayer() ;
    }

    public static final void faceDownMove(ShitheadGame game) {
        final List<Integer> cardChoice = new ArrayList<Integer>() ;
        cardChoice.add(0) ;

        if (game.checkValidMove(cardChoice)) 
            game.play(cardChoice) ;
        else 
            game.playerPickUpPileAndFaceDownCard(cardChoice.get(0)) ;
        
        game.moveToNextPlayer() ;
    }    
}
