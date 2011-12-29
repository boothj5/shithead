package com.boothj5.shithead.game.player.interaction;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public abstract class PlayerInteraction {

    protected Player player ;

    public static PlayerInteraction forPlayer(final Player player, final ShitheadGame game, final ShitheadCli cli) {
        if (player.isComputer())
            return new ComputerPlayerInteraction(player, game) ;
        else
            return new HumanPlayerInteraction(player, game, cli) ;

    }

    public abstract void swap() throws ShitheadException ;

    public abstract void move() throws ShitheadException ;

    public abstract void faceDownMove() ;

    public void makeMove() throws ShitheadException {
        if (player.playingFromFaceDown())
            faceDownMove() ;
        else
            move() ;
    }
}
