package com.boothj5.shithead.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Card.Rank;
import com.boothj5.shithead.game.card.Card.Suit;
import com.boothj5.shithead.game.player.Player;
import com.boothj5.shithead.game.player.PlayerFactory;

public class LastMoveTest {
    Player player ;
    List<Card> cards ;
    LastMove lastMove ;
    
    @Test
    public void constructorSetsPlayer() throws ShitheadException {
        createLastMoveWithNoCards(false, false);

        assertTrue(player == lastMove.getPlayer()) ;
    }

    @Test
    public void constructorSetsCards() throws ShitheadException {
        player = PlayerFactory.createPlayer("h", "James", 3) ;
        cards = new ArrayList<Card>() ;
        cards.add(new Card(Rank.ACE, Suit.SPADES)) ;
        lastMove = new LastMove(player, cards, false, false) ;
        final Card resultCard = lastMove.getCards().get(0) ;
        
        assertEquals(1, lastMove.getCards().size()) ;        
        assertEquals(cards.get(0), resultCard) ;
    }
    
    @Test
    public void constructorSetsBurntFalse() throws ShitheadException {
        createLastMoveWithNoCards(false, false);

        assertFalse(lastMove.getBurnt()) ;
    }

    @Test
    public void constructorSetsBurntTrue() throws ShitheadException {
        createLastMoveWithNoCards(true, false);
 
        assertTrue(lastMove.getBurnt()) ;
    }

    @Test
    public void constructorSetsMissAGoFalse() throws ShitheadException {
        createLastMoveWithNoCards(false, false);

        assertFalse(lastMove.getMissAGo()) ;
    }

    @Test
    public void constructorSetsMissAGoTrue() throws ShitheadException {
        createLastMoveWithNoCards(false, true);

        assertTrue(lastMove.getMissAGo());
    }

    private void createLastMoveWithNoCards(boolean burn, boolean missAGo) throws ShitheadException {
        player = PlayerFactory.createPlayer("h", "James", 3) ;
        cards = new ArrayList<Card>() ;
        lastMove = new LastMove(player, cards, burn, missAGo) ;
    }
}
