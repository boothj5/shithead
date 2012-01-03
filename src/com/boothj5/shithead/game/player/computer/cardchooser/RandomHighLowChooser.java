package com.boothj5.shithead.game.player.computer.cardchooser;

import java.util.List;
import java.util.Random;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;

public class RandomHighLowChooser extends CardChooser {

    private final Random generator = new Random();
    
    public RandomHighLowChooser(PlayerHelper helper, List<Card> hand) {
        super(helper, hand) ;
    }

    @Override
    public List<Integer> chooseCards() {
        int random = generator.nextInt(2);
        if (random == 0) { 
            CardChooser chooser = new HighChooser(helper, hand) ;
            return chooser.chooseCards();
        }
        else {
            CardChooser chooser = new LowChooser(helper, hand) ;
            return chooser.chooseCards() ;
        }
    }
}
