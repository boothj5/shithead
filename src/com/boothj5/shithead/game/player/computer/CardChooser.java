package com.boothj5.shithead.game.player.computer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.ShitheadCardComparator;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;

public class CardChooser {
    
    private final PlayerHelper helper ;
    private final List<Card> cards ;
    private final Random generator = new Random();
    
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
    
    public List<Integer> pickLowCards() {
        List<Integer> chosenCards = null; 

        for (Card tryCard : cards) {
            if (ShitheadRules.canLay(tryCard, helper.getPile())) {
                int chosen = cards.indexOf(tryCard) ;
                chosenCards = new ArrayList<Integer>() ;
                chosenCards.add(chosen) ;
                break ;
            }
        }

        Card.Rank chosenRank = cards.get(chosenCards.get(0)).getRank() ;

        if (ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(chosenRank)) 
            return chosenCards ;
        else {
            // iterate over the players cards for any of the same rank and add them 
            for (Card toCompare : cards)
                if ((cards.get(chosenCards.get(0)).compareTo(toCompare) == 0) && 
                        (!cards.get(chosenCards.get(0)).equals(toCompare))) 
                    chosenCards.add(cards.indexOf(toCompare)) ;    
            return chosenCards;
        }
    }
    
    public List<Integer> pickRandomHighOrLow() {
        int random = generator.nextInt(2);
        if (random == 0) 
            return pickHighCards();
        else
            return pickLowCards() ;
    }
    
    public List<Integer> pickBurnThenLow() {
        Card burnCard = getBurnCard() ;
        if (burnCard != null) {
            List<Integer> chosenCards = new ArrayList<Integer>() ;
            chosenCards.add(cards.indexOf(burnCard)) ;
            return chosenCards ; 
        }
        else 
            return pickLowCards();
    } 

    public List<Integer> pickBurnThenLowWithPileThreshold(int threshold) {
        if (helper.getPile().size() >= threshold)
            return pickBurnThenLow() ;
        else 
            return pickLowCards();
    } 
    
    public List<Integer> pickHighWhenNextPlayerOnFaceDown() {
        PlayerSummary nextPlayer = helper.getNextPlayer() ;

        if ((nextPlayer.getHandSize() == 0) && (nextPlayer.getFaceUp().size() == 0) ) 
            return pickHighCards();
        else 
            return pickLowCards();
    }
    
    
    private Card getBurnCard() {
        for (Card tryCard : cards) {
            if (ShitheadRules.isBurn(tryCard)) 
                return tryCard ;
        }
        return null ;        
    }
    
    
}
