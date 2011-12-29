package com.boothj5.shithead.game.player.interaction;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.SwapResponse;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public final class HumanPlayerInteraction extends PlayerInteraction {
    
    private final ShitheadGame game ;
    private final ShitheadCli cli ;
    
    public HumanPlayerInteraction(final Player player, final ShitheadGame game, final ShitheadCli cli) {
        this.player = player ;
        this.game = game ; 
        this.cli = cli ;
    }
    
    public void swap() {
        final ShitheadGameDetails details = game.getGameDetails() ;
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

    public void move() {
        final Player currentPlayer = game.getCurrentPlayer() ;
        final int handSize = currentPlayer.getCurrentHandSize() ;
        final String playerName = currentPlayer.getName() ;
        List<Integer> cardChoice = cli.requestMove(playerName, handSize, false) ;
        boolean validMove = game.validMove(cardChoice) ;

        // we know there is a valid move, since we've checked, so loop until they pick it
        while (!validMove) {
            cardChoice = cli.requestMove(playerName, handSize, true) ;
            validMove = game.validMove(cardChoice) ;
        }

        // once they've picked, play and move game on 
        game.makeMove(cardChoice) ;
        game.moveToNextPlayer() ;
    }

    public void faceDownMove() {
        final Player currentPlayer = game.getCurrentPlayer() ;
        final int handSize = currentPlayer.getCurrentHandSize() ;
        final String playerName = currentPlayer.getName() ;
        final List<Integer> cardChoice = new ArrayList<Integer>() ;

        final int cardChoiceFromFaceDown = cli.requestFromFaceDown(playerName, handSize) ;
        cardChoice.add(cardChoiceFromFaceDown) ;
        
        // play if valid card
        if (game.validMove(cardChoice)) {
            cli.showHandDownOk(playerName, game.getCurrentPlayer().getFaceDown().get(cardChoiceFromFaceDown)) ;
            game.makeMove(cardChoice) ;
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
