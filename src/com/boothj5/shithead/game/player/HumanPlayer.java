package com.boothj5.shithead.game.player;

import java.util.*;

public final class HumanPlayer extends Player {

    public static final String description = "Human player" ;

    public HumanPlayer(String name, int handSize) {
        this.name = name ;
        this.handSize =  handSize ;
    }

    @Override
    public boolean isComputer() {
        return false ;
    }

    @Override
    public boolean askSwapMore() {
        throw new IllegalStateException("Human players must be asked questions via the user interface") ;
    }

    @Override
    public SwapResponse askSwapChoice() {
        throw new IllegalStateException("Human players must be asked questions via the user interface") ;
    }

    @Override
    public List<Integer> askCardChoiceFromHand(PlayerHelper helper) {
        throw new IllegalStateException("Human players must be asked questions via the user interface") ;
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        throw new IllegalStateException("Human players must be asked questions via the user interface") ;
    }

    @Override
    public int askCardChoiceFromFaceDown(PlayerHelper helper) {
        throw new IllegalStateException("Human players must be asked questions via the user interface") ;
    }
}
