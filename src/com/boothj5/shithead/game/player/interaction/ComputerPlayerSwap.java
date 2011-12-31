package com.boothj5.shithead.game.player.interaction;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.SwapResponse;

public final class ComputerPlayerSwap extends PlayerSwap {

    public ComputerPlayerSwap(ShitheadGame game, Player player) {
        this.game = game ;
        this.player = player ;
    }
    
    public void swap() throws ShitheadException {
        if (player.askSwapMore()) {
            SwapResponse response = player.askSwapChoice() ;
            player.swapCards(response) ;
            while (player.askSwapMore()) {
                response = player.askSwapChoice() ;
                player.swapCards(response) ;
            }
        }
    }
}
