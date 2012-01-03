package com.boothj5.shithead.game.player.computer;

import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;

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
        CardChooser chooser = new CardChooser(helper, getHand()) ;
        return chooser.pickLowCards();
    }	

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        CardChooser chooser = new CardChooser(helper, getFaceUp()) ;
        return chooser.pickLowCards();
    }	
}
