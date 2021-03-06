package com.boothj5.shithead.game.card;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test ;

import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Card.Rank;
import com.boothj5.shithead.game.card.Deck;
import com.boothj5.shithead.game.card.Card.Suit;

import static org.junit.Assert.* ;

public class DeckTest {

    @Test
    public void createDeckContains52Cards() {
        final Deck deck = new Deck(18) ;

        assertEquals(52, deck.size()) ;
    }

    @Test
    public void createDeckContainsCorrectCards() {
        final Deck deck = new Deck(18) ;

        for (Suit suit : Suit.values())
            for (Rank rank : Rank.values())
                assertTrue(deck.contains(new Card(rank, suit))) ;

    }

    @Test 
    public void sizeReturnsCorrectSize() {
        final Deck deck = new Deck(18) ;

        assertEquals(52, deck.size()) ;
    }

    @Test 
    public void sizeReturnsCorrectSizeAfterRemovingOne() {
        final Deck deck = new Deck(18) ;
        deck.takeCard() ;

        assertEquals(51, deck.size()) ;
    }

    @Test
    public void addDeckResultsIn104MoreCards() {
        final Deck deck = new Deck(90) ;

        assertEquals(104, deck.size()) ;
    }

    @Test
    public void addTwoDecksResultsIn156MoreCards() {
        final Deck deck = new Deck(120) ;

        assertEquals(156, deck.size()) ;
    }

    @Test
    public void removeAllRemovesAllCards() {
        final Deck deck = new Deck(18) ;
        final List<Card> toRemove = new ArrayList<Card>() ;
        toRemove.add(new Card(Rank.SEVEN, Suit.SPADES)) ;
        toRemove.add(new Card(Rank.NINE, Suit.DIAMONDS)) ;
        toRemove.add(new Card(Rank.THREE, Suit.HEARTS)) ;
        deck.removeAll(toRemove) ;

        assertFalse(deck.contains(new Card(Rank.SEVEN, Suit.SPADES))) ;
        assertFalse(deck.contains(new Card(Rank.NINE, Suit.DIAMONDS))) ;
        assertFalse(deck.contains(new Card(Rank.THREE, Suit.HEARTS))) ;
    }
}
