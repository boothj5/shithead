package com.boothj5.shithead.game.player.computer.cardchooser;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;

public class BurnThenLowChooser extends CardChooser {
    
    public BurnThenLowChooser(PlayerHelper helper, List<Card> hand) {
        super(helper, hand) ;
    }
    
    @Override
    public List<Integer> chooseCards() {
        Card burnCard = getBurnCard() ;
        if (burnCard != null) {
            List<Integer> chosenCards = new ArrayList<Integer>() ;
            chosenCards.add(hand.indexOf(burnCard)) ;
            return chosenCards ; 
        }
        else {
            CardChooser chooser = new LowChooser(helper, hand) ;
            return chooser.chooseCards();
        }
    } 

    private Card getBurnCard() {
        for (Card tryCard : hand) {
            if (ShitheadRules.isBurn(tryCard)) 
                return tryCard ;
        }
        return null ;        
    }
}
