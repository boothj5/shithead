package com.boothj5.shithead.game.player.computer;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;

import java.util.*;

public abstract class ComputerPlayer extends Player {

    public ComputerPlayer(String name, int handSize) {
        this.name = name ;
        this.handSize = handSize ;
    }

    @Override
    public abstract boolean askSwapMore() ;

    @Override
    public abstract SwapResponse askSwapChoice() throws ShitheadException ;

    @Override
    public abstract List<Integer> askCardChoiceFromHand(PlayerHelper helper) ;

    @Override
    public abstract List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) ;

    @Override
    public int askCardChoiceFromFaceDown(PlayerHelper helper) {
        return 0 ;
    }
    
    @Override
    public boolean isComputer() {
        return true ;
    }
}
