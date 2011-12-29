package com.boothj5.shithead.engine;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.player.*;
import com.boothj5.shithead.game.player.interaction.PlayerInteraction;
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
        cli.showGame(game.getGameDetails(), true);
        cli.showCardsDealtMessage() ;
        cli.waitOnUser() ;
    }

    @Override
    public void swap() throws ShitheadException {
        ShitheadGameDetails details = game.getGameDetails() ;

        for (Player player : details.getPlayers()) {
            final PlayerInteraction playerInteraction = PlayerInteraction.forPlayer(player, game, cli) ;
            playerInteraction.swap() ;
        }

        cli.showGame(game.getGameDetails(), true) ;
    }

    @Override
    public void firstMove() {
        game.firstMove() ;
        final ShitheadGameDetails details = game.getGameDetails() ;
        cli.showGame(details, true) ;
        cli.showLastMove(details) ;
        cli.line() ;
        cli.showNextMoveMessage() ;
        cli.waitOnUser() ;
    }

    @Override
    public void play() throws ShitheadException {
        while (game.canContinue()) {
            cli.showGameWithWait(game.getGameDetails(), false) ;

            final Player currentPlayer = game.getCurrentPlayer() ;

            if (game.currentPlayerCanMove()) {
                final PlayerInteraction playerInteraction = PlayerInteraction.forPlayer(currentPlayer, game, cli) ;
                playerInteraction.makeMove() ;
                if (currentPlayer.isComputer())
                    cli.showGameWithWait(game.getGameDetails(), true) ;
            }
            else {
                showPickupAndWait();
                game.playerPickUpPile() ;
                game.moveToNextPlayer() ;
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
        cli.bail(e, game.getGameDetails()) ;
    }

    private void showPickupAndWait() {
        final String playerName = game.getCurrentPlayer().getName() ;
        cli.showPlayerPickupMessage(playerName) ;
        cli.waitOnUser() ;
    }
}
