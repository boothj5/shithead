package com.boothj5.shithead.engine;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.*;
import com.boothj5.shithead.game.player.interaction.PlayerMove;
import com.boothj5.shithead.game.player.interaction.PlayerSwap;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public class CliEngine extends ShitheadEngine {
    ShitheadCli cli ;

    public CliEngine(ShitheadCli cli) {
        this.cli = cli ;
        numGames = 1 ;
    }

    @Override
    public void init() throws ShitheadException {
        int numPlayers ;
        int numCards ;
        List<String> playerNames = new ArrayList<String>() ;
        List<String> playerTypes = new ArrayList<String>() ;

        cli.clearScreen() ;
        cli.welcome() ;

        numPlayers = cli.requestNumPlayers() ;
        numCards = cli.requestNumCardsPerHand() ;

        String name, type ;
        for (int i = 1 ; i <= numPlayers ; i++) { 
            name = cli.requestPlayerName(i) ;
            playerNames.add(name) ;

            type = cli.requestPlayerType(name) ;
            playerTypes.add(type) ;
        }

        game = new ShitheadGame(playerNames, playerTypes, numCards) ;		
    }

    @Override
    public void deal() {
        game.deal() ;
        cli.showGame(game, true);
        cli.showCardsDealtMessage() ;
        cli.waitOnUser() ;
    }

    @Override
    public void swap() throws ShitheadException {
        for (Player player : game.getPlayers()) {
            final PlayerSwap playerSwap = PlayerSwap.forPlayer(player, game, cli) ;
            playerSwap.swap() ;
        }

        cli.showGame(game, true) ;
    }

    @Override
    public void firstMove() {
        game.firstMove() ;
        cli.showGame(game, true) ;
        cli.showLastMove(game) ;
        cli.line() ;
        cli.showNextMoveMessage() ;
        cli.waitOnUser() ;
    }

    @Override
    public void play() throws ShitheadException {
        while (game.canContinue()) {
            cli.showGameWithWait(game, false) ;

            final Player currentPlayer = game.getCurrentPlayer() ;

            if (game.currentPlayerCanMove()) {
                final PlayerMove playerMove = PlayerMove.forCurrentPlayer(game, cli) ;
                playerMove.makeMove() ;
                if (currentPlayer.isComputer())
                    cli.showGameWithWait(game, true) ;
            }
            else {
                showPickupAndWait();
                game.playerPickUpPile() ;
            }
        }
    }

    @Override
    public void end() throws ShitheadException {
        cli.showGameOver(game.getShithead()) ;
    }

    @Override
    public void globalEnd() {
    }


    @Override
    public void error(final ShitheadException e) {
        cli.bail(e, game) ;
    }

    private void showPickupAndWait() {
        final String playerName = game.getCurrentPlayer().getName() ;
        cli.showPlayerPickupMessage(playerName) ;
        cli.waitOnUser() ;
    }
}
