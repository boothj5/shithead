package com.boothj5.shithead.game.player.computer;

import com.boothj5.shithead.game.card.*;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;
import static com.boothj5.shithead.game.player.computer.Intelligence.* ;

import java.util.*;

public class SimplePlayer extends ComputerPlayer {

    public static final String description = "Always lays lowest";

    public SimplePlayer(String name, int handSize) {
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
        return pickLowCards(helper, myHand);
    }	

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        List<Card> myHand = getFaceUp() ;
        return pickLowCards(helper, myHand);
    }	
}
