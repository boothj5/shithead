package com.boothj5.shithead.game.player.computer;

import java.util.List;
import java.util.Random; 

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;


public class RandomPlayer extends ComputerPlayer {

    public static final String description = "Somes times lays lowest, sometimes hightest" ;

    Random generator = new Random();

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
        List<Card> myHand = getHand() ;

        int random = generator.nextInt( 2 );
        if (random == 0) 
            return pickHighCards(helper, myHand);
        else
            return pickLowCards(helper, myHand) ;
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        List<Card> myHand = getFaceUp() ;

        int random = generator.nextInt( 2 );
        if (random == 0) 
            return pickHighCards(helper, myHand);
        else
            return pickLowCards(helper, myHand) ;
    }	
}
