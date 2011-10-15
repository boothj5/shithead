package com.boothj5.shithead.game.player;

import java.util.* ;

import com.boothj5.shithead.game.ShitheadException;
import com.boothj5.shithead.game.card.Card;
import com.boothj5.shithead.game.card.Hand;

public interface Player {
    
	public boolean isComputer() ;
	
	public boolean askSwapMore() ;
		
	public SwapResponse askSwapChoice() throws ShitheadException;
	
	public void swapCards(SwapResponse swapResponse) ;
	
	public List<Integer> askCardChoiceFromHand(PlayerHelper helper) ;

	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) ;
	
	public String getName() ;

	public Hand getFaceDown() ;

	public Hand getFaceUp() ;

	public Hand getHand() ;
	
	public boolean hasCards() ;

	public void recieve(List<Card> cards) ;

	public void dealToHand(Card card) ;

	public void dealToFaceUp(Card card) ;

	public void dealToFaceDown(Card card) ;
	
}
