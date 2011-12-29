package com.boothj5.shithead.game.player;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Hand;

public abstract class Player {
    protected String name ;
    protected int handSize ;
    
    protected final Hand faceDown = new Hand() ;
    protected final Hand faceUp = new Hand() ;
    protected final Hand hand = new Hand() ;  

	public abstract boolean isComputer() ;
	public abstract boolean askSwapMore() ;
	public abstract SwapResponse askSwapChoice() throws ShitheadException;
	public abstract List<Integer> askCardChoiceFromHand(PlayerHelper helper) ;
	public abstract List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) ;
	
	public final String getName() {
	    return name ;
	}

	public final Hand getFaceDown() {
	    return faceDown ;
	}

	public final Hand getFaceUp() {
	    return faceUp ;
	}

	public final Hand getHand() {
	    return hand ;
	}
	
    public final Card getLowestHandCard() {
        return hand.lowest() ;
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
        hand.sort() ;
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
        hand.sort() ;
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
            hand.sort() ;
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
    
    public final Hand getHandPlayingFrom() {
        if (playingFromHand()) 
            return getHand() ;
        else if (playingFromFaceUp())
            return getFaceUp() ;
        else
            return getFaceDown() ;
    }
    
    public final List<Card> getAllOfSameRankFromHand(Card card) {
        List<Card> result = new ArrayList<Card>() ;
        for(Card current : getHand().cards()) {
            if (current.equalsRank(card)) {
                result.add(current) ;
            }
        }
        return result ;
    }
}
