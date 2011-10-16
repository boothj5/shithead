package com.boothj5.shithead.game.player.interaction;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.SwapResponse;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public final class HumanPlayerInteraction {
    public static void swap(final ShitheadCli cli, final ShitheadGameDetails details, final Player player) {
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

    public static void move(final ShitheadCli cli, final ShitheadGame game) {
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

    public static void faceDownMove(final ShitheadCli cli, final ShitheadGame game) {
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
