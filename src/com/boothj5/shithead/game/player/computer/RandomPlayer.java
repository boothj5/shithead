package com.boothj5.shithead.game.player.computer;

import java.util.List;

import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;

public class RandomPlayer extends ComputerPlayer {

    public static final String description = "Somes times lays lowest, sometimes hightest" ;

    public RandomPlayer(String name, int handSize) {
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
        return chooser.pickRandomHighOrLow() ;
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        CardChooser chooser = new CardChooser(helper, getFaceUp()) ;
        return chooser.pickRandomHighOrLow() ;
    }	
}
