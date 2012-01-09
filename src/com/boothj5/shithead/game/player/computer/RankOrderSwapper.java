package com.boothj5.shithead.game.player.computer;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Card.Rank;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;
import com.boothj5.shithead.game.player.computer.cardchooser.CardChooser;
import com.boothj5.shithead.game.player.computer.cardchooser.RankOrderChooser;

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

    @Override
    public List<Integer> askCardChoiceFromHand(PlayerHelper helper) {
        CardChooser chooser = new RankOrderChooser(helper, getHand(), rankOrder);
        return chooser.chooseCards();
    }

    @Override
    public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
        CardChooser chooser = new RankOrderChooser(helper, getFaceUp(), rankOrder);
        return chooser.chooseCards();
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
}
