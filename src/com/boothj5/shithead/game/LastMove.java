package com.boothj5.shithead.game;
import java.util.* ;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.player.Player;
public final class LastMove {

    private final Player player ;
    private final List<Card> cards ; 
    private final boolean burnt ;
    private final boolean missAGo ;

    public LastMove(final Player player, final List<Card> cards, final boolean burnt, final boolean missAGo) {
        this.player = player ;
        this.cards = cards ;
        this.missAGo = missAGo;
        this.burnt = burnt ;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean getBurnt() {
        return burnt ;
    }

    public boolean getMissAGo() {
        return missAGo;
    }

}
