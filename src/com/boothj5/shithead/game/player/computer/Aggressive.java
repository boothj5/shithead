package com.boothj5.shithead.game.player.computer;

import java.util.List;

import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;
import com.boothj5.shithead.game.player.computer.CardChooser;

public class Aggressive extends ComputerPlayer {

    public static final String description = "Always plays highest cards" ;

    public Aggressive(String name, int handSize) {
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
        return chooser.pickHighCards();
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        CardChooser chooser = new CardChooser(helper, getFaceUp()) ;
        return chooser.pickHighCards();
    }	
}
