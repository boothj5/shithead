package com.boothj5.shithead.game.player.computer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.ShitheadCardComparator;
import com.boothj5.shithead.game.player.PlayerHelper;

public class Intelligence {

    public static List<Integer> pickHighCards(PlayerHelper helper, List<Card> myHand) {
        List<Integer> returnChoice = new ArrayList<Integer>() ;
        List<Card> handMinusSpecial = new ArrayList<Card>() ;

        // get normal cards
        for (Card testCard : myHand) {
            if (!ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(testCard.getRank())) {
                handMinusSpecial.add(testCard) ;
            }
        }

        if (handMinusSpecial.size() > 0) {
            // get max normal card
            Card maxNormalCard = 
                    myHand.get(myHand.indexOf(Collections.max(handMinusSpecial, new ShitheadCardComparator()))) ;

            // if valid search for more and add them to choice
            if (ShitheadRules.canLay(maxNormalCard, helper.getPile())) {
                returnChoice.add(myHand.indexOf(maxNormalCard)) ;
                for (Card toCompare : myHand) {
                    if ((myHand.get(returnChoice.get(0)).getRank().compareTo(toCompare.getRank()) == 0) && 
                            (!myHand.get(returnChoice.get(0)).equals(toCompare))) 
                        returnChoice.add(myHand.indexOf(toCompare)) ;   
                }
                return returnChoice ;
            }
            else {
                returnChoice.add(myHand.indexOf(Collections.max(myHand, new ShitheadCardComparator()))) ;
                return returnChoice ;

            }
        }
        else {
            returnChoice.add(myHand.indexOf(Collections.max(myHand, new ShitheadCardComparator()))) ;
            return returnChoice ;
        }
    }

    public static List<Integer> pickLowCards(PlayerHelper helper, List<Card> myHand) {
        List<Integer> chosenCards = null; 

        for (Card tryCard : myHand) {
            if (ShitheadRules.canLay(tryCard, helper.getPile())) {
                int chosen = myHand.indexOf(tryCard) ;
                chosenCards = new ArrayList<Integer>() ;
                chosenCards.add(chosen) ;
                break ;
            }
        }

        Card.Rank chosenRank = myHand.get(chosenCards.get(0)).getRank() ;

        if (ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(chosenRank)) 
            return chosenCards ;
        else {
            // iterate over the players cards for any of the same rank and add them 
            for (Card toCompare : myHand)
                if ((myHand.get(chosenCards.get(0)).compareTo(toCompare) == 0) && 
                        (!myHand.get(chosenCards.get(0)).equals(toCompare))) 
                    chosenCards.add(myHand.indexOf(toCompare)) ;    
            return chosenCards;
        }
    }

}
