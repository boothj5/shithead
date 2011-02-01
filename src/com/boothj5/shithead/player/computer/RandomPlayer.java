package com.boothj5.shithead.player.computer;

import java.util.List;
import java.util.Random; 

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.PlayerHelper;
import com.boothj5.shithead.player.SwapResponse;


public class RandomPlayer extends ComputerPlayer {
	Random generator = new Random();

	public RandomPlayer(String name, int handSize) {
		super(name, handSize) ;
	}
	
	public Boolean askSwapMore() {
		return new Boolean(false) ;
	}
	
	public SwapResponse askSwapChoice() {
		return null ;
	}
	
	public List<Integer> askCardChoiceFromHand(PlayerHelper helper) {
		List<Card> myHand = getHand() ;
		
		int random = generator.nextInt( 2 );
		if (random == 0) 
			return pickHighCards(helper, myHand);
		else
			return pickLowCards(helper, myHand) ;
	}

	public List<Integer> askCardChoiceFromFaceUp(PlayerHelper helper) {
		List<Card> myHand = getFaceUp() ;

		int random = generator.nextInt( 2 );
		if (random == 0) 
			return pickHighCards(helper, myHand);
		else
			return pickLowCards(helper, myHand) ;
	}	
}
