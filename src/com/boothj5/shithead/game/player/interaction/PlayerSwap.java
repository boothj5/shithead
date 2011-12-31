package com.boothj5.shithead.game.player.interaction;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public abstract class PlayerSwap {
    
    protected ShitheadGame game ;
    protected Player player ;
    
    public static PlayerSwap forPlayer(Player player, ShitheadGame game, ShitheadCli cli) {
        if (player.isComputer())
            return new ComputerPlayerSwap(game, player) ;
        else
            return new HumanPlayerSwap(game, player, cli) ;
    }
    
    public abstract void swap() throws ShitheadException ;
}
