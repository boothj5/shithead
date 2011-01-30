package com.boothj5.shithead.player.computer;

import java.util.List;
import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.ComputerPlayer;
import com.boothj5.shithead.player.SwapResponse;

public class Aggressive extends ComputerPlayer {
	public Aggressive(String name, int handSize) {
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
		return pickHighCards(details, myHand);
	}

	public List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) {
		List<Card> myHand = getFaceUp() ;
		return pickHighCards(details, myHand);
	}	
	
}
