package com.boothj5.shithead.game.player.computer;

import com.boothj5.shithead.game.card.*;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;
import com.boothj5.shithead.game.player.SwapResponse;

import java.util.*;


public class FaceDownChecker extends ComputerPlayer {

    public static final String description = "Lays high if next player on facedown" ;

    public FaceDownChecker(String name, int handSize) {
        super(name, handSize) ;
    }


    @Override
    public boolean askSwapMore() {
        return false ;
    }

    @Override
    public SwapResponse askSwapChoice() {
        return null ;
    }

    @Override
    public List<Integer> askCardChoiceFromHand(PlayerHelper helper) {
        List<Card> myHand = getHand() ;
        PlayerSummary nextPlayer = getNextPlayer(helper) ;

        if ((nextPlayer.getHandSize() == 0) && (nextPlayer.getFaceUp().size() == 0) ) 
            return pickHighCards(helper, myHand);
        else 
            return pickLowCards(helper, myHand);
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        List<Card> myHand = getFaceUp() ;
        PlayerSummary nextPlayer = getNextPlayer(helper) ;

        if ((nextPlayer.getHandSize() == 0) && (nextPlayer.getFaceUp().size() == 0) ) 
            return pickHighCards(helper, myHand);
        else 
            return pickLowCards(helper, myHand);
    }
}
