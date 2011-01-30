package com.boothj5.shithead.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.boothj5.shithead.card.Card;
import com.boothj5.shithead.card.ShitheadCardComparator;
import com.boothj5.shithead.game.ShitheadGameDetails;
import com.boothj5.shithead.game.ShitheadRules;

public class AggressiveComputerPlayer extends ComputerPlayer {
	public AggressiveComputerPlayer(String name, int handSize) {
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
		
		// look through my hand and find the highest card that I am allowed to lay
		List<Card> myHand = getHand() ;
		return pickCards(details, chosenCards, myHand);
	}

	private List<Integer> pickCards(ShitheadGameDetails details,
			List<Integer> chosenCards, List<Card> myHand) {
		
		List<Integer> returnChoice = new ArrayList<Integer>() ;
		returnChoice.add(myHand.indexOf(Collections.max(myHand, new ShitheadCardComparator()))) ;
		return returnChoice ;
	}	

	public List<Integer> askCardChoiceFromFaceUp(ShitheadGameDetails details) {
		List<Integer> chosenCards = null ;
		
		// look through my hand and find the highest card that I am allowed to lay
		List<Card> myHand = getFaceUp() ;
		return pickCards(details, chosenCards, myHand);
	}	
	
}
