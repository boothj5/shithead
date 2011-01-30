package com.boothj5.shithead.player;

import java.util.List;
import java.util.Random; 

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.game.ShitheadGameDetails;


public class RandomComputerPlayer extends ComputerPlayer {
	Random generator = new Random();

	public RandomComputerPlayer(String name, int handSize) {
		super(name, handSize) ;
	}
	
	public Boolean askSwapMore() {
		return new Boolean(false) ;
	}
	
	public SwapResponse askSwapChoice() {
		return null ;
	}
	
	public List<Integer> askCardChoiceFromHand(ShitheadGameDetails details) {
		List<Card> myHand = getHand() ;
		
		int random = generator.nextInt( 2 );
		if (random == 0) 
			return pickHighCards(details, myHand);
		else
			return pickLowCards(details, myHand) ;
	}

	public List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) {
		List<Card> myHand = getFaceUp() ;

		int random = generator.nextInt( 2 );
		if (random == 0) 
			return pickHighCards(details, myHand);
		else
			return pickLowCards(details, myHand) ;
	}	
}
