package com.boothj5.shithead.game.player.interaction;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerHelper;

public final class ComputerPlayerMove extends PlayerMove {

    public ComputerPlayerMove(ShitheadGame game) {
        this.game = game ;
    }

    public void move() throws ShitheadException {
        Player player = game.getCurrentPlayer() ;
        final PlayerHelper helper = game.getPlayerHelper() ;
        List<Integer> cardChoice = new ArrayList<Integer>() ;

        if (player.playingFromFaceUp()) 
            cardChoice = player.askCardChoiceFromFaceUp(helper) ;                        
        else
            cardChoice = player.askCardChoiceFromHand(helper) ;                      

        if (!game.validMove(cardChoice)) 
            throw new ShitheadException("Computer player chose invalid move") ;

        game.makeMove(cardChoice) ;
    }

    public void faceDownMove() {
        Player player = game.getCurrentPlayer() ;
        final PlayerHelper helper = game.getPlayerHelper() ;
        final List<Integer> cardChoice = new ArrayList<Integer>() ;
        int card = player.askCardChoiceFromFaceDown(helper) ;
        
        cardChoice.add(card) ;

        if (game.validMove(cardChoice)) 
            game.makeMove(cardChoice) ;
        else 
            game.playerPickUpPileAndFaceDownCard(cardChoice.get(0)) ;
    }    
}
