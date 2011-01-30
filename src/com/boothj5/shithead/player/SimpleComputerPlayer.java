package com.boothj5.shithead.player;

import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.card.*;

import java.util.*;


public class SimpleComputerPlayer extends ComputerPlayer {

	public SimpleComputerPlayer(String name, int handSize) {
		super(name, handSize) ;
	}
	
	public Boolean askSwapMore() {
		return new Boolean(false) ;
	}
	
	public SwapResponse askSwapChoice() {
		return null ;
	}
	
	public List<Integer> askCardChoiceFromHand(ShitheadGameDetails details) {
		List<Integer> chosenCards = null ;
		List<Card> myHand = getHand() ;

		return pickLowCards(details, chosenCards, myHand);
	}

	public List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) {
		List<Integer> chosenCards = null ;
		List<Card> myHand = getFaceUp() ;

		return pickLowCards(details, chosenCards, myHand);
	}	
	
	
}
