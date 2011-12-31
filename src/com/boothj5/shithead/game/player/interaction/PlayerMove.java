package com.boothj5.shithead.game.player.interaction;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public abstract class PlayerMove {

    protected ShitheadGame game ;

    public static PlayerMove forCurrentPlayer(ShitheadGame game, ShitheadCli cli) {
        Player player = game.getCurrentPlayer() ;
        if (player.isComputer())
            return new ComputerPlayerMove(game) ;
        else
            return new HumanPlayerMove(game, cli) ;

    }

    public void makeMove() throws ShitheadException {
        Player player = game.getCurrentPlayer() ;
        if (player.playingFromFaceDown())
            faceDownMove() ;
        else
            move() ;
    }

    public abstract void move() throws ShitheadException ;

    public abstract void faceDownMove() ;
}
