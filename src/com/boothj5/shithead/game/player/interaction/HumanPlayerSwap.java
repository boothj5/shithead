package com.boothj5.shithead.game.player.interaction;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.SwapResponse;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public final class HumanPlayerSwap extends PlayerSwap {
    
    private final ShitheadCli cli ;
    
    public HumanPlayerSwap(ShitheadGame game, Player player, ShitheadCli cli) {
        this.game = game ;
        this.player = player ;
        this.cli = cli ;
    }
    
    public void swap() {
        cli.showPlayerSwap(game, player) ;

        boolean wantsToSwap = cli.requestIfWantsToSwapCards(player.getName()) ;
        while (wantsToSwap) {
            int cardFromHand = cli.requestCardFromHandToSwap(game.getNumCardsPerHand()) ;
            int cardFromPile = cli.requestCardFromPileToSwap(game.getNumCardsPerHand()) ;
            SwapResponse response = new SwapResponse(cardFromHand, cardFromPile) ;
            player.swapCards(response) ;
            cli.showPlayerSwap(game, player) ;
            wantsToSwap = cli.requestIfWantsToSwapCards(player.getName()) ;
        }
    }
}
