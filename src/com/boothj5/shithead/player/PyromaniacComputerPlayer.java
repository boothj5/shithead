package com.boothj5.shithead.player;

import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.card.*;

import java.util.*;


public class PyromaniacComputerPlayer extends ComputerPlayer {

	public PyromaniacComputerPlayer(String name, int handSize) {
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

		Card burnCard = getBurnCardInHand(myHand) ;
		if (burnCard != null) {
			chosenCards = new ArrayList<Integer>() ;
			chosenCards.add(myHand.indexOf(burnCard)) ;
			return chosenCards ; 
		}
		else 
			return pickLowCards(details, myHand);
	}

	public List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) {
		List<Integer> chosenCards = null ;
		List<Card> myHand = getFaceUp() ;
		Card burnCard = getBurnCardInHand(myHand) ;
		if (burnCard != null) {
			chosenCards = new ArrayList<Integer>() ;
			chosenCards.add(myHand.indexOf(burnCard)) ;
			return chosenCards ;
		}
		else 
			return pickLowCards(details, myHand);
	}	
	
	
}
