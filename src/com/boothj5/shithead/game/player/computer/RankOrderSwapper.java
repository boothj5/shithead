package com.boothj5.shithead.game.player.computer;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Card.Rank;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;

public class RankOrderSwapper extends ComputerPlayer {

    public static final String description = "Has a set of ordered ranks to use, and swaps at beginning" ;

    private List<Card.Rank> rankOrder = new ArrayList<Card.Rank>() ;

    public RankOrderSwapper(String name, int handSize) {
        super(name, handSize) ;
        initRankOrder() ;
    }

    @Override
    public boolean askSwapMore() {
        for (Card handCard : getHand()) {
            for (Card faceUpCard : getFaceUp()) {
                int handIndex = getOrder(handCard.getRank()) ;
                int faceUpIndex = getOrder(faceUpCard.getRank()) ;
                if (handIndex > faceUpIndex) {
                    return true ;
                }
            }
        }
        return false ;
    }

    @Override
    public SwapResponse askSwapChoice() throws ShitheadException {
        for (Card handCard : getHand()) {
            for (Card faceUpCard : getFaceUp()) {
                int handIndex = getOrder(handCard.getRank()) ;
                int faceUpIndex = getOrder(faceUpCard.getRank()) ;
                if (handIndex > faceUpIndex) {
                    return new SwapResponse(getHand().indexOf(handCard), getFaceUp().indexOf(faceUpCard)) ;

                }
            }
        }
        throw new ShitheadException("Computer wanted to swap, but didn't when asked") ;
    }


    private int getOrder(Rank testRank) {
        for (Rank rank : rankOrder) {
            if (testRank.equals(rank))  
                return rankOrder.indexOf(testRank) ;
        }
        return -1 ;

    }

    private void initRankOrder() {
        rankOrder.add(Card.Rank.THREE) ;
        rankOrder.add(Card.Rank.FOUR) ;
        rankOrder.add(Card.Rank.FIVE) ;
        rankOrder.add(Card.Rank.SIX) ;
        rankOrder.add(Card.Rank.EIGHT) ;
        rankOrder.add(Card.Rank.NINE) ;
        rankOrder.add(Card.Rank.JACK) ;
        rankOrder.add(Card.Rank.QUEEN) ;
        rankOrder.add(Card.Rank.KING) ;
        rankOrder.add(Card.Rank.TWO) ;
        rankOrder.add(Card.Rank.SEVEN) ;
        rankOrder.add(Card.Rank.ACE) ;
        rankOrder.add(Card.Rank.TEN) ;
    }	


    @Override
    public List<Integer> askCardChoiceFromHand(PlayerHelper helper) {
        List<Card> myHand = getHand() ;
        List<Integer> choices = new ArrayList<Integer>() ;

        // go through my rank order
        for (Card.Rank testRank : rankOrder) {
            // go through my hand and see if I have one
            for (Card cardFromHand : myHand) {
                if (cardFromHand.getRank().compareTo(testRank) == 0) {
                    // check I can lay it
                    if (checkValidMove(cardFromHand, helper)) {
                        choices.add(myHand.indexOf(cardFromHand)) ;
                        // pick more of the same if not special card 
                        if (!ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(cardFromHand.getRank())) {
                            for (Card toCompare : myHand)
                                if ((myHand.get(choices.get(0)).compareTo(toCompare) == 0) && 
                                        (!myHand.get(choices.get(0)).equals(toCompare))) 
                                    choices.add(myHand.indexOf(toCompare)) ;	
                        }
                        return choices ;
                    }
                }
            }
        }
        return choices ;

    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        List<Card> myHand = getFaceUp();
        List<Integer> choices = new ArrayList<Integer>() ;

        // go through my rank order
        for (Card.Rank testRank : rankOrder) {
            // go through my hand and see if I have one
            for (Card cardFromHand : myHand) {
                if (cardFromHand.getRank().compareTo(testRank) == 0) 
                    // check I can lay it
                    if (checkValidMove(cardFromHand, helper)) {
                        choices.add(myHand.indexOf(cardFromHand)) ;
                        // pick more of the same if not special card 
                        if (!ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(cardFromHand.getRank())) {
                            for (Card toCompare : myHand)
                                if ((myHand.get(choices.get(0)).compareTo(toCompare) == 0) && 
                                        (!myHand.get(choices.get(0)).equals(toCompare))) 
                                    choices.add(myHand.indexOf(toCompare)) ;	
                        }
                        return choices ;
                    }
            }
        }
        return choices ;
    }
}
