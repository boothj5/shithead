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
	
	public List<Integer> askCardChoiceFromHand(ShitheadGameDetails details, Player.Hand hand) {
		Stack<Card> pile = details.getPile() ;
		Card cardToLayOn = pile.peek() ;
		List<Integer> chosenCards = null ;
		
		// look through my hand and find the first card that I am allowed to lay
		List<Card> myHand = getHand(hand) ;
		for (Card tryCard : myHand) {
			ShitheadCardComparator comp = new ShitheadCardComparator() ;
			if (comp.compare(tryCard, cardToLayOn) > 0) {
				int chosen = myHand.indexOf(tryCard) ;
				chosenCards = new ArrayList<Integer>() ;
				chosenCards.add(chosen) ;
				break ;
			}
		}
		return chosenCards;
	}	
	
}
