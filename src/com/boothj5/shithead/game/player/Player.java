package com.boothj5.shithead.game.player;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.ShitheadCardComparator;

public abstract class Player {
    protected String name ;
    protected int handSize ;

    protected final List<Card> hand = new ArrayList<Card>() ;
    protected final List<Card> faceUp= new ArrayList<Card>() ;
    protected final List<Card> faceDown = new ArrayList<Card>() ;  

    public abstract boolean isComputer() ;
    public abstract boolean askSwapMore() ;
    public abstract SwapResponse askSwapChoice() throws ShitheadException;
    public abstract List<Integer> askCardChoiceFromHand(PlayerHelper helper) ;
    public abstract List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) ;
    public abstract int askCardChoiceFromFaceDown(PlayerHelper helper) ;

    public final String getName() {
        return name ;
    }

    public final List<Card> getFaceDown() {
        return faceDown ;
    }

    public final List<Card> getFaceUp() {
        return faceUp ;
    }

    public final List<Card> getHand() {
        return hand ;
    }

    public final int getFaceDownSize() {
        return faceDown.size();
    }

    public final int getFaceUpSize() {
        return faceUp.size();
    }

    public final int getHandSize() {
        return hand.size();
    } 
    
    public void removeFromHand(List<Card> cards) {
        hand.removeAll(cards) ;
    }
    
    public void removeFromFaceUp(List<Card> cards) {
        faceUp.removeAll(cards) ;
    }

    public void removeFromFaceDown(List<Card> cards) {
        faceDown.removeAll(cards) ;
    }

    public final PlayerSummary getSummary() {
        return new PlayerSummary(getName(), getHandSize(), Collections.unmodifiableList(getFaceUp()), 
                getFaceDownSize()) ;
    }

    public final boolean hasCards() {
        if (!faceUp.isEmpty()) 
            return true ;
        else if (!faceDown.isEmpty())
            return true ;
        else if (!hand.isEmpty())
            return true ;
        else 
            return false ;
    }

    public final void recieve(List<Card> cards) {
        hand.addAll(cards) ;
        sortHand() ;
    }

    public final boolean hasCardsInHand() {
        return (getHandSize() > 0) ;
    }

    public final boolean hasCardsInFaceUp() {
        return (getFaceUpSize() > 0) ;
    }

    public final boolean hasCardsInFaceDown() {
        return (getFaceDownSize() > 0) ;
    }

    public final void dealToHand(Card card) {
        hand.add(card) ;
    }

    public final void sortHand() {
        Collections.sort(hand, new ShitheadCardComparator()) ;
    }

    public final void dealToFaceUp(Card card) {
        faceUp.add(card) ;
    }

    public final void dealToFaceDown(Card card) {
        faceDown.add(card) ;
    }

    public final void swapCards(SwapResponse swapResponse) {
        if ((swapResponse.getHandCard() < 0) || !(swapResponse.getHandCard() < handSize) ||
                (swapResponse.getFaceUpCard() < 0) || !(swapResponse.getFaceUpCard() < handSize)) {
            return ;
        }
        else {
            Card savedFromHand = hand.get(swapResponse.getHandCard()) ;
            Card savedFromFaceUp = faceUp.get(swapResponse.getFaceUpCard()) ;
            faceUp.set(swapResponse.getFaceUpCard(), savedFromHand) ;
            hand.set(swapResponse.getHandCard(), savedFromFaceUp) ;     
            sortHand() ;
        }
    }   

    public final int getCurrentHandSize() {
        if (getHandSize() > 0) 
            return getHandSize() ;
        else if (getFaceUpSize() > 0) 
            return getFaceUpSize() ;
        else 
            return getFaceDownSize() ;
    }

    public final boolean playingFromHand() {
        if (getHandSize() > 0) 
            return true ;
        else 
            return false ;
    }

    public final boolean playingFromFaceUp() {
        if (playingFromHand()) 
            return false ;
        else if (getFaceUpSize() > 0) 
            return true ;
        else 
            return false;
    }

    public final boolean playingFromFaceDown() {
        if (playingFromHand() || playingFromFaceUp()) 
            return false ;
        else 
            return true ;
    }

    public final List<Card> getHandPlayingFrom() {
        if (playingFromHand()) 
            return getHand() ;
        else if (playingFromFaceUp())
            return getFaceUp() ;
        else
            return getFaceDown() ;
    }

    public final List<Card> getAllOfSameRankFromHand(Card card) {
        List<Card> result = new ArrayList<Card>() ;
        for(Card current : getHand()) {
            if (current.equalsRank(card)) {
                result.add(current) ;
            }
        }
        return result ;
    }
}
