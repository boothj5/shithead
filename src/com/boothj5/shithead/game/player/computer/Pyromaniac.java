package com.boothj5.shithead.game.player.computer;

import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;
import com.boothj5.shithead.game.player.computer.cardchooser.BurnThenLowChooser;
import com.boothj5.shithead.game.player.computer.cardchooser.CardChooser;

import java.util.*;

public class Pyromaniac extends ComputerPlayer {

    public static final String description = "Plays a 10 if they have one, otherwise lowest" ;

    public Pyromaniac(String name, int handSize) {
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
        CardChooser chooser = new BurnThenLowChooser(helper, getHand()) ;
        return chooser.chooseCards() ;
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        CardChooser chooser = new BurnThenLowChooser(helper, getFaceUp()) ;
        return chooser.chooseCards() ;
    }	
}
