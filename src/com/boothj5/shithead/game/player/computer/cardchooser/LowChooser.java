package com.boothj5.shithead.game.player.computer.cardchooser;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;

public class LowChooser extends CardChooser {

    public LowChooser(PlayerHelper helper, List<Card> hand) {
        super(helper, hand) ;
    }
    
    @Override
    public List<Integer> chooseCards() {
        List<Integer> chosenCards = null; 

        for (Card tryCard : hand) {
            if (ShitheadRules.canLay(tryCard, helper.getPile())) {
                int chosen = hand.indexOf(tryCard) ;
                chosenCards = new ArrayList<Integer>() ;
                chosenCards.add(chosen) ;
                break ;
            }
        }

        Card.Rank chosenRank = hand.get(chosenCards.get(0)).getRank() ;

        if (ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(chosenRank)) 
            return chosenCards ;
        else {
            // iterate over the players cards for any of the same rank and add them 
            for (Card toCompare : hand)
                if ((hand.get(chosenCards.get(0)).compareTo(toCompare) == 0) && 
                        (!hand.get(chosenCards.get(0)).equals(toCompare))) 
                    chosenCards.add(hand.indexOf(toCompare)) ;    
            return chosenCards;
        }
    }
}
