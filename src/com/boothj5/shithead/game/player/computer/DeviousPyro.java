package com.boothj5.shithead.game.player.computer;

import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;

import java.util.*;


public class DeviousPyro extends ComputerPlayer {
    public static final String description = "Plays a 10 if they have one and more than 10 cards on pile, otherwise lowest" ;

    private int threshold = 10 ;

    public int getThreshold() {
        return threshold ;
    }

    public DeviousPyro(String name, int handSize) {
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
        return chooser.pickBurnThenLowWithPileThreshold(threshold) ;
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        CardChooser chooser = new CardChooser(helper, getFaceUp()) ;
        return chooser.pickBurnThenLowWithPileThreshold(threshold) ;
    }
}
