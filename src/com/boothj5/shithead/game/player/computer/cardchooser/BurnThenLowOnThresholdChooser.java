package com.boothj5.shithead.game.player.computer.cardchooser;

import java.util.List;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;

public class BurnThenLowOnThresholdChooser extends CardChooser {
    
    private final int threshold ;
    
    public BurnThenLowOnThresholdChooser(PlayerHelper helper, List<Card> hand, int threshold) {
        super(helper, hand) ;
        this.threshold = threshold ;
    }
    
    @Override
    public List<Integer> chooseCards() {
        if (helper.getPile().size() >= threshold) {
            CardChooser chooser = new BurnThenLowChooser(helper, hand) ;
            return chooser.chooseCards() ;
        }
        else {
            CardChooser chooser = new LowChooser(helper, hand) ;
            return chooser.chooseCards();
        }
    } 
}
