package com.boothj5.shithead.game.player.computer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.ShitheadCardComparator;
import com.boothj5.shithead.game.player.PlayerHelper;

public class CardChooser {
    
    private final PlayerHelper helper ;
    private final List<Card> cards ;
    
    public CardChooser(PlayerHelper helper, List<Card> cards) {
        this.helper = helper ;
        this.cards = cards ;
    }
    
    public List<Integer> pickHighCards() {
        List<Integer> returnChoice = new ArrayList<Integer>() ;
        List<Card> handMinusSpecial = new ArrayList<Card>() ;

        // get normal cards
        for (Card testCard : cards) {
            if (!ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(testCard.getRank())) {
                handMinusSpecial.add(testCard) ;
            }
        }

        if (handMinusSpecial.size() > 0) {
            // get max normal card
            Card maxNormalCard = 
                    cards.get(cards.indexOf(Collections.max(handMinusSpecial, new ShitheadCardComparator()))) ;

            // if valid search for more and add them to choice
            if (ShitheadRules.canLay(maxNormalCard, helper.getPile())) {
                returnChoice.add(cards.indexOf(maxNormalCard)) ;
                for (Card toCompare : cards) {
                    if ((cards.get(returnChoice.get(0)).getRank().compareTo(toCompare.getRank()) == 0) && 
                            (!cards.get(returnChoice.get(0)).equals(toCompare))) 
                        returnChoice.add(cards.indexOf(toCompare)) ;   
                }
                return returnChoice ;
            }
            else {
                returnChoice.add(cards.indexOf(Collections.max(cards, new ShitheadCardComparator()))) ;
                return returnChoice ;

            }
        }
        else {
            returnChoice.add(cards.indexOf(Collections.max(cards, new ShitheadCardComparator()))) ;
            return returnChoice ;
        }
    }
    
    
    

}
