package com.boothj5.shithead.game.player;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Hand;

public abstract class Player {
    
	public abstract boolean isComputer() ;
	
	public abstract boolean askSwapMore() ;
		
	public abstract SwapResponse askSwapChoice() throws ShitheadException;
	
	public abstract void swapCards(SwapResponse swapResponse) ;
	
	public abstract List<Integer> askCardChoiceFromHand(PlayerHelper helper) ;

	public abstract List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) ;
	
	public abstract String getName() ;

	public abstract Hand getFaceDown() ;

	public abstract Hand getFaceUp() ;

	public abstract Hand getHand() ;
	
	public abstract Card getLowestHandCard() ;
	
    public abstract int getFaceDownSize() ;

    public abstract int getFaceUpSize() ;

    public abstract int getHandSize() ;
	
	public abstract boolean hasCards() ;

    public abstract boolean hasCardsInHand() ;

    public abstract boolean hasCardsInFaceUp() ;
    
    public abstract boolean hasCardsInFaceDown() ;
	
	public abstract void recieve(List<Card> cards) ;

	public abstract void dealToHand(Card card) ;

	public abstract void sortHand() ;
	
	public abstract void dealToFaceUp(Card card) ;

	public abstract void dealToFaceDown(Card card) ;

    public int getCurrentHandSize() {
        if (getHandSize() > 0) 
            return getHandSize() ;
        else if (getFaceUpSize() > 0) 
            return getFaceUpSize() ;
        else 
            return getFaceDownSize() ;
    }
    
    public boolean playingFromHand() {
        if (getHandSize() > 0) 
            return true ;
        else 
            return false ;
    }

    public boolean playingFromFaceUp() {
        if (playingFromHand()) 
            return false ;
        else if (getFaceUpSize() > 0) 
            return true ;
        else 
            return false;
    }
    
    public boolean playingFromFaceDown() {
        if (playingFromHand() || playingFromFaceUp()) 
            return false ;
        else 
            return true ;
    }
}
