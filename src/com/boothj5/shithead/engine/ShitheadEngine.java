package com.boothj5.shithead.engine;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.SwapResponse;

public abstract class ShitheadEngine {
    ShitheadGame game ;
    int numGames ;
    
    protected static void computerPlayerSwap(Player player) {
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
    
    public int getNumGames() {
        return numGames ;
    }

    public abstract void globalInit(String[] args) throws ShitheadException;
    public abstract void init() throws ShitheadException;
    public abstract void deal() ;
    public abstract void swap() ;
    public abstract void firstMove() ;
    public abstract void play() throws ShitheadException;
    public abstract void end() throws ShitheadException;
    public abstract void globalEnd() ;
    public abstract void error(ShitheadException e) ;
}	
