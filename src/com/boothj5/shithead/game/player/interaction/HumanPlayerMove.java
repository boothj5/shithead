package com.boothj5.shithead.game.player.interaction;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadGame;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.ui.cli.ShitheadCli;

public final class HumanPlayerMove extends PlayerMove {

    private final ShitheadCli cli ;

    public HumanPlayerMove(ShitheadGame game, ShitheadCli cli) {
        this.game = game ; 
        this.cli = cli ;
    }

    public void move() {
        Player player = game.getCurrentPlayer() ;
        int handSize = player.getCurrentHandSize() ;
        String playerName = player.getName() ;
        List<Integer> cardChoice = cli.requestMove(playerName, handSize, false) ;

        while (!game.validMove(cardChoice))
            cardChoice = cli.requestMove(playerName, handSize, true) ;

        game.makeMove(cardChoice) ;
    }

    public void faceDownMove() {
        Player player = game.getCurrentPlayer() ;
        int handSize = player.getCurrentHandSize() ;
        String playerName = player.getName() ;
        List<Integer> cardChoice = new ArrayList<Integer>() ;

        int cardChoiceFromFaceDown = cli.requestFromFaceDown(playerName, handSize) ;
        cardChoice.add(cardChoiceFromFaceDown) ;

        if (game.validMove(cardChoice)) {
            cli.showHandDownOk(playerName, player.getFaceDown().get(cardChoiceFromFaceDown)) ;
            game.makeMove(cardChoice) ;
        }
        else {
            cli.showHandDownNotOk(playerName, player.getFaceDown().get(cardChoiceFromFaceDown)) ;
            game.playerPickUpPileAndFaceDownCard(cardChoiceFromFaceDown) ;
        }
    }
}
