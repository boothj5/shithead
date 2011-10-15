package com.boothj5.shithead.game.card;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.* ;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

import com.boothj5.shithead.game.card.Card.Rank;
import com.boothj5.shithead.game.card.Card.Suit;

public class HandTest {

    @Test
    public void newHandCreatesEmptyList() {
        final Hand hand = new Hand() ;
        
        assertEquals(0, hand.size()) ;
    }
    
    @Test
    public void emptyHandIsEmpty() {
        final Hand hand = new Hand() ;
        
        assertTrue(hand.isEmpty()) ;
    }
    
    @Test
    public void addAllResultInNonEmptyHand() {
        final Hand hand = new Hand() ;
        final List<Card> toAdd = new ArrayList<Card>() ;
        toAdd.add(new Card(Rank.ACE, Suit.DIAMONDS)) ;
        toAdd.add(new Card(Rank.THREE, Suit.SPADES)) ;
        hand.addAll(toAdd) ;

        assertFalse(hand.isEmpty()) ;
    }
    
    @Test
    public void addAllAddsCorrectNumber() {
        final Hand hand = new Hand() ;
        final List<Card> toAdd = new ArrayList<Card>() ;
        toAdd.add(new Card(Rank.ACE, Suit.DIAMONDS)) ;
        toAdd.add(new Card(Rank.THREE, Suit.SPADES)) ;
        hand.addAll(toAdd) ;
        
        assertEquals(2, hand.size()) ;
    }
    
    @Test
    public void addAllAddsAllCards() {
        final Hand hand = new Hand() ;
        final List<Card> toAdd = new ArrayList<Card>() ;
        toAdd.add(new Card(Rank.ACE, Suit.DIAMONDS)) ;
        toAdd.add(new Card(Rank.THREE, Suit.SPADES)) ;
        hand.addAll(toAdd) ;

        assertThat(hand.cards(), hasItems(new Card(Rank.ACE, Suit.DIAMONDS), new Card(Rank.THREE, Suit.SPADES))) ;
    }
    
    @Test
    public void sortSortsCards() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.ACE, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.THREE, Suit.SPADES)) ;
        hand.sort() ;
        
        assertEquals(new Card(Rank.THREE, Suit.SPADES), hand.get(0)) ;
        assertEquals(new Card(Rank.ACE, Suit.DIAMONDS), hand.get(1)) ;
    }
    
    @Test
    public void getGetsCorrectCard() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.ACE, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.THREE, Suit.SPADES)) ;
        
        assertEquals(new Card(Rank.ACE, Suit.DIAMONDS), hand.get(0)) ;
    }
    
    @Test
    public void addAddsCard() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.ACE, Suit.SPADES)) ;
        
        assertEquals(new Card(Rank.ACE, Suit.SPADES), hand.get(0)) ;
    }
    
    @Test 
    public void addAddsOnlyOneCard() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.ACE, Suit.SPADES)) ;

        assertEquals(1, hand.size()) ;
    }
    
    @Test
    public void setSetsCorrectCard() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.ACE, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.THREE, Suit.SPADES)) ;
        hand.set(0, new Card(Rank.SEVEN, Suit.CLUBS)) ;
       
        assertEquals(new Card(Rank.SEVEN, Suit.CLUBS), hand.get(0)) ;
    }
    
    @Test
    public void indexOfReturnsCorrectIndex() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.ACE, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.THREE, Suit.SPADES)) ;
        
        assertEquals(1, hand.indexOf(new Card(Rank.THREE, Suit.SPADES))) ;
    }

    @Test
    public void indexOfReturnsMinusOneWhenNotPresent() {
        final Hand hand = new Hand() ;
        
        assertEquals(-1, hand.indexOf(new Card(Rank.THREE, Suit.SPADES))) ;
    }
    
    @Test 
    public void highestReturnsHighestCard() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.FOUR, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        
        assertEquals(new Card(Rank.JACK, Suit.DIAMONDS), hand.highest()) ;
    }
    
    @Test
    public void containsBurnCardReturnsTrueWhenDoes() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.TEN, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        
        assertTrue(hand.containsBurnCard()) ;
    }
    
    @Test
    public void containsBurnCardReturnsFalseWhenDoesNot() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.THREE, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        
        assertFalse(hand.containsBurnCard()) ;
    }
    
    @Test
    public void returnsBurnCardWhenContainsOne() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.TEN, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        
        assertEquals(new Card(Rank.TEN, Suit.SPADES), hand.getBurnCard()) ;
    }

    @Test
    public void returnsNullWhenDoesntHaveBurnCard() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.THREE, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        
        assertNull(hand.getBurnCard()) ;
    }
    
    @Test 
    public void lowestReturnsLowestCard() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.FOUR, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        
        assertEquals(new Card(Rank.FOUR, Suit.SPADES), hand.lowest()) ;
    }
    
    @Test 
    public void removeReducesSizeByOne() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.FOUR, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        hand.remove(2) ;
        
        assertEquals(3, hand.size()) ;
    }

    @Test 
    public void removeRemovesCard() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.FOUR, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        hand.remove(2) ;
        
        assertFalse(hand.cards().contains(new Card(Rank.JACK, Suit.DIAMONDS))) ;
    }
    
    @Test 
    public void removeAllRemovesCorrectAmount() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.FOUR, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        final List<Card> toRemove = new ArrayList<Card>() ;
        toRemove.add(new Card(Rank.FOUR, Suit.SPADES)) ;
        toRemove.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.removeAll(toRemove) ;

        assertEquals(2, hand.size()) ;
    }

    @Test 
    public void removeAllRemovesCards() {
        final Hand hand = new Hand() ;
        hand.add(new Card(Rank.EIGHT, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.FOUR, Suit.SPADES)) ;
        hand.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.add(new Card(Rank.NINE, Suit.SPADES)) ;
        final List<Card> toRemove = new ArrayList<Card>() ;
        toRemove.add(new Card(Rank.FOUR, Suit.SPADES)) ;
        toRemove.add(new Card(Rank.JACK, Suit.DIAMONDS)) ;
        hand.removeAll(toRemove) ;

        assertFalse(hand.cards().contains(new Card(Rank.FOUR, Suit.SPADES))) ;
        assertFalse(hand.cards().contains(new Card(Rank.JACK, Suit.DIAMONDS))) ;
    }


}
