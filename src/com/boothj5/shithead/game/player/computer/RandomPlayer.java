package com.boothj5.shithead.game.player.computer;

import java.util.List;

import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;
import com.boothj5.shithead.game.player.computer.cardchooser.CardChooser;
import com.boothj5.shithead.game.player.computer.cardchooser.RandomHighLowChooser;

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
        CardChooser chooser = new RandomHighLowChooser(helper, getHand()) ;
        return chooser.chooseCards();
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        CardChooser chooser = new RandomHighLowChooser(helper, getFaceUp()) ;
        return chooser.chooseCards();
    }	
}
