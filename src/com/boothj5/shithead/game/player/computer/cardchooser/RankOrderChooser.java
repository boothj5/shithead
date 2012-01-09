package com.boothj5.shithead.game.player.computer.cardchooser;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.ShitheadRules;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;

public class RankOrderChooser extends CardChooser {

    private List<Card.Rank> rankOrder = new ArrayList<Card.Rank>();
    
    public RankOrderChooser(PlayerHelper helper, List<Card> hand, List<Card.Rank> rankOrder) {
        super(helper, hand);
        this.rankOrder = rankOrder;
    }
    
    @Override
    public List<Integer> chooseCards() {
        List<Integer> choices = new ArrayList<Integer>() ;

        // go through my rank order
        for (Card.Rank testRank : rankOrder) {
            // go through my hand and see if I have one
            for (Card cardFromHand : hand) {
                if (cardFromHand.getRank().compareTo(testRank) == 0) {
                    // check I can lay it
                    if (ShitheadRules.canLay(cardFromHand, helper.getPile())) {
                        choices.add(hand.indexOf(cardFromHand)) ;
                        // pick more of the same if not special card 
                        if (!ShitheadRules.LAY_ON_ANYTHING_RANKS.contains(cardFromHand.getRank())) {
                            for (Card toCompare : hand)
                                if ((hand.get(choices.get(0)).compareTo(toCompare) == 0) && 
                                        (!hand.get(choices.get(0)).equals(toCompare))) 
                                    choices.add(hand.indexOf(toCompare)) ;    
                        }
                        return choices ;
                    }
                }
            }
        }
        return choices ;
    }

}
