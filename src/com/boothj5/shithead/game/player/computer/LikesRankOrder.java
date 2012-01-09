package com.boothj5.shithead.game.player.computer;

import java.util.ArrayList;
import java.util.List;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.SwapResponse;
import com.boothj5.shithead.game.player.computer.cardchooser.CardChooser;
import com.boothj5.shithead.game.player.computer.cardchooser.RankOrderChooser;

public class LikesRankOrder extends ComputerPlayer {

    public static final String description = "Has a set of ordered ranks to use" ;
    private List<Card.Rank> rankOrder = new ArrayList<Card.Rank>() ;

    public LikesRankOrder(String name, int handSize) {
        super(name, handSize);
        initRankOrder();
    }

    @Override
    public boolean askSwapMore() {
        return false ;
    }

    @Override
    public SwapResponse askSwapChoice() {
        return null ;
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
