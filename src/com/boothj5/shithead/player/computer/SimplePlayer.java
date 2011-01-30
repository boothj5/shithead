package com.boothj5.shithead.player.computer;

import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.player.ComputerPlayer;
import com.boothj5.shithead.player.SwapResponse;
import com.boothj5.shithead.card.*;

import java.util.*;


public class SimplePlayer extends ComputerPlayer {

	public SimplePlayer(String name, int handSize) {
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
		return pickLowCards(details, myHand);
	}	
	
	public List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) {
		List<Card> myHand = getFaceUp() ;
		return pickLowCards(details, myHand);
	}	
}
