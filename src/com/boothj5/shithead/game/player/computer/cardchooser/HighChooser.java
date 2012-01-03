package com.boothj5.shithead.game.player.computer.cardchooser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.ShitheadCardComparator;
import com.boothj5.shithead.game.player.PlayerHelper;

public class HighChooser extends CardChooser {
    
    public HighChooser(PlayerHelper helper, List<Card> hand) {
        super(helper, hand) ;
    }
    
    @Override
    public List<Integer> chooseCards() {
        List<Integer> returnChoice = new ArrayList<Integer>() ;
        List<Card> handMinusSpecial = new ArrayList<Card>() ;

        // get normal cards
        for (Card testCard : hand) {
            if (!ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(testCard.getRank())) {
                handMinusSpecial.add(testCard) ;
            }
        }

        if (handMinusSpecial.size() > 0) {
            // get max normal card
            Card maxNormalCard = 
                    hand.get(hand.indexOf(Collections.max(handMinusSpecial, new ShitheadCardComparator()))) ;

            // if valid search for more and add them to choice
            if (ShitheadRules.canLay(maxNormalCard, helper.getPile())) {
                returnChoice.add(hand.indexOf(maxNormalCard)) ;
                for (Card toCompare : hand) {
                    if ((hand.get(returnChoice.get(0)).getRank().compareTo(toCompare.getRank()) == 0) && 
                            (!hand.get(returnChoice.get(0)).equals(toCompare))) 
                        returnChoice.add(hand.indexOf(toCompare)) ;   
                }
                return returnChoice ;
            }
            else {
                returnChoice.add(hand.indexOf(Collections.max(hand, new ShitheadCardComparator()))) ;
                return returnChoice ;

            }
        }
        else {
            returnChoice.add(hand.indexOf(Collections.max(hand, new ShitheadCardComparator()))) ;
            return returnChoice ;
        }
    }
}
