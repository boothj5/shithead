package com.boothj5.shithead.game.player.computer.cardchooser;

import java.util.List;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;
import com.boothj5.shithead.game.player.PlayerSummary;

public class HighWhenNextPlayerFaceDownChooser extends CardChooser {
    
    public HighWhenNextPlayerFaceDownChooser(PlayerHelper helper, List<Card> hand) {
        super(helper, hand) ;
    }
        
    @Override
    public List<Integer> chooseCards() {
        PlayerSummary nextPlayer = helper.getNextPlayer() ;

        if ((nextPlayer.getHandSize() == 0) && (nextPlayer.getFaceUp().size() == 0) ) {
            CardChooser chooser = new HighChooser(helper, hand) ;
            return chooser.chooseCards();
        }
        else {
            CardChooser chooser = new LowChooser(helper, hand) ;
            return chooser.chooseCards();
        }
    }
}
