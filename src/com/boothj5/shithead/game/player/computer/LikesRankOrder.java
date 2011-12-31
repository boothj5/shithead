package com.boothj5.shithead.game.player.computer;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;
import static com.boothj5.shithead.game.player.computer.Intelligence.* ;

public class LikesRankOrder extends ComputerPlayer {

    public static final String description = "Has a set of ordered ranks to use" ;

    private List<Card.Rank> rankOrder = new ArrayList<Card.Rank>() ;

    public LikesRankOrder(String name, int handSize) {
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
        initRankOrder() ;
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
        initRankOrder() ;
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
