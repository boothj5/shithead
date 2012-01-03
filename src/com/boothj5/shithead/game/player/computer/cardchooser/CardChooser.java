package com.boothj5.shithead.game.player.computer.cardchooser;

import java.util.List;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.PlayerHelper;

public abstract class CardChooser {
    protected final PlayerHelper helper ;
    protected final List<Card> hand ;

    public CardChooser(PlayerHelper helper, List<Card> hand) {
        this.helper = helper ;
        this.hand = hand ;
    }
    
    public abstract List<Integer> chooseCards() ;
}
