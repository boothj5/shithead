package com.boothj5.shithead.game.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.boothj5.shithead.game.ShitheadRules;

public class Hand {
    private final List<Card> cards ;
    
    public Hand() {
        cards = new ArrayList<Card>() ;
    }
    
    public int size() {
        return cards.size() ;
    }
    
    public boolean isEmpty() {
        return cards.isEmpty() ;
    }
    
    public void add(Card card) {
        cards.add(card) ;
    }
    
    public void addAll(List<Card> cards) {
        this.cards.addAll(cards) ;
    }
    
    public void sort() {
        Collections.sort(cards, new ShitheadCardComparator()) ;
    }
    
    public Card get(int index) {
        return cards.get(index) ;
    }
    
    public void set(int index, Card card) {
        cards.set(index, card) ;
    }
    
    public List<Card> cards() {
        return cards ;
    }
    
    public int indexOf(Card card) {
        return cards.indexOf(card) ;
    }
    
    public Card highest() {
        return Collections.max(cards, new ShitheadCardComparator()) ;
    }
    
    public Card lowest() {
        return Collections.min(cards, new ShitheadCardComparator()) ;
    }
    
    public boolean containsBurnCard() {
        for (Card tryCard : cards) {
            if (tryCard.getRank().equals(ShitheadRules.BURN)) 
                return true ;
        }
        return false ;        
    }
    
    public Card getBurnCard() {
        for (Card tryCard : cards) {
            if (tryCard.getRank().equals(ShitheadRules.BURN)) 
                return tryCard ;
        }
        return null ;        
    }
    
    public Iterator<Card> iterator() {
        return cards.iterator() ;
    }
    
    public Card remove(int index) {
        return cards.remove(index) ;
    }
    
    public void removeAll(List<Card> cards) {
        this.cards.removeAll(cards) ;
    }
}
