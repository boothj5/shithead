package com.boothj5.shithead.game.player.computer;

import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.*;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;
import static com.boothj5.shithead.game.player.computer.Intelligence.* ;

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
        List<Integer> chosenCards = null ;
        List<Card> myHand = getHand() ;

        Card burnCard = getBurnCard(myHand) ;
        if (burnCard != null) {
            chosenCards = new ArrayList<Integer>() ;
            chosenCards.add(myHand.indexOf(burnCard)) ;
            return chosenCards ; 
        }
        else 
            return pickLowCards(helper, myHand);
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        List<Integer> chosenCards = null ;
        List<Card> myHand = getFaceUp() ;
        Card burnCard = getBurnCard(myHand) ;
        if (burnCard != null) {
            chosenCards = new ArrayList<Integer>() ;
            chosenCards.add(myHand.indexOf(burnCard)) ;
            return chosenCards ;
        }
        else 
            return pickLowCards(helper, myHand);
    }	

    private Card getBurnCard(List<Card> cards) {
        for (Card tryCard : cards) {
            if (tryCard.getRank().equals(ShitheadRules.BURN)) 
                return tryCard ;
        }
        return null ;        
    }

}
